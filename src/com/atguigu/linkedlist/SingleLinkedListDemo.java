package com.atguigu.linkedlist;

import java.util.Stack;

/**
 * @author alick
 * @description:
 * @create 2022-05-05 10:34
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {

        //进行测试，先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        //创建链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();

        //加入链表
        singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);

        //按照编号的顺序加入链表
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero2);

        //显示
        singleLinkedList.list();

        //测试修改节点代码
        HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟～～");
        singleLinkedList.update(newHeroNode);

        System.out.println("修改后的链表情况");
        singleLinkedList.list();

        //删除一个节点
        singleLinkedList.del(1);
        singleLinkedList.del(4);

        System.out.println("删除后的链表情况");
        singleLinkedList.list();

    }
}

//定义SingleLinkedList管理我们的英雄
class SingleLinkedList {

    //先初始化一个头节点，头节点不要动，不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "");

    //添加节点到单向链表
    //不考虑编号的顺序 1，找到最后的节点 2，这最后这个节点的next指向新的节点
    public void add(HeroNode heroNode) {
        //因为head节点不能动，因此我们需要一个辅助遍历temp
        HeroNode temp = head;
        //遍历链表，找到最后一个节点
        while (true) {
            //找到链表的最后
            if (temp.next == null) {
                break;
            }
            //如果没有找到的话，就将temp后移
            temp = temp.next;
        }
        //退出while循环的时候，temp就指向了链表的最后
        //将最后这个节点的next指向新的节点
        temp.next = heroNode;
    }

    //根据排名插入到指定的位置
    public void addByOrder(HeroNode heroNode) {
        //因为是单链表，我们要找的temp是位于添加位置的前一个节点
        HeroNode temp = head;
        //flag标志添加的编号是否存在
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                //说明temp已经到了链表的最后
                break;
            }
            if (temp.next.no > heroNode.no) {
                //位置找到，就在temp的后面插入
                break;
            } else if (temp.next.no == heroNode.no) {
                //说明希望添加的编号已经存在了
                flag = true;
                break;
            }
            temp = temp.next;//后移，遍历当前链表
        }

        //判断flag的值
        if (true) {
            System.out.println("准备插入的英雄编号" + heroNode.no + "已经存在了，不能加入。");
        } else {
            //插入到链表中，temp的后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //修改节点的信息，根据no编号来修改，no编号不可以改
    public void update(HeroNode newHeroNode) {
        //判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //根据编号找到需要修改的节点
        //定义一个辅助变量
        HeroNode temp = head.next;
        boolean flag = false;//表示是否找到该节点
        while (true) {
            if (temp == null) {
                break;//已经遍历完了链表
            }
            if (temp.no == newHeroNode.no) {
                //找到了
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = newHeroNode.name;
            ;
            temp.nickname = newHeroNode.nickname;
            ;
        } else {
            System.out.println("没有找到编号" + newHeroNode.no + "的节点，不能修改。");
        }

    }

    //删除节点
    //head不能动，我们需要一个temp辅助节点找到待删除节点的前一个节点
    public void del(int no) {
        HeroNode temp = head;
        boolean flag = false;//表示是否找到待删除节点
        while (true) {
            if (temp.next == null) {
                //说明已经找到了链表的最后
                break;
            }
            if (temp.next.no == no) {
                //找到了待删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next;//后移，遍历节点
        }
        if (flag) {
            //找到了，可以进行删除
            temp.next = temp.next.next;
        } else {
            System.out.println("要删除的节点" + no + "不存在。");
        }
    }

    //显示链表
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }

        //因为是头节点，不能动，因此我们需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while (true) {
            //判断是否到了链表的最后
            if (temp == null) {
                break;
            }
            //输出节点的信息
            System.out.println(temp);
            //将temp后移
            temp = temp.next;
        }
    }

    //求单链表中有效节点的个数
    public static int getLength(HeroNode head){
        if (head.next == null){
            //空链表
            return 0;
        }
        int length = 0;
        //定义一个辅助变量，这里我们没有统计头节点
        HeroNode cur = head.next;
        while (cur != null){
            length++;
            cur = cur.next;
        }
        return length;
    }

    //查找单链表中倒数第k个节点
    public static HeroNode findLastIndexNode(HeroNode head,int index){
        //如果链表为空，返回null
        if (head.next == null){
            return null;//什么也没有找到
        }
        //第一次遍历得到单链表的长度
        int size = getLength(head);

        //第二次遍历size-index位置，就是我们倒数第k个节点
        //先对index进行一次校验
        if(index <= 0 || index > size){
            return null;
        }
        //定义辅助变量，循环定位到倒数的index
        HeroNode cur = head.next;
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    //将单链表反转
    public static void reverseList(HeroNode head){
        //如果链表为空，或者只有一个节点，无需反转，直接返回
        if (head.next == null || head.next.next == null){
            return;
        }

        //定义一个辅助的指针，帮助我们遍历原来的链表
        HeroNode cur = head.next;
        //指向cur节点的下一个节点
        HeroNode next = null;
        HeroNode reverseHead = new HeroNode(0,"","");

        //遍历原来的链表，每遍历一个节点，就将其取出，放在新的链表的最前端
        while (cur != null){
            next = cur.next;//先暂时保存当前节点的下一个节点，后面需要使用
            cur.next = reverseHead.next;//将cur的下一个节点指向新的链表的最前端
            reverseHead.next = cur;//将cur连接到新的链表上
            cur = next;//cur后移
        }
        //将head.mext指向reverseHead.next，实现链表的反转
        head.next = reverseHead.next;
    }

    //单链表的逆序打印算法
    public static void reversePrint(HeroNode head){
        if (head.next == null){
            return;
        }
        //创建一个要给的栈，将各个节点压入栈内
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        //将所有节点压入栈
        while (cur != null){
            stack.push(cur);
            cur = cur.next;//cur后移，接着压入下一个节点
        }
        //将栈中的节点进行打印
        while (stack.size() > 0){
            System.out.println(stack.pop());//栈的特点是先进后出
        }
    }

}

//定义HeroNode ，每个HeroNode对象就是一个节点
class HeroNode {

    public int no;
    public String name;
    public String nickname;
    public HeroNode next;//指向下一个节点

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
