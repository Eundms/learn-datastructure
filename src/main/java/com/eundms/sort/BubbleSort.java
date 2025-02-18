package com.eundms.sort;

import java.util.Arrays;

public class BubbleSort {
	public static void main(String[] args) {
		int[] arr = {5, 4, 1, 3, 2};
		/**
		 * [4, 5, 1, 3, 2] [4, 1, 5, 3, 2] [4, 1, 3, 5, 2] [4, 1, 3, 2, 5]
		 * [1, 4, 3, 2, 5] [1, 3, 4, 2, 5] [1, 3, 2, 4, 5]
		 * [1, 3, 2, 4, 5] [1, 2, 3, 4, 5]
		 * [1, 2, 3, 4, 5]
		 * */
		bubbleSort(arr);
	}
	/**
	 * 버블 정렬 (오름차순) : 최악, 최선, 평균 O(N^2)
	 *
	 * 앞에서부터 인접한 데이터 값을 비교한다
	 * 현재 데이터와 다음 데이터를 비교하여 다음 데이터가 더 작다면 위치를 바꾸고 아니라면 그대로 둔다
	 * 다음 데이터로 이동하여 값을 비교한다
	 *
	 * 오름차순 정렬시 맨 뒷 값부터 채워지게 됨
	 * */
	static void  bubbleSort(int[] arr) {
		int N = arr.length;
		for (int i = N - 1 ; i >= 0; i--) { // 어디까지 정렬을 진행할 것인지
			for (int j = 0; j < i; j++) {
				if (arr[j] > arr[j+1]) {
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
				System.out.print(Arrays.toString(arr)+" ");
			}
			System.out.println();
		}
	}
}
