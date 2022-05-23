package com.atguigu.search;

import java.util.Arrays;

/**
 * @author alick
 * @description:
 * @create 2022-05-11 09:53
 */
public class FibonacciSearch {

    public static int maxSize = 20;

    public static void main(String[] args) {

        int[] arr = {1,8,10,89,1000,1234};

        System.out.println("index = " + fibSearch(arr,89));
    }

    //因为我们后边用到斐波那契数列，因此我们需要先获取一个斐波那契数列
    //非递归方法获取斐波那契数列
    public static int[] fib(){

        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    //编写斐波那契查找算法
    //使用非递归的方式编写算法

    /**
     *
     * @param a 数组
     * @param key 我们需要查找的关键值
     * @return 返回对应的下标，没有的话则返回-1
     */
    public static int fibSearch(int[] a,int key){

        int low = 0;
        int high = a.length - 1;
        int k = 0;//表示斐波那契分割数值的下标
        int mid = 0;//存放mid的值
        int f[] = fib();//获取到斐波那契数列

        //获取到斐波那契数列分割数值的下标
        while (high > f[k] - 1){
            k++;
        }

        //因为f[k]的值可能大于a的长度，因此我们需要使用Arrays类，构造一个新的数组，并指向temp[]，不足的部分使用0填充
        int[] temp = Arrays.copyOf(a,f[k]);

        //实际上需要使用a数组的最后的数填充temp
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = a[high];
        }

        //使用while来循环处理，找到我们数key
        while (low <= high){
            //只要满足这个条件就继续找
            mid = low + f[k - 1] - 1;
            if (key < temp[mid]){
                //我们应该继续向数组的前面查找
                high = mid - 1;
                k--;
            }else if (key > temp[mid]){
                //我们应该继续向数组的后面查找
                low = mid + 1;
                k -= 2;
            }else{
                //找到了
                //需要确定返回的是哪个下标
                if (mid <= high){
                    return mid;
                }else{
                    return high;
                }
            }
        }
        return -1;
    }

}
