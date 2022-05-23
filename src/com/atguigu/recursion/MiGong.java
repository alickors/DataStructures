package com.atguigu.recursion;

/**
 * @author alick
 * @description:
 * @create 2022-05-08 09:56
 */
public class MiGong {

    public static void main(String[] args) {

        //先创建一个二维数组，模拟迷宫
        int[][] map = new int[8][7];

        //1代表墙壁
        //地图上下全部置1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }

        //左右全部置1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }

        //其他的墙壁
        map[3][1] = 1;
        map[3][2] = 1;


        //输出地图
        System.out.println("地图的情况");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + "\t");
            }
            System.out.println();
        }

        //使用递归回溯给小球找路
        setWay(map,1,1);

        //输出新的地图，小球走过的，并标识过的递归
        System.out.println("小球走过的，标识过的地图");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + "\t");
            }
            System.out.println();
        }
    }

    //使用递归回溯给小球找路说明
    /**
     * 1，mapbiaoshiditu
     * 2，i，j表示从地图的那个位置开始（1，1）
     * 3，如果小球能到达map【6】【5】位置，则说明通路找到了
     * 4，约定：当map【i】【j】为零表示该路没有走过，当为1的时候表示围墙 2表示通路可以走 3表示该路已经走过走不通
     * 5，走迷宫时，需要确定好一个策略，下-右-上-左，如果该点走不通，再回溯
     *
     */
    /**
     * @param map 表示地图
     * @param i
     * @param j   从那个位置开始找
     * @return 如果找到通路就返回true，找不到就返回false
     */

    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            //通路已经找到
            return true;
        } else {
            if (map[i][j] == 0) {
                //如果当前这个点还没有走过按照策略走
                map[i][j] = 2;//假定该点是可以走通的
                if (setWay(map, i + 1, j)) {//向下走
                    return true;
                } else if (setWay(map, i, j + 1)) {//向右走
                    return true;
                } else if (setWay(map, i - 1, j)) {//向上走
                    return true;
                } else if (setWay(map, i, j - 1)) {//向左走
                    return true;
                } else {
                    //说明该点是死路，走不通
                    map[i][j] = 3;
                    return false;
                }
            } else {//如果map[i][j] != 0;可能是1 2 3
                return false;
            }
        }
    }

}
