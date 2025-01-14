package tree;

/**
 * BinarySearchTree
 */
public class BinarySearchTree<T extends Comparable<T>> {
	private Node root = null;
	private int nodeCnt;

	private class Node {
		private T element;

		private Node left;
		private Node right;

		Node(T element) {
			this.element = element;
			this.left = null;
			this.right = null;
		}

		Node(Node left, Node right, T element) {
			this.left = left;
			this.right = right;
			this.element = element;
		}
	}

	/**
	 * INSERT
	 */
	public boolean insert(T element) {
		if (element == null) {
			return false;
		}
		if (contains(element)) { // 중복 허용하지 않음
			return false;
		}
		root = insert(root, element);
		nodeCnt++;
		return true;
	}

	private Node insert(Node current, T element) {
		if (current == null) {
			return new Node(element);
		}

		if (element.compareTo(current.element) < 0) {
			current.left = insert(current.left, element);
		} else {
			current.right = insert(current.right, element);
		}

		return current;
	}

	/**
	 * DELETE
	 */
	public boolean delete(T element) {
		if (contains(element)) {
			root = delete(root, element);
			nodeCnt -= 1;
			return true;
		}
		return false;
	}

	private Node delete(Node node, T element) {
		if (node == null) {
			return null;
		}

		// 삭제할 대상 찾기위해 탐색 진행
		if (element.compareTo(node.element) < 0) {
			node.left = delete(node.left, element);
		} else if (element.compareTo(node.element) > 0) {
			node.right = delete(node.right, element);
		}

		// 삭제할 대상 찾음
		if (element.compareTo(node.element) == 0) {
			if (node.right == null) { // 삭제 대상의 왼쪽에 자식이 있을 수 있으므로
				Node left = node.left; // 복사해서
				node = null;
				return left; // 상위로 매핑해야 함
			} else if (node.left == null) { // 삭제 대상의 오른쪽에 자식이 있을 수 있으므로
				Node right = node.right; // 복사해서
				node = null;
				return right; // 상위로 매핑 진행
			} else { // 삭제 대상의 왼쪽 오른쪽 모두에 자식이 있을 때
				// 삭제하려는 노드 대신 적절한 대체 노드를 찾아야 한다
				// 대체 노드는 삭제하려는 노드보다 크지만, 오른쪽 서브트리에서 가장 작은 값을 가진 노드여야 한다
				Node minMostInRight = findLeftMostNode(node.right); // 삭제하려는 노드의 오른쪽 서브트리에서 가장 왼쪽에 있는 노드를 찾아 반환한다.
				node.element = minMostInRight.element; // 방금 대체 노드로 선택된 노드를 트리에서 제거한다
				node.right = delete(node.right, node.element); // 대체 노드로 선택된 노드를 트리에서 제거
			}
		}

		return node;
	}

	/**
	 * 최솟값
	 */
	public T min() {
		if (root == null) {
			return null;
		}
		return findLeftMostNode(root).element;
	}

	private Node findLeftMostNode(Node node) {
		while (node.left != null) {
			node = node.left;
		}
		return node;
	}

	/**
	 * 최댓값
	 */
	public T max() {
		if (root == null) {
			return null;
		}
		return findRightMostNode(root).element;
	}

	private Node findRightMostNode(Node node) {
		while (node.right != null) {
			node = node.right;
		}
		return node;
	}

	/**
	 * 루트값
	 */
	public T root() {
		if (root == null) {
			return null;
		}

		return root.element;
	}

	/**
	 * 트리 내 노드 갯수
	 */
	public int size() {
		return this.nodeCnt;
	}

	/**
	 * 트리 높이
	 */
	public int height() {
		return height(root);
	}

	private int height(Node node) {
		if (node == null)
			return 0;
		return Math.max(height(node.left), height(node.right)) + 1;
	}

	/**
	 * element가 있는지 찾는 함수
	 */
	public boolean contains(T element) {
		return contains(root, element);
	}

	private boolean contains(Node current, T element) {
		if (current == null || element == null) {
			return false;
		}
		if (element.compareTo(current.element) == 0) {
			return true;
		}
		return element.compareTo(current.element) < 0 ? contains(current.left, element)
			: contains(current.right, element);
	}

}