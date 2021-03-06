package com.atguigu.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author alick
 * @description:
 * @create 2022-05-10 13:47
 */
public class RadixSort {

    public static void main(String[] args) {

        int[] arr = new int[80000];

        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时间是" + date1Str);

        //测试基数排序
        radixSort(arr);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是" + date2Str);

    }

    //基数排序方法
    public static void radixSort(int[] arr){

        //得到数组中最大的数的位数
        int max = arr[0];//假设第一个数就是最大的数
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max){
                max = arr[i];
            }
        }

        //得到最大的数是几位数
        int maxLength = (max + "").length();

        //定义一个二维数组，表示十个桶，每个桶就是一个一位数组
        //说明
        //二维数组包含十个一位数组
        //为了防止再放入数的时候溢出，每一个一位数组，大小定位arr.length
        //基数排序是经典的空间换时间的排序方法
        int[][] bucket = new int[10][arr.length];

        //为了记录每个桶中实际存放了多少个数据，我们定义一个一维数组来记录每个桶的每次放入的数据的个数
        int[] bucketElementCounts = new int[10];

        //这里我们使用循环将代码处理
        for (int i = 0,n = 1; i < maxLength; i++,n *= 10) {
            //针对每个元素的对应位进行排序处理
            for (int j = 0; j < arr.length; j++) {
                //取出每个元素的对应位的值
                int digitOfElement = arr[j] / n % 10;
                //放入到对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }

            //按照这个桶的顺序（一维数组的下标依次取出数据，放入原来的数组）
            int index = 0;
            //遍历每一个桶，并将桶中的数据放入到愿数组
            for (int k = 0; k < bucketElementCounts.length; k++) {
                //如果桶中有数据，我们才将数据放入愿数组
                if (bucketElementCounts[k] != 0){
                    //循环该桶即将第j个桶放入
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        //取出元素放入到arr
                        arr[index++] = bucket[k][l];
                    }
                }
                //第i + 1轮处理后，需要将每个bucketElementCOunts[j]=0
                bucketElementCounts[k] = 0;
            }
        }
    }
}
