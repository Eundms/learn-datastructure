package com.eundms.sort;

import java.util.Arrays;

public class QuickSort {
	public static void main(String[] args) {
		int[] arr = {1, 5, 6, 2, 3, 4};
		quickSort(arr, 0, arr.length-1);
		System.out.println(Arrays.toString(arr));
	}
	/**
	 * 퀵소트 O(N)
	 * 임의의 피벗 기준으로 작은 수 큰수 2개의 부분 배열을 만들고, 이를 정렬
	 * */
	static void quickSort(int[] arr, int left, int right) {
		if(left >= right) return;
		int pivot = partition(arr, left, right);
		quickSort(arr, left, pivot-1); // 피벗을 제외한 2개의 부분 배열을 대상으로 호출
		quickSort(arr, pivot+1, right);
	}

	static int partition(int[] arr, int left, int right) {
		int pivot = arr[left]; // 가장 왼쪽 값을 피벗으로 설정
		int i = left, j = right;
		while(i < j) {
			while(pivot < arr[j]) {
				j--;
			}
			while(i < j && pivot >= arr[i]){
				i++;
			}
			swap(arr, i, j);
		}
		arr[left] = arr[i];
		arr[i] = pivot;
		return i;
	}

	static void swap(int[] arr, int i, int j){
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
