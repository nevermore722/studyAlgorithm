package com.zjh.sort;

import java.util.Arrays;

/**
 * @author ：ZouJiaHui
 * @date ：Created in 2020/2/13 20:50
 * @description：
 * @modified By：
 * @version: 1.0
 */
public class RadixSort {

  public static void main(String[] args) {
    int arr[] = {53, 3, 542, 748, 14, 214};
    radixSort(arr);

  }

  //基数排序方法
  public static void radixSort(int[] arr) {
    //第一轮(针对每个元素的个位进行排序处理)

    //定义一个二维数组，表示10个桶，每个桶就是一个一维数组
    //说明
    //1.二维数组包含10个一维数组
    //2.为了防止在放入数的时候，数据溢出，则每个一维数组（桶），大小定为arr.length
    //3.明确，基数排序是使用空间换时间的经典算法
    int[][] bucket = new int[10][arr.length];

    //为了记录每个桶中实际存放了多少个数据，我们定义一个一维数组来记录各个桶的每次放入的数据个数
    //可以这样理解
    //比如：bucketElementCounts[0],记录的就是bucket[0]桶的放入数据的个数
    int[] bucketElementCounts = new int[10];

    //第1轮（针对每个元素的个位数进行排序处理）
    for (int j = 0; j < arr.length; j++) {
      //取出每个元素的个数
      int digitOfElement = arr[j] % 10;
      //放入到对应的桶中
      bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
      bucketElementCounts[digitOfElement]++;
    }
    //按照这个桶的顺序（一维数组的下标依次取出数组，放入原来数组）
    int index = 0;
    //遍历每一个桶，并将桶中的数据，放入到原数组
    for (int k = 0; k < bucketElementCounts.length; k++) {
      //如果桶中，有数据，我们才放入到原数组
      if (bucketElementCounts[k] != 0) {
        //循环该桶即第k个桶（即第k个一维数组）,放入
        for (int l = 0; l < bucketElementCounts[k]; l++) {
          //取出元素放入到arr
          arr[index++] = bucket[k][l];
        }
      }
      //第一轮处理后，需要将每个bucketElementCounts[k] = 0！！！！！！！！！
      bucketElementCounts[k] = 0;
    }
    System.out.println("第1轮，对个位的排序处理 arr = " + Arrays.toString(arr));

    //第2轮（针对每个元素的十位数进行排序处理）
    for (int j = 0; j < arr.length; j++) {
      //取出每个元素的个数
      int digitOfElement = arr[j] / 10 % 10;
      //放入到对应的桶中
      bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
      bucketElementCounts[digitOfElement]++;
    }
    //按照这个桶的顺序（一维数组的下标依次取出数组，放入原来数组）
    index = 0;
    //遍历每一个桶，并将桶中的数据，放入到原数组
    for (int k = 0; k < bucketElementCounts.length; k++) {
      //如果桶中，有数据，我们才放入到原数组
      if (bucketElementCounts[k] != 0) {
        //循环该桶即第k个桶（即第k个一维数组）,放入
        for (int l = 0; l < bucketElementCounts[k]; l++) {
          //取出元素放入到arr
          arr[index++] = bucket[k][l];
        }
      }
      //第一轮处理后，需要将每个bucketElementCounts[k] = 0！！！！！！！！！
      bucketElementCounts[k] = 0;
    }
    System.out.println("第2轮，对十位的排序处理 arr = " + Arrays.toString(arr));

    //第3轮（针对每个元素的百位数进行排序处理）
    for (int j = 0; j < arr.length; j++) {
      //取出每个元素的个数
      int digitOfElement = arr[j] / 100 % 10;
      //放入到对应的桶中
      bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
      bucketElementCounts[digitOfElement]++;
    }
    //按照这个桶的顺序（一维数组的下标依次取出数组，放入原来数组）
    index = 0;
    //遍历每一个桶，并将桶中的数据，放入到原数组
    for (int k = 0; k < bucketElementCounts.length; k++) {
      //如果桶中，有数据，我们才放入到原数组
      if (bucketElementCounts[k] != 0) {
        //循环该桶即第k个桶（即第k个一维数组）,放入
        for (int l = 0; l < bucketElementCounts[k]; l++) {
          //取出元素放入到arr
          arr[index++] = bucket[k][l];
        }
      }
    }
    System.out.println("第3轮，对百位的排序处理 arr = " + Arrays.toString(arr));
  }

}
