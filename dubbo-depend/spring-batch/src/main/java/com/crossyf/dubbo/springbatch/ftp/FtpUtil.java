package com.crossyf.dubbo.springbatch.ftp;

import com.jcraft.jsch.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

@Slf4j
@Component
public class FtpUtil {

    private FTPClient ftpClient = null;

    /**
     * 连接数据源并登陆
     *
     * @param datasource
     * @return
     */
    public boolean connect(FtpModle datasource) {

        ftpClient = new FTPClient();
        boolean success = true;
        try {
            ftpClient.connect(datasource.getIp(), datasource.getPort());
            ftpClient.login(datasource.getUserName(), datasource.getPassword());
            success = true;
            log.info("连接成功");
        } catch (IOException e){
            log.error("FtpUtil-->connect异常" + e.getMessage());
        }

        return success;
    }


    /**
     * 断开连接
     *
     * @return
     */
    public boolean disconnect() {
        if (ftpClient.isConnected()) {
            try {
                ftpClient.disconnect();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
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
    public boolean uploadFile(String remoteDir, String localDir) {
        log.info("上传的本地路径是：" + localDir);

        try {
            if (!ftpClient.changeWorkingDirectory(remoteDir)) {
                log.error("ftp服务器文件路径不存在");

                // 创建文件路径
                /*ftpClient.*/
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
                    FileInputStream input = new FileInputStream(new File(src));
                    ftpClient.storeFile(dst, input);
                    input.close();
                } catch (Exception e) {
                    log.info("上传文件失败!");
                    e.printStackTrace();
                    return false;
                }
            }

            log.info("上传全部文件成功");
            return true;
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Description: 从FTP服务器下载指定文件
     *
     * @param remotePath FTP服务器上的相对路径
     * @param remoteFileName   要下载的文件名
     * @param localPath  下载后保存到本地的路径
     * @param localFileName   下载到本地的文件名
     * @return
     */
    public boolean downloadFile(String remotePath, String remoteFileName, String localPath, String localFileName) {
        log.info("本地文件夹:" + localPath);

        try {
            // 切换目录
            if (!ftpClient.changeWorkingDirectory(remotePath)) {
                log.error("ftp服务器文件路径不存在");
                return false;
            }

            FTPFile[] fs = ftpClient.listFiles();
            Boolean flag = false;
            for (FTPFile ff : fs) {
                log.info("远程文件名称"+ff.getName());

                if (ff.getName().equals(remoteFileName)) {
                    flag = true;
                    File localFile = new File(localPath + localFileName);
                    log.info("下载路径: " + localFile);
                    FileOutputStream output = new FileOutputStream(localFile);
                    ftpClient.retrieveFile(localFileName, output);
                    log.info("ftp下载文件: " + remoteFileName + "成功");
                    output.close();
                }
            }
            if(!flag){
                log.info("服务器上不存在文件: " + remoteFileName);
            }
            return flag;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 匹配文件名下载
     *
     * @Description:
     * @Param remotePath: ftp服务器文件下载路径
     * @Param localPath: 文件下载到本地的路径
     * @Param isSingleFile: 下载单个文件
     * @Param prefixFileName: 文件前缀
     * @return List<String> 下载的文件名称
     */
    public List<String> downLoadFileMatch(String remotePath, String localPath,
                                          String isSingleFile,String prefixFileName){
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
            log.info("ftp下载任务完成");
            disconnect();
            return downLoadFiles;
        }

    }

    /**
     * 列出目录下的文件
     *
     * @param directory：要列出的目录
     * @return
     * @throws IOException
     */
    public FTPFile[] listFiles(String directory) throws IOException {
        return ftpClient.listFiles(directory);
    }

    /**
     * 匹配目录下的文件
     *
     * @param directory：要列出的目录
     * @param prefixFileName：匹配字段
     * @return
     * @throws IOException
     */
    public List<String> matchFstpFiles(String directory, String prefixFileName) throws IOException {
        List<FTPFile> ftpFiles = Arrays.asList(ftpClient.listFiles(directory));

        log.info("匹配规则：" + prefixFileName);

        // 寻找匹配的文件名称
        List<String> ftpMatchNames = new ArrayList<>();
        if (ftpFiles.size() > 0) {
            Iterator it = ftpFiles.iterator();
            for(FTPFile file: ftpFiles){
                String filename = file.getName();
                if (!file.isDirectory()) {
                    // 不为空时： 正则匹配
                    if(prefixFileName != null && !"".equals(prefixFileName)){
                        boolean isMatch= Pattern.matches(prefixFileName, filename);
                        if(isMatch){
                            ftpMatchNames.add(filename);
                        }
                    }else{  // 为空：匹配全部
                        ftpMatchNames.add(filename);
                    }
                }
            }
        }

        return ftpMatchNames;
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
     * 删除文件夹下的所有文件
     *
     * @param filePath：服务器路径
     * @return
     * @throws SftpException
     */
    public void deleteFiles(String filePath){
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
    }

    /**
     * 删除指定文件
     *
     * @param filePath：服务器路径
     * @param fileName：文件名称
     * @return
     * @throws SftpException
     */
    public void deleteFile(String filePath, String fileName){
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
    }


    /**
     * 删除指定文件夹
     *
     * @param filePath：服务器路径
     * @return
     * @throws SftpException
     */
    public void deleteFilePath(String filePath){
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
    }


    /**
     * 创建文件夹
     *
     * @param filePath：服务器路径
     * @return
     * @throws SftpException
     */
    public void createFilePath(String filePath){
        try{
            if(!ftpClient.changeWorkingDirectory(filePath)){
                ftpClient.makeDirectory(filePath);
                log.info("ftp服务器上创建文件夹"+ filePath +"成功");
            }else{
                log.info("ftp服务器上文件夹路径已存在，不能重复创建");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
