package com.atguigu.linkedlist;

import java.util.Stack;

/**
 * @author alick
 * @description:
 * @create 2022-05-27 11:47
 */
public class SingleLinkedListDemo {

    public static void main(String[] args) {

        SingLinkedList singLinkedList = new SingLinkedList();

    }
}

class SingLinkedList{

    private HeroNode head = new HeroNode(0,"","");

    public void add(HeroNode heroNode){
        HeroNode temp = head;
        while (true){
            if (temp.next == null){
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode;
    }

    public void addByOrder(HeroNode heroNode){
        HeroNode temp = head;
        boolean flag = false;
        while (true){
            if (temp.next ==null){
                break;
            }
            if (temp.next.no > heroNode.no){
                break;
            }else if (temp.next.no == heroNode.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            System.out.println("编号已经存在");
        }else{
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    public void update(HeroNode newHeroNode){
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }

        HeroNode temp = head.next;
        boolean flag = false;
        while (true){
            if (temp == null){
                break;
            }
            if (temp.no == newHeroNode.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        }else{
            System.out.println("没有找到指定结点");
        }
    }

    public void del(int no){
        HeroNode temp = head;
        boolean flag = false;
        while (true){
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        }else{
            System.out.println("要删除的节点不存在");
        }
    }

    public void list(){
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;
        while (true){
            if (temp == null){
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

    public int getLength(HeroNode head){
        int count = 0;
        if (head.next == null){
            return 0;
        }
        HeroNode temp = head.next;
        while (temp != null){
            count++;
            temp = temp.next;
        }
        return count;
    }

    //查找单链表中倒数第k个结点
    public HeroNode findHeroNode(HeroNode head, int k){
        if (head.next == null){
            System.out.println("链表为空");
            return null;
        }
        int count = getLength(head);

        if (k <= 0 || k > count){
            return null;
        }
        HeroNode temp = head.next;
        for (int i = 0; i < count - k; i++) {
            temp = temp.next;
        }
        return temp;
    }

    //单链表反转
    public static void reverseList(HeroNode head){
        //如果当前链表只有一个节点或者为空，无需反转，直接返回
        if (head.next == null || head.next.next == null){
            return;
        }

        //定义一个辅助指针帮助我们遍历原来的链表
        HeroNode cur = head.next;
        HeroNode next = null;//指向当前结点的下一个结点
        HeroNode reverseHead = new HeroNode(0,"","");

        //遍历原来的链表，每遍历一个结点，就将其取出，并放在新链表的最前端
        while (cur != null){
            next = cur.next;//先暂时保存当前结点的下一个结点，后面需要使用
            cur.next = reverseHead.next;
            reverseHead.next = cur;
            cur = next;
        }
        //将head.next指向reverseHead.next,实现单链表的反转
        head.next = reverseHead.next;
    }

    //使用栈的数据结构，将各个结点压入栈中，然后利用栈的先进后出的特点，实现了逆序打印的效果
    public static void reversePrint(HeroNode head){
        if (head.next == null){
            return;//空链表，不能打印
        }
        //创建一个栈，将各个结点压入栈中
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;

        //将链表的所有节点压入栈
        while (cur != null){
            stack.push(cur);
            cur = cur.next;
        }

        //将栈中的数据进行打印，pop出栈
        while (stack.size() > 0){
            System.out.println(stack.pop());
        }
    }
}

class HeroNode{

    public int no;
    public String name;
    public String nickName;
    public HeroNode next;

    public HeroNode(int no, String name, String nickName){
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
