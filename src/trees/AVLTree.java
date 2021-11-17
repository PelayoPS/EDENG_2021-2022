package trees;


public class AVLTree<T extends Comparable<T>> {

	private AVLNode<T> root;

	/**
	 * Returns the root
	 * 
	 * @return
	 */
	public AVLNode<T> getRoot() {
		return root;
	}

	/**
	 * Sets the root
	 * 
	 * @param root
	 */
	public void setRoot(AVLNode<T> root) {
		this.root = root;
	}

	/**
	 * Travels the tree in order to add a new element
	 * 
	 * @param element
	 */
	public void add(T element) {
		root = add(element, root);
	}

	/**
	 * Private method that adds the element.
	 * 1 if null then return new node with the element
	 * 2 keeps the element in a variable
	 * 3 if the element is equal to theRoot element then
	 * 		throw exception(already in he tree)
	 * 4 if the element is less than theRoot element then
	 * 		setLeft of theRoot and call add to check child
	 * 5 if the element is greater than theRoot element then
	 * 		setRight of theRoot and call add to check child
	 * 6 if theRoot not null return itself
	 * @param element
	 * @param theRoot
	 * @return
	 */
	private AVLNode<T> add(T element, AVLNode<T> theRoot) {
		// 1
		if (theRoot == null) {
			return new AVLNode<T>(element);
		}
		// 2
		T rootElement = theRoot.getElement();
		// 3
		if (element.compareTo(rootElement) == 0) {
			throw new IllegalArgumentException("Node already in the tree");
		}
		// 4
		if (element.compareTo(rootElement) < 0) {
			theRoot.setLeft(add(element, theRoot.getLeft()));
		}
		// 5
		if (element.compareTo(rootElement) > 0) {
			theRoot.setRight(add(element, theRoot.getRight()));
		}
		// 6
		updateHeight(theRoot);
		return updateBF(theRoot);
	}
	
	
	/**
	 * Travels the tree and returns true when the element is found
	 * @param element
	 * @return
	 */
	public boolean search(T element) {
		return search(element, root);
	}

	/**
	 * Private method that searchs for the element.
	 * 1 if null then return false
	 * 2 keeps the element in a variable
	 * 3 if the element is equal to theRoot element then
	 * 		return true
	 * 4 if the element is less than theRoot element then
	 * 		search left child
	 * 5 if the element is greater than theRoot element then
	 * 		search right child
	 * 6 return false
	 * @param element
	 * @param theRoot
	 * @return
	 */
	private boolean search(T element, AVLNode<T> theRoot) {
		// 1
		if (theRoot == null) {
			return false;
		}
		// 2
		T rootElement = theRoot.getElement();
		// 3
		if (element.compareTo(rootElement) == 0) {
			return true;
		}
		// 4
		if (element.compareTo(rootElement) < 0) {
			return search(element, theRoot.getLeft());
		}
		// 5
		if (element.compareTo(rootElement) > 0) {
			return search(element, theRoot.getRight());
		}
		
		return false;
	}
	
	
	/**
	 * returns the max in the tree
	 * @param theRoot
	 * @return
	 */
	public T getMax(AVLNode<T> theRoot) {
		while(theRoot.getRight() != null) {
			theRoot = theRoot.getRight();
		}
		return theRoot.getElement();
	}
	
	/**
	 * Travels the tree in order to remove a new element
	 * 
	 * @param element
	 * @throws Exception 
	 */
	public void remove(T element) throws Exception {
		root = remove(element, root);
	}

	/**
	 * Private method that removes the element.
	 * 1 if null then return null
	 * 2 keeps the element in a variable
	 * 3 if the element is equal to theRoot element then
	 * 		return theRoot.getLeft()
	 * 4 if the element is less than theRoot element then
	 * 		setLeft of theRoot and call remove to check child
	 * 5 if the element is greater than theRoot element then
	 * 		setRight of theRoot and call remove to check child
	 * 6 if theRoot not null return itself
	 * @param element
	 * @param theRoot
	 * @return
	 * @throws Exception 
	 */
	private AVLNode<T> remove(T element, AVLNode<T> theRoot) throws Exception {
		// 1
		if (theRoot == null) {
			throw new Exception("Element not found");
		}
		// 2
		T rootElement = theRoot.getElement();
		// 3
		if (element.compareTo(rootElement) == 0) {
			//no child
			if(theRoot.getLeft() == null && theRoot.getRight() == null) {
				return null;
			}
			//only left child
			if(theRoot.getLeft() != null && theRoot.getRight() == null) {
				return theRoot.getLeft();
			}
			//only right child
			if(theRoot.getLeft() == null && theRoot.getRight() != null) {
				return theRoot.getRight();
			}
			//both children
			if(theRoot.getLeft() != null && theRoot.getRight() != null) {
				theRoot.setElement(getMax(theRoot.getLeft()));
				theRoot.setLeft(remove(theRoot.getElement(),theRoot.getLeft()));
			}
						
		}
		// 4
		if (element.compareTo(rootElement) < 0) {
			theRoot.setLeft(remove(element, theRoot.getLeft()));
		}
		// 5
		if (element.compareTo(rootElement) > 0) {
			theRoot.setRight(remove(element, theRoot.getRight()));
		}
		// 6
		updateHeight(theRoot);
		return updateBF(theRoot);
	}
	
	/**
	 * Private method that updates the height of the tree using the root
	 * @param theRoot
	 */
	private void updateHeight(AVLNode<T> theRoot) {
		theRoot.updateHeight();
	}
	
	/**
	 * Private method that updates the balance factor of the tree using the root
	 * @param theRoot
	 * @return
	 */
	private AVLNode<T> updateBF (AVLNode<T> theRoot) {
		if (theRoot.getBF() == -2) {
			if(theRoot.getLeft().getBF() <= 0) {
				theRoot = singleLeftRotation(theRoot);
			} else {
				theRoot = doubleLeftRotation(theRoot);
			}
		}
		if (theRoot.getBF() == 2) {
			if(theRoot.getRight().getBF() >= 0 ) {
				theRoot = singleRightRotation(theRoot);
			} else {
				theRoot = doubleRightRotation(theRoot);
			}
		}
		theRoot.updateHeight();
		return theRoot;
	}
	

	
	/**
	 * Private method that rotates the tree to the left
	 * @param theRoot
	 * @return
	 */
	private AVLNode<T> singleLeftRotation (AVLNode<T> b) {
		AVLNode<T> a = b.getLeft();
		AVLNode<T> c = a.getRight();
		a.setRight(b);
		b.setLeft(c);
		b.updateHeight();
		return a;
	}
	
	/**
	 * Private method that rotates the tree to the right
	 * @param theRoot
	 * @return
	 */
	private AVLNode<T> singleRightRotation (AVLNode<T> b) {
		AVLNode<T> a = b.getRight();
		AVLNode<T> c = a.getLeft();
		a.setLeft(b);
		b.setRight(c);
		b.updateHeight();
		return a;
	}
	
	/**
	 * Private method that rotates the tree to the left double rotation
	 * @param theRoot
	 * @return
	 */
	private AVLNode<T> doubleLeftRotation (AVLNode<T> c) {
		AVLNode<T> a = c.getLeft();
		AVLNode<T> b = a.getRight();
		c.setLeft(b.getRight());
		a.setRight(b.getLeft());
		b.setLeft(a);
		b.setRight(c);
		b.updateHeight();
		a.updateHeight();
		c.updateHeight();
		return b;
	}
	
	
	/**
	 * Private method that rotates the tree to the right double rotation
	 * @param theRoot
	 * @return
	 */
	private AVLNode<T> doubleRightRotation (AVLNode<T> c) {
		
		AVLNode<T> a = c.getRight();
		AVLNode<T> b = a.getLeft();
		
		c.setRight(b.getLeft());
		a.setLeft(b.getRight());
		b.setRight(a);
		b.setLeft(c);
		
		
		b.updateHeight();
		a.updateHeight();
		c.updateHeight();
		return b;
	}
	
	/**
	 * @return the height of the tree
	 */
	public int getHeight() {
		return getHeight(root) + 1;
	}
	
	/**
	 * recursive method for the getHeight
	 * @param theRoot
	 * @return
	 */
	private int getHeight(AVLNode<T> theRoot) {
		int rightHeight = 0;
		int leftHeight = 0;
		if (theRoot.getRight() == null) {
			rightHeight = 0;//if the right chils is null right height is 0
		} else {
			rightHeight = getHeight(theRoot.getRight()) + 1;
			//otherwise it is the height of the right child + 1
		}
		if (theRoot.getLeft() == null) {
			leftHeight = 0;//if the left child is null left height is 0
		} else {
			leftHeight = getHeight(theRoot.getLeft()) + 1;
			//otherwise it is the height of the left child + 1
		}
		//return the max of the left and right height
		return Math.max(rightHeight, leftHeight);
	}
	
	/**
	 * @return the string representation of the tree
	 */
	public String toString() {
		return toString(this.root);
	}
	
	/**
	 * recursive method for the toString
	 * @param theRoot
	 * @return
	 */
	private String toString(AVLNode<T> theRoot) {
		StringBuilder result = new StringBuilder();
		if (theRoot == null) {
			result.append("-");
		} else {
			result.append(theRoot.toString()); //+ "(" + theRoot.getBF() + ")");
			result.append(toString(theRoot.getLeft()));
			result.append(toString(theRoot.getRight())); 
		}
		return result.toString();
	}
	
}
