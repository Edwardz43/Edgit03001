package tw.org.iii.ed;

public class BinarySearchTree {
	protected Node root;
	public BinarySearchTree (){
		this.root = null;
	}
	
	public void insert(int key) {
		if (this.root == null)
			this.root = new Node(key);
		else 
			insert(this.root, key);
	}
	
	protected void insert(Node p, int key) {
		// p can never be null
		if(p.key > key) {
			if(p.left == null)
				p.left = new Node(key);
			else insert(p.left, key);
		}else {
			if(p.right == null)
				p.right = new Node(key);
			else 
				insert(p.right, key);
		}
	}
	
	public final boolean search(int key) {
		if(this.root == null)
			return false;
		else
			return search(this.root, key);
		
	}
	public final boolean search(Node p, int key) {
		if(p.key > key) {
			if(p.left == null)
				return false;
			else 
				return search(p.left, key);
		}else if (p.key < key){
			if(p.right == null)
				return false;
			else 
				return search(p.right, key);
		}else
			return true;
	}
	
	public final void printInOrder() {
		if (this.root == null)
			System.out.print(" ");
		else 
			printInOrder(this.root);
	}
	
	public final void printInOrder(Node p) {
		if(p.left != null)
			printInOrder(p.left);
		System.out.print(p.key + " ");
		if(p.right != null)
			printInOrder(p.right);
		
	}
	
	public final void printPreOrder() {
		if (this.root == null)
			System.out.print(" ");
		else 
			printPreOrder(this.root);
	}
	
	public final void printPreOrder(Node p) {
		System.out.print(p.key + " ");
		if(p.left != null)
			printPreOrder(p.left);
		if(p.right != null)
			printPreOrder(p.right);	
	}
	
	public final void printPostOrder() {
		if (this.root == null)
			System.out.print(" ");
		else 
			printPostOrder(this.root);
	}
	
	public final void printPostOrder(Node p) {
		if(p.left != null)
			printPostOrder(p.left);
		if(p.right != null)
			printPostOrder(p.right);	
		System.out.print(p.key + " ");
	}
	
	public int getRoot() {
		return this.root.getKey();
	}
}

class Node{
	public int key;
	public Node left;
	public Node right;
	public Node(int key) {
		this.key = key;
		this.left = this.right = null;
	}
	
	public int getKey() {
		return this.key;
	}
}
