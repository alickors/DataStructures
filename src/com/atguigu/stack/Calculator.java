package com.atguigu.stack;

/**
 * @author alick
 * @description:
 * @create 2022-05-07 09:43
 */
public class Calculator {

    public static void main(String[] args) {

        String expression = "7*2*2-5+1-5+3-40";
        //创建两个栈，一个是数栈，一个是符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);

        //定义需要的相关变量
        int index = 0;//用于扫描
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch;//将每次扫描到的char保存到ch
        String keepNum = "";//用于拼接多位数
        //开始循环扫描expression
        while (true){
            //依次得到expression的每一个字符
            ch = expression.substring(index,index + 1).charAt(0);
            //判断ch是什么，然后做相应的处理
            if (operStack.isOper(ch)){//如果是运算符
                //判断当前的符号栈是否为空
                if (!operStack.isEmpty()){
                    //如果符号栈有操作符，就进行比较，如果当前的操作符的优先级小于或者等于栈中的操作符，就需要从数栈中pop出两个数
                    //再从符号栈中pop出一个符号，进行运算，将得到的结果压入数栈，再将当前的操作符压入符号栈
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())){
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1,num2,oper);
                        //把运算的结果压入数栈
                        numStack.push(res);
                        //然后将当前的操作符入符号栈
                        operStack.push(ch);
                    }else{
                        //如果当前操作符的优先级大于栈中的操作符，就直接入符号栈
                        operStack.push(ch);
                    }
                }else{
                    //如果符号栈为空，就直接入符号栈
                    operStack.push(ch);
                }
            }else {
                //如果是数字，就直接入数栈
                //1，当处理多位数时，不能发现是一个数就直接入栈，因为他可能是多位数
                //2，处理数时，需要向expression的表达式的index后一位再看一位，如果是数就进行扫描，如果是符号才入栈
                //3，因此我们需要时定义一个变量字符串，用于拼接
                //处理多位数
                keepNum += ch;

                //如果ch已经是expression的最后一位就直接入栈
                if (index == expression.length() - 1){
                    numStack.push(Integer.parseInt(keepNum));
                }else{
                    //判断下一个字符是不是数字，如果是数字，就继续扫描，如果是运算符，则入栈
                    if (operStack.isOper(expression.substring(index + 1,index + 2).charAt(0))){
                        //如果后一位是运算符，则入栈
                        numStack.push(Integer.parseInt(keepNum));
                        //将keepNum清空
                        keepNum = "";
                    }
                }
            }
            //让index+1，并判断是否扫描到最后
            index++;
            if (index >= expression.length()){
                break;
            }
        }

        //当表达式扫描完毕，就顺序的从符号栈和数栈pop出相应的数和符号进行运算
        while (true){
            //如果符号栈为空，则计算到最后的结果，数栈中只有一个数字
            if (operStack.isEmpty()){
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1,num2,oper);
            numStack.push(res);
        }
        //讲述栈的最后一个数pop，就是计算结果
        int res2 = numStack.pop();
        System.out.println("运算结果是：" + res2);
    }

}

//先创建一个栈
class ArrayStack2 {

    private int maxSize;//栈的大小
    private int[] stack;//数组模拟栈，数据就放在该数组里
    private int top = -1;//top表示栈顶，初始值为-1

    //构造器
    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //增加一个方法，可以返回当前栈顶的值，但不是真正的pop
    public int peek(){
        return stack[top];
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈
    public void push(int value) {
        //先判断栈是否是满的
        if (isFull()) {
            System.out.println("栈是满的");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈
    public int pop() {
        //先判断栈是否为空
        if (isEmpty()) {
            throw new RuntimeException("栈是空的");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //显示栈的情况
    //遍历栈
    public void list() {
        if (isEmpty()) {
            System.out.println("栈是空的");
            return;
        }
        //需要从栈顶开始显示数据
        for (int i = top; i >= 0; i--) {
            System.out.print(stack[i] + "\t");
        }
    }

    //返回运算符的优先级，优先级是由程序猿决定的，优先级是用数字来表示
    //数字越是大，优先级就越高
    public int priority(int oper){
        if (oper == '*' || oper == '/'){
            return 1;
        }else if (oper == '+' || oper == '-'){
            return 0;
        }else{
            return -1;//假定目前的表达式只有+ - * /
        }
    }

    //判断是否是一个运算符
    public boolean isOper(char val){
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算方法
    public int cal(int num1,int num2,int oper){
        int res = 0;//res用于存放运算结果
        switch (oper){
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }

}

