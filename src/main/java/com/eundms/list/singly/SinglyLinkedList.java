package com.eundms.list.singly;

public class SinglyLinkedList {
	private Node head;
	private Node tail;
	private int size;

	public static void main(String[] args) {
		SinglyLinkedList list = new SinglyLinkedList();

		System.out.println("== 초기 상태 ==");
		list.print();

		System.out.println("== insertFirst(0) ==");
		list.insertFirst(0);  // [0]
		list.print();

		System.out.println("== insertLast(1) ==");
		list.insertLast(1);   // [0, 1]
		list.print();

		System.out.println("== insertLast(2), insertLast(3) ==");
		list.insertLast(2);
		list.insertLast(3);   // [0, 1, 2, 3]
		list.print();

		System.out.println("== insertAt(2, 99) ==");
		list.insertAt(2, 99); // [0, 1, 99, 2, 3]
		list.print();

		System.out.println("== delete(99) ==");
		list.delete(99);      // [0, 1, 2, 3]
		list.print();

		System.out.println("== deleteByIndex(0) ==");
		list.deleteByIndex(0); // [1, 2, 3]
		list.print();

		System.out.println("== deleteByIndex(2) ==");
		list.deleteByIndex(2); // [1, 2]
		list.print();

		System.out.println("== delete(5) [없는 값] ==");
		boolean deleted = list.delete(5); // false
		System.out.println("삭제 성공 여부: " + deleted);
		list.print();

		System.out.println("== size() ==");
		System.out.println("현재 사이즈: " + list.size());
	}


	public boolean isEmpty() {
		return head == null;
	}

	public void insertFirst(int data) {
		Node node = new Node(data);
		node.next = head;
		head = node;
		if(tail == null){ // 처음으로 삽입되는 노드
			tail = node;
		}
		size++;
	}

	public void insertLast(int data) {
		Node node = new Node(data);
		if(isEmpty()) {
			head = tail = node;
		} else {
			tail.next = node;
			tail = node;
		}
		size++;
	}

	public void insertAt(int index, int data) {
		Node node = new Node(data);

		Node current = head;
		Node prev = null;
		if(index == 0 && current != null) {
			insertFirst(data);
			return;
		}
		if(index == size && current != null) {
			insertLast(data);
			return;
		}

		int cnt = 0;
		while(cnt < index) {
			prev = current;
			current = current.next;
			cnt++;
		}
		prev.next = node;
		node.next = current;
	}

	public boolean delete(int data) {
		if (isEmpty()) return false;

		if (head.data == data) {
			head = head.next;
			if (head == null) tail = null;
			size--;
			return true;
		}

		Node current = head;
		while (current.next != null && current.next.data != data) {
			current = current.next;
		}

		if (current.next == null) return false;

		current.next = current.next.next;

		if (current.next == null) tail = current;
		size--;

		return true;
	}

	public boolean deleteByIndex(int index) {

		// store head node
		Node currentNode = head;

		// store the previous node to the current one
		Node prevNode = null;

		// if index is 0 then head node itself is to be deleted
		if (index == 0 && currentNode != null) {
			head = currentNode.next;

			// if the head is null then the tail is null as well
			if (head == null) {
				tail = null;
			}

			// set the new size
			size--;

			return true;
		}

		// if index > 0
		int pointer = 0;
		while (currentNode != null) {

			if (pointer == index) {
				// unlink currentNode from linked list
				prevNode.next = currentNode.next;

				// prev becomes null
				if (prevNode.next == null) {
					tail = prevNode;
				}

				// set the new size
				size--;

				return true;
			} else {
				// continue searching to next node
				prevNode = currentNode;
				currentNode = currentNode.next;

				pointer++;
			}
		}

		// we cannot find the given index
		return false;
	}


	public void print() {
		System.out.println("Head (" + head +") >>> Last (" + tail +")");

		Node current = head;
		while (current != null) {
			System.out.println(current.data);
			current = current.next;
		}
		System.out.println();
	}

	public int size() {
		return size;
	}
}
