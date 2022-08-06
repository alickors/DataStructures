package com.atguigu.sparsearray;

/**
 * @author alick
 * @description:
 * @create 2022-05-04 10:31
 */
public class SparseArray {

    public static void main(String[] args) {

        //创建原始二维数组
        int[][] chessArr = new int[11][11];

        //0：代表没有棋子 1表示黑子 2表示蓝子
        chessArr[1][2] = 1;
        chessArr[2][3] = 2;

        System.out.println("输出原始二维数组：");

        for (int[] row : chessArr){
            for (int data : row){
                System.out.print(data + "\t");
            }
            System.out.println();
        }

        //将二维数组转为稀疏数组
        int sum = 0;
        //遍历二维数所有不为零的数据个数
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr[i][j] != 0){
                    sum++;
                }
            }
        }

        //创建对应稀疏数组
        int[][] sparseArr = new int [sum + 1][3];

        //给稀疏数组赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        //遍历二维数组，将非零的值赋值给稀疏数组
        int count = 0;//记录非零值的个数
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr[i][j] != 0){
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr[i][j];
                }
            }
        }

        //输出稀疏矩阵
        System.out.println("得到的稀疏矩阵为：");
        for (int[] row : sparseArr){
            for (int data : row){
                System.out.print(data + "\t");
            }
            System.out.println();
        }

        //稀疏数组转换为二维数组
        int[][] arr = new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int i = 1; i < sparseArr.length; i++) {
            arr[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        //输出恢复后的二维数组
        System.out.println("恢复后的二维数组：");
        for (int[] row : arr){
            for (int data : row){
                System.out.print(data + "\t");
            }
            System.out.println();
        }


    }



}
