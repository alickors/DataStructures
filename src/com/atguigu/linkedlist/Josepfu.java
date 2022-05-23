package com.atguigu.linkedlist;

/**
 * @author alick
 * @description:
 * @create 2022-05-06 14:56
 */
public class Josepfu {

    public static void main(String[] args) {

        //测试一把看看构建链表和遍历是否ok
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);//加入5个小孩节点
        circleSingleLinkedList.showBoy();

        //测试一把小孩出圈是否正确
        circleSingleLinkedList.countBoy(2,2,5);
    }
}


//创建一个环形的单向链表
class CircleSingleLinkedList{
    //创建一个first节点，当前没有编号
    private Boy first = null;

    //添加小孩节点，构成一个环形的链表
    public void addBoy(int nums){
        //对nums做一个校验
        if (nums < 1){
            System.out.println("nums的值不正确");
            return;
        }
        Boy curBoy = null;//辅助指针，帮助构建环形链表
        //使用for来创建我们的环形链表
        for (int i = 1; i <= nums; i++) {
            //根据编号创建小孩节点
            Boy boy = new Boy(i);
            //如果是第一个小孩
            if (i == 1){
                first = boy;
                first.setNext(first);//构成环状
                curBoy = first;//让curBoy指向第一个小孩
            }else{
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    //遍历当前的循环链表
    public void showBoy(){
        //判断链表是否为空
        if (first == null){
            System.out.println("没有任何小孩");
            return;
        }
        //因为first不能动，我们仍然使用一个辅助指针完成遍历
        Boy curBoy = first;
        while (true){
            System.out.println("小孩的编号：" + curBoy.getNo());
            if (curBoy.getNext() == first){
                //说明已经遍历结束
                break;
            }
            curBoy = curBoy.getNext();//curBoy后移
        }
    }

    //根据用户的输入，计算出小孩的出圈顺序

    /**
     *
     * @param startNo 从第几个小孩开始数数
     * @param countNum 表示数多少下
     * @param nums 表示最初有多少小孩再圈里
     */
    public void countBoy(int startNo,int countNum,int nums){
        //先对数据进行校验
        if (first == null || startNo < 1 || startNo > nums){
            System.out.println("参数输入有误，请重新输入");
            return;
        }

        //创建辅助指针，帮助小孩完成出圈,事先指向环形链表最后一个节点
        Boy helper = first;
        while (true){
            if (helper.getNext() == first){//说明helper指向了最后一个小孩节点
                break;
            }
            helper = helper.getNext();
        }

        //小孩报数前，让first和helper指针同时的移动m-1次
        while (true){
            if (helper == first){
                //说明圈中只有一个节点
                break;
            }
            //让first和helper指针同时移动countNum-1
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //这时first指向的节点，就是出圈小孩的节点
            System.out.println("小孩" + first.getNo() + "出圈");
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.println("最后留在圈中的小孩编号为" + first.getNo());
    }
}

class Boy{

    private int no;//编号
    private Boy next;//指向下一个节点，默认为null

    public Boy(int no){
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
