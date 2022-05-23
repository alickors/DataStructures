package com.atguigu.binarysearchnorecursion;

/**
 * @author alick
 * @description:
 * @create 2022-05-18 10:57
 */
public class BinarySearchNoRecur {

    public static void main(String[] args) {

        //测试
        int[] arr = {1,3,8,10,11,67,100};
    }

    public static int binarySearch(int[] arr, int target){

        int left = 0;
        int right = arr.length - 1;
        while (left <= right){
            //继续查找
            int mid = (left + right) / 2;
            if (arr[mid] == target){
                return mid;
            }else if (arr[mid] > target){
                right = mid - 1;//继续向左边找
            }else{
                left = mid + 1;//继续向右边找
            }
        }
        return -1;
    }
}
