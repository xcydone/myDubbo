package com.crossyf.dubbo.springtest.test.testExam;

import java.util.*;

public class shopping {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        while(scan.hasNext()){
            int sumMoney = scan.nextInt();
            int sumCnt = scan.nextInt();
            HashMap<Integer, Integer> goodsAndPriceMap = new HashMap<>();
            HashMap<Integer, Integer> goodsAndImportMap = new HashMap<>();
            HashMap<Integer, List<Integer>> goodsAndAppendixMap = new HashMap<>();
            for (int i = 0; scan.hasNext(); i++) {
                int price = scan.nextInt();
                int important = scan.nextInt();
                int leader = scan.nextInt();
                if(i%3 == 0){
                    // 存放商品和价格
                    goodsAndPriceMap.put(i/3, price);

                    // 存放商品和重要度
                    goodsAndImportMap.put(i/3, important);

                    // 存放商品和附件
                    if(leader == 0){
                        goodsAndAppendixMap.put(i/3, new ArrayList<>());
                    }else{
                        // 附件
                        if(goodsAndAppendixMap.containsKey(leader)){
                            List<Integer> appendixs = goodsAndAppendixMap.get(leader);
                            appendixs.add(price);
                            goodsAndAppendixMap.put(leader, appendixs);
                        }
                    }
                }
            }

        }
    }
}
