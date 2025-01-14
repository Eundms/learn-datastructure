package com.eundms.tree;

public class RedBlackTree {
	private static final int RED = 0;
	private static final int BLACK = 1;

	private final Node nil = new Node(-999);

	private Node root = nil;

	private class Node {
		int element;
		int color = BLACK;
		Node parent = nil;
		Node left = nil;
		Node right =  nil;
		private Node(int element) {
			this.element = element;
		}
	}
	/**
	 * Insert
	 * */
	public void insert(int element) {
		insert(new Node(element));
	}

	private void insert(Node node) {
		Node current = root;
		if(root == nil) { // 루트 노드가 비어있다면
			root = node; // 루트 노드에 해당 노드 입력
			node.color = BLACK; // 루트 노드는 블랙임
		} else {
			node.color = RED; // 새로운 노드는 항상 RED
			while(true) {
				if (node.element < current.element) { // 이진 탐색 노드 조건에 따라 왼쪽에 위치하게 해야 함
					if (current.left == nil) { // 왼쪽이 없다면,
						current.left = node; // 그 곳에 새로운 노드를 위치시킴
						node.parent = current; // 새로운 노드에 부모도 표시해줌
						break;
					} else {
						current = current.left;
					}
				} else { // 오른쪽에 위치해야 함
					if(current.right == nil) {
						current.right = node;
						node.parent = current;
						break;
					} else {
						current = current.right;
					}
				}
			}
			fixAfterInsert(node); // 색상 및 균형 복구
		}
	}

	private void fixAfterInsert(Node node) {
		// 삽입된 노드의 부모가 RED일 동안 반복한다.
		while (node.parent.color == RED) {
			Node ancestor; // 조부모 노드의 다른 자식 (삼촌 노드)을 가리킴

			// 부모가 조부모의 왼쪽 자식일 경우
			if (node.parent == node.parent.parent.left) {
				ancestor = node.parent.parent.right; // 삼촌 노드는 조부모의 오른쪽 자식

				// CASE 1 : 삼촌이 RED인 경우
				if (ancestor != nil & ancestor.color == RED) {
					// 부모와 삼촌을 BLACK으로, 조부모를 RED로 바꿈
					node.parent.color = BLACK;
					ancestor.color = BLACK;
					node.parent.parent.color = RED;

					// 조부모를 기준으로 다시 확인
					node = node.parent.parent;
					continue;
				}

				// 삼촌이 BLACK인 경우
				if (node == node.parent.right) { // CASE 2: 삼촌 BLACK + 현재 노드가 부모의 오른쪽 자식인 경우
					node = node.parent; // 부모를 기준으로 좌회전 필요
					rotateLeft(node);
				}

				// CASE 3: 현재 노드가 부모의 왼쪽 자식인 경우
				node.parent.color = BLACK;
				node.parent.parent.color = RED; // 조부모를 RED로 설정
				rotateRight(node.parent.parent); // 조부모를 기준으로 우회전

			} else { // 부모가 조부모의 오른쪽 자식일 경우 (위의 과정을 좌/우 대칭적으로 처리)
				ancestor = node.parent.parent.left; // 삼촌 노드는 조부모의 왼쪽 자식

				// CASE 1: 삼촌이 RED인 경우
				if (ancestor != nil && ancestor.color == RED) {
					node.parent.color = BLACK;
					ancestor.color = BLACK;
					node.parent.parent.color = RED;

					node = node.parent.parent;
					continue;
				}

				// CASE 2: 삼촌이 BLACK이고 현재 노드가 부모의 왼쪽 자식인 경우
				if (node == node.parent.left) {
					node = node.parent; // 부모를 기준으로 우회전 필요
					rotateRight(node);
				}

				// CASE 3: 삼촌이 BLACK이고 현재 노드가 부모의 오른쪽 자식인 경우
				node.parent.color = BLACK; // 부모를 BLACK으로 설정
				node.parent.parent.color = RED; // 조부모를 RED로 설정
				rotateLeft(node.parent.parent); // 조부모를 기준으로 좌회전
			}
		}
		root.color = BLACK;
	}

	/**
	 * 노드 삭제
	 * */
	public boolean delete(int element) {
		return delete(new Node(element)); // 삭제할 요소를 포함한 노드를 삭제
	}

	private boolean delete(Node toDelete) {
		// 삭제할 노드를 찾음
		if ((toDelete = contains(toDelete, root)) == null) {
			return false; // 트리에 노드가 없으면 false 반환
		}

		Node p; // 삭제 후 대체할 노드
		Node q = toDelete; // 삭제할 노드
		int qColor = q.color; // 삭제될 노드의 색상 저장

		if (toDelete.left == nil) { // 왼쪽 자식이 없는 경우
			p = toDelete.right; // 오른쪽 자식을 대체 노드로 설정
			replace(toDelete, toDelete.right);
		} else if (toDelete.right == nil) { // 오른쪽 자식이 없는 경우
			p = toDelete.left; // 왼쪽 자식을 대체 노드로 설정
			replace(toDelete, toDelete.left);
		} else {
			// 삭제할 노드의 successor(후계자)를 찾음
			q = minimum(toDelete.right); // 오른쪽 서브트리에서 최소값 노드 찾기
			qColor = q.color; // 후계자의 색상 저장
			p = q.right; // 후계자의 오른쪽 자식을 대체 노드로 설정
			if (q.parent == toDelete) {
				p.parent = q; // 후계자의 부모를 설정
			} else {
				replace(q, q.right); // 후계자를 삭제 위치로 대체
				q.right = toDelete.right; // 삭제 노드의 오른쪽 자식을 연결
				q.right.parent = q;
			}
			replace(toDelete, q); // 삭제 노드를 후계자로 대체
			q.left = toDelete.left; // 삭제 노드의 왼쪽 자식을 연결
			q.left.parent = q;
			q.color = toDelete.color; // 후계자의 색상을 삭제 노드 색상으로 설정
		}

		if (qColor == BLACK) {
			// 삭제로 인해 트리 균형이 깨졌다면 복구
			fixAfterDelete(p);
		}

		return true;
	}

	private void fixAfterDelete(Node p) {
		while (p != root && p.color == BLACK) {
			if (p == p.parent.left) {
				Node ppr = p.parent.right;
				if (ppr.color == RED) {
					ppr.color = BLACK;
					p.parent.color = RED;
					rotateLeft(p.parent);
					ppr = p.parent.right;
				}
				if (ppr.left.color == BLACK && ppr.right.color == BLACK) {
					ppr.color = RED;
					p = p.parent;
					continue;
				} else if (ppr.right.color == BLACK) {
					ppr.left.color = BLACK;
					ppr.color = RED;
					rotateRight(ppr);
					ppr = p.parent.right;
				}
				if (ppr.right.color == RED) {
					ppr.color = p.parent.color;
					p.parent.color = BLACK;
					ppr.right.color = BLACK;
					rotateLeft(p.parent);
					p = root;
				}
			} else {
				Node ppl = p.parent.left;
				if (ppl.color == RED) {
					ppl.color = BLACK;
					p.parent.color = RED;
					rotateRight(p.parent);
					ppl = p.parent.left;
				}
				if (ppl.right.color == BLACK && ppl.left.color == BLACK) {
					ppl.color = RED;
					p = p.parent;
					continue;
				} else if (ppl.left.color == BLACK) {
					ppl.right.color = BLACK;
					ppl.color = RED;
					rotateLeft(ppl);
					ppl = p.parent.left;
				}
				if (ppl.left.color == RED) {
					ppl.color = p.parent.color;
					p.parent.color = BLACK;
					ppl.left.color = BLACK;
					rotateRight(p.parent);
					p = root;
				}
			}
		}

		p.color = BLACK;
	}

	// 트리에 특정 요소가 있는지 확인
	public boolean contains(int element) {
		Node result = contains(new Node(element), root); // 요소를 검색
		return result != null;
	}

	private Node contains(Node toFind, Node node) {

		if (root == nil) {
			return null; // 빈 트리
		}

		// 요소를 찾기 위해 탐색
		if (toFind.element < node.element) {
			if (node.left != nil) {
				return contains(toFind, node.left);
			}
		} else if (toFind.element > node.element) {
			if (node.right != nil) {
				return contains(toFind, node.right);
			}
		} else if (toFind.element == node.element) {
			return node; // 요소를 찾은 경우 노드 반환
		}

		return null;
	}

	// 트리 내용을 출력 (중위 순회)
	public void print() {
		System.out.println("In-Order Traversal:");
		print(root);
	}

	private void print(Node node) {

		if (node == nil) {
			return; // nil 노드는 출력하지 않음
		}

		print(node.left); // 왼쪽 서브트리 출력
		System.out.print(((node.color == RED) ? "Color: Red " : "Color: Black ")
			+ "Element: " + node.element + "\n");
		print(node.right); // 오른쪽 서브트리 출력
	}

    /*
    HELPER METHODS
     */

	// 왼쪽 회전 (Left Rotation)
	private void rotateLeft(Node node) {
		// 회전 수행
		if (node.parent != nil) {
			if (node == node.parent.left) {
				node.parent.left = node.right;
			} else {
				node.parent.right = node.right;
			}

			node.right.parent = node.parent; // 부모 노드를 갱신
			node.parent = node.right; // 오른쪽 자식을 부모로 설정

			if (node.right.left != nil) {
				node.right.left.parent = node; // 왼쪽 자식을 갱신
			}

			node.right = node.right.left; // 오른쪽 자식의 왼쪽 자식을 설정
			node.parent.left = node; // 부모의 왼쪽 자식을 갱신
		} else {
			// 루트를 회전
			Node right = root.right;
			root.right = right.left;
			right.left.parent = root;
			root.parent = right;
			right.left = root;
			right.parent = nil;
			root = right; // 새 루트 설정
		}
	}

	// 오른쪽 회전 (Right Rotation)
	private void rotateRight(Node node) {
		// 회전 수행
		if (node.parent != nil) {
			if (node == node.parent.left) {
				node.parent.left = node.left;
			} else {
				node.parent.right = node.left;
			}

			node.left.parent = node.parent; // 부모 노드를 갱신
			node.parent = node.left; // 왼쪽 자식을 부모로 설정

			if (node.left.right != nil) {
				node.left.right.parent = node; // 오른쪽 자식을 갱신
			}

			node.left = node.left.right; // 왼쪽 자식의 오른쪽 자식을 설정
			node.parent.right = node; // 부모의 오른쪽 자식을 갱신
		} else {
			// 루트를 회전
			Node left = root.left;
			root.left = root.left.right;
			left.right.parent = root;
			root.parent = left;
			left.right = root;
			left.parent = nil;
			root = left; // 새 루트 설정
		}
	}


	private void replace(Node p, Node q) {

		if (p.parent == nil) {
			root = q;
		} else if (p == p.parent.left) {
			p.parent.left = q;
		} else {
			p.parent.right = q;
		}

		q.parent = p.parent;
	}

	private Node minimum(Node n) {
		while (n.left != nil) {
			n = n.left;
		}
		return n;
	}

}
