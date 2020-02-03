package com.zjh.sort;

import java.util.Arrays;

/**
 * @author ：ZouJiaHui
 * @date ：Created in 2020/2/2 21:00
 * @description：
 * @modified By：
 * @version: 1.0
 */
public class SelectSort {

  public static void main(String[] args) {
    int[] arr = {101, 34, 119, 1, -1, 90, 123};
    System.out.println("排序前");
    System.out.println(Arrays.toString(arr));
    selectSort(arr);
    System.out.println("排序后");
    System.out.println(Arrays.toString(arr));
  }

  //选择排序
  public static void selectSort(int[] arr) {

    //在推导的过程中，我们发现了规律，因此，可以使用for来解决
    //选择排序时间复杂度是O(n^2)
    for (int i = 0; i < arr.length - 1; i++) {
      int minIndex = i;
      int min = arr[i];
      for (int j = i + 1; j < arr.length; j++) {
        if (min > arr[j]) {
          //说明假定的最小值，并不是最小
          //重置min
          min = arr[j];
          //重置minIndex
          minIndex = j;
        }
      }

      //将最小值，放在arr[i],即交换
      if (minIndex != i) {
        arr[minIndex] = arr[i];
        arr[i] = min;
      }

      System.out.println("第" + (i + 1) + "轮后~~");
      //101，34，119，1
      System.out.println(Arrays.toString(arr));
    }

   /*
    //使用逐步推导的方式来，讲解选择排序
    //第一轮
    //原始的数组：101，34，119，1
    //第一轮排序：1，34，119，101
    //算法 先简单-->再复杂，就是可以把一个复杂的算法，拆分成简单的问题->逐步解决

    //第一轮
    int minIndex = 0;
    int min = arr[0];
    for (int j = 0 + 1; j < arr.length; j++) {
      if (min > arr[j]) {
        //说明假定的最小值，并不是最小
        //重置min
        min = arr[j];
        //重置minIndex
        minIndex = j;
      }
    }

    //将最小值，放在arr[0],即交换
    if (minIndex != 0) {
      arr[minIndex] = arr[0];
      arr[0] = min;
    }

    System.out.println("第一轮后~~");
    //101，34，119，1
    System.out.println(Arrays.toString(arr));

    //第二轮
    minIndex = 1;
    min = arr[1];
    for (int j = 1 + 1; j < arr.length; j++) {
      if (min > arr[j]) {
        //说明假定的最小值，并不是最小
        //重置min
        min = arr[j];
        //重置minIndex
        minIndex = j;
      }
    }

    //将最小值，放在arr[0],即交换
    if (minIndex != 1) {
      arr[minIndex] = arr[1];
      arr[1] = min;
    }

    System.out.println("第二轮后~~");
    //1，34，119，101
    System.out.println(Arrays.toString(arr));

    //第三轮
    minIndex = 2;
    min = arr[2];
    for (int j = 1 + 2; j < arr.length; j++) {
      if (min > arr[j]) {
        //说明假定的最小值，并不是最小
        //重置min
        min = arr[j];
        //重置minIndex
        minIndex = j;
      }
    }

    //将最小值，放在arr[0],即交换
    if (minIndex != 2) {
      arr[minIndex] = arr[2];
      arr[2] = min;
    }

    System.out.println("第三轮后~~");
    //1，34，101，119
    System.out.println(Arrays.toString(arr));
*/
  }
}
