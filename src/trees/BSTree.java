package trees;


public class BSTree<T extends Comparable<T>> {

	private BSTNode<T> root;

	/**
	 * Returns the root
	 * 
	 * @return
	 */
	public BSTNode<T> getRoot() {
		return root;
	}

	/**
	 * Sets the root
	 * 
	 * @param root
	 */
	public void setRoot(BSTNode<T> root) {
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
	private BSTNode<T> add(T element, BSTNode<T> theRoot) {
		// 1
		if (theRoot == null) {
			return new BSTNode<T>(element);
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
		return theRoot;
	}
	
	/**
	 * Returns a string representation of the tree
	 */
	public String toString() {
		return toString(this.root);
	}
	
	/**
	 * Recursive method for the toString  
	 * @param theRoot
	 * @return
	 */
	private String toString(BSTNode<T> theRoot) {
		StringBuilder result = new StringBuilder();
		if (theRoot == null) {
			result.append("-");
		} else {
			//Format rootleft...right...
			result.append(String.format("%s(%d)",theRoot.toString(),theRoot.getHeight()));
			result.append(toString(theRoot.getLeft()));
			result.append(toString(theRoot.getRight())); 
		}
		return result.toString();
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
	private boolean search(T element, BSTNode<T> theRoot) {
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
	
	public T getMax(BSTNode<T> theRoot) {
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

	
	private BSTNode<T> remove(T element, BSTNode<T> theRoot) throws Exception {
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
			if(!(theRoot.getLeft() == null) && theRoot.getRight() == null) {
				return theRoot.getLeft();
			}
			//only right child
			if(theRoot.getLeft() == null && !(theRoot.getRight() == null)) {
				return theRoot.getRight();
			}
			//both children
			if(!(theRoot.getLeft() == null) && !(theRoot.getRight() == null)) {
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
		return theRoot;
	}
	
	private void updateHeight(BSTNode<T> theRoot) {
		theRoot.updateHeight();
	}
	
}
