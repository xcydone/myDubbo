package com.crossyf.dubbo.springtest.test.testExam;

import java.util.*;

public class primeNumPartner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNext()){
            Integer num = scanner.nextInt();
            List<Integer> primesOdd = new ArrayList<>(); // 奇数
            List<Integer> primesEven = new ArrayList<>(); // 偶数

            for (int i = 0; i < num; i++) {
                Integer prime = scanner.nextInt();
                if(prime%2 == 0){
                    primesEven.add(prime);
                }else{
                    primesOdd.add(prime);
                }
            }

            // 奇数+奇数=偶数 偶数+偶数=偶数 奇数+偶数？=素数
            Map<Integer,Integer> numOddToFlagMap = new HashMap<>();
            for (int i = 0; i < primesOdd.size(); i++) {
                int flag = 0;
                for (int j = 0; j < primesEven.size(); j++) {
                    if(isPrime(primesOdd.get(i) + primesEven.get(j))){
                        flag++;
                    }
                }
                numOddToFlagMap.put(primesOdd.get(i), flag);
            }

            Map<Integer,Integer> numEvenToFlagMap = new HashMap<>();
            for (int i = 0; i < primesEven.size(); i++) {
                int flag = 0;
                for (int j = 0; j < primesOdd.size(); j++) {
                    if(isPrime(primesOdd.get(j) + primesEven.get(i))){
                        flag++;
                    }
                }
                numEvenToFlagMap.put(primesEven.get(i), flag);
            }

            System.out.println();
        }
    }

    /**
     * 是否素数 2 3 5 7
     * @param num
     * @return
     */
    public static Boolean isPrime(Integer num) {
        for (int i = 1; i <= num; i++) {
            if(num%i == 0 && i != 1 && i != num){
                return false;
            }
        }

        return true;
    }
}
