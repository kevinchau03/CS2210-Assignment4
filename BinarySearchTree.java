/**
 * The purpose of this class is to implement an ordered dictionary using a
 * BinarySearchTree. Where each Node stores a pel object.
 * 
 * @author Kevin Chau #251215166 Date: 2022-11-19
 */

public class BinarySearchTree implements BinarySearchTreeADT {
	// Declare private variables
	private BNode root;

	/**
	 * Constructor that creates a tree with a root that is a leaf node.
	 */
	public BinarySearchTree() {
		root = new BNode();
	}

	/**
	 * Getter Method that returns the root of the tree
	 * 
	 * @return root
	 */
	public BNode getRoot() {
		// TODO Auto-generated method stub
		return root;
	}

	/**
	 * This method helps to return the node given a key
	 * 
	 * @param r
	 * @param key
	 * @return BNode r
	 */
	private BNode getNode(BNode r, Location key) {
		// When encounter a leaf return r
		if (r.isLeaf()) {
			return r;
		} else {
			int result = key.compareTo(r.getData().getLocus()); // Determine where the key is located
			if (result == 0) { // parent
				return r;
			} else if (result == -1) { // left child
				return getNode(r.leftChild(), key);
			} else { // right child
				return getNode(r.rightChild(), key);
			}
		}
	}

	/**
	 * This method returns the Pel object storing the given key
	 * 
	 * @param r
	 * @param key
	 * @return Pel object
	 */
	@Override
	public Pel get(BNode r, Location key) {
		// TODO Auto-generated method stub
		BNode curr = getNode(r, key); // Get curr node
		if (curr.getData() == null) { // If no data
			return null;
		} else {
			if (key.compareTo(curr.getData().getLocus()) == 0) { // if the key matches the curr key
				return curr.getData(); // return the Pel object
			} else {
				return null;
			}
		}

	}

	/**
	 * This method inserts newData in the tree
	 * 
	 * @param r
	 * @param data
	 */
	@Override
	public void put(BNode r, Pel data) throws DuplicatedKeyException {
		Pel p = get(r, data.getLocus()); // Get data from node r
		BNode curr = getNode(r, data.getLocus()); // Get node
		if (p != null) { // If there is no data
			throw new DuplicatedKeyException("This key already exists");
		} else {
			// Take the node and pass in data while setting its specified values
			curr.setContent(data);
			curr.setLeftChild(new BNode());
			curr.setRightChild(new BNode());
			curr.leftChild().setParent(curr);
			curr.rightChild().setParent(curr);
		}
	}

	/**
	 * This method helps to remove a Node from the Binary Search Tree
	 * 
	 * @param r
	 * @param key
	 * @return False if leaf
	 * @return True
	 */
	private boolean removeNode(BNode r, Location key) {
		BNode curr = getNode(r, key);
		if (curr.isLeaf()) {
			return false;
		} else {
			BNode parent = curr.parent(); // New node parent of curr parent
			// Checks to see if the left child is a leaf
			if (curr.leftChild().isLeaf()) {
				curr.rightChild().setParent(parent); // Let the other child be a child of parent
				if (parent != null) {
					// Sets the other childs parent to new parent instead of curr parent
					if (parent.getData().getLocus().compareTo(key) == 1) {
						curr.parent().setLeftChild(curr.rightChild());
					} else {
						curr.parent().setRightChild(curr.rightChild());
					}
				} else {
					// Set the other child to the root of the tree
					this.root = curr.rightChild();
				}
				// Checks to see if the right child is a leaf and do same thing
			} else if (curr.rightChild().isLeaf()) {
				curr.leftChild().setParent(parent);
				if (parent != null) {
					if (parent.getData().getLocus().compareTo(key) == 1) {
						curr.parent().setLeftChild(curr.leftChild());
					} else {
						curr.parent().setRightChild(curr.leftChild());
					}
				} else {
					this.root = curr.rightChild();
				}
			} else {
				// Otherwise take the smallest node in the right tree and copy it and remove it
				BNode s = getSmallest(curr.rightChild());
				curr.setContent(s.getData());
				removeNode(s, key);
			}
		}
		return true;
	}

	/**
	 * This method removes the data itme with a given key
	 * 
	 * @param r
	 * @param key
	 */
	@Override
	public void remove(BNode r, Location key) throws InexistentKeyException {
		Pel p = get(r, key);
		if (p != null) {
			removeNode(r, key); // Remove the node with the key
		} else {
			throw new InexistentKeyException("This key does not exist.");
		}

	}

	/**
	 * This method returns the Pel object with the smallest key larger than the
	 * given one
	 * 
	 * @param r
	 * @param key
	 * @return Pel data
	 */
	@Override
	public Pel successor(BNode r, Location key) {
		// TODO Auto-generated method stub
		if (r.isLeaf()) {
			return null;
		} else {
			BNode curr = getNode(r, key);
			if (!curr.isLeaf() && !curr.rightChild().isLeaf()) { // if the curr node and its right child are internal
				return getSmallest(curr.rightChild()).getData(); // get smallest from the right tree
			} else {
				// While curr has a parent and is the right child
				while (curr.parent() != null && (curr.parent().getData().getLocus().compareTo(key) == -1)) {
					curr = curr.parent(); // make curr parent
				}
				if (curr == root) { // if curr is root
					return null;
				} else {
					return curr.parent().getData(); // return parent data
				}
			}
		}
	}

	/**
	 * This method returns the Pel object with the largest key smaller than the
	 * given one
	 * 
	 * @param r
	 * @param key
	 * @return Pel data
	 */
	@Override
	public Pel predecessor(BNode r, Location key) {
		// TODO Auto-generated method stub
		if (r.isLeaf()) {
			return null;
		} else {
			BNode curr = getNode(r, key);
			if (!curr.isLeaf() && !curr.leftChild().isLeaf()) { // if the curr node and its left child are internal
				return getLargest(curr.leftChild()).getData(); // get the largest from the left tree
			} else {
				// While curr has a parent and is the left child
				while (curr.parent() != null && (curr.parent().getData().getLocus().compareTo(key) == 1)) {
					curr = curr.parent();
				}
				if (curr == root) {
					return null;
				} else {
					return curr.parent().getData();
				}
			}
		}
	}

	/**
	 * This method returns the Pel object in the tree with the smallest key
	 * 
	 * @param r
	 * @return Pel data from smallest
	 */
	@Override
	public Pel smallest(BNode r) throws EmptyTreeException {
		// TODO Auto-generated method stub
		BNode s = getSmallest(r); // Get the node with the smallest key
		if (s != null) { // If not empty
			return s.getData(); // return the Pel data
		} else { // If it does not contain any data
			throw new EmptyTreeException("Does not contain any data");
		}
	}

	/**
	 * This method helps to obtained the node with the smallest key
	 * 
	 * @param r
	 * @return BNode with smallest key
	 */
	private BNode getSmallest(BNode r) {
		if (r.isLeaf()) {
			return null;
		} else {
			BNode curr = r;
			// Iterate all the way to the left most side of tree
			while (!curr.isLeaf()) {
				curr = curr.leftChild();
			}
			return curr.parent(); // return the parent
		}
	}

	/**
	 * This method returns the Pel object in the tree with the largest key
	 * 
	 * @param r
	 * @return Pel data of largest
	 */
	@Override
	public Pel largest(BNode r) throws EmptyTreeException {
		// TODO Auto-generated method stub
		BNode l = getLargest(r); // Get the node containing largest key
		if (l != null) {
			return l.getData();
		} else {
			throw new EmptyTreeException("Does not contain any data");
		}
	}

	/**
	 * This method helps to get the node with the largest key
	 * 
	 * @param r
	 * @return BNode with largest key
	 */
	private BNode getLargest(BNode r) {
		if (r.isLeaf()) {
			return null;
		} else {
			BNode curr = r;
			// Iterate to the right most side of tree
			while (!curr.isLeaf()) {
				curr = curr.rightChild();
			}
			return curr.parent();
		}
	}
}
