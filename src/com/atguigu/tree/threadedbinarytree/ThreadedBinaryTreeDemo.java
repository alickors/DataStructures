package com.atguigu.tree.threadedbinarytree;

/**
 * @author alick
 * @description:
 * @create 2022-05-12 11:28
 */
public class ThreadedBinaryTreeDemo {

    public static void main(String[] args) {

        //测试一把中序线索二叉树的功能
        HeroNode root = new HeroNode(1,"tom");
        HeroNode node2 = new HeroNode(3,"jack");
        HeroNode node3 = new HeroNode(6,"smith");
        HeroNode node4 = new HeroNode(8,"mary");
        HeroNode node5 = new HeroNode(10,"king");
        HeroNode node6 = new HeroNode(14,"dim");

        //二叉树，后面我们要递归创建，现在简单处理使用手动创建
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        //测试中序线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();

        //测试：以10号结点测试
        HeroNode leftNode = node5.getLeft();
        HeroNode rightNode = node5.getRight();
        System.out.println("10号结点的前驱结点是 = " + leftNode);
        System.out.println("10号结点的后继结点是 = " + rightNode);

        //遍历线索化二叉树
        System.out.println("遍历线索化二叉树");
        threadedBinaryTree.threadList();

    }
}

//定义ThreadedBinaryTree实现了线索化功能的二叉树
class ThreadedBinaryTree{

    private HeroNode root;

    //为了实现线索化，需要创建指向当前节点的前驱结点的指针
    //在递归进行线索化时，pre总是保留前一个结点
    private HeroNode pre = null;

    public void setRoot(HeroNode root){
        this.root = root;
    }

    //重载一把threadedNodes方法
    public void threadedNodes(){
        this.threadedNodes(root);
    }

    //遍历线索化二叉树的方法
    public void threadList(){

        //定义一个变量，存储当前遍历的结点，从root开始
        HeroNode node = root;
        while (node != null){
            //循环的找到leftType==1的结点，第一个找到的就是8结点
            //后面随着遍历而变化，因为当leftType==1时，说明该节点是按照线索化处理后的有效结点
            while (node.getLeftType() == 0){
                node = node.getLeft();
            }

            //打印当前这个结点
            System.out.println(node);

            //如果当前结点的右指针指向的是后继结点，就一直输出
            while (node.getRightType() == 1){

                //获取到当前结点的后继结点
                node = node.getRight();
                System.out.println(node);
            }

            //替换这个遍历的结点
            node = node.getRight();
        }
    }

    //编写对二叉树进行中序线索化的方法

    /**
     * @param node 当前需要线索化的结点
     */
    public void threadedNodes(HeroNode node){

        //如果node == null，不能线索化
        if (node == null){
            return;
        }

        //先线索化左子树
        threadedNodes(node.getLeft());

        //线索化当前结点
        //处理当前节点的前驱结点
        //以8结点来理解 8结点left = null，8结点的leftType = 1
        if (node.getLeft() == null){

            //让当前结点的左指针指向前驱结点
            node.setLeft(pre);

            //修改当前结点的左指针的类型，指向前驱结点
            node.setLeftType(1);
        }

        //处理后继结点
        if (pre != null && pre.getRight() == null){

            //让前驱结点的右指针指向当前结点
            pre.setRight(node);

            //修改前驱结点的右指针类型
            pre.setRightType(1);
        }

        //每处理一个节点之后，让当前结点是下一个结点的前驱结点
        pre = node;

        //线索化右子树
        threadedNodes(node.getRight());
    }
}

//先创建HeroNode节点
class HeroNode {

    private int no;
    private String name;
    private HeroNode left;//默认null
    private HeroNode right;//默认null

    //说明
    //如果leftType == 0，表示指向的是左子树，如果是1则表示指向前驱结点
    //如果rightType == 0，表示指向的是右子树，如果是1则表示指向后继结点
    private int leftType;
    private int rightType;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //编写前序遍历的方法
    public void preOrder() {
        System.out.println(this);//先输出父节点
        //递归向左子树前序遍历
        if (this.left != null) {
            this.left.preOrder();
        }

        //递归向右子树前序遍历
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //中序遍历
    public void infixOrder() {
        //递归向左子树中序遍历
        if (this.left != null) {
            this.left.infixOrder();
        }

        //输出父节点
        System.out.println(this);

        //递归向右子树中序遍历
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    //前序遍历查找

    /**
     * @param no 查找no
     * @return 如果找到的话返回node，如果找不到的话返回null
     */
    public HeroNode preOrderSearch(int no) {
        System.out.println("进入前序遍历");
        //比较当前节点是不是
        if (this.no == no) {
            return this;
        }
        //1，判断当前节点的左子节点是否为空，如果不为空，则递归前序查找
        //2，如果左递归前序查找，找到节点，则返回
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.preOrderSearch(no);
        }

        if (resNode != null) {
            //说明左子树找到
            return resNode;
        }
        //1，左递归前序查找，找到节点则返回，否则继续
        //2，当前节点的右子节点是否为空，如果不空，则继续向右递归前序查找
        if (this.right != null) {
            resNode = this.preOrderSearch(no);
        }
        return resNode;
    }

    //中序比遍历查找
    public HeroNode infixOrderSearch(int no) {
        //判断当前节点的左子节点是否为空，如果不为空，则递归中序查找
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("进入中序查找");
        //如果找到的话，则返回，如果没找到，就和当前的节点比较，如果是的话，就返回当前节点
        if (this.no == no) {
            return this;
        }
        //否则继续进行右递归的中序查找
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    //后序遍历查找
    public HeroNode postOrderSearch(int no) {
        //判断当前节点的左子节点是否为空，如果不为空，则递归后序查找
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null) {//说明左子树找到了
            return resNode;
        }
        //如果左子树没有找到的话，则向右子树递归进行后序遍历查找
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("进入后序查找");
        //如果左右子树都没有找到的话，就比较当前节点是不是
        if (this.no == no) {
            return this;
        }
        return resNode;
    }

    //递归删除节点
    //如果删除的节点是叶子节点，则删除该节点，如果删除的是非叶子节点，则删除该子树
    public void delNode(int no) {
        //如果当前节点的左子节点不为空，并且左子节点就是要删除的节点，就将this.left = null;结束递归删除
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }

        //如果当前节点的右子节点不为空，并且右子节点就是要删除的节点，就将this.right = null;结束递归删除
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }

        //我们需要向左子树进行递归删除
        if (this.left != null) {
            this.left.delNode(no);
        }

        //则应当向右子树进行递归删除
        if (this.right != null) {
            this.right.delNode(no);
        }

    }

}

