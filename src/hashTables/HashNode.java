package hashTables;

public class HashNode<T> {
	
	private T element;
	private int status;
	public static final int EMPTY = 0;
	public static final int VALID = 1;
	public static final int DELETED = 2;
	
	public HashNode() {
		status = EMPTY;
	}
	
	public HashNode(T element) {
		this.element = element;
		status = VALID;
	}
	
	public T getElement() {
		return element;
	}
	public void setElement(T element) {
		this.element = element;
		this.status = VALID;
	}
	public int getStatus() {
		return status;
	}
	public void removeElement() {
		this.status = DELETED;
	}
	@Override
	public String toString() {
		return "(" + this.status + ") = " + this.element; 
	}
}
