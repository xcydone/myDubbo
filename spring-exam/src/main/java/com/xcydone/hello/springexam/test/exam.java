package com.xcydone.hello.springexam.test;

import java.util.*;
import java.util.stream.Collectors;

public class exam {
    public static void main(String[] args) {
        //String strSrc = "I like Like China! I like China.";
        String strSrc = "";
        Map<String, Integer> map = cntStr(strSrc);

        if(!Objects.isNull(map) && map.size() != 0){
            Collection<Integer> values = map.values();
            List<Integer> valuesList = values.stream().distinct().collect(Collectors.toList());
            Collections.sort(valuesList, Collections.reverseOrder());
            valuesList.forEach(value ->{
                Set<String> strings = map.keySet();
                for (String str: strings){
                    if(map.get(str) == value){
                        System.out.println("key=" + str + " -> " + value);
                    }
                }

            });
        }

    }

    /**
     * 统计单词的数量
     * @param strSrc
     * @return
     */
    public static Map<String, Integer> cntStr(String strSrc) {
        if("".equals(strSrc.trim()) || Objects.isNull(strSrc)){
            return null;
        }

        // 处理字符串中的特殊字符
        char[] chs = strSrc.toCharArray();
        StringBuffer stringBuffer = new StringBuffer("");
        for (int i = 0; i < chs.length; i++) {
            if((chs[i] >= 'A' && chs[i] <= 'Z') || (chs[i] >= 'a' && chs[i] <= 'z') || chs[i] == ' '){
                stringBuffer.append(chs[i]);
            }
        }

        String objStr = stringBuffer.toString();

        // 小写处理
        objStr = objStr.toLowerCase();

        // 分隔单词
        String[] strArrs = objStr.split(" ");

        // 统计单词
        Map<String, Integer> strToCntMap = new LinkedHashMap<>();
        for (int i = 0; i < strArrs.length; i++) {
            if(strToCntMap.containsKey(strArrs[i])){
                strToCntMap.put(strArrs[i], strToCntMap.get(strArrs[i]) + 1);
            }else{
                strToCntMap.put(strArrs[i], 1);
            }
        }

        return strToCntMap;


    }


}
