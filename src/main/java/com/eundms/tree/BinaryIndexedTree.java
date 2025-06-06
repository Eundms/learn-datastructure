package com.eundms.tree;

public class BinaryIndexedTree { // Fenwick Tree
	/**
	 * 특정 수의 누적합을 빠르게 구하려고 만든 트리 기반 배열
	 * Q. x번째 값을 val로 변경하는 쿼리가 들어온다면?
	 * Q. 1부터 x까지의 합이 아니라 1부터 x까지의 곱/GCD/최댓값/최솟값을 구해야 하는 쿼리가 들어온다면?
	 * */
	private int N;
	private int[] BIT;


	// 마지막 1이 나타내는 값만큼의 수의 합을 저장
	// BIT = [1, 2, 3, 4, 5, 6, 7, 8, ... 15, 16, 17, 18, 19, 20]
	// BIT[i] = [i - (i & -i), i] 범위의 합
	public BinaryIndexedTree(int size) {
		this.N = size;
		this.BIT = new int[N + 1];
	}

	/**
	 * 1부터 i까지의 합을 구하기
	 * */
	// arr[1] ... arr[x] : logN
	// BIT[21] = arr[21=10101] + BIT[20=10100] + BIT[16=10000]
	// BIT[15] = arr[15=1111] + BIT[14=1110] + BIT[12=1100] + BIT[8=1000]
	public int query(int i) { // arr[i[ = value 로 변경
		int ret = 0;
		while (i > 0) {
			ret += BIT[i];
			i -= (i & -i); // 상위 노드들에도 값을 전파하기 위함
		}
		return ret;
	}

	/**
	 * i에 value 추가
	 * */
	// arr[13=1101] 변경 -> BIT[1101],  BIT[1110] , BIT[10000]
	// arr[17=10001] 변경 -> BIT[10001] , BIT[10010] , BIT[10100]
	// 이전에 변경한 index에서 마지막 1 더하기
	public void update(int i, int value) {
		while(i <= N) {
			BIT[i] += value;
			i += (i & -i);
		}
	}

	public int rangeQuery(int l, int r) {
		return query(r) - query(l - 1); // [l, r]의 합
	}

}
