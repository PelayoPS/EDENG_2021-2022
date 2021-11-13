package trees;

public class AVLNode<T extends Comparable<T>> {
	private T element;
	private AVLNode<T> left, right;
	private int height;


	/**
	 * Default constructor for the BSTNode
	 * 
	 * @param element2
	 */
	public AVLNode(T element2) {
		this.element = element2;
	}

	/**
	 * Returns the element
	 * 
	 * @return
	 */
	public T getElement() {
		return element;
	}

	/**
	 * Sets the element
	 * 
	 * @param element
	 */
	public void setElement(T element) {
		this.element = element;
	}

	/**
	 * Returns the left child
	 * 
	 * @return
	 */
	public AVLNode<T> getLeft() {
		return left;
	}

	/**
	 * Sets the left child
	 * 
	 * @param left
	 */
	public void setLeft(AVLNode<T> left) {
		this.left = left;
	}

	/**
	 * Returns the right child
	 * 
	 * @return
	 */
	public AVLNode<T> getRight() {
		return right;
	}

	/**
	 * Sets the right child
	 * 
	 * @param right
	 */
	public void setRight(AVLNode<T> right) {
		this.right = right;
	}

	/**
	 * returns true when the node has a left child
	 */
	private boolean hasLeft() {
		return this.left != null;
	}
	
	/**
	 * returns true when the node has a right child
	 */
	private boolean hasRight() {
		return this.right != null;
	}
	
	/**
	 * returns true when the node has a left and but not a right child
	 */
	private boolean hasOnlyLeft() {
		return hasLeft() && !hasRight();
	}
	
	/**
	 * returns true when the node has a right and but not a left child
	 */
	private boolean hasOnlyRight() {
		return !hasLeft() && hasRight();
	}
	
	/**
	 * returns true when the node doesn't have a left or right child
	 */
	private boolean hasNochild() {
		return right == null && left == null;
	}
	
	/**
	 * returns true when the node has a left and a right child
	 */
	private boolean hasTwochildren() {
		return right != null && left != null;
	}
	
	/**
	 * Returns a String representing the element
	 */
	public String toString() {
		return getElement().toString()  + "(" + getBF() + ")";
	}
	
	/**
	 * returns the height of the node
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * sets the height of the node
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * updates the height of the node
	 */
	public void updateHeight() {
		if (hasNochild()) {
			setHeight(0);// leaf
		}
		if (hasOnlyLeft()) {
			setHeight(getLeft().getHeight() + 1);// left child
		} else if (hasOnlyRight()) {
			setHeight(getRight().getHeight() + 1);// right child
		}
		if (hasTwochildren()) {
			if (getRight().getHeight() > getLeft().getHeight()) {
				setHeight(getRight().getHeight() + 1);//right height is greater
			} else {
				setHeight(getLeft().getHeight() + 1);//left height is greater
			}
		}
	}

	/**
	 * returns the balance factor of the node
	 */
	public int getBF() {
		int right = 0;
		int left = 0;
		if (hasRight()) {
			right = getRight().getHeight() + 1;// adds one if only right child
		}
		if (hasLeft()) {
			left =  getLeft().getHeight() + 1;// substract one if only left child
		}
		return right - left;// returns the difference
	}
	
	
}
