package com.zjh.linkedlist;


/**
 * @author ：ZouJiaHui
 * @date ：Created in 2019/12/1 20:52
 * @description：
 * @modified By：
 * @version: 1.0
 */
public class SingleLinkedListDemo {

  public static void main(String[] args) {
    //进行测试
    //先创建节点
    HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
    HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
    HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
    HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

    //创建一个链表
    SingleLinkedList singleLinkedList = new SingleLinkedList();
/*    //加入
    singleLinkedList.add(hero1);
    singleLinkedList.add(hero4);
    singleLinkedList.add(hero2);
    singleLinkedList.add(hero3);*/

    //加入按照编号的顺序
    singleLinkedList.addByOrder(hero1);
    singleLinkedList.addByOrder(hero4);
    singleLinkedList.addByOrder(hero2);
    singleLinkedList.addByOrder(hero3);

    //显示一把
    singleLinkedList.list();
  }
}

//定义一个SingleLinkedList 管理我们的英雄
class SingleLinkedList {

  //先初始化一个头节点，头节点不要动,不存放具体的数据
  private HeroNode head = new HeroNode(0, "", "");

  //添加节点到单向链表
  //思路，当不考虑编号顺序时
  //1、找到当前链表的最后节点
  //2、将最后这个节点的next指向新的节点
  public void add(HeroNode heroNode) {
    //因为head节点不能动，因此我们需要一个辅助遍历temp
    HeroNode temp = head;
    //遍历链表，找到最后
    while (true) {
      //找到链表的最后
      if (temp.next == null) {
        break;
      }
      //如果没有找到最后，就将temp后移
      temp = temp.next;
    }
    //当退出while循环时，temp就指向了链表的最后
    //将最后的这个节点的next指向新的节点
    temp.next = heroNode;
  }

  //第二种方式在添加英雄时，根据排名将英雄插入到指定位置
  //（如果有这个排名，则添加失败，并给出提示）
  public void addByOrder(HeroNode heroNode) {
    //因为头节点不能动，因此我们仍然通过一个辅助指针（变量）来帮助找到添加的位置
    //因为单链表，因为我们找的temp是位于添加位置的前一个节点，否则插入不了
    HeroNode temp = head;
    // flag标识添加的编号是否存在，默认为false
    boolean flag = false;
    while (true) {
      //说明temp已经在链表的最后
      if (temp.next == null) {
        break;
      }
      //位置找到，就在temp的后面插入
      if (temp.next.no > heroNode.no) {
        break;
      } else if (temp.next.no == heroNode.no) {//说明希望添加的heroNode的编号已然存在
        //说明编号存在
        flag = true;
        break;
      }
      //后移，遍历当前链表
      temp = temp.next;
    }
    //判断flag的值
    if (flag) { //不能添加，说明编号存在
      System.out.printf("准备插入的英雄的编号 %d 已经存在，不能加入\n", heroNode.no);
    } else {
      // 插入到链表中，temp的后面
      heroNode.next = temp.next;
      temp.next = heroNode;
    }
  }

  //显示链表【遍历】
  public void list() {
    //判断链表是否为空
    if (head.next == null) {
      System.out.println("链表为空");
      return;
    }
    //因为头节点，不能动，因此我们需要一个辅助变量来遍历
    HeroNode temp = head.next;
    while (true) {
      //判断是否到链表最后
      if (temp == null) {
        break;
      }
      //输出节点的信息
      System.out.println(temp);
      //将temp后移，一定小心
      temp = temp.next;
    }
  }
}

//定义HerNode,每个HeroNode对象就是一个节点
class HeroNode {

  public int no;
  public String name;
  public String nickName;
  //指向下一个节点
  public HeroNode next;

  //构造器
  public HeroNode(int hNo, String hName, String hNickname) {
    this.no = hNo;
    this.name = hName;
    this.nickName = hNickname;
  }

  //为了显示方便，我们重写toString
  //next不打印
  @Override
  public String toString() {
    return "HeroNode{" +
        "no=" + no +
        ", name='" + name + '\'' +
        ", nickName='" + nickName + '\''
//                +
//                ", next=" + next +
//                '}'
        ;
  }
}