package com.crossyf.dubbo.springtest.test.testSort;

import java.util.Arrays;

public class testSearchArray {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] arr = {4,6,8,33,66,44,99,54};
        int num=searchKey(arr,66);
        System.out.println(num);

        // 必须是排序后的数组--那就找不到是原数组的哪个位置了
        int[] arrSort = sort(arr);
        System.out.println(Arrays.toString(arrSort));
        int num2=searchArray(arrSort,66);
        System.out.println(num2);


        int[] arr1 = {1,4,6,83,45};
        copyArrays(arr1,1, 2, 3);

        testArrays();

    }

    //查找一个元素在数组中的第一次出现的位置
    public static int searchKey(int[] arr,int key) {
        for(int i=0;i<arr.length;i++) {
            if(arr[i] == key) {
                return i;
            }
        }
        return -1;//-1代表的是交表不存在的情况
    }

    public static int[] sort(int[] sourceArray){
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

    //二分查找。前天：数组必须是有序的。
    /*
     * 思路：
     * 1.通过角标先获取中间角标上的元素
     * 2.让该元素和要找的数据比较。
     * 3.如果要找的数大了，缩小范围，要找的范围应该是 中间的角标+1---尾角标
     *   如果要找的数效率，要找的范围   头角标---中间角标-1。
     * 4.不断如此重复，就可以找到元素对应的角标。
     * */
    public static int searchArray(int[] arr,int key) {
        int max,min,mid;
        min = 0;
        max = arr.length-1;

        /*mid = (min+max)>>1; // 右移一位，向下取整，相当于除以2
        while(arr[mid]!=key) {
            if(key > arr[mid]) {
                min = mid + 1;
            }else{
                max = mid - 1;
            }
            //判断元素是否存在。
            if(max<min) {
                return -1;
            }
            mid = (min+max)>>1;
        }
        return mid;
        */

        while(min < max) {
            mid = (min+max)>>1;
            if(key > arr[mid]) {
                min = mid + 1;
            }else if(key > arr[mid]){
                max = mid - 1;
            }else{
                return mid;
            }

        }
        return -1;
    }


    public static void copyArrays(int[] arr1, int srcStartIndex, int deStartIndex, int length) {
        int[] arr2 = new int[arr1.length];
        // 源数组    源数组起始位置  目标数组  目标数组起始位置 复制长度
        System.arraycopy(arr1, srcStartIndex, arr2, deStartIndex, length);
        System.out.println(Arrays.toString(arr2));

        Arrays.sort(arr1);
    }

    public static void testArrays() {
        //Arrays
        int[] arr = {1,4,7,434,232,55};

        //将数组转换成字符串
        System.out.println(Arrays.toString(arr));

        //对数组进行升序排序
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));

        //排序数组名  排序数组元素开始位置  排序数组元素结束位置（实际上，取到终止位置减一）[起始位置，终止位置)
        int[] arrd = {434,1,232,4,7,55};
        Arrays.sort(arrd,2,4);
        System.out.println(Arrays.toString(arrd));

        //多核处理器下并行操作使用
        Arrays.parallelSort(arr);

        //二分查找下标，数组   查找的数字，返回的是插入点，没有的话返回的是负的插入点减一的值
        System.out.println(Arrays.binarySearch(arr, 8));

        //数组比较:元素的个数和对应位置的数组元素相同
        int[] arr1 = {1,2,5};
        int[] arr2 = {1,2,5};
        System.out.println(Arrays.equals(arr1, arr2));

        //数组的填充,将数组中所有的元素替换为666
        Arrays.fill(arr, 666);
        System.out.println(Arrays.toString(arr));

        //数组的复制,返回的是一个数组,  （要复制的数组，几个元素）
        int[] arr3 = Arrays.copyOf(arr1, 2);
        System.out.println(Arrays.toString(arr3));
    }
}
