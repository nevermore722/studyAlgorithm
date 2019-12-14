package com.zjh.linkedlist;

import java.util.Stack;

/**
 * @author ：ZouJiaHui
 * @date ：Created in 2019/12/14 16:06
 * @description：
 * @modified By：
 * @version: 1.0
 */
//演示栈的基本使用
public class TestStack {

  public static void main(String[] args) {
    Stack<String> stack = new Stack();
    //入栈
    stack.add("jack");
    stack.add("tom");
    stack.add("smith");

    //取出
    //smith,tom,jack
    while (stack.size()>0){
      //pop就是将栈顶的数据取出
      System.out.println(stack.pop());
    }
  }

}
