package com.crossyf.dubbo.springtest.test.testExam;

import java.util.*;
import java.util.stream.Collectors;

public class testPrime2 {
    /**
     * 最大公约数
     * @param args
     */
    public static void main(String[] args) {
        List<Integer> srcNum = new ArrayList<>();
        srcNum.add(32);
        srcNum.add(60);

        Map<Integer, Integer> primeToMaxMap = new HashMap<>();
        for(Integer num: srcNum){
            List<Integer> primeMul = isPrimeMul(num);
            Map<Integer,List<Integer>> primeToCntMap = primeMul.stream().collect(Collectors.groupingBy(Integer::intValue));
            primeToCntMap.keySet().forEach(key->{
                if(!primeToMaxMap.containsKey(key) ||
                        (primeToMaxMap.containsKey(key) && primeToMaxMap.get(key) < primeToCntMap.get(key).size()) ) {
                    primeToMaxMap.put(key, primeToCntMap.get(key).size());
                }
            });
        }

        // 计算
        List<Double> ls = new ArrayList<>();
        primeToMaxMap.keySet().forEach(key->{
            Double num = Math.pow(key, primeToMaxMap.get(key));
            ls.add(num);
        });

        System.out.println(Math.round(ls.stream().reduce((k1,k2)->k1*k2).get()));
    }

    /**
     * 统计所有的质因子
     * @param num
     * @return
     */
    public static List<Integer> isPrimeMul(Integer num) {
        List<Integer> list = new ArrayList<>();

        // 质数直接返回
        if(num == 1 || isPrime(num)){
            list.add(num);
        }else{
            for (int i = 2; i < num; i++) {
                if(num%i == 0){
                    if(isPrime(i)){
                        list.add(i);
                        list.addAll(isPrimeMul(num/i));
                        break;
                    }
                }
            }
        }

        return list;
    }

    /**
     * 判断是否素数 1和它本身
     * @param num
     * @return
     */
    public static Boolean isPrime(Integer num) {
        if(num < 2){
            return false;
        }

        for (int i = 2; i < num; i++) {
            if(num%i == 0){
                return false;
            }
        }

        return true;
    }
}
