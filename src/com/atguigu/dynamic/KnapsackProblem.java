package com.atguigu.dynamic;

/**
 * @author alick
 * @description:
 * @create 2022-05-18 11:17
 */
public class KnapsackProblem {

    public static void main(String[] args) {

        int[] w = {1, 4, 3};//物品的重量
        int[] val = {1500, 3000, 2000};//物品的价值
        int m = 4;//背包的容量
        int n = val.length;//物品的个数

        //创建二维数组
        //v[i][j]表示在前i个物品中能够装入容量为j的背包中的最大价值
        int[][] v = new int[n + 1][m + 1];

        //为了记录放入商品的数量的情况，我们定一个二维数组
        int[][] path = new int[n + 1][m + 1];

        //初始化第一行和第一列，这里在本程序中，可以不去处理，因为默认情况就是零
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;//第一列设置为零
        }

        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;//将第一行设置为零
        }

        //根据前面得到公式来动态规划处理
        for (int i = 1; i < v.length; i++) {
            //不处理第一行，i是从1开始
            for (int j = 1; j < v[0].length; j++) {
                //不处理第一列，也是从一开始
                //公式
                if (w[i - 1] > j) {
                    v[i][j] = v[i - 1][j];
                } else {
                    //为了记录商品存放到背包的情况，我们不能直接使用上面的公式，需要使用if else来体现公式
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        //把当前的情况记录到path
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }

            }

        }
        //输出一下v看看目前的情况
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("+++++++++++++++++++++++++");

        //输出最后是放入的那些商品
        //遍历path，这样的输出会把所有的情况都得到，其实我们只需要最后的情况
        int i = path.length - 1;//行的最大下标
        int j = path[0].length - 1;//列的最大下标
        while (i > 0 && j > 0) {
            //从path的最后开始找
            if (path[i][j] == 1) {
                System.out.println("第" + i + "个商品加入背包");
                j -= w[i - 1];
            }
            i--;
        }

    }
}
