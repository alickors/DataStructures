package com.atguigu.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author alick
 * @description:
 * @create 2022-05-10 09:11
 */
public class MergetSort {

    public static void main(String[] args) {

        int[] arr = new int[80000];

        int[] temp = new int[80000];

        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时间是" + date1Str);

        //测试归并排序
        mergeSort(arr, 0, arr.length - 1,temp);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是" + date2Str);

    }

    //分加合方法
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {

        if (left < right) {
            int mid = (left + right) / 2;//中间索引

            //向左递归进行分解
            mergeSort(arr, left, mid, temp);

            //向右递归进行分解
            mergeSort(arr, mid + 1, right, temp);

            //合并

        }
    }

    //合并的方法

    /**
     * @param arr   排序的原始数组
     * @param left  左边有序序列的原始数组
     * @param mid   中间索引
     * @param right 右边索引
     * @param temp  做中转的数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {

        int i = left;//初始化i，左边有序序列的初始化索引
        int j = mid + 1;//初始化将，右边有序序列的初始化索引
        int t = 0;//指向temp数组的当前索引

        //先把左右两边的数据按照规则填充到temp数组，直到两边有一边结束为止
        while (i <= mid && j <= right) {
            //如果左边有序序列的当前元素小于等于右边有后续序列的当前元素，就将左边的当前元素填充到temp数组
            //随后t++，i++
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t += 1;
                i += 1;
            } else {//反之，就将右边的有序序列的当前元素填充到temp数组
                temp[t] = arr[j];
                t += 1;
                j += 1;
            }
        }

        //把有剩余数据的一边的数据依次全部填充到temp
        while (i < mid) {//左边的有序序列还有剩余元素，就全部填充到temp
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }

        while (j < right) {//右边的有序序列还有剩余元素，就全部填充到temp
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }

        //将temp数组的元素拷贝到arr
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right){
            arr[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }
    }
}
