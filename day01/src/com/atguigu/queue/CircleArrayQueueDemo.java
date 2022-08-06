package com.atguigu.queue;

import java.util.Scanner;

/**
 * @author alick
 * @description:
 * @create 2022-05-27 11:07
 */
public class CircleArrayQueueDemo {

    public static void main(String[] args) {

        CircleArray circleArray = new CircleArray(3);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s:显示队列");
            System.out.println("e:退出程序");
            System.out.println("a:添加数据");
            System.out.println("g:取出数据");
            System.out.println("h:显示队头数据");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    try {
                        circleArray.showQueue();
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
                    circleArray.addQueue(num);
                    break;
                case 'g':
                    try {
                        circleArray.getQueue();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int firstOfQueue = circleArray.getFirstOfQueue();
                        System.out.println(firstOfQueue);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    break;
            }
        }


    }
}

class CircleArray{

    private int maxSize;
    private int rear;
    private int front;
    private int[] arr;

    public CircleArray(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[maxSize];
    }

    public boolean ifFull(){
        return (rear + 1) % maxSize == front;
    }

    public boolean ifEmpty(){
        return front == rear;
    }

    public void addQueue(int n){
        if (ifFull()){
            System.out.println("队里已满");
            return;
        }
        arr[rear] = n;
        rear = (rear + 1) % maxSize;
    }

    public int getQueue(){
        if (ifEmpty()){
            throw new RuntimeException("队列为空");
        }
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    public void showQueue(){
        if (ifEmpty()){
            System.out.println("队里为空");
            return;
        }
        for (int i = front; i < front + size(); i++) {
            System.out.println(arr[i]);
        }
    }

    public int getFirstOfQueue(){
        if (ifEmpty()){
            throw new RuntimeException("队列为空");
        }
        return arr[front];
    }

    public int size(){
        return (rear + maxSize - front) % maxSize;
    }
}
