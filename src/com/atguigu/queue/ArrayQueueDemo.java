package com.atguigu.queue;

import java.util.Scanner;

/**
 * @author alick
 * @description:
 * @create 2022-05-04 11:22
 */
public class ArrayQueueDemo {

    public static void main(String[] args) {

        //创建一个队列
        ArrayQueue queue = new ArrayQueue(3);
        char key;//接受用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取数据");
            System.out.println("h(head):查看队列头的数据");
            key = scanner.next().charAt(0);//从控制台接受一个字符
            switch (key){
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数：");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = queue.getQueue();
                        System.out.println("取出的数据是：" + res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = queue.headQueue();
                        System.out.println("队列的头部数据：" + res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;

            }
        }
        System.out.println("程序退出");
    }
}

//使用数组模拟队列
class ArrayQueue{

    private int maxSize;//数组的最大容量
    private int front;//队列头
    private int rear;//队列尾
    private int[] arr;//模拟队列的数组

    //创建队列的构造器
    public ArrayQueue(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = -1;//指向队列头部
        rear = -1;//指向队尾部
    }

    //判断队列是否满
    public boolean ifFull(){
        return rear == maxSize - 1;
    }

    //判断队列是否为空
    public boolean ifEmpty(){
        return rear == front;
    }

    //添加数据到队列
    public void addQueue(int n){
        if (ifFull()){
            System.out.println("队列已满");
            return;
        }
        rear++;//rear后移
        arr[rear] = n;
    }

    //数据出队列
    public int getQueue(){
        if (ifEmpty()){
            throw new RuntimeException("队列为空");
        }
        front++;//front后移
        return arr[front];
    }

    //现实队列所有的数据
    public void showQueue(){
        if (ifEmpty()){
            System.out.println("队列是空的");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i] + "\t");
        }
    }

    //现实队列的头数据
    public int headQueue(){
        if (ifEmpty()){
            throw new RuntimeException("队列为空");
        }
        return arr[front + 1];
    }

}




















