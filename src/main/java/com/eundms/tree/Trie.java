package com.eundms.tree;

import java.util.HashMap;
import java.util.Map;

/***
 * 접두사 트리
 */
public class Trie {
	private class TrieNode {
		Map<Character, TrieNode> children; // 자식 노드 저장
		boolean isEndOfWord; // 단어의 끝인지 여부
		public TrieNode() {
			children = new HashMap<>(); // 자식을 위한 HashMap 초기화
			isEndOfWord = false; // 기본값은 단어 끝이 아님
		}
	}

	private TrieNode root;
	public Trie() {
		root = new TrieNode();        // 루트 노드 초기화
	}

	public void insert(String word) {
		TrieNode node = root;
		for (char c : word.toCharArray()) {
			node.children.putIfAbsent(c, new TrieNode()); // 자식 노드 추가
			node = node.children.get(c);                 // 다음 노드로 이동
		}
		node.isEndOfWord = true; // 단어의 끝 표시
	}

	public boolean search(String word) {
		TrieNode node = root;
		for (char c : word.toCharArray()) {
			if (!node.children.containsKey(c)) {
				return false; // 문자가 존재하지 않으면 false 반환
			}
			node = node.children.get(c); // 다음 노드로 이동
		}
		return node.isEndOfWord; // 단어 끝이면 true, 아니면 false
	}

	public boolean startsWith(String prefix) {
		TrieNode node = root;
		for (char c : prefix.toCharArray()) {
			if (!node.children.containsKey(c)) {
				return false; // 접두사가 존재하지 않음
			}
			node = node.children.get(c); // 다음 노드로 이동
		}
		return true; // 접두사가 존재함
	}
}
