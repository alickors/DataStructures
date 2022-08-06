package com.atguigu.sort;

import java.util.Arrays;

/**
 * @author alick
 * @description:
 * @create 2022-06-09 11:32
 */
public class InsertSort {

    public static void main(String[] args) {

        int[] arr = new int[]{101, 34, 119, 1, -1, 89};

        int insertVal = 0;
        int insertIndex = 0;

        for (int i = 1; i < arr.length; i++) {
            insertVal = arr[i];
            insertIndex = i - 1;
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertVal;
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
