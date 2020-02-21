package com.zjh.tree;

import lombok.Data;
import lombok.ToString;

/**
 * @author ：ZouJiaHui
 * @date ：Created in 2020/2/21 17:46
 * @description：
 * @modified By：
 * @version: 1.0
 */
public class BinaryTreeDemo {

  public static void main(String[] args) {
    //先需要创建一颗二叉树
    BinaryTree binaryTree = new BinaryTree();
    //创建需要的节点
    HeroNode root = new HeroNode(1, "宋江");
    HeroNode node2 = new HeroNode(2, "吴用");
    HeroNode node3 = new HeroNode(3, "卢俊义");
    HeroNode node4 = new HeroNode(4, "林冲");
    //新加的
    HeroNode node5 = new HeroNode(5, "关胜");

    //说明，我们先手动创建该二叉树，后面我们学习递归的方式创建二叉树
    root.setLeft(node2);
    root.setRight(node3);
    node3.setRight(node4);
    //新加的
    node3.setLeft(node5);
    binaryTree.setRoot(root);

    //测试
    //1,2,3,4  新加1，2，3，5，4
    System.out.println("前序遍历");
    binaryTree.preOrder();

    //测试
    System.out.println("中序遍历");
    //2,1,3,4  新加2，1，5，3，4
    binaryTree.infixOrder();

    //测试
    System.out.println("后续遍历");
    //2,4,3,1  新加2，5，4，3，1
    binaryTree.postOrder();

  }

}

//定义BinaryTree二叉树
class BinaryTree {

  private HeroNode root;

  public void setRoot(HeroNode root) {
    this.root = root;
  }

  //前序遍历
  public void preOrder() {
    if (this.root != null) {
      this.root.preOrder();
    } else {
      System.out.println("二叉树为空，无法遍历");
    }
  }

  //中序遍历
  public void infixOrder() {
    if (this.root != null) {
      this.root.infixOrder();
    } else {
      System.out.println("二叉树为空，无法遍历");
    }
  }

  //后序遍历
  public void postOrder() {
    if (this.root != null) {
      this.root.postOrder();
    } else {
      System.out.println("二叉树为空，无法遍历");
    }
  }
}

//先创建HeroNode 节点
@Data
class HeroNode {

  private int no;
  private String name;
  //默认null
  private HeroNode left;
  //默认null
  private HeroNode right;

  public HeroNode(int no, String name) {
    this.no = no;
    this.name = name;
  }

  //编写前序遍历的方法
  public void preOrder() {
    //先输出父节点
    System.out.println(this);
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

  //后续遍历
  public void postOrder() {
    if (this.left != null) {
      this.left.postOrder();
    }
    if (this.right != null) {
      this.right.postOrder();
    }
    System.out.println(this);
  }

  @Override
  public String toString() {
    return "HeroNode{" +
        "no=" + no +
        ", name='" + name + '\'' +
        '}';
  }
}
