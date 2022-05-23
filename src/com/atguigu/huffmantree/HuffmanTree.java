package com.atguigu.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author alick
 * @description:
 * @create 2022-05-14 11:01
 */
public class HuffmanTree {

    public static void main(String[] args) {

        int[] arr = {13,7,8,3,29,6,1};

        Node root = createHuffmanTree(arr);

        //测试一把
        preOrder(root);
    }

    //编写一个前序遍历的方法
    public static void preOrder(Node root){
        if (root != null){
            root.preOrder();
        }else{
            System.out.println("是空树，不能遍历");
        }
    }

    //创建霍夫曼树的方法
    public static Node createHuffmanTree(int[] arr){

        //第一步为了操作方便
        //遍历arr数组
        //将arr的每一个元素构成一个Node
        //将Node放到ArrayList中
        List<Node> nodes = new ArrayList<>();
        for (int value : arr){
            nodes.add(new Node(value));
        }

        //我们处理的过程是一个循环的过程
        while(nodes.size() > 1){
            //排序 从小到大
            Collections.sort(nodes);

            System.out.println("nodes = " + nodes);

            //  取出根结点权值最小的两棵二叉树
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);

            //创建一棵新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;

            //从ArrayList删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);

            //将parent加入到nodes
            nodes.add(parent);
        }
        //返回哈夫曼树的root结点
        return nodes.get(0);
    }
}

//创建结点类
//为了让node对象持续排序Collections集合排序
//让Node实现Comparable接口
class Node implements Comparable<Node>{

    int value;//结点权值
    Node left;//指向左子结点
    Node right;//指向右子结点

    public Node(int value){
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    //写一个前序遍历
    public void preOrder(){
        System.out.println(this);
        if (this.left != null){
            this.left.preOrder();
        }
        if (this.right != null){
            this.right.preOrder();
        }
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }
}
