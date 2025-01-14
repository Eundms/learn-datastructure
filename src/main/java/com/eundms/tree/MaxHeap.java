package com.eundms.tree;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class MaxHeap <T extends Comparable<T>>{
	private static final int DEFAULT_CAPACITY = 5;

	private int capacity;
	private int size;
	private T[] heap;
	public MaxHeap() {
		capacity = DEFAULT_CAPACITY;
		this.heap = (T[]) Array.newInstance(
			Comparable[].class.getComponentType(), DEFAULT_CAPACITY);
	}

	public void add(T element) {
		ensureCapacity();
		heap[size] = element;
		size++;
		heapifyUp();
	}

	public T poll() {

		if (size == 0) {
			throw new NoSuchElementException();
		}

		T element = heap[0];

		heap[0] = heap[size - 1];
		heap[size - 1] = null;

		size--;

		heapifyDown();

		return element;
	}

	private void heapifyDown() {

		// 루트 노드에서부터 시작
		int index = 0;

		while (hasLeftChild(index)) {

			// 현재 노드의 자식들중 가장 큰 값을 찾는다
			int largestChildIndex = getLeftChildIndex(index);
			if (hasRightChild(index) && rightChild(index).compareTo(leftChild(index)) > 0) {
				largestChildIndex = getRightChildIndex(index);
			}

			// 현재 노드보다 그 값이 작다면, 두 노드를 swap한다
			if (heap[index].compareTo(heap[largestChildIndex]) < 0) {
				swap(index, largestChildIndex);
			} else {
				// 완벽함
				break;
			}

			index = largestChildIndex;
		}
	}

	private void heapifyUp() {
		int index = size - 1; // 새로 추가한 노드 위치에서부터 시작
		while(hasParent(index) && parent(index).compareTo(heap[index]) < 0) { // 조건을 만족하지 않는다면 수정
			swap(getParentIndex(index), index);
			index = getParentIndex(index);
		}
	}
	private void ensureCapacity() {

		if (size == capacity) {
			heap = Arrays.copyOf(heap, capacity * 2);
			capacity = capacity * 2;
		}
	}

	private void swap(int index1, int index2) {
		T element = heap[index1];
		heap[index1] = heap[index2];
		heap[index2] = element;
	}

	private int getLeftChildIndex(int parentIndex) {
		return 2 * parentIndex + 1;
	}

	private int getRightChildIndex(int parentIndex) {
		return 2 * parentIndex + 2;
	}

	private int getParentIndex(int childIndex) {
		return (childIndex - 1) / 2;
	}

	private boolean hasLeftChild(int index) {
		return getLeftChildIndex(index) < size;
	}

	private boolean hasRightChild(int index) {
		return getRightChildIndex(index) < size;
	}

	private boolean hasParent(int index) {
		return getParentIndex(index) >= 0;
	}

	private T leftChild(int parentIndex) {
		return heap[getLeftChildIndex(parentIndex)];
	}

	private T rightChild(int parentIndex) {
		return heap[getRightChildIndex(parentIndex)];
	}

	private T parent(int childIndex) {
		return heap[getParentIndex(childIndex)];
	}
}
