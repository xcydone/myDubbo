package com.crossyf.dubbo.springtest.test.testChat;

import java.io.*;

public class BRRead {

    public static void main(String args[]) throws IOException {
        String str;

        String out = "写出";
        byte[] b = out.getBytes();

        // 使用 System.in 创建 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("请输入内容, 按下 'end' 键退出。");

        // 读取字符
        do {
            str = br.readLine();
            System.out.println(str);

            System.out.write(b);
            System.out.write('\n');
        } while (!str.equals("end"));
    }
}
