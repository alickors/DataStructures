package com.atguigu.huffmancode;

import java.io.*;
import java.util.*;

/**
 * @author alick
 * @description:
 * @create 2022-05-14 14:21
 */
public class HuffmanCode {

    public static void main(String[] args) {

        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
        System.out.println(contentBytes.length);

        List<Node> nodes = getNodes(contentBytes);

        System.out.println("nodes = " + nodes);

        //测试一把，创建的霍夫曼树
        System.out.println("霍夫曼树");
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        System.out.println("前序遍历");
        huffmanTreeRoot.preOrder();

        //测试一把byteToStirng方法
//        System.out.println(byteToBitString());

    }

    //编写一个方法，完成对压缩文件的解压
    public static void unZipFile(String zipFile,String dstFile){

        //定义文件输入流
        InputStream is = null;

        //定义一个对象输入流
        ObjectInputStream ois = null;

        //定义文件输出流
        OutputStream os = null;

        try {
            //创建文件输入流
            is = new FileInputStream(zipFile);

            //创建一个和is关联的对象输入流
            ois = new ObjectInputStream(is);

            //读取byte数组 huffmanBytes
            byte[] huffmanBytes = (byte[]) ois.readObject();

            //读取哈夫曼编码表
            Map<Byte,String> huffmanCodes = (Map<Byte, String>) ois.readObject();

            //解码
            byte[] bytes = decode(huffmanCodes,huffmanBytes);

            //将bytes数组写入到目标文件
            os = new FileOutputStream(dstFile);

            //写数据到dstFile文件
            os.write(bytes);
        } catch (Exception e) {
            System.out.println(e.getMessage());;
        } finally {
            try {
                is.close();
                ois.close();
                os.close();
            } catch (IOException e2) {
                System.out.println(e2.getMessage());
            }
        }


    }

    //编写方法，将一个文件进行压缩
    public static void zipFile(String srcFile,String dstFile){

        FileInputStream is = null;

        OutputStream os = null;

        ObjectOutputStream oos = null;

        //创建输出流
        try {
            //创建文件输入流
            is = new FileInputStream(srcFile);

            //创建一个和源文件大小一样的byte[]
            byte[] b = new byte[is.available()];

            //读取文件
            is.read(b);

            //直接对源文件压缩
            byte[] huffmanBytes = huffmanZip(b);

            //创建文件的输出流，存放压缩文件
            os = new FileOutputStream(dstFile);

            //创建一个和文件输出流相关的ObjectOutputStream
            oos = new ObjectOutputStream(os);

            //把哈夫曼编码后的字节数组写入压缩文件
            oos.writeObject(huffmanBytes);
            //这里我们一对象流的方式写入哈夫曼编码，是为了以后我们恢复原文件时使用
            oos.writeObject(huffmanCodes);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                is.close();
                os.close();
                oos.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    //编写一个方法，完成对压缩数据的解压
    private static byte[] decode(Map<Byte,String> huffmanCodes, byte[] huffmanBytes){

        //1,先得到huffmanBytes对应的二进制的字符串
        StringBuilder stringBuilder = new StringBuilder();

        //将byte数组转换成二进制字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];

            //判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag,b));
        }

        //把字符串按照指定的霍夫曼编码进行解码
        //把哈夫曼编码表进行调换  反向查询
        Map<String,Byte> map = new HashMap<>();
        for (Map.Entry<Byte,String> entry : huffmanCodes.entrySet()){
            map.put(entry.getValue(),entry.getKey());
        }

        //创建一个集合，存放byte
        List<Byte> list = new ArrayList<>();

        //i可以理解为就是索引，扫描stringBuilder
        for (int i = 0; i < stringBuilder.length();) {
            int count = 1;//计数器
            boolean flag = true;
            Byte b = null;
            while (flag){
                //递增的取出key
                String key = stringBuilder.substring(i,i + count);//i不动，让count移动，直到匹配到一个字符
                b = map.get(key);
                if (b == null){
                    //说明没有匹配到
                    count++;
                }else{
                    //匹配到了
                    flag = false;
                }
            }
            list.add(b);
            i += count;//i直接移动到count
        }

        //当for循环结束后，我们list就存放了所有的字符
        //把list中的数据放入到byte[]并返回
        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }

    /**
     * 将一个byte转成一个二进制的字符串
     * @param flag 标志是否需要补高位，如果是true，表示需要补高位，如果是false表示不补，如果是最后一个字节，不需要补高位
     * @param b 传入的byte
     * @return b对应的二进制的字符串
     */
    private static String byteToBitString(boolean flag,byte b){

        //使用变量保存b
        int temp = b;//b转成int

        //如果是正数我们还存在补高位
        if (flag){
            temp |= 256;//按位或256 1 0000 0000 ｜ 0000 0001 = 1 0000 0001
        }

        String str = Integer.toBinaryString(temp);//返回的是temp对应的二进制的补码

        if (flag){
            return str.substring(str.length() - 8);
        }else{
            return str;
        }
    }

    //使用一个方法，将前面的方法封装起来，便于我们调用

    /**
     *
     * @param bytes 原始的字符串对应的字节数组
     * @return 是经过哈夫曼编码后的字节数组（压缩后的数组）
     */
    private static byte[] huffmanZip(byte[] bytes){

        List<Node> nodes = getNodes(bytes);

        //根据nodes创建哈夫曼树
        Node huffmanTreeRoot = createHuffmanTree(nodes);

        //对应的哈夫曼编码
        Map<Byte, String> huffmancodes = getCodes(huffmanTreeRoot);

        //根据生成的哈夫曼编码，压缩得到压缩后的哈夫曼编码字节数组
        byte[] huffmanCodeBytes = zip(bytes,huffmancodes);

        return huffmanCodeBytes;
    }


    //编写一个方法，将字符串对应的byte[]数组，通过生成的哈夫曼编码表，返回一个哈夫曼编码压缩后的byte[]
    /**
     *
     * @param bytes 原始的字符串对应的byte[]
     * @param huffmanCodes 生成的哈夫曼编码map
     * @return 返回哈夫曼编码处理后的byte[]
     */
    private static byte[] zip(byte[] bytes,Map<Byte,String> huffmanCodes){

        //利用huffmanCodes将bytes转成哈夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();

        //遍历bytes数组
        for (byte b : bytes){
            stringBuilder.append(huffmanCodes.get(b));
        }

        //将"10101000101..."转成byte[]
        //统计返回byte[] huffmanCodeBytes长度
        //一句话 int len = (stringBuilder.length + 7) / 8;

        int len;
        if (stringBuilder.length() % 8 == 0){
            len = stringBuilder.length() / 8;
        }else{
            len = stringBuilder.length() / 8 + 1;
        }

        //创建存储压缩后的byte数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;//记录是第几个byte
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            //每8位对应一个byte，所以步长为8
            String strByte;
            if (i + 8 > stringBuilder.length()){
                //不够八位
                strByte = stringBuilder.substring(i);
            }else{
                strByte = stringBuilder.substring(i,i + 8);
            }

            //将strByte转成一个byte，放入到huffmanCodeBytes
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte,2);
            index++;
        }
        return huffmanCodeBytes;
    }

    //生成哈夫曼树对应的哈夫曼编码
    //思路：
    //1，将哈夫曼编码表存放在Map<Byte,Stirng>形式
    //生成的哈夫曼编码表{32 = 01, 97 = 100, ......}
    static Map<Byte,String> huffmanCodes = new HashMap<>();
    //2,为了生成哈夫曼编码表示，需要去拼接路径，定义一个StirngBuilder存储某个叶子结点的路径
    static StringBuilder stringBuilder = new StringBuilder();

    //为了调用方便，我们重载getCodes
    private static Map<Byte,String> getCodes(Node root){
        if (root == null){
            return null;
        }

        //处理root左子树
        getCodes(root.left,"0",stringBuilder);

        //处理root右子树
        getCodes(root.right,"1",stringBuilder);

        return huffmanCodes;
    }

    /**
     * 功能：将传入的node结点的所有叶子结点的哈夫曼编码找到，并放入到huffmanCodes集合
     * @param node 传入结点
     * @param code 路径：左子结点是0，右子结点是1
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder){
         StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
         //将code加入到stringBuilder2
        if (node != null){
            //如果node == null不处理
            //判断当前node结点是叶子结点还是非叶子结点
            if (node.data == null){
                //非叶子结点，递归处理
                //向左递归
                getCodes(node.left,"0",stringBuilder2);
                //向右递归
                getCodes(node.right,"1",stringBuilder2);

            }else{
                //说明是一个叶子结点
                //就找到某个叶子结点的最后
                huffmanCodes.put(node.data,stringBuilder2.toString());
            }
        }
     }

    //前序遍历的方法
    private static void preOrder(Node root){
        if (root != null){
            root.preOrder();
        }else{
            System.out.println("哈夫曼树为空");
        }
    }

    /**
     *
     * @param bytes 接受字节数组
     * @return 返回的就是List形式 [Node[data = 97, weight = 5], Node[data = 32, weight = 9]......]
     */
    private static List<Node> getNodes(byte[] bytes){

        //创建一个ArrayList
        ArrayList<Node> nodes = new ArrayList<>();

        //遍历bytes，统计每一个byte出现的次数 map[key,value]
        Map<Byte,Integer> counts = new HashMap<>();
        for (byte b : bytes){
            Integer count = counts.get(b);
            if (count == null){
                counts.put(b,1);//map还没有这个字符数据，这是第一次
            }else{
                counts.put(b,count + 1);
            }
        }

        //把每一个键值对转成一个Node对象，并加入到nodes集合
        //遍历map
        for (Map.Entry<Byte,Integer> entry : counts.entrySet()){
            nodes.add(new Node(entry.getKey(),entry.getValue()));
        }
        return nodes;
    }

    //可以痛过List创建对应的哈夫曼树
    private static Node createHuffmanTree(List<Node> nodes){

        while (nodes.size() > 1){

            //排序，从小到大
            Collections.sort(nodes);

            //取出第一棵最小的二叉树
            Node leftNode = nodes.get(0);

            //取出第二棵最小的二叉树
            Node rightNode = nodes.get(1);

            //创建一棵新的二叉树，它的根结点没有data，只有权值
            Node parent = new Node(null,leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;

            //将已经处理好的两颗二叉树从nodes删除
            nodes.remove(leftNode);
            nodes.remove(rightNode);

            //新的二叉树，加入nodes
            nodes.add(parent);

        }

        //nodes最后的结点就是哈夫曼树的根结点
        return nodes.get(0);
    }
}


//创建Node，带有数据和权值
class Node implements Comparable<Node> {

    Byte data;//存放数据本身
    int weight;//权值，表现字符出现的次数
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        //从小到大排序
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}
