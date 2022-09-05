package com.atguigu.ability;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author alick
 * @description:
 * @create 2022-08-30 11:11
 */
public class Suanfa {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(sum(new int[]{1,2,3,4,5,6}, 10)));
    }

    //两数之和
    public static  int[] sum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if(map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }
    //牛顿迭代
    public static int newton(int x) {
        return (int) sqrt(x,x);
    }

    public static double sqrt(double i, int x) {
        double res = (i + x / i) / 2;
        if(res == i) {
            return i;
        } else {
            return sqrt(res, x);
        }
    }

    //二分法查找平方根
    public static int binarySearch(int x) {
        int index = -1, l = 0, r = x;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (mid * mid <= x) {
                index = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return index;
    }

    //埃氏筛选法求质数个数
    public static int eratosthenes(int n) {
        boolean[] isPrime = new boolean[n];
        int count = 0;
        for(int i = 2; i < n; i++) {
            if(!isPrime[i]) {
                count++;
                for(int j = i * i; j < n; j += i) {
                    isPrime[j] = true;
                }
            }
        }
        return count;
    }

    //删除数组中重复项后的数组个数
    public static  int removeDuplicates(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if(nums[i] != nums[j]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i;
    }

    //返回中心元素下标
    public static int pivotIndex(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        int total = 0;
        for (int i = 0; i < nums.length; i++) {
            total += nums[i];
            if (total == sum) {
                return i;
            }
            sum -= nums[i];
        }
        return -1;
    }

    public static int getMaxMin(int[] nums) {
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;

        for (int x : nums) {
            if (x < min1) {
                min2 = min1;
                min1 = x;
            } else if(x < min2) {
                min2 = x;
            }
            if(x > max1) {
                max3 = max2;
                max2 = max1;
                max1 = x;
            } else if(x > max2) {
                max3 = max2;
                max2 = x;
            } else if(x > max3) {
                max3 = x;
            }
        }
        return Math.max(max1 * max2 * max3, min1 * min2 * max1);
    }
}
