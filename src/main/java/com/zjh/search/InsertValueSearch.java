package com.zjh.search;

import java.util.Arrays;

/**
 * @author ：ZouJiaHui
 * @date ：Created in 2020/2/18 18:33
 * @description：
 * @modified By：
 * @version: 1.0
 */
public class InsertValueSearch {

  public static void main(String[] args) {
//    int[] arr = new int[100];
//    for (int i = 0; i < 100; i++) {
//      arr[i] = i + 1;
//    }
    //关键字分布不均匀的话，不一定更好
    int arr[] = {1, 8, 10, 89, 1000, 1000, 1000, 1234};

    int index = insertValueSearch(arr, 0, arr.length - 1, 10);
    System.out.println("index = " + index);

//    System.out.println(Arrays.toString(arr));

  }
  //编写插值查找算法
  //说明：插值查找算法，也要求数组是有序的

  /**
   * @param arr 数组
   * @param left 左边索引
   * @param right 右边索引
   * @param findVal 查找的值
   * @return 如果找到，就返回对应的下标，如果没有找到，返回-1
   */
  public static int insertValueSearch(int[] arr, int left, int right, int findVal) {
    System.out.println("查找次数~~");
    //注意：findVal < arr[0] 和 findVal > arr[arr.length - 1] 必须需要
    //否则我们得到的mid 可能越界
    if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
      return -1;
    }

    //求出mid，自适应
    int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
    int midVal = arr[mid];
    if (findVal > midVal) {
      //说明应该向右边递归
      return insertValueSearch(arr, mid + 1, right, findVal);
    } else if (findVal < midVal) {
      //说明向左递归查找
      return insertValueSearch(arr, left, mid - 1, findVal);
    } else {
      return mid;
    }
  }
}
