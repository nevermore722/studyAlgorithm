package com.zjh.stack;

/**
 * @author ：ZouJiaHui
 * @date ：Created in 2019/12/29 18:06
 * @description：
 * @modified By：
 * @version: 1.0
 */
public class Calculator {

  public static void main(String[] args) {
    //根据前面老师的思路，完成表达式的运算
    String expression = "700+2*6-4";
    //创建两个栈，数栈，一个符号栈
    ArrayStack2 numStack = new ArrayStack2(10);
    ArrayStack2 operStack = new ArrayStack2(10);
    //定义需要的相关变量
    //用于扫描
    int index = 0;
    int num1 = 0;
    int num2 = 0;
    int oper = 0;
    int res = 0;
    //将每次扫描得到的char保存到ch
    char ch = ' ';
    //用于拼接多位数
    String keepNum = "";
    //开始while循环的扫描expression
    while (true) {
      //以此得到expression的每一个字符
      ch = expression.substring(index, index + 1).charAt(0);
      //判断ch是什么，然后做相应的处理
      //如果是运算符
      if (operStack.isOper(ch)) {
        //判断当前的符号栈是否为空
        if (!operStack.isEmpty()) {
          //如果符号栈有操作符，就进行比较，如果当前的操作符的优先级小于或等于栈种的操作符
          //就需要从数栈中pop出两个数，再从符号栈中pop出一个符号，进行运算，将得到结果，入数栈，然后将当前的操作符入符号栈
          if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            //把运算的结果加数栈
            numStack.push(res);
            //然后将当前的操作符号入符号栈
            operStack.push(ch);
          } else {
            //如果当前操作符的优先级大于栈中的操作符，就直接入符号栈
            operStack.push(ch);
          }
        } else {
          //如果为空直接入栈。。
          //1+3
          operStack.push(ch);
        }
      } else {
        //如果是数，则直接入数栈
        //?"1+3" '1'->1 ACSCII码
        //numStack.push(ch - 48);
        //分析思路
        //1.当处理多位数时，不能发现是一个数就立即入栈，因为它可能是多位数
        //2.在处理数时，需要向expression的表达式的index后再看一位，如果是数就进行
        //扫描，如果是符号才出栈
        //3.因此我们需要定义一个变量字符串，用于拼接

        //处理多位数
        keepNum += ch;

        //如果ch已经是expression的最后一位，就直接入栈
        if (index == expression.length() - 1) {
          numStack.push(Integer.parseInt(keepNum));

        } else {
          //判断下一个字符串是不是数字，如果是数字，就继续扫描，如果是运算符，则入栈
          //注意是看后一位，不是index++
          if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
            //如果后一位是运算符，则入栈,keepNum = "1"或者"123"
            numStack.push(Integer.parseInt(keepNum));
            //重要的!!!!!!,keepNum清空
            keepNum = "";
          }
        }

      }
      //让index + 1,并判断是否扫描到expression最后，
      index++;
      if (index >= expression.length()) {
        break;
      }
    }
    //当表达式扫描完毕，就顺序的从数栈和符号栈中pop出相应的数和符号，并运行
    while (true) {
      //如果符号栈为空，则计算到最后的结果，数栈中只有一个数字【结果】
      if (operStack.isEmpty()) {
        break;
      }
      num1 = numStack.pop();
      num2 = numStack.pop();
      oper = operStack.pop();
      res = numStack.cal(num1, num2, oper);
      //入栈
      numStack.push(res);
    }
    int res2 = numStack.pop();
    System.out.printf("表达式%s = %d", expression, res2);
  }
}

//先创建一个栈，直接使用前面创建好
//定义一个ArrayStack2表示栈，需要扩展功能
class ArrayStack2 {

  //栈的大小
  private int maxSize;

  //数组，数组模拟栈，数据就放在该数组
  private int[] stack;

  //top表示栈顶，初始化为-1
  private int top = -1;

  public ArrayStack2(int maxSize) {
    this.maxSize = maxSize;
    stack = new int[this.maxSize];
  }

  //增加一个方法，可以返回当前栈顶的值，但是不是真正的pop
  public int peek() {
    return stack[top];
  }

  //栈满
  public boolean isFull() {
    return top == maxSize - 1;
  }

  //栈空
  public boolean isEmpty() {
    return top == -1;
  }

  //入栈-push
  public void push(int value) {
    //先判断栈是否满
    if (isFull()) {
      System.out.println("栈满");
      return;
    }
    top++;
    stack[top] = value;
  }

  //出栈-pop将栈顶的数据返回
  public int pop() {
    //先判断栈是否空
    if (isEmpty()) {
      //抛出异常
      throw new RuntimeException("栈空，没有数据~");
    }
    int value = stack[top];
    top--;
    return value;
  }

  //显示栈的情况[遍历栈]，遍历时，需要从栈顶开始显示数据
  public void list() {
    if (isEmpty()) {
      System.out.println("栈空，没有数据~~~");
      return;
    }
    for (int i = top; i >= 0; i--) {
      System.out.printf("stack[%d]=%d\n", i, stack[i]);
    }
  }

  //返回运算符的优先级，优先级是程序员来确定，优先级使用数字表示
  //数字越大，则优先级就越高。
  public int priority(int oper) {
    if (oper == '*' || oper == '/') {
      return 1;
    } else if (oper == '+' || oper == '-') {
      return 0;
    } else {
      //假定目前的表达式只有+，-，*，/
      return -1;
    }
  }

  //判断是不是一个运算符
  public boolean isOper(char var) {
    return var == '+' || var == '-' || var == '*' || var == '/';
  }

  //计算方法
  public int cal(int num1, int num2, int oper) {
    //res用于存放计算的结果
    int res = 0;
    switch (oper) {
      case '+':
        res = num1 + num2;
        break;
      case '-':
        //注意顺序
        res = num2 - num1;
        break;
      case '*':
        res = num2 * num1;
        break;
      case '/':
        res = num2 / num1;
        break;
    }
    return res;
  }
}