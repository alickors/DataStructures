package com.atguigu.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author alick
 * @description:
 * @create 2022-05-09 10:41
 */
public class SelectSort {

    public static void main(String[] args) {

        int[] arr = new int[80000];

        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时间是" + date1Str);

        //测试选择排序
        selectSort(arr);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是" + date2Str);

    }

    //选择排序
    public static void selectSort(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[0];
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {//说明假定的最小值并不是最小的
                    min = arr[j];//重置最小值min
                    minIndex = j;//重置最小值索引
                }
            }
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[0] = min;
            }
        }
    }
}