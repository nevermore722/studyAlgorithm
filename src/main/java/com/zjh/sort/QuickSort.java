package com.zjh.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author ：ZouJiaHui
 * @date ：Created in 2020/2/10 11:29
 * @description：
 * @modified By：
 * @version: 1.0
 */
public class QuickSort {

  public static void main(String[] args) {
//    int[] arr = {-9, 78, 0, 23, -567, 70, -1, 900, 4561};

    //创建一个80000个的随机数组
    int[] arr = new int[8000000];
    for (int i = 0; i < 8000000; i++) {
      //生成一个[0,8000000)数
      arr[i] = (int) (Math.random() * 8000000);
    }

    System.out.println("排序前");
    Date date1 = new Date();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String date1Str = simpleDateFormat.format(date1);
    System.out.println("排序前的时间是=" + date1Str);

    //交换式
    quickSort(arr, 0, arr.length - 1);
    System.out.println("排序后");
    Date date2 = new Date();
    String date2Str = simpleDateFormat.format(date2);
    System.out.println("排序后的时间是=" + date2Str);
//    System.out.println(Arrays.toString(arr));
  }

  public static void quickSort(int[] arr, int left, int right) {
    //左下标
    int l = left;
    //右下标
    int r = right;
    //pivot中轴值
    int pivot = arr[(left + right) / 2];
    //临时变量，作为交换时适用
    int temp = 0;
    //while循环的目的是让比pivot 值小的值放到左边
    //比pivot 值大的值放到右边
    while (l < r) {
      //在pivot的左边一直找，找到大于等于pivot值，才退出
      while (arr[l] < pivot) {
        l += 1;
      }
      //在pivot的右边一直找，找到小于等于pivot值，才退出
      while (arr[r] > pivot) {
        r -= 1;
      }
      //如果l >= r说明pivot 的左右两的值，已经按照左边全部是
      //小于等于pivot值，右边全部是大于等于pivot的值
      if (l >= r) {
        break;
      }

      //交换
      temp = arr[l];
      arr[l] = arr[r];
      arr[r] = temp;

      //如果交换完后，发现这个arr[l] == pivot值 相等 r--,前移
      if (arr[l] == pivot) {
        r -= 1;
      }
      //如果交换完后，发现这个arr[r] == pivot值 相等 l--,前移
      if (arr[r] == pivot) {
        l += 1;
      }
    }

    //如果l == r,必须l++,r--,否则会出现栈溢出
    if (l == r) {
      l += 1;
      r -= 1;
    }
    //向左递归
    if (left < r) {
      quickSort(arr, left, r);
    }
    //向右递归
    if (right > l) {
      quickSort(arr, l, right);
    }
  }

}
