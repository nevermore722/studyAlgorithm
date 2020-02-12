package com.zjh.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author ：ZouJiaHui
 * @date ：Created in 2020/2/11 20:56
 * @description：
 * @modified By：
 * @version: 1.0
 */
public class MergeSort {

  public static void main(String[] args) {
    //8->merge 7 ，80000->mergr 80000-1 冒泡80000 * O(n^2)
//    int arr[] = {8, 4, 5, 7, 1, 3, 6, 2, 0, 234};
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

    //归并排序需要一个额外空间
    int temp[] = new int[arr.length];
    mergeSort(arr, 0, arr.length - 1, temp);
    System.out.println("排序后");
    Date date2 = new Date();
    String date2Str = simpleDateFormat.format(date2);
    System.out.println("排序后的时间是=" + date2Str);
//    System.out.println("归并排序后=" + Arrays.toString(arr));
  }

  //分+合的方法
  public static void mergeSort(int[] arr, int left, int right, int[] temp) {
    if (left < right) {
      //中间的索引
      int mid = (left + right) / 2;
      //向左递归进行分解
      mergeSort(arr, left, mid, temp);
      //向右递归进行分解
      mergeSort(arr, mid + 1, right, temp);
      //到合并
      merge(arr, left, mid, right, temp);

    }
  }

  //合并的方法

  /**
   * @param arr 排序的原始数组
   * @param left 左边有序序列的初始索引
   * @param mid 中间索引
   * @param right 右边索引
   * @param temp 做中转的数组
   */
  public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
//    System.out.println("xxxx");
    //c初始化i，左边有序序列的初始索引
    int i = left;
    //初始化j，右边有序序列的初始索引
    int j = mid + 1;
    //指向temp数组的当前索引
    int t = 0;

    //(一)
    //先把左右两边（有序）的数据按照规则填充到temp数组
    //直到左右两边的有序序列，有一边处理完毕为止
    while (i <= mid && j <= right) {
      //继续
      if (arr[i] <= arr[j]) {
        //如果左边的有序序列的当前元素，小于等于右边有序序列的当前元素
        //即将左边的当前元素，填充到temp数组
        //让后t++，i++
        temp[t] = arr[i];
        t += 1;
        i += 1;
      } else {
        //反之，将右边有序序列的当前元素，填充到temp数组
        temp[t] = arr[j];
        t += 1;
        j += 1;
      }
    }

    //(二)
    //把有剩余的数据的一边的数据依次全部填充到temp
    while (i <= mid) {
      //左边的有序序列还有剩余的元素，就全部填充到temp
      temp[t] = arr[i];
      t += 1;
      i += 1;
    }
    while (j <= right) {
      //右边的有序序列还有剩余的元素，就全部填充到temp
      temp[t] = arr[j];
      t += 1;
      j += 1;
    }
    //（三）
    //将temp数组的元素拷贝到arr
    //注意，并不是每次都拷贝所有
    t = 0;
    //
    int tempLeft = left;
//    System.out.println("tempLeft=" + tempLeft + " right=" + right);
    while (tempLeft <= right) {
      //第一次合并tempLeft = 0，right = 1 //templeft = 2 right = 3 //tL = 0 ri = 3
      //最后一次tempLeft = 0，right = 7
      arr[tempLeft] = temp[t];
      t += 1;
      tempLeft += 1;
    }

  }

}
