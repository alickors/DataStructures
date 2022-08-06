package com.atguigu.sort;

import java.util.Arrays;

/**
 * @author alick
 * @description:
 * @create 2022-06-11 08:55
 */
public class ShellSort {

    public static void main(String[] args) {

        int[] arr = {8,9,1,7,2,3,5,4,6,0};

        int temp = 0;
        int count = 0;

        //根据前面的逐步分析，使用循环处理
        for (int gap = arr.length / 2;gap > 0;gap /= 2){
            for (int i = gap;i < arr.length;i++){
                //遍历各组中所有的元素，步长gap
                for (int j = i - gap;j >= 0;j -= gap){
                    //如果当前元素大于加上步长后的那个元素，说明交换
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
