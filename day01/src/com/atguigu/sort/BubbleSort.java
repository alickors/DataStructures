package com.atguigu.sort;

import java.util.Arrays;

/**
 * @author alick
 * @description:
 * @create 2022-06-09 10:39
 */
public class BubbleSort {

    public static void main(String[] args) {

        int[] arr = new int[]{3,9,-1,10,-2};

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]){
                    int max = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = max;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
