package com.atguigu.sparsearray;

/**
 * @author alick
 * @description:
 * @create 2022-05-25 11:48
 */
public class SparseArray {

    public static void main(String[] args) {

        int[][] chessArray = new int[11][11];
        chessArray[1][2] = 1;
        chessArray[2][3] = 2;

        int num = 0;//记录数组中不为0的个数
        for (int i = 0; i < chessArray.length; i++) {
            for (int j = 0; j < chessArray[i].length; j++) {
                if (chessArray[i][j] != 0){
                    num++;
                }
            }
        }

//        System.out.println(num);

        int count = 0;//用来记录是第几个非0数据
        int[][] sparseArray = new int[num + 1][3];//定义稀疏数组
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = num;

        for (int i = 0; i < chessArray.length; i++) {
            for (int j = 0; j < chessArray[i].length; j++) {
                if (chessArray[i][j] != 0){
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = chessArray[i][j];
                }
            }
        }

        System.out.println("输出稀疏矩阵");
        printArray(sparseArray);
//        printArray(chessArray);

        //从稀疏矩阵转换为二维数组
        int[][] arr = new int[sparseArray[0][0]][sparseArray[0][1]];
//        printArray(arr);
        for (int i = 1; i <= sparseArray[0][2]; i++) {
            arr[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }

        printArray(arr);

    }

    //打印数组
    public static void printArray(int[][] array){
        for (int[] row : array){
            for (int data : row){
                System.out.print(data + "\t");
            }
            System.out.println();
        }
    }
}
