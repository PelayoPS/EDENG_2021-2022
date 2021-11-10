package trees;

public class BSTNode<T extends Comparable<T>> {
	private T element;
	private BSTNode<T> left, right;
	private int height;


	/**
	 * Default constructor for the BSTNode
	 * 
	 * @param element2
	 */
	public BSTNode(T element2) {
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
	public BSTNode<T> getLeft() {
		return left;
	}

	/**
	 * Sets the left child
	 * 
	 * @param left
	 */
	public void setLeft(BSTNode<T> left) {
		this.left = left;
	}

	/**
	 * Returns the right child
	 * 
	 * @return
	 */
	public BSTNode<T> getRight() {
		return right;
	}

	/**
	 * Sets the right child
	 * 
	 * @param right
	 */
	public void setRight(BSTNode<T> right) {
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
		if(getLeft() != null) {
			if(getLeft().getHeight() == 0) {
				setHeight(getHeight() + 1);
			}else {
			setHeight(getLeft().getHeight() + getHeight());
			}
		}
		if(getRight() != null) {
			if(getRight().getHeight() == 0) {
				setHeight(getHeight() + 1);
			}else {
			setHeight(getRight().getHeight() + getHeight());
			}
		}
		if(getRight() != null && getLeft() != null) {
			if(getRight().getHeight() == 0 && getLeft().getHeight() == 0) {
				setHeight(getHeight() + 1);
			} else {
				setHeight(Math.max(getLeft().getHeight(), getRight().getHeight()));
			}
		}
	}
}
