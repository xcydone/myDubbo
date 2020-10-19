package com.crossyf.dubbo.springtest.test.testExam;

import java.util.*;

public class weightcnt {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNext()){
            int num = scanner.nextInt();

            // 砝码
            List<Integer> weights = new ArrayList<>();
            for (int i = 0; i < num; i++) {
                weights.add(scanner.nextInt());
            }

            // 砝码个数
            List<Integer> weightNums = new ArrayList<>();
            for (int i = 0; i < num; i++) {
                weightNums.add(scanner.nextInt());
            }

            System.out.println(cntWeight(num, weights, weightNums));
        }
    }

    /**
     * 称重
     * @param num
     * @param weights
     * @param weightNums
     * @return
     */
    public static Integer cntWeight(Integer num, List<Integer> weights, List<Integer> weightNums){
        Set<Integer> weightSet = new HashSet<>();

        // 先放入第一砝码
        for (int i = 0; i <= weightNums.get(0); i++) {
            weightSet.add(weights.get(0) * i);
        }

        // 放入后面的砝码，每次都是和前面的总和累加
        for (int i = 1; i < num; i++) { // 遍历砝码
            List<Integer> list = new ArrayList<Integer>(weightSet);
            for (int j = 0; j <= weightNums.get(i); j++) { // 遍历砝码的数量
                for (int k = 0; k < list.size(); k++) { // 遍历set相加
                    weightSet.add(list.get(k) + weights.get(i) * j);
                }
            }
        }

        return weightSet.size();
    }

}
