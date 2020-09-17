package com.crossyf.dubbo.springtest.test.testSort;

import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class QuickSort {

    public int[] sort(int[] sourceArray) throws Exception {

        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        return quickSort(arr, 0, arr.length - 1);
    }

    private int[] quickSort(int[] arr, int left, int right) {
        // 递归处理
        if (left < right) {
            int partitionIndex = partition(arr, left, right);
            quickSort(arr, left, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, right);
        }
        return arr;
    }

    private int partition(int[] arr, int left, int right) {
        // 设定基准值（pivot）
        int pivot = arr[left];
        while(left < right){
            // 先从右边开始
            while(left < right && arr[right] >= pivot){
                right--;
            }
            arr[left] = arr[right];  // 交换位置，从左边开始

            while(left < right && arr[left] <= pivot){
                left++;
            }
            arr[right] = arr[left];  // 交换位置，从右边开始
        }

        // left和right相等会结束while循环
        arr[left] = pivot;
        return left;  // 修改数组返回分区位置
    }
}
