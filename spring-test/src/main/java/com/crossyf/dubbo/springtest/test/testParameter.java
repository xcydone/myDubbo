package com.crossyf.dubbo.springtest.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class testParameter {
    public static void main(String[] args) {

        // 值传递的是基本数据类型和String类型
        /*int  a = 5;  changeInt(a);  System.out.println(a); // 5
        String s = "abcdefg"; changeStr(s); System.out.println(s); // abcdefg

        Object object = 12345; handleObject(object);System.out.println(object.toString()); // 12345

        // 引用传递，一般都是那些复杂的数据，比如集合，数组，map
        int [] b = {5};  handleArray(b);  System.out.println(b[0]); // 11
        List list = new ArrayList();
        list.add("one");
        handleList(list);
        System.out.println(list);*/

        Integer  as = 5;  changeInteger(as);  System.out.println(as); // 5
    }
    private static  void changeInt(int  a){
        a=a+1;System.out.println(a); // 6
    }

    private static  void changeInteger(Integer  a){
        a=a+1;System.out.println(a); // 6
    }

    private static void handleArray (int array[]){
        for (int i=0;i<array.length;i++){
            array[i]+=array[i]+1;
        }
    }
    private static void changeStr(String s){
        s = s.substring(2,3);
    }
    private static void handleObject (Object object){
        object = object.toString()+"aaa";
    }
    private static void handleList(List<String> stringList){
        stringList.add("two");
        stringList.set(0,stringList.get(0)+"one");
    }

}
