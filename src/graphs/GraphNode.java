package graphs;

public class GraphNode<T> {

	private T element;
	private boolean visited = false;

	/**
	 * 
	 * @param element
	 * @param visited
	 */
	public GraphNode(T element) {
		setElement(element);
		setVisited(visited);
	}

	/**
	 * returns the element
	 * 
	 * @return
	 */
	public T getElement() {
		return element;
	}

	/**
	 * sets the element to he one provided as parameter
	 * 
	 * @param element
	 */
	public void setElement(T element) {
		this.element = element;
	}

	/**
	 * returns the value of visited of this node
	 * 
	 * @return
	 */
	public boolean isVisited() {
		return visited;
	}

	/**
	 * sets he value of visited to the one provided as parameter
	 * 
	 * @param visited
	 */
	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	/**
	 * returns a string representation of the node
	 * 
	 * @return
	 */
	public String toString() {
		return String.format("GN(N:%s/V:%b)", this.element, this.visited);
	}

	/**
	 * prints the string representation of the node
	 */
	public void print() {
		System.out.println(this.toString());
	}

}
