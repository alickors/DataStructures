package com.atguigu.sort;

import java.util.Arrays;

/**
 * @author alick
 * @description:
 * @create 2022-06-09 11:01
 */
public class SelectSort {

    public static void main(String[] args) {

        int[] arr = new int[]{101,34,119,1,-1,90,123};

        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]){
                    minIndex = j;
                    min = arr[j];
                }
            }
            if (minIndex != i){
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
