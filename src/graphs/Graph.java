package graphs;

import java.util.ArrayList;

public class Graph<T> {
	protected ArrayList<GraphNode<T>> nodes;
	protected boolean[][] edges;
	protected double[][] weight;

	public static int INDEX_NOT_FOUND = -1;

	/**
	 * Default constructor for the Graph class reserves memory for the needed
	 * attributes of the class
	 * 
	 * @param n size of the n^2 matrices
	 * @throws Exception if less than 0
	 */
	public Graph(int n) throws Exception {
		if (n < 0) {
			throw new Exception("Invalid value for n");
		}

		nodes = new ArrayList<GraphNode<T>>();
		edges = new boolean[n][n];
		weight = new double[n][n];
	}

	/**
	 * returns the list of nodes
	 * 
	 * @return
	 */
	public ArrayList<GraphNode<T>> getNodes() {
		return nodes;
	}

	/**
	 * sets the list of nodes to a new one passed as parameter
	 * 
	 * @param nodes
	 */
	public void setNodes(ArrayList<GraphNode<T>> nodes) {
		this.nodes = nodes;
	}

	/**
	 * returns the edge matrix
	 * 
	 * @return
	 */
	public boolean[][] getEdges() {
		return edges;
	}

	/**
	 * sets the edges to a new one passed as parameter
	 * 
	 * @param edges
	 */
	public void setEdges(boolean[][] edges) {
		this.edges = edges;
	}

	/**
	 * returns the weight matrix
	 * 
	 * @return
	 */
	public double[][] getWeight() {
		return weight;
	}

	/**
	 * sets the weight matrix to a new one passed as parameter
	 * 
	 * @param weight
	 */
	public void setweight(double[][] weight) {
		this.weight = weight;
	}

	/**
	 * returns the size of the node list
	 * 
	 * @return
	 */
	public int getSize() {
		return nodes.size();
	}

	/**
	 * returns the position of the node with the element provided as parameter
	 * 
	 * @param element
	 * @return
	 * @throws Exception if element is null
	 */
	public int getNode(T element) throws Exception {
		if (element == null)
			throw new Exception("Null element provided");
		for (GraphNode<T> item : nodes) {
			if (item.getElement().equals(element)) {
				return nodes.indexOf(item);
			}
		}
		return INDEX_NOT_FOUND;
	}

	/**
	 * adds a node setting the value for edges and weight to the default ones
	 * 
	 * @param element
	 * @throws Exception if element is null, if it is already there or if the
	 *                   maximum capacity is reached
	 */
	public void addNode(T element) throws Exception {
		if (element == null)
			throw new Exception();
		if (getNode(element) != INDEX_NOT_FOUND)
			throw new Exception();
		if (nodes.size() == edges.length)
			throw new Exception();

		for (int i = 0; i < edges.length; i++) {
			edges[getSize()][i] = false;
			edges[i][getSize()] = false;
			weight[getSize()][i] = 0;
			weight[i][getSize()] = 0;
		}

		nodes.add(new GraphNode<T>(element));
	}

	/**
	 * checks if an edge exits between two nodes
	 * 
	 * @param origin
	 * @param dest
	 * @return
	 * @throws Exception if one of the nodes does not exist
	 */
	public boolean existsEdge(T origin, T dest) throws Exception {
		if (getNode(origin) == INDEX_NOT_FOUND || getNode(dest) == INDEX_NOT_FOUND)
			throw new Exception("Origin or dest doesnt exist.");
		if (!edges[getNode(origin)][getNode(dest)])
			return false;

		return true;
	}

	/**
	 * adds and edge having the origin, the destination and the weight
	 * 
	 * @param origin
	 * @param dest
	 * @param nWeight
	 * @throws Exception if one of the elements is null or if the weight<0 also if
	 *                   the nodes does not exist
	 */
	public void addEdge(T origin, T dest, double nWeight) throws Exception {
		if (origin == null || dest == null || nWeight < 0)
			throw new Exception("Origin or dest are null or weight lower than 0.");
		if (getNode(origin) == INDEX_NOT_FOUND || getNode(dest) == INDEX_NOT_FOUND)
			throw new Exception("Origin or dest doesnt exist.");

		edges[getNode(origin)][getNode(dest)] = true;
		weight[getNode(origin)][getNode(dest)] = nWeight;

	}

	/**
	 * removes and edge having the origin and the destination, setting the value in
	 * edges and weight to the default ones
	 * 
	 * @param origin
	 * @param dest
	 * @throws Exception if he nodes are not found in nodes list
	 */
	public void removeEdge(T origin, T dest) throws Exception {
		if (getNode(dest) == INDEX_NOT_FOUND || getNode(origin) == INDEX_NOT_FOUND) {
			throw new Exception("The node asked does not exit");
		}
		if (existsEdge(origin, dest)) {
			edges[getNode(origin)][getNode(dest)] = false;
			weight[getNode(origin)][getNode(dest)] = 0.0;
		} else {
			throw new IllegalArgumentException("the edge does not exist");
		}

	}

	/**
	 * removes a node, moving the last element information to the needed position
	 * 
	 * @param element
	 * @throws Exception if node is not found
	 */
	public void removeNode(T element) throws Exception {
		int index = getNode(element);
		if (getNode(element) == INDEX_NOT_FOUND) {
			throw new Exception("The node asked does not exit");
		}
		nodes.remove(index);
		if (index != getSize()) {
			for (int j = 0; j < getSize(); j++) {
				edges[j][index] = edges[j][getSize()];
				edges[index][j] = edges[getSize()][j];
				weight[j][index] = weight[j][getSize()];
				weight[index][j] = weight[getSize()][j];
			}
			edges[index][index] = edges[getSize()][getSize()];
			weight[index][index] = weight[getSize()][getSize()];
		}
	}

	/**
	 * prints all the nodes calling the print of each one
	 */
	public void print() {
		for (GraphNode<T> node : nodes) {
			node.print();
		}
	}

}
