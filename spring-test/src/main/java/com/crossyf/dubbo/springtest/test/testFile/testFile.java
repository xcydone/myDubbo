package com.crossyf.dubbo.springtest.test.testFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class testFile {
    public static void main(String[] args) {
        String path = "E:\\eclipse\\practisespace\\dubbo-use\\data\\srcData";

        List<String> str = readFileNames(path);
        // str.remove(path);
        System.out.println(Arrays.asList(str));
        System.out.println(str.size());

        readFile(path + "\\hello.txt");

        List<String> objStr = new ArrayList<>();
        objStr.add("我是中文");
        objStr.add("bbbbb");
        writeFile(path + "\\hello.txt", objStr);
    }

    /**
     * 读取文件下的文件名称
     * @param filePath
     */
    public static List<String> readFileNames(String filePath) {
        File fileDir = new File(filePath);
        List<String> listStr = new ArrayList<>();

        // 获取所有的文件名称
        /*if(!fileDir.isDirectory()){
            listStr.add(fileDir.getName());
        }else{
            File[] files = fileDir.listFiles();
            for (File file: files){
                if(!file.isDirectory()){
                    listStr.add(file.getName());
                }else{
                    listStr.addAll(readFileNames(file.getAbsolutePath()));
                }
            }
        }*/

        // 获取所有文件的路径，包括文件夹的
        if(!fileDir.isDirectory()){
            listStr.add(fileDir.getAbsolutePath());
        }else{
            listStr.add(fileDir.getAbsolutePath());
            File[] files = fileDir.listFiles();
            for (File file: files){
                if(!file.isDirectory()){
                    listStr.add(file.getAbsolutePath());
                }else{
                    listStr.addAll(readFileNames(file.getAbsolutePath()));
                }
            }
        }

        return listStr;
    }

    /**
     * 读取指定文件
     * @param filePath
     */
    public static void readFile(String filePath) {
        File fileSrc = new File(filePath); // 句柄

        if(fileSrc.isDirectory()){
            return;
        }else{
            try {
                FileInputStream fileInputStream = new FileInputStream(fileSrc);  // 字节流

                InputStreamReader streamReader = new InputStreamReader(fileInputStream,"utf-8"); // 字符流

                BufferedReader bufferedReader = new BufferedReader(streamReader); // 缓冲方式获取一行
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    System.out.println(lineTxt);
                }
                bufferedReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 写入文件
     * @param filePath
     * @param objStr
     */
    public static void writeFile(String filePath, List<String> objStr) {
        File fileSrc = new File(filePath);

        if(fileSrc.isDirectory()){
            return;
        }else{
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(fileSrc, true); //追加
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "utf-8");
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

                for (String str: objStr)
                    bufferedWriter.write(str);

                bufferedWriter.flush();
                bufferedWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
