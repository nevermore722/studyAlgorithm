package com.zjh.hashtab;

import lombok.AllArgsConstructor;

/**
 * @author ：ZouJiaHui
 * @date ：Created in 2020/2/20 9:50
 * @description：
 * @modified By：
 * @version: 1.0
 */
public class HashTabDemo {

  public static void main(String[] args) {

  }
}

//创建HashTab 管理多条链表
class HashTab {

  private EmpLinkedList[] empLinkedListArray;
  //表示有多少条链表
  private int size;

  //构造器
  public HashTab(int size) {
    this.size = size;
    //初始化empLinkedListArray
    empLinkedListArray = new EmpLinkedList[size];

  }

  //添加雇员
  public void add(Emp emp) {
    //根据员工的id，得到该员工应当添加到哪条链表
    int empLinkedListNO = hashFun(emp.id);
    //将emp 添加到对应的链表中
    empLinkedListArray[empLinkedListNO].add(emp);
  }

  //遍历所有的链表,遍历hashtab
  public void list() {
    for (int i = 0; i < size; i++) {
      empLinkedListArray[i].list();
    }

  }

  //编写散列函数，使用一个简单取模法
  public int hashFun(int id) {
    return id % size;
  }

}

//表示一个雇员
@AllArgsConstructor
class Emp {

  public int id;
  public String name;
  //next默认为null
  public Emp next;
}

//创建EmpLinkedList,表示链表
class EmpLinkedList {

  //头指针，指向第一个Emp，因此我们这个链表的head是直接指向第一个Emp
  //默认null
  private Emp head;

  //添加雇员到链表
  //说明
  //1.假定，当添加雇员时，id 是自增长，即id的分配总是从小到大
  //因此我们将该雇员直接加入到本链表的最后即可
  public void add(Emp emp) {
    //如果是添加第一个雇员
    if (head == null) {
      head = emp;
      return;
    }
    //如果不是第一个雇员，则使用一个辅助的指针，帮助定位到最后
    Emp curEmp = head;
    while (true) {
      if (curEmp.next == null) {
        //说明到链表最后
        break;
      }
      //后移
      curEmp = curEmp.next;
    }
    //退出时直接将emp加入链表
    curEmp.next = emp;
  }

  //遍历链表的雇员信息
  public void list() {
    if (head == null) {
      //说明链表为空
      System.out.println("当前链表为空");
      return;
    }
    System.out.println("当前链表的信息为");
    //辅助指针
    Emp curEmp = head;
    while (true) {
      System.out.printf("=> id = %d name=%s\t", curEmp.id, curEmp.name);
      if (curEmp.next == null) {
        //说明curEmp已经是最后节点
        break;
      }
      //后移，遍历
      curEmp = curEmp.next;
    }
    System.out.println();
  }
}