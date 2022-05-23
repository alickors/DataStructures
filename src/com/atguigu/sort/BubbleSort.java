package com.atguigu.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author alick
 * @description:
 * @create 2022-05-08 11:05
 */
public class BubbleSort {

    public static void main(String[] args) {

        int[] arr = new int[80000];

        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random()*8000000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时间是" + date1Str);

        //测试冒泡排序
        bubbleSort(arr);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是" + date2Str);

    }

    //冒泡排序
    public static void bubbleSort(int[] arr) {

        int temp = 0;//临时变量
        boolean flag = false;//标识变量，标识是否进行过交换
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]){
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (!flag){//如果一次排序中，一次交换都没有发生过
                break;
            }else{
                flag = false;//重制flag进行下次判断
            }
        }
    }
}
