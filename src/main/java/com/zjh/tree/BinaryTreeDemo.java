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
    //创建需要的结点
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

/*    //前序遍历查找
    //前序遍历的次数：4
    System.out.println("前序遍历方式~~");
    HeroNode resNode = binaryTree.preOrderSearch(5);
    if (resNode != null) {
      System.out.printf("找到了，信息为no=%d name=%s", resNode.getNo(), resNode.getName());
    } else {
      System.out.printf("没有找到 no=%d 的英雄", 5);
    }*/

/*    //中序遍历查找
    //中序遍历3次
    System.out.println("中序遍历方式~~~");
    HeroNode resNode = binaryTree.infixOrderSearch(5);
    if (resNode != null) {
      System.out.printf("找到了，信息为no=%d name=%s", resNode.getNo(), resNode.getName());
    } else {
      System.out.printf("没有找到 no=%d 的英雄", 5);
    }*/

   /* //后序遍历
    //后序遍历2次
    System.out.println("后序遍历方式~~~");
    HeroNode resNode = binaryTree.postOrderSearch(5);
    if (resNode != null) {
      System.out.printf("找到了，信息为no=%d name=%s", resNode.getNo(), resNode.getName());
    } else {
      System.out.printf("没有找到 no=%d 的英雄", 5);
    }*/

   //测试一把删除结点
    System.out.println("删除前，前序遍历");
    //1，2，3，5，4
    binaryTree.preOrder();
    binaryTree.delNode(3);
    System.out.println("删除后，前序遍历");
    //1,2
    binaryTree.preOrder();
  }

}

//定义BinaryTree二叉树
class BinaryTree {

  private HeroNode root;

  public void setRoot(HeroNode root) {
    this.root = root;
  }

  //删除结点
  public void delNode(int no) {
    if (root != null) {
      //如果只有一个root结点，这里立即判断root是不是就是要删除结点
      if (root.getNo() == no) {
        root = null;
      } else {
        //递归删除
        root.delNode(no);
      }
    } else {
      System.out.println("空树，不能删除~");
    }
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

  //前序遍历
  public HeroNode preOrderSearch(int no) {
    if (root != null) {
      return root.preOrderSearch(no);
    } else {
      return null;
    }
  }

  //中序遍历
  public HeroNode infixOrderSearch(int no) {
    if (root != null) {
      return root.infixOrderSearch(no);
    } else {
      return null;
    }
  }

  //后序遍历
  public HeroNode postOrderSearch(int no) {
    if (root != null) {
      return this.root.postOrderSearch(no);
    } else {
      return null;
    }
  }
}

//先创建HeroNode 结点
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

  //递归删除结点
  //1.如果删除的结点是叶子结点，则删除该结点
  //2.如果删除的结点是非叶子结点，则删除该子树
  public void delNode(int no) {
    //2.如果当前结点的左子结点不为空，并且左子结点就是要删除结点，就将this.left = null;并且就返回（结束递归删除）
    if (this.left != null && this.left.no == no) {
      this.left = null;
      return;
    }
    //3.如果当前结点的右子结点不为空，并且右子结点就是要删除结点，就将this.right = null;并且就返回(结束递归删除)
    if (this.right != null && this.right.no == no) {
      this.right = null;
      return;
    }
    //4.我们就需要向左子树进行递归删除
    if (this.left != null) {
      this.left.delNode(no);
    }
    //5.则应当向右子树进行递归删除
    if (this.right != null) {
      this.right.delNode(no);
    }
  }


  //编写前序遍历的方法
  public void preOrder() {
    //先输出父结点
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
    //输出父结点
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

  //前序遍历查找

  /**
   * @param no 查找no
   * @return 如果找到就返回该Node，如果没有找到返回null
   */
  public HeroNode preOrderSearch(int no) {
    System.out.println("进入前序遍历~~");
    //比较当前结点是不是
    if (this.no == no) {
      return this;
    }
    //1.则判断当前结点的左子结点是否为空，如果不为空，则递归前序查找
    //2.如果左递归前序查找，找到结点，则返回
    HeroNode resNode = null;
    if (this.left != null) {
      resNode = this.left.preOrderSearch(no);
    }
    if (resNode != null) {
      //说明我们左子树找到
      return resNode;
    }
    //1.左递归前序查找，找到结点，则返回，否则继续判断，
    //2.当前结点的右子结点是否为空，如果不空,则继续向右递归前序查找
    if (this.right != null) {
      resNode = this.right.preOrderSearch(no);
    }
    return resNode;
  }

  //中序遍历查找
  public HeroNode infixOrderSearch(int no) {
    //判断当前结点的左子结点是否为空，如果不为空，则递归中序查找
    HeroNode resNode = null;
    if (this.left != null) {
      resNode = this.left.infixOrderSearch(no);
    }
    if (resNode != null) {
      return resNode;
    }
    System.out.println("进入中序查找");
    //如果找到，则返回，如果没有找到，就和当前结点比较，如果是则返回当前结点
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
    //判断当前结点的左子结点是否为空，如果不为空，则递归后序查找
    HeroNode resNode = null;
    if (this.left != null) {
      resNode = this.left.postOrderSearch(no);
    }
    if (resNode != null) {
      //说明在左子树找到
      return resNode;
    }

    //如果左子树没有找到，则向右子树递归进行后序遍历查找
    if (this.right != null) {
      resNode = this.right.postOrderSearch(no);
    }
    if (resNode != null) {
      return resNode;
    }
    System.out.println("进入后序查找");
    //如果左右子树都没有找到，就比较当前结点是不是
    if (this.no == no) {
      return this;
    }
    return resNode;
  }
}
