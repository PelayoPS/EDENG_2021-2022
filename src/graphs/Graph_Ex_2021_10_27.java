package graphs;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.Queue;
import java.util.ArrayDeque;


public class Graph_Ex_2021_10_27<T> {
	protected ArrayList<GraphNode<T>> nodes;
	protected boolean[][] edges;
	protected double[][] weight;

	public static int INDEX_NOT_FOUND = -1;

	// Floyd Matrices
	protected double[][] A;// cost matrix
	protected int[][] P;// pathway matrix
	// End Foyd constants
	
	// Dijkstra constants
	private double[] D;// cost matrix
	private int[] PD;// pathway matrix
	// End Dijkstra costants
	
	public static double INFINITE = Double.POSITIVE_INFINITY;
	public int EMPTY = -1;

	/**
	 * Default constructor for the Graph class reserves memory for the needed
	 * attributes of the class
	 * @param n size of the n^2 matrices
	 * @throws Exception if less than 0
	 */
	public Graph_Ex_2021_10_27(int n) throws Exception {
		if (n < 0) {
			throw new Exception("Invalid value for n");
		}

		nodes = new ArrayList<GraphNode<T>>();
		edges = new boolean[n][n];
		weight = new double[n][n];

		// Floyd
		A = new double[n][n];
		P = new int[n][n];
		
		// Dijkstra
		D = new double[n];
		PD = new int[n];
	}
	
	//====GETTERS AND SETTERS====

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
	 * returns the A matrix
	 * @return
	 */
	public double[][] getA() {
		return A;
	}
	
	/**
	 * returns the P matrix
	 * @return
	 */
	public int[][] getP() {
		return P;
	}
	
	//====END GETTERS AND SETTERS====

	//====EDIT GRAPH METHODS====
	
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
		nodes.set(index, nodes.get(getSize()-1));
		nodes.remove(getSize()-1);
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
	
	//====END EDIT GRAPH METHODS====
	
	//====LOGIC FOR NODES METHODS====

	/**
	 * checks if a node is drain node, which means that the output degree = 0 and
	 * the input degree > 0
	 * 
	 * @param element
	 * @return
	 * @throws Exception
	 */
	public boolean isDrainNode(T element) throws Exception {
		int inputDegree = intputOutputDegree(element)[0];
		int outputDegree = intputOutputDegree(element)[1];
		if (outputDegree == 0 && inputDegree > 0) {// condition to be drainNode
			return true;
		}
		return false;

	}

	/**
	 * checks if a node is source node, which means that the output degree > 0 and
	 * input degree = 0
	 * 
	 * @param element
	 * @return
	 * @throws Exception
	 */
	public boolean isSourceNode(T element) throws Exception {
		int inputDegree = intputOutputDegree(element)[0];
		int outputDegree = intputOutputDegree(element)[1];
		if (inputDegree == 0 && outputDegree > 0) {// condition to be sourceNode
			return true;
		}
		return false;
	}

	/**
	 * returns an array with the input and output degree
	 * 
	 * @param element
	 * @return
	 * @throws Exception
	 */
	private int[] intputOutputDegree(T element) throws Exception {
		int[] result = new int[2];
		int inputDegree = 0;
		int outputDegree = 0;

		for (int i = 0; i < getSize(); i++) {
			if (edges[i][getNode(element)] == true) {
				inputDegree++;// if there is an edge to the element
			}
			if (edges[getNode(element)][i] == true) {
				outputDegree++;// if there is an edge from the element
			}
		}
		result[0] = inputDegree;
		result[1] = outputDegree;
		return result;
	}

	/**
	 * Returns the number of drain nodes
	 * 
	 * @return
	 */
	public int countDrainNodes() throws Exception {
		int counter = 0;
		for (GraphNode<T> node : nodes) {
			if (isDrainNode(node.getElement())) {
				counter++;
			}
		}
		return counter;
	}

	/**
	 * Returns the number of source nodes
	 * 
	 * @return
	 */
	public int countSourceNodes() throws Exception {
		int counter = 0;
		for (GraphNode<T> node : nodes) {
			if (isSourceNode(node.getElement())) {
				counter++;
			}
		}
		return counter;
	}
	

	/**
	 * prints all the nodes calling the print of each one
	 */
	public void print() {
		for (GraphNode<T> node : nodes) {
			node.print();
		}
	}
	
	//====END LOGIC FOR NODES METHODS====
	
	//====TRAVERSAL METHODS====

	/**
	 * 1 sets all nodes to visited
	 * 2 sets the value of the current index
	 * 3 returns null if not found, print string otherwise
	 * 
	 * @param element
	 * @return
	 * @throws Exception
	 */
	public String traverseGraphDF(T element) throws Exception {
		// 1
		for (GraphNode<T> node : nodes) {
			node.setVisited(false);
		}
		// 2
		int currentIndex = getNode(element);
		// 3
		if (currentIndex == INDEX_NOT_FOUND) {
			return null;
		}
		return DFPrint(currentIndex);

	}

	/**
	 * 1 sets this node to visited 
	 * 2 gets the string representation and saves it 
	 * 3 checks if there is a child 
	 * 4 checks if the child has been visited 
	 * 5 calls the method again to check for child of the child 
	 * @param currentIndex
	 * @return
	 */
	private String DFPrint(int currentIndex) {
		// 1
		nodes.get(currentIndex).setVisited(true);
		// 2
		String aux = nodes.get(currentIndex).getElement()+"-";
		for (int i = 0; i < nodes.size(); i++) {
			// 3
			if (edges[currentIndex][i]) {
				// 4
				if (!nodes.get(i).isVisited()) {
					// 5
					aux += DFPrint(i);
				}
			}
		}
		return aux;
	}
	
	/**
	 * 1  Aux structure to keep the elements in order
	 * 2  Gets the first element 
	 * 3  Checks if the element exists
	 * 4  Sets all the elements to not visited
	 * 5  Sets the first element to the head of the aux structure
	 * 6  Gets the string for the head
	 * 7  Loop until list is empty
	 * 8  Loop over all the nodes
	 * 9  If there is a child not visited
	 * 10 Sets the child to visited
	 * 11 Gets the string of the child
	 * 12 Adds the child to the list to check for sub child
	 * 13 Removes the head of the queue
	 * @param element
	 * @return
	 * @throws Exception
	 */
	public String BFPrint(T element) throws Exception {
		// 1
		Queue<Integer> queue = new ArrayDeque<Integer>(getSize());
		// 2
		int v = getNode(element);
		// 3
		if (v == INDEX_NOT_FOUND) {
			return "";
		}
		// 4
		for (GraphNode<T> node : nodes) {
			node.setVisited(false);
		}
		// 5
		queue.add(v);
		// 6
		String result = nodes.get(v).getElement() + "-";
		// 7
		do {
			// 8
			for (int i = 0; i < getSize(); i++) {
				// 9
				if(edges[queue.peek()][i] && !nodes.get(i).isVisited()) {
					// 10
					nodes.get(i).setVisited(true);
					// 11
					result += nodes.get(i).getElement() + "-";
					// 12
					queue.add(i);
				}
			}
			// 13
			queue.remove();
		}  while(!queue.isEmpty());
		return result;
	}
	
	//====END TRAVERSAL METHODS====
	
	//====FLOYD METHODS====

	/**
	 * 1 goes through both matrices 
	 * 2 filling with 0 the diagonal as cost from node to same node is 0 
	 * 3 filling with weight where and edge exists 
	 * 4 filling with INFINITE if the edge doesn't exist 
	 * 5 filling with EMPTY all the P matrix
	 */
	private void initsFloyd() {
		// 1
		for (int i = 0; i < getSize(); i++) {
			for (int j = 0; j < getSize(); j++) {
				
				// 3
				if (edges[i][j]) {
					A[i][j] = weight[i][j];
				}
				// 4
				else {
					A[i][j] = INFINITE;
				}
				// 5
				P[i][j] = EMPTY;
				
				// 2
				if (i == j) {
					A[i][j] = 0;
				}
			}
		}
	}

	/**
	 * 1 iterates over all the nodes
	 * 2 iterates over the matrix first position in [i][]
	 * 3 iterates over the matrix second position in [][j]
	 * 4 if cost using intermediate node is less than from i to j
	 * 5 update A and P
	 * @param iteractions
	 */
	public void floyd(int iterations/* also means the intermediate node */) {
		initsFloyd();
		// 1
		for (int k = 0; k < iterations; k++) {
			// 2
			for (int i = 0; i < getSize(); i++) {
				// 3
				for (int j = 0; j < getSize(); j++) {
					// 4
					if(A[i][k] + A[k][j] < A[i][j]) {
						// 5
						A[i][j] = A[i][k] + A[k][j];
						P[i][j] = k;
					}
				}
			}
		}
	}
	
	/**
	 * 1 iterates over all the nodes
	 * 2 iterates over the matrix first position in [i][]
	 * 3 iterates over the matrix second position in [][j]
	 * 4 if cost using intermediate node is less than from i to j
	 * 5 update A and P
	 * @param iteractions
	 */
	public void floyd(int iterations, double[][] weight2) {
		initsFloyd(weight2);
		// 1
		for (int k = 0; k < iterations; k++) {
			// 2
			for (int i = 0; i < getSize(); i++) {
				// 3
				for (int j = 0; j < getSize(); j++) {
					// 4
					if(A[i][k] + A[k][j] < A[i][j]) {
						// 5
						A[i][j] = A[i][k] + A[k][j];
						P[i][j] = k;
					}
				}
			}
		}
	}
	
	/**
	 * 1 goes through both matrices 
	 * 2 filling with 0 the diagonal as cost from node to same node is 0 
	 * 3 filling with weight where and edge exists 
	 * 4 filling with INFINITE if the edge doesn't exist 
	 * 5 filling with EMPTY all the P matrix
	 */
	private void initsFloyd(double[][] weight2) {
		// 1
		for (int i = 0; i < getSize(); i++) {
			for (int j = 0; j < getSize(); j++) {
				
				// 3
				if (edges[i][j]) {
					A[i][j] = weight2[i][j];
				}
				// 4
				else {
					A[i][j] = INFINITE;
				}
				// 5
				P[i][j] = EMPTY;
				
				// 2
				if (i == j) {
					A[i][j] = 0;
				}
			}
		}
	}

	/**
	 * public method that calls floyd to set up the path
	 * returns the
	 * @param departure
	 * @param arrival
	 * @return
	 * @throws Exception
	 */
	public String printFloydPath(T departure, T arrival) throws Exception {
		floyd(getSize());
		String aux = departure.toString() + "-" + printPath(departure, arrival) + arrival.toString();
		return aux;
	}
	
	
	/**
	 * Iterates like usual floyd does, but with nodes that are not 
	 * pivots = INDEX_NOT_FOUND it jumps to the next iteration
	 */
	public void floydPivots(T[] pivots) {
		initsFloyd();
		
		for (int k = 0; k < pivots.length; k++) {
				for (int i = 0; i < getSize(); i++) {
					for (int j = 0; j < getSize(); j++) {
						int pos = nodes.indexOf(pivots[k]);
						if(A[i][pos] + A[pos][j] < A[i][j]) {
							A[i][j] = A[i][pos] + A[pos][j];
							P[i][j] = pos;
						}
					}
				}
			}
		}

	/**
	 * 1 gets the path value from departure to arrival
	 * 2 checks if there is a path
	 * 3 uses recursion to get all the nodes of the path
	 * @param i
	 * @param j
	 */
	private String printPath(T departure, T arrival) throws Exception {
		String aux = "";
		// 1
		int k = P[getNode(departure)][getNode(arrival)];
		// 2
		if (k != EMPTY) {
			// 3
			aux += printPath(departure, nodes.get(k).getElement());
			aux += nodes.get(k).getElement()+"-";
			aux += printPath(nodes.get(k).getElement(), arrival);
		}
		return aux;
	}
	
	//====END FLOYD METHODS====
	
	//====DIJKSTRA METHODS====
	
	/**
	 * 1 sets the D's slots to INFINITE if there is no edge from the element to the node
	 * 1 place the EMPTY in PD if there is there is no edge from the element to the node
	 * 2 the weight of the edge otherwise
	 * 2 the index of the node otherwise
	 * 3 place 0 in the position of the node(going from the element to itself)
	 * 3 place the index of the element(going from the element to itself)
	 * 
	 * @param elementIndex
	 * @throws Exception 
	 */
	public void initDijkstra(int elementIndex) throws Exception {
		D = new double[getSize()];
		PD = new int[getSize()];
		
		for (int i = 0; i < getSize(); i++) {
			// 1
			if(!existsEdge(nodes.get(elementIndex).getElement(), nodes.get(i).getElement())) {
				D[i] = INFINITE;
				PD[i] = EMPTY;
			// 2	
			} else {
					D[i] = weight[elementIndex][i];
					PD[i] = elementIndex;
				}
		}
		// 3
		D[elementIndex] = 0;
		
		PD[elementIndex] = 0;
		
	}
	
	
	/**
	 * 1 initialize the S set
	 * 2 get the first pivot
	 * 3 while valid pivot
	 * 4 add current pivot to S
	 * 	5 for each node in the graph
	 * 		6 check if there is path from element to i using the pivot 
	 * 		  cheaper than the cost from element to i
	 * 			7 update D and PD
	 * @param element
	 * @return
	 * @throws Exception
	 */
	public double[] dijkstra(T element) throws Exception {
		
		int index = getNode(element);
		
		if(index == EMPTY) {
			throw new Exception("The element is not in the graph");
		}
		
		initDijkstra(index);
		
		//1 initialize the S set
		for(GraphNode<T> node : nodes) {
			node.setVisited(false);
		}
		
		//2 get the first pivot
		int pivot = getPivot();
		
		//3 while valid pivot
		while(pivot != INDEX_NOT_FOUND) {
			
			//4 add current pivot to S
			nodes.get(pivot).setVisited(true);
			
			//5 for each node in the graph
			for(int i = 0; i < getSize(); i++) {
				//6 check if there is path from element to i using the pivot
				//  cheaper than the cost from element to i
				if(edges[pivot][i] && (D[pivot] + weight[pivot][i]) < D[i]) {
					//7 update D and PD
					D[i] = D[pivot] + weight[pivot][i];
					PD[i] = pivot;
				}
			}
			
			pivot = getPivot();
		}
		

		return D;
	}
	
	/**
	 * return index of the node with cheapest cost from the element and not in S
	 * returns empty if pivot is not found
	 * @return
	 */
	private int getPivot() {
		double cost = INFINITE;
		
		int pivot = INDEX_NOT_FOUND;
		for (int i = 0; i < getSize(); i++) {
			//cheapest cost and not in S
			if((D[i] < cost) && !nodes.get(i).isVisited()) {
				cost = D[i];
				pivot = i;
			}
		}
		
		return pivot;
	}
	
	//====END DIJKSTRA METHODS====
	
	//====SHORTEST PATH METHODS====

	/**
	 * 1 Gets the departure and the arrival and checks it
	 * 2 Copies the weight matrix to a new one so the graph is not modified
	 * 3 Loops over the new matrix to set all the costs to 1
	 * 4 Calls a new floyd that uses the new matrix
	 * 5 Returns the value of cost stored in A with all costs 1 so we get the length of the path
	 * @param departure
	 * @param arrival
	 * @return
	 * @throws Exception
	 */
	public int shortestPathLength(T departure, T arrival) throws Exception {
		//1
		int departureIndex = getNode(departure);
		int arrivalIndex = getNode(arrival);
		if(departureIndex == INDEX_NOT_FOUND || arrivalIndex == INDEX_NOT_FOUND) {
			throw new Exception("The element is not in the graph");
		}
		// 2
		double[][] copy = Arrays.copyOf(weight, weight.length);
		// 3
		for (int i = 0; i < getSize(); i++) {
			for (int j = 0; j < getSize(); j++) {
				copy[i][j] = 1;
			}
		}
		// 4
		floyd(getSize(), copy);
		
		// 5
		return (int)A[getNode(departure)][getNode(arrival)];
	}
	
	//====END SHORTEST PATH METHODS====
	
	//====STRONGLY CONNECTED====
	
	/**
	 * Executes floyd so if at the end there is still a node that can't be reached we get 
	 * that the graph is not strongly connected
	 * @return
	 */
	public boolean isStronglyConnected() {
		boolean result = true;
		floyd(getSize());
		for (int i = 0; i < P.length; i++) {
			for (int j = 0; j < P.length; j++) {
				if(P[i][j] == EMPTY) {
					result = false;
				}
			}
		}
		return result;
	}
	
	//====END STRONGLY CONNECTED====

	//====CENTER RELATED METHODS====
	
	/**
	 * Returns the center of the graph which is the one with the minExcentricity
	 * @return
	 * @throws Exception
	 */
	public T getCenter() throws Exception {
		floyd(getSize(), this.weight);
		int centro = -1;
		double minExcentricity = Double.POSITIVE_INFINITY;
		for(int i = 0; i < getSize(); i++){
			if(excentricity(nodes.get(i).getElement())<minExcentricity){
				minExcentricity = excentricity(nodes.get(i).getElement());
				centro=i;
			}
		}
		return nodes.get(centro).getElement();
	}
	
	/**
	 * Returns the maximum cost of reaching any other nodes from the node n
	 * @param source
	 * @return
	 * @throws Exception
	 */
	public double excentricity (T source) throws Exception {
		double excentricity = -1;
		floyd(getSize(), this.weight);
		if(source!=null && !(getNode(source) == INDEX_NOT_FOUND)){
			for(int i = 0; i < getSize(); i++){
				if(A[i][getNode(source)] > excentricity){
					excentricity = A[i][getNode(source)];
				}
			}
		}
		return excentricity;
	}

	//====END CENTER RELATED METHODS====
	

	//====RECIPROCITY====

	public double reciprocity() {
		return getReciprocal() / getAllEdges();
	}

	private int getReciprocal() {
		int result = 0;
		for(int i = 0; i < getSize(); i++){
			for(int j = 0; j < getSize(); j++){
				if(edges[i][j] == true && edges[j][i] == true){
					result++;
				}
			}
		}

		return result;
	}


	private int getAllEdges() {
		int result = 0;
		for(int i = 0; i < getSize(); i++){
			for(int j = 0; j < getSize(); j++){
				if(edges[i][j]){
					result++;
				}
			}
		}

		return result;
	}


	//====END RECIPROCITY====
}
