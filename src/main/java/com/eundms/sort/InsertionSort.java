package com.eundms.sort;

import java.util.Arrays;

public class InsertionSort {
	public static void main(String[] args) {
		int[] arr = {3, 1, 2, 4, 8, 9, 6, 5};
		insertionSort(arr);
	}
	/**
	 * 삽입 정렬 >> 평균 & 최악 : O(N^2), 최선 : O(N)(정렬되어있는경우)
	 * 앞에 있는 모든 원소가 정렬이 되어 있다는 가정하에서 현재 원소의 위치를 적절하게 집어넣는 정렬
	 * */
	static void insertionSort(int[] arr) {
		int N = arr.length;
		for (int i = 1; i < N; i++) {
			int key = arr[i]; // 현재 삽입될 숫자

			int j = i - 1; // 이미 정렬된 배열의 어느 위치에 넣을지 결정하기 위해 역순으로 살펴봄
			while(j >= 0 && arr[j] > key) { // 앞쪽에 넣어야 하므로 계속 진행
				arr[j + 1] = arr[j]; // 오른쪽으로 밀음
				j--;
			}
			arr[j + 1]  = key;
		}
		System.out.println(Arrays.toString(arr));
	}

}
