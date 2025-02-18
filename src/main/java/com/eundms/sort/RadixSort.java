package com.eundms.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RadixSort {

	public static void main(String[] args) {
		List<Integer> arr = Arrays.asList(170, 45, 75, 90, 802, 24, 2, 66);
		int maxDigits = getMaxDigits(arr);
		List<Integer> sortedArr = radixSort(arr, maxDigits);

		System.out.println("정렬 결과: " + sortedArr);
	}

	public static List<Integer> radixSort(List<Integer> arr, int k) {
		for (int pos = k - 1; pos >= 0; pos--) {
			List<List<Integer>> arrNew = new ArrayList<>();
			for (int i = 0; i < 10; i++) {
				arrNew.add(new ArrayList<>()); // 0~9까지의 버킷 생성
			}

			// 각 숫자를 해당 자릿수의 버킷에 분배
			for (int num : arr) {
				int digit = getDigit(num, pos);
				arrNew.get(digit).add(num);
			}

			// 버킷의 값을 다시 원래 리스트에 합치기
			List<Integer> storeArr = new ArrayList<>();
			for (List<Integer> bucket : arrNew) {
				storeArr.addAll(bucket);
			}

			arr = storeArr; // 정렬된 리스트로 교체
		}

		return arr;
	}

	// 주어진 숫자의 특정 자리(pos)의 숫자를 가져오는 메서드
	private static int getDigit(int num, int pos) {
		return (num / (int) Math.pow(10, pos)) % 10;
	}

	// 배열 내 가장 큰 숫자의 자릿수를 구하는 메서드
	private static int getMaxDigits(List<Integer> arr) {
		int max = arr.stream().max(Integer::compareTo).orElse(0);
		return Integer.toString(max).length();
	}
}
