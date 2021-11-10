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
		this.left = null;
		this.right = null;
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
	
	public String toString() {
		return getElement().toString();
	}
	
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void updateHeight() {
		if (getRight() == null && getLeft() == null) {
			setHeight(0);
		}
		if (getRight() == null && getLeft() != null) {
			setHeight(getLeft().getHeight() + 1);
		} 
		if (getRight() != null && getLeft() == null) {
			setHeight(getRight().getHeight() + 1);
		}
		if (getRight() != null && getLeft() != null) {
			if (getRight().getHeight() > getLeft().getHeight()) {
				setHeight(getRight().getHeight() + 1);
			} else {
				setHeight(getLeft().getHeight() + 1);
			}
		}
	}

	
	public int getBF() {		
		int right = 0;
		int left = 0;
		if (getRight() == null && getLeft() != null) {
			right = getLeft().getHeight() - 1;
		}
		if (getRight() != null && getLeft() == null) {
			left =  getRight().getHeight() + 1;
		}
		return right - left;
	}
	
	
}
