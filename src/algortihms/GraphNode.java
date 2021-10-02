package algortihms;

public class GraphNode<T> {

	private T element;
	private boolean visited = false;

	/**
	 * Default constructor for the GraphNode class
	 * 
	 * @param element
	 * @param visited
	 */
	public GraphNode(T element) {
		setElement(element);
		setVisited(visited);
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
	 * Returns if the node has been visited
	 * 
	 * @return
	 */
	public boolean isVisited() {
		return visited;
	}

	/**
	 * Sets the value of visited for the node
	 * 
	 * @param visited
	 */
	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	/**
	 * Returns a string representation of the node
	 * 
	 * @return
	 */
	public String toString() {
		return String.format("GN(N:%s/V:%b)", this.element, this.visited);
	}

	/**
	 * Prints the string representation of the node
	 */
	public void print() {
		System.out.println(this.toString());
	}

}
