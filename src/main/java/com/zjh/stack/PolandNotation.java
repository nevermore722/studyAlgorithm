package com.zjh.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author ：ZouJiaHui
 * @date ：Created in 2020/1/4 17:43
 * @description：
 * @modified By：
 * @version: 1.0
 */
public class PolandNotation {

  public static void main(String[] args) {

    //完成将一个中缀表达式转成后缀表达式的功能
    //说明
    //1. 1+（（2+3）*4）-5 => 转成 1 2 3 + 4 * + 5 -
    //2. 因为直接对str 进行操作，不方便，因此先将"1+（（2+3）*4）-5"=>中缀的表达式对应的List
    //  即"1+((2+3)*4)-5" => ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]

    String expression = "1+((2+3)*4)-5";
    List<String> infixExpressionList = toInfixExpressionList(expression);
    //ArrayList[1,+,(,(,2,+,3,),*,4,),-,5]
    System.out.println(infixExpressionList);

/*    //先定义一个逆波兰表达式
    //(3+4)*5-6  => 3 4 + 5 * 6 -     =29
    //(30+4)*5-6  => 30 4 + 5 * 6 -   =164
    //4 * 5 - 8 + 60 + 8 / 2 => 4 5 * 8 - 60 + 8 2 / + =76
    //说明：为了方便，逆波兰表达式的数字和符号使用空格隔开
//    String suffixExpression = "30 4 + 5 * 6 -";
    String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";//76
    //思路
    //1.先将 "3 4 + 5 * 6 -" => 放入到ArrayList中
    //2.将 ArrayList 传递给一个方法，遍历 ArrayList 配合栈 完成计算
    List<String> rpnList = getListString(suffixExpression);
    System.out.println("rpnList=" + rpnList);
    int res = calculate(rpnList);
    System.out.println("计算的结果是=" + res);*/
  }

  /**
   * 方法：将中缀表达式转成对应的List
   */
  public static List<String> toInfixExpressionList(String s) {
    //定义一个List,存放中缀表达式对应的内容
    List<String> ls = new ArrayList<String>();
    //这是一个指针，用于遍历中缀表达式字符串
    int i = 0;
    //对多位数的拼接
    String str;
    //每遍历到一个字符串，就放入到c
    char c;
    do {
      //如果c是一个非数字，我需要加入到ls
      if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
        ls.add("" + c);
        //i需要后移
        i++;
      } else { //如果是一个数，需要考虑多位数
        //先将str置成""   '0'[48]->'9'[57]
        str = "";
        while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
          //拼接
          str += c;
          i++;
        }
        ls.add(str);
      }
    } while (i < s.length());
    return ls;
  }

  /**
   * 将一个逆波兰表达式，一次将数据和运算符放入到ArrayList中
   */
  public static List<String> getListString(String suffixExpression) {
    //将suffixExpression分割
    String[] split = suffixExpression.split(" ");
    List<String> list = new ArrayList<String>();

    for (String ele : split) {
      list.add(ele);
    }
    return list;
  }

  //完成对逆波兰表达式的运算

  /**
   * 1）从左至右扫描，将3和4压入堆栈； 2）遇到+运算符，因此弹出4和3（4为栈顶元素，3为次顶元素），计算出3+4的值，得7.再将7入栈； 3）将5入栈；
   * 4）接下来是*运算符，因此弹出5和7，计算出7*5=35，将35入栈； 5）将6入栈； 6）最后是-运算符，计算出35-6的值，即29，由此得出最终结果
   */
  public static int calculate(List<String> ls) {
    //创建一个栈，只需要一个栈即可
    Stack<String> stack = new Stack<String>();
    //遍历ls
    for (String item : ls) {
      //这里使用正则表达式来取出数
      //匹配的是多位数
      if (item.matches("\\d+")) {
        //入栈
        stack.push(item);
      } else {
        //pop出两个数，并运算，再入栈
        int num2 = Integer.parseInt(stack.pop());
        int num1 = Integer.parseInt(stack.pop());
        int res = 0;
        if (item.equals("+")) {
          res = num1 + num2;
        } else if (item.equals("-")) {
          res = num1 - num2;
        } else if (item.equals("*")) {
          res = num1 * num2;
        } else if (item.equals("/")) {
          res = num1 / num2;
        } else {
          throw new RuntimeException("运算符有误");
        }
        //把res 入栈
        stack.push(String.valueOf(res));
      }
    }
    //最后留在stack中的数据是运算结果
    return Integer.parseInt(stack.pop());
  }

}
