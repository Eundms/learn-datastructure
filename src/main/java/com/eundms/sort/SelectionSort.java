package com.eundms.sort;

import java.util.Arrays;

public class SelectionSort {
	public static void main(String[] args) {
		int[] arr = {3, 1, 2, 4, 8, 9, 6, 5};
		selectionSort(arr);
	}
	/**
	 * 선택 정렬 : O(N^2)
	 * 1. 전체 값 중 가장 작은 값을 찾음
	 * 2. 해당 값을 맨 첫번째에 배치함
	 * 3. 첫번째값을 제외하고 가장 작은 값을 찾아 두번째에 배치함
	 * 4. 두번째, 세번째, .. N-1번째 값을 제외하고 가장 작은 값을 정해진 위치에 배치함
	 * */
	static void selectionSort(int[] arr) {
		int N = arr.length;
		for (int i = 0; i < N; i++) {
			int minIdx = i, minValue = arr[i];
			for(int j = i + 1; j < N; j++){
				if(arr[j] < minValue) {
					minIdx = j;
					minValue = arr[j];
				}
			}
			int temp = arr[i];
			arr[i] = arr[minIdx];
			arr[minIdx] = temp;
			System.out.println(Arrays.toString(arr));
		}
	}
	/**
	 * 1 2 4 7 5 6 3
	 * 1 2 3 7 5 6 4
	 * 1 2 3 4 5 6 7
	 * */
}
