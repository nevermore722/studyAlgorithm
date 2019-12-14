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

    //加入
    singleLinkedList.add(hero1);
    singleLinkedList.add(hero4);
    singleLinkedList.add(hero2);
    singleLinkedList.add(hero3);

    //测试一下单链表的反转功能
    System.out.println("原来链表的情况~~");
    singleLinkedList.list();

    System.out.println("反转单链表~~");
    reversetList(singleLinkedList.getHead());
    singleLinkedList.list();


/*    //加入按照编号的顺序
    singleLinkedList.addByOrder(hero1);
    singleLinkedList.addByOrder(hero4);
    singleLinkedList.addByOrder(hero2);
    singleLinkedList.addByOrder(hero3);

    //显示一把
    singleLinkedList.list();

    //测试修改节点的代码
    HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟~~");
    singleLinkedList.update(newHeroNode);

    System.out.println("修改后的链表情况~~");
    singleLinkedList.list();

    //删除一个节点
    singleLinkedList.del(1);
    singleLinkedList.del(4);
    System.out.println("删除后的链表情况~~");
    singleLinkedList.list();

    //测试一下 求单链表中有效节点的个数
    System.out.println("有效的节点个数=" + getLength(singleLinkedList.getHead()));

    //测试一下看看是否得到了倒数第k个节点
    HeroNode res = findLastIndexNode(singleLinkedList.getHead(), 3);
    System.out.println("res=" + res);
*/
  }

  //将单链表反转
  public static void reversetList(HeroNode head) {
    //如果当前链表为空，或者只有一个节点，无需反转，直接返回
    if (head.next == null || head.next.next == null) {
      return;
    }
    //定义一个辅助的指针（变量），帮助我们遍历原来的链表
    HeroNode cur = head.next;
    // 指向当前节点[cur]的下一个节点
    HeroNode next = null;
    HeroNode reverseHead = new HeroNode(0, "", "");
    //遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表reverseHead的最前端
    while (cur != null) {
      //先暂时保存当前节点的下一个节点，因为后面需要使用
      next = cur.next;
      //将cur的下一个节点指向新的链表的最前端
      cur.next = reverseHead.next;
      //将cur 连接到新的链表上
      reverseHead.next = cur;
      //让cur后移
      cur = next;
    }
    //将head.next指向reverseHead.next
    head.next = reverseHead.next;
  }

  //查找单链表中的倒数第k个节点
  //思路
  //1.编写一个方法，接收head节点，同时接收一个index
  //2.index 表示是倒数第index个节点
  //3.先把链表从头到尾遍历，得到链表的总的长度 getLength
  //4.得到size后，我们从链表的第一个开始遍历（size-index）个，就可以得到
  //5.如果找到了，则返回该节点，否则返回null
  public static HeroNode findLastIndexNode(HeroNode head, int indxe) {
    //判断如果链表为空，返回null
    if (head.next == null) {
      //没有找到
      return null;
    }
    //第一次遍历得到链表的长度（节点个数）
    int size = getLength(head);
    //第二次遍历 size-index 位置，就是我们倒数的第k个节点
    //先做一个index的校验
    if (indxe <= 0 || indxe > size) {
      return null;
    }
    //定义一个辅助变量,for 循环定位到倒数的index
    //3  3-1 = 2
    HeroNode cur = head.next;
    for (int i = 0; i < size - indxe; i++) {
      cur = cur.next;
    }
    return cur;
  }

  //方法：获取到单链表的节点的个数（如果是带头节点的链表，需要不统计头节点）

  /**
   * @param head 链表的头节点
   * @return 返回的就是有效节点的个数
   */
  public static int getLength(HeroNode head) {
    //空链表
    if (head.next == null) {
      return 0;
    }
    int length = 0;
    //定义一个辅助变量,这里我们没有统计头节点
    HeroNode cur = head.next;
    while (cur != null) {
      length++;
      //遍历
      cur = cur.next;
    }
    return length;
  }
}

//定义一个SingleLinkedList 管理我们的英雄
class SingleLinkedList {

  //先初始化一个头节点，头节点不要动,不存放具体的数据
  private HeroNode head = new HeroNode(0, "", "");

  //返回头节点
  public HeroNode getHead() {
    return head;
  }

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

  //修改节点的信息，根据no编号来修改，即no编号不能改
  //说明
  //1.根据newHeroNode的no来修改即可
  public void update(HeroNode newHeroNode) {
    //判断是否空
    if (head.next == null) {
      System.out.println("链表为空~");
      return;
    }
    //找到需要修改的节点。根据no编号
    //定义一个辅助变量
    HeroNode temp = head.next;
    boolean flag = false;
    while (true) {
      if (temp == null) {
        //已经遍历完链表
        break;
      }
      if (temp.no == newHeroNode.no) {
        //z找到
        flag = true;
        break;
      }
      temp = temp.next;
    }
    //根据flag判断是否找到要修改的节点
    if (flag) {
      temp.name = newHeroNode.name;
      temp.nickName = newHeroNode.nickName;
    } else {
      //没有找到
      System.out.printf("没有找到编号%d的节点,不能修改\n", newHeroNode.no);
    }
  }

  //删除思路
  //思路
  //1.head不能动，因此我们需要一个temp辅助节点找到待删除节点的前一个节点
  //2.说明我们在比较时，是temp，next.no和需要删除的节点的no比较
  public void del(int no) {
    HeroNode temp = head;
    // 标志是否找到待删除节点的
    boolean flag = false;
    while (true) {
      //已经到链表的最后
      if (temp.next == null) {
        break;
      }
      if (temp.next.no == no) {
        //找到的待删除节点的前一个节点temp
        flag = true;
        break;
      }
      //temp后移，遍历
      temp = temp.next;
    }
    //判断flag
    //找到
    if (flag) {
      //可以删除
      temp.next = temp.next.next;
    } else {
      System.out.printf("要删除的%d 节点不存在\n", no);
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