package binarySearchTree;

import java.util.LinkedList;
import java.util.Queue;

public class MyBST {
	MyBSTNode root;

	public void insert(int data) {
		root = insert(root, data);
	}

	public MyBSTNode insert(MyBSTNode node, int data) {
		if (node == null) {
			return node = new MyBSTNode(data);
		}

		if (data < node.data) {
			node.left = insert(node.left, data);
		} else if (data > node.data) {
			node.right = insert(node.right, data);
		}
		return node;
	}

	public void inOrder(MyBSTNode root) {
		if (root != null) {
			inOrder(root.left);
			System.out.print(root.data + " ");
			inOrder(root.right);
		}
	}

	public void preOrder(MyBSTNode root) {
		if (root != null) {
			System.out.print(root.data + " ");
			preOrder(root.left);
			preOrder(root.right);
		}
	}

	public void postOrder(MyBSTNode root) {
		if (root != null) {
			postOrder(root.left);
			postOrder(root.right);
			System.out.print(root.data + " ");
		}
	}
	
	public void levelOrder(MyBSTNode root) {
		Queue<MyBSTNode> queueObj = new LinkedList<>();
		queueObj.add(root);
		while (!queueObj.isEmpty()) {
			MyBSTNode head = queueObj.poll();
			System.out.print(head.data+" ");
			if (head.left != null)
				queueObj.add(head.left);
			if (head.right != null)
				queueObj.add(head.right);
		}
	}

	private void printInOrder() {
		System.out.print("InOrder -> ");
		inOrder(root);
		System.out.println();
	}

	private void printPreOrder() {
		System.out.print("PreOrder -> ");
		preOrder(root);
		System.out.println();
	}

	private void printPostOrder() {
		System.out.print("PostOrder -> ");
		postOrder(root);
		System.out.println();
	}
	
	private void printLevelOrder() {
		System.out.print("LevelOrder -> ");
		levelOrder(root);
		System.out.println();
	}

	private boolean search(MyBSTNode root, int data) {
		if (root == null) {
			return false;
		}
		if (root.data == data) {
			return true;
		} else if (data < root.data) {
			return search(root.left, data);
		} else {
			return search(root.right, data);
		}
	}

	private boolean search(int data) {
		return search(root, data);
	}

	private MyBSTNode getLeastFromRightSubTree(MyBSTNode node) {
		if (node.left.left == null) {
			MyBSTNode tempLessDataNode = node.left;
			node.left = node.left.right;
			return tempLessDataNode;
		}
		return getLeastFromRightSubTree(node.left);
	}

	private MyBSTNode deleteFoundObject(MyBSTNode node) {
		if (node.left == null && node.right == null)
			return null;
		else if (node.left == null)
			return node.right;
		else if (node.right == null)
			return node.left;
		else {
			MyBSTNode lessRightTreeNode = null;
			if (node.right.left == null) {
				lessRightTreeNode = node.right;
				node.right = node.right.right;
			} else {
				lessRightTreeNode = getLeastFromRightSubTree(node.right);
			}
			node.data = lessRightTreeNode.data;
			return node;
		}
	}

	private MyBSTNode deleteElement(MyBSTNode root, int data) {
		if (root.data == data) {
			return deleteFoundObject(root);
		} else if (data < root.data) {
			root.left = deleteElement(root.left, data);
		} else {
			root.right = deleteElement(root.right, data);
		}
		return root;
	}

	private void delete(int data) {
		if (search(data)) {
			deleteElement(root, data);
			System.out.println("Deleted -> " + data);
		} else {
			System.out.println("Element " + data + " not found to delete");
		}
	}

	private int getLeastNodeFromRightSubTree(MyBSTNode node) {
		while (node != null && node.left != null) {
			node = node.left;
		}
		return node.data;
	}

	private MyBSTNode deleteNode(MyBSTNode root, int data) {
		if (data < root.data) {
			root.left = deleteNode(root.left, data);
		} else if (data > root.data) {
			root.right = deleteNode(root.right, data);
		} else {
			if (root.left == null && root.right == null)
				return null;
			else if (root.left == null)
				return root.right;
			else if (root.right == null)
				return root.left;
			else {
				int lessRightTreeData = getLeastNodeFromRightSubTree(root.right);
				root.data = lessRightTreeData;
				root.right = deleteNode(root.right, lessRightTreeData);
			}
		}
		return root;
	}

	private void deleteNode(int data) {
		root = deleteNode(root, data);
	}
	
	private int getMinValue(MyBSTNode node) {
		while (node != null && node.left != null) {
			node = node.left;
		}
		return node.data;
	}
	
	public int minValue() {
		return getMinValue(root);
	}

	/*
	 * 			   10
	 * 			/      \
	 * 			3       18
	 * 		   / \   /     \
	 * 		  1   9 15      20
	 * 			   /  \    /  \
	 * 			  13   17 19  27
	 */
	public static void main(String[] args) {
		MyBST bstObj = new MyBST();
		// INSERT
		bstObj.insert(10);
		bstObj.insert(3);
		bstObj.insert(18);
		bstObj.insert(9);
		bstObj.insert(15);
		bstObj.insert(1);
		bstObj.insert(20);
		bstObj.insert(13);
		bstObj.insert(17);
		bstObj.insert(19);
		bstObj.insert(27);

		// TRAVERSE
		bstObj.printInOrder();
		bstObj.printPreOrder();
		bstObj.printPostOrder();
		bstObj.printLevelOrder();

		// SEARCH
		System.out.println(bstObj.search(10) ? "found -> 10" : "Not found -> 10");

		// DELETE
//		bstObj.delete(10);

		// DELETE BY OPTIMISTIC WAY
		bstObj.deleteNode(10);

		// TRAVERSE
		bstObj.printInOrder();
		
		System.out.println("Minimum value : "+ bstObj.minValue());
	}
}

class MyBSTNode {
	MyBSTNode left, right;
	int data;

	public MyBSTNode(int data) {
		this.data = data;
		this.left = this.right = null;
	}

}