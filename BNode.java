/**
 * The purpose of this class is to represent the nodes of the BinarySearchTree
 * in which each node will store Object Pel.
 * 
 * @author Kevin Chau #251215166
 *
 */
public class BNode {
	// Declaring Private Variables
	private Pel value;
	private BNode left, right, parent;

	/**
	 * Constructor for a node where its values are specified
	 * 
	 * @param value
	 * @param left
	 * @param right
	 * @param parent
	 */
	public BNode(Pel value, BNode left, BNode right, BNode parent) {
		this.value = null;
		this.parent = parent;
		this.left = left;
		this.right = right;

	}

	/**
	 * Constructor for a leaf node.
	 */
	public BNode() {
		this.value = null;
		this.parent = null;
		this.left = null;
		this.right = null;
	}

	/**
	 * return the parent of this node
	 * 
	 * @return
	 */
	public BNode parent() {
		return parent;
	}

	/**
	 * Setter method to set parent of this Node to new Parent
	 * 
	 * @param newParent
	 */
	public void setParent(BNode newParent) {
		this.parent = newParent;
	}

	/**
	 * Setter method to set the left child of this Node to new Left
	 * 
	 * @param p
	 */
	public void setLeftChild(BNode p) {
		this.left = p;

	}

	/**
	 * Setter method to set the right child of this Node to new right
	 * 
	 * @param p
	 */
	public void setRightChild(BNode p) {
		this.right = p;
	}

	/**
	 * Setter method to set the contents of this Node to new Pel Value
	 * 
	 * @param value
	 */
	public void setContent(Pel value) {
		this.value = value;
	}

	/**
	 * Method to check whether or not the node is a leaf
	 * 
	 * @return true if both children are null
	 * @return false if there is at least one child
	 */
	public boolean isLeaf() {
		return ((this.leftChild() == null) && (this.rightChild() == null));
	}

	/**
	 * Getter method to return value in this Node
	 * 
	 * @return
	 */
	public Pel getData() {
		return value;
	}

	/**
	 * Getter method to return the left child of this Node
	 * 
	 * @return
	 */
	public BNode leftChild() {
		return left;
	}

	/**
	 * Getter method to return the right child of this Node
	 * 
	 * @return
	 */
	public BNode rightChild() {
		return right;
	}

}
