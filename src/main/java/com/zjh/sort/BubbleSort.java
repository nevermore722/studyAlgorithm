package com.zjh.sort;

import java.util.Arrays;

/**
 * @author ：ZouJiaHui
 * @date ：Created in 2020/2/1 11:23
 * @description：
 * @modified By：
 * @version: 1.0
 */
public class BubbleSort {

  public static void main(String[] args) {
    int arr[] = {3, 9, -1, 10, -2};

    //为了容易理解，我们把冒泡排序的演变过程，给大家展示

    //第一趟排序，就是将最大的数排在最后
    //临时变量
    int temp = 0;
    for (int i = 0; i < arr.length - 1; i++) {
      for (int j = 0; j < arr.length - 1 - i; j++) {
        //如果前面的数比后面的数大，则交换
        if (arr[j] > arr[j + 1]) {
          temp = arr[j];
          arr[j] = arr[j + 1];
          arr[j + 1] = temp;
        }
      }
      System.out.println("第" + (i + 1) + "趟排序后的数组");
      System.out.println(Arrays.toString(arr));
    }

/*
    //第二趟排序，就是将第二大的数排在倒数第二位
    for (int j = 0; j < arr.length - 1 - 1; j++) {
      //如果前面的数比后面的数大，则交换
      if (arr[j] > arr[j + 1]) {
        temp = arr[j];
        arr[j] = arr[j + 1];
        arr[j + 1] = temp;
      }
    }
    System.out.println("第二趟排序后的数组");
    System.out.println(Arrays.toString(arr));

    //第三趟排序，就是将第三大的数排在倒数第三位
    for (int j = 0; j < arr.length - 1 - 2; j++) {
      //如果前面的数比后面的数大，则交换
      if (arr[j] > arr[j + 1]) {
        temp = arr[j];
        arr[j] = arr[j + 1];
        arr[j + 1] = temp;
      }
    }
    System.out.println("第三趟排序后的数组");
    System.out.println(Arrays.toString(arr));

    //第四趟排序，就是将第四大的数排在倒数第四位
    for (int j = 0; j < arr.length - 1 - 3; j++) {
      //如果前面的数比后面的数大，则交换
      if (arr[j] > arr[j + 1]) {
        temp = arr[j];
        arr[j] = arr[j + 1];
        arr[j + 1] = temp;
      }
    }
    System.out.println("第四趟排序后的数组");
    System.out.println(Arrays.toString(arr));*/
  }

}
