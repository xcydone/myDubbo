package com.crossyf.dubbo.springtest.test.testSort;

import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class InsertSort {

    public int[] sort(int[] sourceArray){
        int[] source = Arrays.copyOf(sourceArray, sourceArray.length);

        // 外层大循环n-1次，每个元素都和前面的元素比较，第一个元素除外
        for(int i = 1; i < source.length; i++){

            int tmp = source[i];
            int j = i;
            while (j > 0 && tmp < source[j - 1]) {
                source[j] = source[j - 1];
                j--;
            }

            if(j != i){
                source[j] = tmp;
            }
        }

        return source;
    }
}
