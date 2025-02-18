package com.eundms.sort;

import java.util.Arrays;

public class MergeSort {
	public static void main(String[] args) {
		int[] arr = {5, 2, 6, 1, 3, 8};
		mergeSort(arr, 0, arr.length - 1);
		System.out.println(Arrays.toString(arr));
	}
	/**
	 * 병합 정렬 - 최선, 평균, 최악 : O(nlogn)
	 * 영역을 쪼갤 수 있을 만큼 쪼갠 뒤 정렬
	 * */
	static void mergeSort(int[] array, int left, int right) {
		if(left < right) {
			int mid = (left + right) / 2;
			mergeSort(array, left, mid);
			mergeSort(array, mid + 1, right);
			merge(array, left, mid, right);
		}
	}
	static void merge(int[] arr, int left, int mid, int right) {
		int[] lArr = Arrays.copyOfRange(arr, left, mid + 1);
		int[] rArr = Arrays.copyOfRange(arr, mid + 1, right + 1);

		int l = 0, r = 0, i = left; // arr의 인덱스 left 가 시작해야 함
		int maxL = lArr.length, maxR = rArr.length;

		while (l < maxL && r < maxR) {
			if (lArr[l] <= rArr[r]) {
				arr[i] = lArr[l++];
			} else {
				arr[i] = rArr[r++];
			}
			i++;
		}

		// remain
		while(l < maxL) {
			arr[i++] = lArr[l++];
		}
		while(r < maxR) {
			arr[i++] = rArr[r++];
		}
	}
}
