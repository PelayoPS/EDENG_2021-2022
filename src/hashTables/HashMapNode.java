package hashTables;

public class HashMapNode<K, V> {
	
	private K element;
	private V value;
	private int status;
	public static final int EMPTY = 0;
	public static final int VALID = 1;
	public static final int DELETED = 2;
	
	public HashMapNode() {
		status = EMPTY;
	}
	
	public HashMapNode(K element, V value) {
		this.element = element;
		this.value = value;
		status = VALID;
	}
	
	public K getElement() {
		return element;
	}
	public void setElement(K element) {
		this.element = element;
		this.status = VALID;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
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
		return "(" + this.status + ") = " + this.element + " - " + this.value; 
	}
}
