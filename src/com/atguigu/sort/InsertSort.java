package com.atguigu.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author alick
 * @description:
 * @create 2022-05-09 10:58
 */
public class InsertSort {
    public static void main(String[] args) {

        int[] arr = new int[80000];

        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时间是" + date1Str);

        //测试插入排序
        insertSort(arr);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是" + date2Str);

    }

    //插入排序
    public static void insertSort(int[] arr){

        int insertVal = 0;
        int insertIndex = 0;

        //使用for循环简化代码
        for (int i = 1; i < arr.length; i++) {

            //定义待插入的数
            insertVal = arr[i];

            //arr[i]前面这个数的下标
            insertIndex = i - 1;

            //给inserVal找到插入的位置
            while (insertIndex >= 0 && insertVal < arr[insertIndex]){
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }

            //当退出while（）循环的时候，说明插入的位置已经找到了，insertIndex + 1
            //这里进行判断是否需要进行赋值
            if (insertIndex + 1 != i){
                arr[insertIndex + 1] = insertVal;
            }
        }
    }
}
