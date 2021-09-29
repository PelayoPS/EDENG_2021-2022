package algortihms;

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
	 * 
	 * @return
	 */
	public T getElement() {
		return element;
	}
	
	/**
	 * 
	 * @param element
	 */
	public void setElement(T element) {
		this.element = element;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isVisited() {
		return visited;
	}
	
	/**
	 * 
	 * @param visited
	 */
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	
	/**
	 * 
	 * @return
	 */
	public String toString() {
		return String.format("GN(N:%s/V:%b)",this.element,this.visited);
	}
	
	/**
	 * 
	 */
	public void print() {
		System.out.println(this.toString());
	}
	
	
	
}
