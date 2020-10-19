package com.crossyf.dubbo.springtest.test.testExam;

import java.util.*;

public class brotherWord {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            Integer total = scanner.nextInt();

            // 字典
            List<String> strDict = new ArrayList<>();
            for (int i = 0; i < total; i++) {
                String str = scanner.next();
                strDict.add(str);
            }

            // 单词
            String obj = scanner.next();
            Integer index = scanner.nextInt();

            List<String> brotherWord = new ArrayList<>();
            for (int i = 0; i < strDict.size(); i++) {
                if(isBrother(obj, strDict.get(i))){
                    brotherWord.add(strDict.get(i));
                }
            }

            // 排序
            Collections.sort(brotherWord);
            System.out.println(brotherWord.size());

            if(index <= brotherWord.size()){
                System.out.println(brotherWord.get(index-1) + " ");
            }
        }
    }

    /**
     * 查看单词是否兄弟单词
     * @param obj
     * @param src
     * @return
     */
    public static Boolean isBrother(String obj, String src) {
        if(obj.equals(src) || obj.equals("") || src.equals("")) return false;

        Map<Character, Integer> objToMap = new HashMap<>();
        char[] objChars = obj.toCharArray();
        for(char ch: objChars){
            if(objToMap.containsKey(ch)){
                objToMap.put(ch, objToMap.get(ch)+1);
            }else{
                objToMap.put(ch,1);
            }
        }

        Map<Character, Integer> srcToMap = new HashMap<>();
        char[] srcChars = src.toCharArray();
        for(char ch: srcChars){
            if(srcToMap.containsKey(ch)){
                srcToMap.put(ch, srcToMap.get(ch)+1);
            }else{
                srcToMap.put(ch,1);
            }
        }

        if(objToMap.size() != srcToMap.size()) return false;

        for (Character ch: objToMap.keySet()){
            if(objToMap.get(ch) != srcToMap.get(ch)){
                return false;
            }
        }

        return true;
    }
}
