package com.eundms.cache;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
	private final int CAPACITY;
	private final Map<Integer, Node> map;
	private final Node head, tail;
	private class Node {
		int key, value;
		Node prev, next;
		Node(int k, int v){
			this.key = k;
			this.value = v;
		}
	}
	public static void main(String[] args) {
		// least recently used
		LRUCache lruCache = new LRUCache(2);
		lruCache.put(1, 1); // {1=1}
		lruCache.put(2, 2); // {1=1, 2=2}
		lruCache.get(1); // return 1
		lruCache.put(3, 3); // {1=1, 3=3}
		lruCache.get(2); // return -1
		lruCache.put(4, 4); // {4=4, 3=3}
		lruCache.get(1); // return -1
		lruCache.get(3); // return 3
		lruCache.get(4); // return 4
	}

	public LRUCache(int capacity) { // capacity 크기로 초기화
		CAPACITY = capacity;
		map = new HashMap<>();
		head = new Node(0, 0); // dummy head
		tail = new Node(0, 0); // dummy tail
		head.next = tail;
		tail.prev = head;
	}

	public int get(int key) { // key가 존재하면 값을 반환 아니면 -1 반환
		if(map.containsKey(key)) { // key 존재 x
			return map.get(key).value;
		}
		return -1;
	}

	public void put(int key, int value) { // key가 존재하면 값 업데이트, 그렇지 않으면 쌍을 캐시에 추가
		// 가장 오래 된 캐시 삭제
	}
}
