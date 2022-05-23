package com.atguigu.stack;


import java.util.Scanner;

/**
 * @author alick
 * @description:
 * @create 2022-05-07 09:09
 */
public class ArrayStackDemo {

    public static void main(String[] args) {

        //测试一下ArrayStack是否正确
        //先创建一个ArrayStack对象表示栈
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true;//控制是否退出菜单
        Scanner scanner = new Scanner(System.in);

        while (loop){
            System.out.println("show:表示显示栈");
            System.out.println("exit:退出程序");
            System.out.println("push:表示添加程序到栈");
            System.out.println("pop:表示取出数据");
            System.out.println("请输入你的选择:");

            key = scanner.next();
            switch (key){
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数：");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.println("出栈的数据是" + res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出了");
    }
}

//定义一个ArrayStack表示栈
class ArrayStack{

    private int maxSize;//栈的大小
    private int[] stack;//数组模拟栈，数据就放在该数组里
    private int top = -1;//top表示栈顶，初始值为1

    //构造器
    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //栈满
    public boolean isFull(){
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty(){
        return top == -1;
    }

    //入栈
    public void push(int value){
        //先判断栈是否是满的
        if (isFull()){
            System.out.println("栈是满的");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈
    public int pop(){
        //先判断栈是否为空
        if (isEmpty()){
            throw new RuntimeException("栈是空的");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //显示栈的情况
    //遍历栈
    public void list(){
        if (isEmpty()){
            System.out.println("栈是空的");
            return;
        }
        //需要从栈顶开始显示数据
        for (int i = top; i >= 0; i--) {
            System.out.print(stack[i] + "\t");
        }
    }


}
