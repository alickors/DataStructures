package com.atguigu.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author alick
 * @description:
 * @create 2022-05-09 14:55
 */
public class QuickSort {

    public static void main(String[] args) {


        int[] arr = new int[80000];

        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时间是" + date1Str);

        //测试快速排序
        quickSort(arr,0,arr.length - 1);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是" + date2Str);
    }

    //快速排序
    public static void quickSort(int[] arr,int left,int right){
        int l = left;//左下标
        int r = right;//右下标

        //中轴值
        int pivot = arr[(left + right) / 2];
        int temp = 0;//临时变量，交换时使用

        //while循环的目的是让比pivot值小的放在左边，大的放在右边
        while (l < r){
            //在pivot的左边一直找，找到大于pivot的值才退出
            while (arr[l] < pivot){
                l += 1;
            }

            //在pivot的右边一直找，找到大于pivot的值才退出
            while (arr[r] > pivot){
                r -= 1;
            }

            //如果l > r说明pivot左右两边的值已同号
            if (l >= r){
                break;
            }

            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //如果交换完成之后，发现arr[l] == pivot r--
            if (arr[l] == pivot){
                r -= 1;
            }

            //如果交换完之后，发现arr[r] == pivot l++
            if (arr[r] == pivot){
                l += 1;
            }

        }
        //如果l == r，必须l++，r--，否则出现栈溢出
        if (l == r) {
            l += 1;
            r -= 1;
        }

        //向左递归
        if (left < r) {
            quickSort(arr, left, r);
        }

        //向右递归
        if (right > l) {
            quickSort(arr, l, right);
        }
    }
}
