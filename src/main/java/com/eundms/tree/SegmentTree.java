package com.eundms.tree;

public class SegmentTree {
	private long[] tree; // 세그먼트 트리 배열
	private int n;       // 원본 배열의 크기

	public SegmentTree(long[] arr) {
		this.n = arr.length;
		int h = (int) Math.ceil(Math.log(n) / Math.log(2));
		int size = 1 << (h + 1); // 트리 배열 크기 계산
		tree = new long[size];
		build(arr, 1, 0, n - 1);
	}

	// 트리 초기화
	private long build(long[] arr, int node, int start, int end) {
		if (start == end) {
			return tree[node] = arr[start];
		}
		int mid = (start + end) / 2;
		return tree[node] = build(arr, node * 2, start, mid)
			+ build(arr, node * 2 + 1, mid + 1, end);
	}

	// 구간 합 조회
	public long query(int left, int right) {
		return query(1, 0, n - 1, left, right);
	}

	private long query(int node, int start, int end, int left, int right) {
		if (right < start || end < left) return 0; // 범위 밖
		if (left <= start && end <= right) return tree[node]; // 완전 포함
		int mid = (start + end) / 2;
		return query(node * 2, start, mid, left, right)
			+ query(node * 2 + 1, mid + 1, end, left, right);
	}

	// 값 갱신 (차이값 이용)
	public void update(int index, long newValue, long[] arr) {
		long diff = newValue - arr[index];
		arr[index] = newValue;
		update(1, 0, n - 1, index, diff);
	}

	private void update(int node, int start, int end, int index, long diff) {
		if (index < start || index > end) return;
		tree[node] += diff;
		if (start != end) {
			int mid = (start + end) / 2;
			update(node * 2, start, mid, index, diff);
			update(node * 2 + 1, mid + 1, end, index, diff);
		}
	}
}
