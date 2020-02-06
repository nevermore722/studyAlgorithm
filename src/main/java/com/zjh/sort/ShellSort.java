package com.zjh.sort;

import java.util.Arrays;

/**
 * @author ：ZouJiaHui
 * @date ：Created in 2020/2/6 16:43
 * @description：
 * @modified By：
 * @version: 1.0
 */
public class ShellSort {

  public static void main(String[] args) {
    int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
    shellSort(arr);
  }

  //使用逐步推导的方式来编写希尔排序
  //希尔排序时，对有序序列在插入时采用交换法，
  //思路（算法）===>代码
  public static void shellSort(int[] arr) {
    int temp = 0;
    int count = 0;
    //根据前面的逐步分析，使用循环处理
    for (int gap = arr.length / 2; gap > 0; gap /= 2) {
      for (int i = gap; i < arr.length; i++) {
        //遍历各组中所有的元素(共有gap组)，步长gap
        for (int j = i - gap; j >= 0; j -= gap) {
          //如果当前元素大于加上步长后的那个元素，说明交换
          if (arr[j] > arr[j + gap]) {
            temp = arr[j];
            arr[j] = arr[j + gap];
            arr[j + gap] = temp;
          }
        }
      }
      System.out.println("希尔排序第" + (++count) + "轮=" + Arrays.toString(arr));
    }
/*
    //希尔排序的第1轮排序
    //因为第1轮排序，是将10个数据分成了5组
    for (int i = 5; i < arr.length; i++) {
      //遍历各组中所有的元素(共有5组，每组两个元素)，步长5
      for (int j = i - 5; j >= 0; j -= 5) {
        //如果当前元素大于加上步长后的那个元素，说明交换
        if (arr[j] > arr[j + 5]) {
          temp = arr[j];
          arr[j] = arr[j + 5];
          arr[j + 5] = temp;
        }
      }
    }
    System.out.println("希尔排序1轮后=" + Arrays.toString(arr));

    //希尔排序的第2轮排序
    //因为第2轮排序，是将10个数据分成了5/2=2组
    for (int i = 2; i < arr.length; i++) {
      //遍历各组中所有的元素(共有5组，每组两个元素)，步长5
      for (int j = i - 2; j >= 0; j -= 2) {
        //如果当前元素大于加上步长后的那个元素，说明交换
        if (arr[j] > arr[j + 2]) {
          temp = arr[j];
          arr[j] = arr[j + 2];
          arr[j + 2] = temp;
        }
      }
    }
    System.out.println("希尔排序2轮后=" + Arrays.toString(arr));

    //希尔排序的第3轮排序
    //因为第3轮排序，是将10个数据分成了2/2=1组
    for (int i = 1; i < arr.length; i++) {
      //遍历各组中所有的元素(共有5组，每组两个元素)，步长5
      for (int j = i - 1; j >= 0; j -= 1) {
        //如果当前元素大于加上步长后的那个元素，说明交换
        if (arr[j] > arr[j + 1]) {
          temp = arr[j];
          arr[j] = arr[j + 1];
          arr[j + 1] = temp;
        }
      }
    }
    System.out.println("希尔排序3轮后=" + Arrays.toString(arr));*/
  }

}
