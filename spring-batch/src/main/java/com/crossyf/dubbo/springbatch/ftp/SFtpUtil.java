package com.crossyf.dubbo.springbatch.ftp;

import com.jcraft.jsch.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

@Slf4j
@Component
public class SFtpUtil {

    public static final String NO_FILE = "No such file";

    private JSch jsch;

    private Session sshSession = null;

    private ChannelSftp sftp = null;

    public SFtpUtil() {
        super();
        jsch = new JSch();
    }

    /**
     * 连接数据源并登陆，设置传输参数
     *
     * @param datasource
     * @return
     */
    public boolean connect(FtpModle datasource) {
        boolean success = false;
        try {
            sshSession = jsch.getSession(datasource.getUserName(), datasource.getIp(), datasource.getPort());
            //sshSession = jsch.getSession(username, host, port);
            //log.info("ftp---Session created.");
            sshSession.setPassword(datasource.getPassword());
            Properties properties = new Properties();
            properties.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(properties);
            sshSession.connect();

            Channel channel = sshSession.openChannel("sftp");
            channel.connect();

            sftp = (ChannelSftp) channel;
            log.info("连接成功");
            success = true;

        } catch (JSchException e) {
            //    CollectException collecException = new CollectException("FTP连接服务器失败，", e);
            //    collecException.setCollectName("Ftp init");
            log.error("SFtpUtil-->connect异常" + e.getMessage());
        }

        return success;
    }

    /**
     * 退出登陆并断开连接
     *
     * @return
     */
    public boolean closeConnect() {
        return true;
    }

    /**
     * 匹配文件名下载
     *
     * @Description:
     * @Param remotePath: sftp服务器文件下载路径
     * @Param localPath: 文件下载到本地的路径
     * @Param isSingleFile: 下载单个文件
     * @Param prefixFileName: 文件前缀
     * @return boolean
    */
    public List<String> downLoadFileMatch(String remotePath, String localPath,
                                     String isSingleFile, String prefixFileName){
        List<String> downLoadFiles = new ArrayList<>(); // 返回下载的文件

        try {
            List<String> matchFiles = matchFstpFiles(remotePath, prefixFileName);
            log.info("matchFiles：" + matchFiles.size());
            if(matchFiles!= null && matchFiles.size() > 0){
                if(matchFiles.size() == 1){
                    downloadFile(remotePath, matchFiles.get(0), localPath, matchFiles.get(0));
                    downLoadFiles.add(localPath + matchFiles.get(0));
                }else{
                    if(isSingleFile.equals("0")){
                        // 排序后取最大
                        List<String> sortfileNames = sortFstpFiles(matchFiles);
                        downloadFile(remotePath, sortfileNames.get(0), localPath, sortfileNames.get(0));
                        downLoadFiles.add(localPath + sortfileNames.get(0));
                    }else{
                        for(String fileName: matchFiles){
                            downloadFile(remotePath, fileName, localPath, fileName);
                            downLoadFiles.add(localPath + fileName);
                        }
                    }
                }
            }else{
                log.info("服务器目录下没有文件");
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            log.info("sftp下载任务完成");
            disconnect();
            return downLoadFiles;
        }

    }


    /**
     * 下载单个文件
     *
     * @param remotePath：远程下载目录(以路径符号结束)
     * @param remoteFileName：下载文件名
     * @param localPath：本地保存目录(以路径符号结束)
     * @param localFileName：保存文件名
     * @return
     */
    public boolean downloadFile(String remotePath, String remoteFileName, String localPath, String localFileName) {
        log.info("本地文件夹:" + localPath);

        try {
            File file = new File(localPath + localFileName);
            FileOutputStream fieloutput = new FileOutputStream(file);

            sftp.get(remotePath + "/" +remoteFileName, fieloutput);
            log.info("===DownloadFile:" + remoteFileName + " success from sftp.");

            fieloutput.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 列出目录下的文件
     *
     * @param directory：要列出的目录
     * @return
     * @throws SftpException
     */
    public Vector listFiles(String directory) throws SftpException {
        return sftp.ls(directory);
    }

    /**
     * 匹配目录下的文件
     *
     * @param directory：要列出的目录
     * @param prefixFileName：匹配字段
     * @return
     * @throws SftpException
     */
    public List<String> matchFstpFiles(String directory, String prefixFileName) throws SftpException {
        Vector sftpFiles = sftp.ls(directory);

        log.info("匹配规则：" + prefixFileName);

        // 寻找匹配的文件名称
        List<String> sftpMatchNames = new ArrayList<>();
        if (sftpFiles.size() > 0) {
            Iterator it = sftpFiles.iterator();
            while (it.hasNext()) {
                ChannelSftp.LsEntry entry = (ChannelSftp.LsEntry) it.next();
                String filename = entry.getFilename();
                SftpATTRS attrs = entry.getAttrs();
                if (!attrs.isDir()) {
                    boolean isMatch= Pattern.matches(prefixFileName, filename);
                    if(isMatch){
                        sftpMatchNames.add(filename);
                    }
                }
            }
        }

        return sftpMatchNames;
    }


    /**
     * 排序
     *
     * @param fileNames：目录
     * @return
     * @throws SftpException
     */
    public List<String> sortFstpFiles(List<String> fileNames){
        fileNames.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                /*String strO1 = o1.replace(".txt", "").split("-")[-1];
                String strO2 = o2.replace(".txt", "").split("-")[-1];*/
                return o2.compareTo(o1);
            }
        });

        return fileNames;
    }

    /**
     * 文件下载
     *
     * @param directory
     * @param remoteFileName
     * @param localFile
     * @return
     * @throws IOException
     */
    public boolean downloadFile(String directory, String remoteFileName, String localFile) {
        File file = null;
        FileOutputStream output = null;
        try {
            log.info("本地文件夹:" + localFile);
            file = new File(localFile);
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            log.info("进入远程目录文件夹:" + directory);
            sftp.cd(directory);
            output = new FileOutputStream(file);
            log.info("获取文件:" + remoteFileName);
            sftp.get(remoteFileName, output);
            log.info("下载文件" + remoteFileName + "成功");
            return true;
        } catch (SftpException e) {
            if (e.toString().equals(NO_FILE)) {
            }
            log.error("ftp目录或者文件异常，检查ftp目录和文件" + e.toString());
        } catch (FileNotFoundException e) {
            log.error("本地目录异常，请检查" + file.getPath() + e.getMessage());
        } catch (IOException e) {
            log.error("创建本地文件失败" + file.getPath() + e.getMessage());
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    log.error("Close stream error." + e.getMessage());
                }
            }
            disconnect();
        }
        return false;
    }


    /**
     * 文件上传
     *
     * @param remoteDir
     * @param localDir
     * @return
     *
     */
    public boolean uploadFile(String remoteDir, String localDir) throws SftpException{
        log.info("上传的本地路径是：" + localDir);
        if (!sftp.lstat(remoteDir).isDir()) {
            log.error("sftp服务器文件路径不存在");
            return false;
        }

        File localDirectory = new File(localDir);
        if (!localDirectory.isDirectory()) {
            log.error("配置的本地路径不是文件夹");
            return false;
        }

        File[] files = localDirectory.listFiles();
        log.info("上传文件个数：" + files.length);

        for (File file : files) {
            try {
                //上传文件
                String src = localDir + "\\" + file.getName();
                String dst = remoteDir + "/" + file.getName();
                sftp.put(src, dst,ChannelSftp.OVERWRITE);

                // 删除本地文件
                /*file.delete();*/
            } catch (Exception e) {
                log.info("上传文件失败!");
                e.printStackTrace();
                return false;
            }
        }

        log.info("上传全部文件成功");
        return true;
    }

    public void disconnect() {
        if (this.sftp != null) {
            if (this.sftp.isConnected()) {
                this.sftp.disconnect();
                this.sftp = null;
                log.info("sftp is closed already");
            }
        }
        if (this.sshSession != null) {
            if (this.sshSession.isConnected()) {
                this.sshSession.disconnect();
                this.sshSession = null;
                log.info("sshSession is closed already");
            }
        }
    }

    /**
     * 删除文件夹下的所有文件
     *
     * @param filePath：服务器路径
     * @return
     * @throws SftpException
     */
    /*public void deleteFiles(String filePath){
        try{
            if(ftpClient.changeWorkingDirectory(filePath)){
                List<FTPFile> ftpFiles = Arrays.asList(ftpClient.listFiles());
                if(ftpFiles != null && ftpFiles.size() > 0){
                    for(FTPFile file: ftpFiles){
                        ftpClient.deleteFile(filePath + file.getName());
                    }
                    log.info("删除文件："+ ftpFiles.size() +"个");
                }
            }else{
                log.info("ftp服务器上不存在要删除的文件路径");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }*/

    /**
     * 删除指定文件
     *
     * @param filePath：服务器路径
     * @param fileName：文件名称
     * @return
     * @throws SftpException
     */
    /*public void deleteFile(String filePath, String fileName){
        try{
            if(ftpClient.changeWorkingDirectory(filePath)){
                List<FTPFile> ftpFiles = Arrays.asList(ftpClient.listFiles());
                Boolean flag = false;
                if(ftpFiles != null && ftpFiles.size() > 0){
                    for(FTPFile file: ftpFiles){
                        if(file.getName().equals(fileName)){
                            flag = true;
                            ftpClient.deleteFile(filePath + file.getName());
                        }
                    }
                }
                if(!flag){
                    log.info("ftp服务器上不存在要删除的文件");
                }
            }else{
                log.info("ftp服务器上不存在要删除的文件路径");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }*/


    /**
     * 删除指定文件夹
     *
     * @param filePath：服务器路径
     * @return
     * @throws SftpException
     */
    /*public void deleteFilePath(String filePath){
        try{
            if(ftpClient.changeWorkingDirectory(filePath)){
                List<FTPFile> ftpFiles = Arrays.asList(ftpClient.listFiles());
                if(ftpFiles != null && ftpFiles.size() > 0){
                    log.info("文件夹下存在文件，请先删除文件");
                }else{
                    ftpClient.removeDirectory(filePath);
                    log.info("删除文件夹成功");
                }
            }else{
                log.info("ftp服务器上不存在要删除的文件路径");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }*/


    /**
     * 创建文件夹
     *
     * @param filePath：服务器路径
     * @return
     * @throws SftpException
     */
    public void createFilePath(String filePath){
        try{
            sftp.mkdir(filePath);
            log.error("ftp服务器上创建文件夹"+ filePath +"成功");
            /*if(!sftp.lstat(filePath).isDir()) {
                sftp.mkdir(filePath);
                log.error("ftp服务器上创建文件夹"+ filePath +"成功");
            }else{
                log.info("ftp服务器上文件夹路径已存在，不能重复创建");
            }*/
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
