package com.zjh.tree;

import java.util.Arrays;

/**
 * @author ：ZouJiaHui
 * @date ：Created in 2020/3/2 10:49
 * @description：
 * @modified By：
 * @version: 1.0
 */
public class HeapSort {

  public static void main(String[] args) {
    //要求将数组进行升序排序
    int arr[] = {4, 6, 8, 5, 9, -1, 90, 89, 56, -999};
    heapSort(arr);
  }

  //编写一个堆排序的方法
  public static void heapSort(int arr[]) {
    int temp = 0;
    System.out.println("堆排序!!");

/*    //分步完成
    adjustHeap(arr, 1, arr.length);
    //4, 9, 8, 5, 6
    System.out.println("第1次" + Arrays.toString(arr));

    adjustHeap(arr, 0, arr.length);
    //9, 6 , 8, 5, 4
    System.out.println("第2次"+Arrays.toString(arr));*/

    //完成我们的最终代码
    //将无序序列构成一个堆，根据升序降序需求选择大顶堆或小顶堆
    for (int i = arr.length / 2 - 1; i >= 0; i--) {
      adjustHeap(arr, i, arr.length);
    }
    /**
     * 2).将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端；
     * 3).重新调整结构，使其满足堆定义，然后继续交换顶堆元素前末尾元素，反复执行调整+交换步骤，直到整个序列有序。
     */
    for (int j = arr.length - 1; j > 0; j--) {
      //交换
      temp = arr[j];
      arr[j] = arr[0];
      arr[0] = temp;
      adjustHeap(arr, 0, j);
    }

    System.out.println("数组=" + Arrays.toString(arr));
  }

  //将一个数组（二叉树），调整成一个大顶堆

  /**
   * 功能：完成 将以i对应的非叶子节点的数，调整成大顶堆 举例  int arr[] = {4,6,8,5,9}; => i = 1 => adjustHeap => 得到｛4，9，8，5，6｝
   * 如果我们再次调用 adjustHeap 传入的是 i = 0 => 得到｛4，9，8，5，6｝=> {9, 6, 8, 5, 4}
   *
   * @param arr 待调整的数组
   * @param i 表示非叶子结点在数组中的索引
   * @param length 表示对多少个元素进行调整，length是在逐渐的减少
   */
  public static void adjustHeap(int arr[], int i, int length) {

    //先取出当前元素的值，保存在临时变量
    int temp = arr[i];
    //开始调整
    //说明
    //1.k=i * 2 + 1  k是i结点的左子结点
    for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
      if (k + 1 < length && arr[k] < arr[k + 1]) {
        //说明左子结点的值小于右子结点的值
        // k 指向右子结点
        k++;
      }
      if (arr[k] > temp) {
        //如果子结点大于父结点
        //把较大的值赋给当前结点
        arr[i] = arr[k];
        //!!! i 指向k，继续循环比较
        i = k;
      } else {
        //!
        break;
      }
    }
    //当for 循环结束后，我们已经将以i 为父结点的数的最大值，放在了最顶（局部）
    //将temp值放到调整后的位置
    arr[i] = temp;
  }
}
