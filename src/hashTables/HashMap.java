package hashTables;


public class HashMap<K, V> {
	
	private HashMapNode<K, V>[] elements;
    private HashMapNode<K, V>[] values;
	private int B;
	private int R;
	
	private double nElements;
	private double maxLF;
	private double minLF = 0.0;
    private int probingType;
	// use of enums could be the best way to do this
	public final static int LINEAR_PROBING = 0;
	public final static int QUADRATIC_PROBING = 1;
	public final static int DOUBLE_HASHING = 2;
	
	/**
	 * default constructor for the hash table
	 * @param B
	 * @param probingType
	 * @param maxLF
	 */
	public HashMap(int B,int probingType,  double maxLF) {
		this.B = B;
		this.R = getPrevPrimeNumber(B);
		this.probingType = probingType;
		this.maxLF = maxLF;
		this.elements = new HashMapNode[B];
        this.values = new HashMapNode[B];
		for (int i = 0; i < B; i++) {
			elements[i] = new HashMapNode<K, V>();
		}
		
	}
	
	/**
	 * constructor for the hash map using minlf as parameter
	 * @param B
	 * @param probingType
	 * @param maxLF
	 * @param minLF
	 */
	public HashMap(int B, int probingType, double maxLF, double minLF) {
		this(B,probingType,maxLF);
		this.minLF = minLF;
	}

	/**
	 * hashing function for the hash table using the probing type
	 * @param element
	 * @param attempt
	 * @return
	 */
	public int f(K element, int attempt) {
		if(probingType == LINEAR_PROBING) {// linear probing
			return (Math.abs(element.hashCode()) + attempt)%B;
		}
		else if(probingType == QUADRATIC_PROBING) {// quadratic probing
			int x = Math.abs(element.hashCode());
			int i = attempt*attempt;
		
			return (x+i)% B;
		}else {// double hashing
			int x = Math.abs(element.hashCode());
		
			int H = R - x % R;
			int i = attempt*H;
		
			return (x+i)% B;
		}
	}

	/**
	 * String representation of the hash table
	 */
	public String toString() {
		String aux = "";
		int pos = 0;
		for(HashMapNode<K, V> hn: elements) {
			aux += "[" + pos +"] " + hn.toString() + " - ";	// [pos](status) - element
			pos++;
		}
		return aux;
	}
	
	/**
	 * returns the load factor of the table
	 */
	public double getLF() {
		return nElements/B;
	}
	
	/**
	 * adds an element to the hash table
	 * @param element
     * @param value
	 */
	public void put(K element, V value) {
		put(element, value, 0);
		if(getLF() > maxLF) {
			while(getLF() > 0.4) {
				dynamicResizeUp(getNextPrimeNumber(B));
			}
		}
	}

	/**
	 * private method to add an element to the table
	 * @param element
     * @param value
	 * @param attempt
	 */
	private void put(K element, V value, int attempt) {
		int pos = f(element, attempt);
		if(elements[pos].getStatus() == HashNode.VALID) {
			put(element, value, attempt+1);
		}else {
			elements[pos].setElement(element);
            values[pos].setValue(value);
			nElements++;
		}
	}
	
	/**
	 * searchs for an element in the hash table
     * returns null when not found
	 * @param element
	 */
	public V get(K element) {
		return get(element,0);
	}

	/**
	 * private method to search for an element in the table
	 * @param element
	 * @param attempt
	 */
	private V get(K element, int attempt) {
		int pos = f(element, attempt);
		if(elements[pos].getStatus() == HashNode.EMPTY) {
			return null;// not found
		}
		else if(!elements[pos].getElement().equals(element)){
			return get(element,attempt+1);// not found but valid node
		}else {
			if(elements[pos].getStatus() == HashNode.DELETED) {
				return null;// not found on deleted node
			}
			return values[pos].getValue();// found
		}
	}
	
	/**
	 * removes an element from the hash table
	 * @param element
	 */
	public void remove(K element) {
		remove(element,0);
		if (getLF() < minLF) {
			dynamicResizeDown(R);
		}
	}

	/**
	 * private method to remove an element from the table
	 * @param element
	 * @param attempt
	 */
	private void remove(K element, int attempt) {
		int pos = f(element, attempt);
		int status = elements[pos].getStatus();
		if(status == HashNode.EMPTY) {
			throw new IllegalArgumentException("this element is not in the table");
		}else {
			if(status == HashNode.DELETED) {
				remove(element, attempt+1);// not found on already deleted node
			}else if(!elements[pos].getElement().equals(element)) {
				remove(element, attempt+1);// not found on valid node
			}
			else {// found
				elements[pos].removeElement();
				nElements--;
			}
		}
		
	}
	
	/**
	 * returns true if the number i prime
	 */
	public static boolean isPrime(int i) {
		if(i < 0) {
			throw new IllegalArgumentException("negative numbers are invalid");
		}
		if(i == 1 || i == 2) {
			return true;
		}
		for(int x=2 ;x <i;x++) {
			if (i % x == 0) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * returns the next prime number after the given number
	 */
	public static int getNextPrimeNumber(int i) {
		int x = i+1;
		if(isPrime(x)) {
			return x;
		}
		return getNextPrimeNumber(x);
	}

	/**
	 * returns the previous prime number before the given number
	 * @param i
	 * @return
	 */
	public static int getPrevPrimeNumber(int i) {
		int x = i-1;
		if(isPrime(x)) {
			return x;
		}
		return getPrevPrimeNumber(x);
	
	}
	
	/**
	 * resizes the hash table up
	 * @param newSize
	 */
	private void dynamicResizeUp(int newSize) {
		HashMap<K, V> aux = new HashMap<K, V>(newSize, probingType, maxLF);
		for(int i = 0; i < elements.length; i++) {//adds the nodes again to new table
			if(elements[i].getStatus() == HashNode.VALID) {
				aux.put(elements[i].getElement(), values[i].getValue());				
			}
		}

		this.elements = aux.getElements();
		this.R = B;
		this.B = newSize;
	}
	
	/**
	 * resizes the hash table down
	 * @param newSize
	 */
	private void dynamicResizeDown(int newSize) {
		HashMap<K, V> aux = new HashMap<K, V>(newSize, probingType, maxLF);
		for(int i = 0; i < elements.length; i++) {//adds the nodes again to new table
			if(elements[i].getStatus() == HashNode.VALID) {
				aux.put(elements[i].getElement(), values[i].getValue());			
			}
		}
		this.elements = aux.getElements();
		this.B = newSize;
		this.R = getPrevPrimeNumber(newSize);
	}
	
	/**
	 * returns the array of elements
	 * @return
	 */
	private HashMapNode<K, V>[] getElements(){
		return this.elements;
	}
	
	
}