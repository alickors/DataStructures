package com.atguigu.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alick
 * @description:
 * @create 2022-05-10 14:29
 */
public class BinarySerach {

    public static void main(String[] args) {

        //二分法使用的前提是数组是有序的
        int arr[] = {1,8,10,89,1000,1000,1234};

        List<Integer> resIndedxList = binarySerach2(arr,0,arr.length - 1,1000);
        System.out.println("resIndexList = " + resIndedxList);
    }

    //二分法查找

    /*
     * @param arr 数组
     * @param left 左边的索引
     * @param right 右边的索引
     * @param findValue 要查找的值
     */
    public static int binarySerach(int[] arr,int left,int right,int findValue){

        //当left > right时，说明递归整个数组，但是没有找到
        if (left > right){
            return -1;
        }

        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findValue > midVal){
            //向右递归
            return binarySerach(arr,mid + 1,right,findValue);
        }else if (findValue < midVal){
            //向左递归
            return binarySerach(arr,left,mid - 1,findValue);
        }else{
            return mid;
        }
    }


    //处理数组中重复元素的二分查找
    public static List<Integer> binarySerach2(int[] arr, int left, int right, int findValue) {

        //当left > right时，说明递归整个数组，但是没有找到
        if (left > right) {
            return new ArrayList<Integer>();
        }

        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findValue > midVal) {
            //向右递归
            return binarySerach2(arr, mid + 1, right, findValue);
        } else if (findValue < midVal) {
            //向左递归
            return binarySerach2(arr, left, mid - 1, findValue);
        } else {

            /**
             * 思路分析
             * 1，找到mid索引值之后不要马上返回
             * 2，向mid索引值左边扫描，将所有满足的值的下标，加入到集合中
             * 3，向mid索引值右边扫描，将所有满足的值的下标，加入到集合中
             * 4，将集合返回
             */
            List<Integer> resIndexList = new ArrayList<>();

            int temp = mid - 1;
            while (true){
                if (temp < 0 || arr[temp] != findValue){
                    //退出
                    break;
                }
                //否则，就将temp放入到resIndexList
                resIndexList.add(temp);
                temp -= 1;
            }

            resIndexList.add(mid);

            temp = mid + 1;
            while (true){
                if (temp > arr.length - 1 || arr[temp] != findValue){
                    break;
                }
                resIndexList.add(temp);
                temp += 1;
            }
            return resIndexList;
        }
    }
}
