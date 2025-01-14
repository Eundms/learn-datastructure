package com.eundms;

import com.eundms.tree.Trie;

public class Main {
	public static void main(String[] args) {
		Trie trie = new Trie();
		trie.insert("apple");
		trie.insert("app");

		System.out.println(trie.search("apple"));  // true
		System.out.println(trie.search("app"));    // true
		System.out.println(trie.startsWith("ap")); // true
		System.out.println(trie.search("ap"));     // false
	}
}