package com.atguigu.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author alick
 * @description:
 * @create 2022-05-07 10:37
 */
public class PolandNotation {

    public static void main(String[] args) {

//        //先给定逆波兰表达式
//        //说明为了方便，逆波兰表达式的数字和符号使用空格隔开
//        String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";//76
//
//        //1，先将逆波兰表达式放到ArrayList表达式中
//        //2，将ArrayList传递给一个方法，遍历ArrayList配合栈完成计算
//
//        List<String> list = getListString(suffixExpression);
//        System.out.println("rpnList = " + list);
//        int res = calculate(list);
//        System.out.println("运算结果是：" + res);

        String expression = "1+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println("中缀表达式对应的List = " + infixExpressionList);
        List<String> suffixExpressionList = parseSuffixExpressionList(infixExpressionList);
        System.out.println("后缀表达式对应的List = " + suffixExpressionList);
        System.out.println("expression = " + calculate(suffixExpressionList));
    }

    //将得到的中缀表达式对应的list转变为后缀表达式对应的List

    public static List<String> parseSuffixExpressionList(List<String> ls) {
        //定义两个栈
        Stack<String> s1 = new Stack<>();//符号栈
        List<String> s2 = new ArrayList<>();//存储中间结果的List

        //遍历ls
        for (String item : ls) {
            //如果是一个数，加入s2
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                //如果是右括号，则依次弹出s1栈顶的运算符压入s2，直到遇到左括号为止，然后将这一对括号丢弃
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();//将（弹出s1栈，消除小括号
            } else {
                //当item的优先级小于或者等于s1栈顶运算符，将s1栈顶运算符弹出并加入到s2中
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                //还需将item压入栈
                s1.push(item);
            }
        }
        //将s1中剩余的运算符压入栈s2
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;
    }

    //中缀表达式转变为对应的List
    public static List<String> toInfixExpressionList(String s) {
        //定义一个list，存放中缀表达式
        List<String> ls = new ArrayList<>();
        int i = 0;//这是一个指针，用于遍历中缀表达式字符串
        String str;//对多位数的拼接
        char c;//每遍历一个字符就放入到c
        do {
            //如果c是一个非数字，我们需要加入到ls
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                ls.add("" + c);
                i++;//i需要后移
            } else {//如果是一个数，需要考虑多位数的情况
                str = "";
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c;
                    i++;
                }
                ls.add(str);
            }
        } while (i < s.length());
        return ls;
    }

    public static List<String> getListString(String suffixExpression) {
        //将suffixExpression分割
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }

    //完成对逆波兰表达式的运算
    public static int calculate(List<String> ls) {
        //创建栈，只需要一个栈即可
        Stack<String> stack = new Stack<>();
        //遍历ls
        for (String item : ls) {
            //这里使用正则表达式取出来数字
            if (item.matches("\\d+")) {
                //入栈
                stack.push(item);
            } else {
                //pop出两个数，并运算，再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                //把res入栈
                stack.push("" + res);
            }
        }
        //最后留在stack中的数据是运算结果
        return Integer.parseInt(stack.pop());
    }
}

//编写一个类Operation可以返回一个运算符对应的优先级
class Operation {

    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //写一个方法，返回对应的优先级数字
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return result;
    }


}