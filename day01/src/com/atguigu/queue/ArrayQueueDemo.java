package com.atguigu.queue;

import java.util.Scanner;

/**
 * @author alick
 * @description:
 * @create 2022-05-26 22:03
 */
public class ArrayQueueDemo {

    public static void main(String[] args) {

        ArrayQueue arrayQueue = new ArrayQueue(3);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            System.out.println("s:显示队列");
            System.out.println("e:退出程序");
            System.out.println("a:添加数据");
            System.out.println("g:取出数据");
            System.out.println("h:显示队头数据");
            key = scanner.next().charAt(0);
            switch (key){
                case 's':
                    try {
                        arrayQueue.showQueue();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                case 'a':
                    System.out.println("请输入要添加的数字：");
                    int num = scanner.nextInt();
                    arrayQueue.addQueue(num);
                    break;
                case 'g':
                    try {
                        arrayQueue.getQueue();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int firstOfQueue = arrayQueue.getFirstOfQueue();
                        System.out.println(firstOfQueue);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
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

    private int maxSize;
    private int front;
    private int rear;
    private int[] arr;

    public ArrayQueue(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = -1;
        rear = -1;
    }

    public boolean ifFull(){
        return rear == maxSize - 1;
    }

    public boolean ifEmpty(){
        return rear == front;
    }

    public void addQueue(int n){
        if (ifFull()){
            System.out.println("队列已满");
            return;
        }
        rear++;
        arr[rear] = n;
    }

    public int getQueue(){
        if (ifEmpty()){
            throw new RuntimeException("队列为空");
        }
        front++;
        return arr[front];
    }

    public void showQueue(){
        if (ifEmpty()){
            System.out.println("队列为空");
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public int getFirstOfQueue(){
        if (ifEmpty()){
            throw new RuntimeException("队列为空");
        }
        return arr[front + 1];
    }
}
