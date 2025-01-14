package com.eundms.tree;

public class DisjointSet {
	private int[] parent;
	private int[] rank;

	public DisjointSet(int n) { // 집합의 수 n
		parent = new int[n+1];
		rank = new int[n+1];
		for(int i = 0; i <= n; i++) {
			parent[i] = i;
			rank[i] = 1;
		}
	}

	public void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) {
			return;
		}
		if(rank[aRoot]< rank[bRoot]) {
			parent[aRoot] = bRoot;
		}
		else if(rank[aRoot] == rank[bRoot]) {
			rank[aRoot] += 1;
		} else {
			parent[bRoot] = aRoot;
		}
	}

	public int find(int x) {
		if(parent[x] == x){ // 루트
			return x;
		}
		return parent[x] = find(parent[x]);
	}
	public boolean isConnected(int x, int y) {
		return find(x) == find(y);
	}
}
