package com.atguigu.linkedlist;

/**
 * @author alick
 * @description:
 * @create 2022-05-06 14:02
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {

        System.out.println("双向链表的测试");

        //先创建节点
        HeroNode2 hero1 = new HeroNode2(1,"宋江","及时雨");
        HeroNode2 hero2 = new HeroNode2(2,"卢俊义","玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3,"吴用","智多星");
        HeroNode2 hero4 = new HeroNode2(4,"林冲","豹子头");

        //创建一个双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);

        doubleLinkedList.list();

    }
}

//创建一个双向链表的类
class DoubleLinkedList {

    //先初始化一个头节点，头节点不要动，不存放具体的数据
    private HeroNode2 head = new HeroNode2(0, "", "");

    //返回头节点
    public HeroNode2 getHead() {
        return head;
    }

    //遍历双向链表的方法
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头节点不能动，因此我们需要一个辅助变量来遍历
        HeroNode2 temp = head.next;
        while (true) {
            //判断是否到链表的最后
            if (temp == null) {
                break;
            }
            //输出节点的信息
            System.out.println(temp);
            //temp后移
            temp = temp.next;
        }
    }

    //添加一个节点到双向链表的最后
    public void add(HeroNode2 heroNode) {
        //因为head不能动，因此我们需要一个辅助遍历temp
        HeroNode2 temp = head;
        //遍历链表，找到最后
        while (true) {
            //找到链表的最后
            if (temp.next == null) {
                break;
            }
            //如果没有找到最后，将temp后移
            temp = temp.next;
        }
        //当退出while循环后，temp就指向了链表的最后
        //形成一个双向链表
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    //修改一个节点的内容
    public void update(HeroNode2 newHeroNode){
        //判断是否为空
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        //根据no编号，找到需要修改的节点
        //定义一个辅助变量
        HeroNode2 temp = head.next;
        boolean flag = false;//表示是否找到该节点
        while (true){
            if (temp == null){
                break;//已经遍历完了节点
            }
            if (temp.no == newHeroNode.no){
                //找到了该节点
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否找到要修改的节点
        if(flag){
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        }else{
            System.out.println("没有找到编号为" + newHeroNode.no + "的节点");
        }
    }

    //从双向链表删除一个节点
    public void del(int no){
        //判断当前的链表是否为空
        if (head.next == null){
            System.out.println("链表为空，无法删除");
            return;
        }
        HeroNode2 temp = head.next;
        boolean flag = false;//是否找到待删除节点
        while (true){
            if (temp == null){
                //已经到了链表的最后
                break;
            }
            if (temp.no == no){
                //找到了待删除节点
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag){
            //删除节点
            temp.pre.next = temp.next;
            //如果是最后一个节点，就需要执行下面这句话，否则就会出现空指针
            if (temp.next != null){
                temp.next.pre = temp.pre;
            }else{
                System.out.println("要删除的节点" + no + "不存在。");
            }
        }
    }
}

class HeroNode2 {

    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;//指向下一个节点，默认为null
    public HeroNode2 pre;//指向前一个节点，默认为null

    //构造器
    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    //为了显示方法，我们重写toString

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
