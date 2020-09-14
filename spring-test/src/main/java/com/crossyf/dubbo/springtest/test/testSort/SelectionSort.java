package com.crossyf.dubbo.springtest.test.testSort;

import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class SelectionSort {

    public int[] sort(int[] sourceArray){
        int[] source = Arrays.copyOf(sourceArray, sourceArray.length);

        // 每个数都要去选择排序，所以要排n-1次
        for(int i = 0; i < source.length - 1; i++){
            int min = i;
            for(int j = i + 1; j < source.length; j++){
                if(source[j] < source[min]){
                    min = j; // 找到最小值的位置
                }
            }

            if(min != i){  // 替换值
                int orig = source[i];
                source[i] = source[min];
                source[min] = orig;
            }
        }

        return source;
    }

}
