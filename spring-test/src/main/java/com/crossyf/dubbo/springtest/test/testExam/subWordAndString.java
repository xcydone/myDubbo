package com.crossyf.dubbo.springtest.test.testExam;

import java.util.Scanner;

public class subWordAndString {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            String strSrc = scanner.nextLine();
            char[] chs = strSrc.toCharArray();
            int num = Integer.parseInt(scanner.nextLine());

            if(num > chs.length){
                System.out.println(strSrc);
            }else{
                int sum = 0;
                for (int i = 0; i < chs.length; i++) {
                    if(chs[i] > 128){
                        sum += 2;
                    }else{
                        sum += 1;
                    }

                    if(sum == num){
                        System.out.println(strSrc.substring(0, i+1));
                        break;
                    }
                    if(sum > num){
                        System.out.println(strSrc.substring(0, i));
                        break;
                    }
                }
            }
        }
    }

    /**
     * 判断是否为中文汉字
     * 中文汉字的编码范围：[\u4e00-\u9fa5]
     * @param ch
     * @return
     */
    public static Boolean isCharacter(Character ch) {
        return String.valueOf(ch).matches("[\u4e00-\u9fa5]");
    }
}


/*public static void main(String[] args) throws Exception{
    Scanner sc = new Scanner(System.in);
    while (sc.hasNext()) {
        String str = sc.next();
        String[] strs = new String[str.length()];
        for (int i = 0; i < strs.length; i++) {
            strs[i] = String.valueOf(str.charAt(i));
        }
        int byteNum = sc.nextInt();
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < strs.length; i++) {
            int chLen = strs[i].getBytes("GBK").length;
            if (byteNum >= chLen){
                byteNum -= chLen;
                res.append(strs[i]);
            }else {
                break;
            }
        }
        System.out.println(res.toString());
    }
}*/
