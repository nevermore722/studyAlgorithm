package com.zjh.huffmancode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：ZouJiaHui
 * @date ：Created in 2020/4/8 18:11
 * @description：
 * @modified By：
 * @version: 1.0
 */
public class HuffmanCode {

  public static void main(String[] args) {
    String content = "i like like like java do you like a java";
    byte[] contentBytes = content.getBytes();
    //40
    System.out.println(contentBytes.length);

    List<Node> nodes = getNodes(contentBytes);
    System.out.println("nodes=" + nodes);

    //测试一把，创建的二叉树
    System.out.println("赫夫曼树");
    Node huffmanTreeRoot = createHuffmanTree(nodes);
    System.out.println("前序遍历");
    huffmanTreeRoot.preOrder();

    //测试一把是否生成了对应的赫夫曼编码
    Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
    System.out.println("~生成的赫夫曼编码表" + huffmanCodes);
  }

  //前序遍历的方法
  private static void preOrder(Node root) {
    if (root != null) {
      root.preOrder();
    } else {
      System.out.println("赫夫曼树为空");
    }
  }

  //生成赫夫曼树对应的赫夫曼编码
  //思路：
  //1.将赫夫曼编码表存放在Map<Byte,String>
  static Map<Byte, String> huffmanCodes = new HashMap();
  //2.在生成赫夫曼编码表时，需要拼接路径，定义一个StringBuilder存储某个叶子节点的路径
  static StringBuilder stringBuilder = new StringBuilder();

  //为了调用方便，我们重载getCodes
  private static Map<Byte, String> getCodes(Node root) {
    if (root == null) {
      return null;
    }
    //出来root的左子树
    getCodes(root.left, "0", stringBuilder);
    //处理root的右子树
    getCodes(root.right, "1", stringBuilder);
    return huffmanCodes;
  }

  /**
   * 功能：将传入的node结点的所有叶子结点的赫夫曼编码得到，并放入到huffmanCodes集合
   *
   * @param node 传入结点
   * @param code 路径：左子节点是0，右子结点是1
   * @param stringBuilder 用于拼接路径
   */
  private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
    StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
    //将code加入到stringBuilder2
    stringBuilder2.append(code);
    if (node != null) {
      //如果node==null不处理
      //判断当前node是叶子结点还是非叶子结点
      if (node.data == null) {
        //非叶子结点
        //递归处理
        //向左
        getCodes(node.left, "0", stringBuilder2);
        //向右递归
        getCodes(node.right, "1", stringBuilder2);
      } else {
        //说明是一个叶子结点
        //就表示找到某个叶子结点的最后
        huffmanCodes.put(node.data, stringBuilder2.toString());
      }
    }

  }

  /**
   * @param bytes 接收字符数组
   * @return 返回的就是List形式
   */
  private static List<Node> getNodes(byte[] bytes) {
    //1.创建一个ArrayList
    ArrayList<Node> nodes = new ArrayList<>();

    //遍历bytes，统计 每一个byte出现的次数->map[key,value]
    HashMap<Byte, Integer> counts = new HashMap<>();
    for (byte b : bytes) {
      Integer count = counts.get(b);
      if (count == null) {
        //Map还没有这个字符数据，第一次
        counts.put(b, 1);
      } else {
        counts.put(b, count + 1);
      }
    }
    //把每个键值对转成一个Node对象，并加入到nodes集合
    //遍历map
    for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
      nodes.add(new Node(entry.getKey(), entry.getValue()));
    }
    return nodes;
  }

  //可以通过List创建对应的赫夫曼树
  private static Node createHuffmanTree(List<Node> nodes) {
    while (nodes.size() > 1) {
      //排序，从小到大
      Collections.sort(nodes);
      //取出第一颗最小的二叉树
      Node leftNode = nodes.get(0);
      //取出第二颗最小的二叉树
      Node rightNode = nodes.get(1);

      //创建一颗新的二叉树，它的根结点 没有data，只有权值
      Node parent = new Node(null, leftNode.weight + rightNode.weight);
      parent.left = leftNode;
      parent.right = rightNode;

      //将已处理的两颗二叉树从nodes删除
      nodes.remove(leftNode);
      nodes.remove(rightNode);
      //将新的二叉树，加入到nodes
      nodes.add(parent);

    }
    //nodes 最后的结点，就是赫夫曼树的根结点
    return nodes.get(0);
  }
}


//创建Node，带数据和权值
class Node implements Comparable<Node> {

  //存放数据（字符）本身，比如'a'=>97 ' '=>32
  Byte data;
  //权值，表示字符出现的次数
  int weight;

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