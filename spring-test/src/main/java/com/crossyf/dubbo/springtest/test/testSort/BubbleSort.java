package com.crossyf.dubbo.springtest.test.testSort;

import org.springframework.stereotype.Component;

import java.util.Arrays;

// 冒泡排序
@Component
public class BubbleSort{

    public int[] sort(int[] sourceArray){
        // 拷贝，不改变原参数的内容
        int[] source = Arrays.copyOf(sourceArray, sourceArray.length);

        for(int i = 0; i < source.length - 1; i++){
            for(int j = 0; j < source.length - i - 1; j++ ){
                if(source[j] > source[j + 1]){
                    int large = source[j];
                    source[j] = source[j + 1];
                    source[j + 1] = large;
                }
            }
        }

        return source;
    }

}
