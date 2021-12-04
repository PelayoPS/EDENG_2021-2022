package hashTables;


public class HashTable<T> {
    
    //Use of enums is better but for the subject constatns are valid
    public static int LINEAR_PROBING = 0;
    public static int QUADRATIC_PROBING = 1;
	public static int DOUBLE_HASHING = 2;

    private int B;
    private int redispersionType;
    private double maxLoadFactor;
    private HashNode<T>[] associativeArray;

	/**
	 * Constructor for the HashTable class
	 * @param size
	 * @param redispersionType2
	 * @param maxLoadFactor2
	 */
	public HashTable(int size, int redispersionType2, double maxLoadFactor2) {
		
		this.B = size;
		this.redispersionType = redispersionType2;
		this.maxLoadFactor = maxLoadFactor2;
		this.associativeArray = new HashNode[size];
		for (int i = 0; i < associativeArray.length; i++) {
        	associativeArray[i] = new HashNode<T>();
        }
	}

	/**
	 * Method to get the hash value of the key
	 * @param key
	 * @param value
	 */
	public int f(T element, int attempt) {
    	return redispersionType == LINEAR_PROBING ? 
    			(Math.abs(element.hashCode()) + attempt) % B
    			:
    			(Math.abs(element.hashCode()) + attempt * attempt) % B;     
    }
	
	/**
	 * apply the f function increasing the number of attempts everytime there is 
	 * a collision until the element is inserted
	 * @param element
	 */
	public void add(T element) {
		int attempt = 0;
		int index = f(element, attempt);
		while (associativeArray[index].element != null) {
			attempt++;
			index = f(element, attempt);
		}
		associativeArray[index] = new HashNode<T>(element);
	}
	
	/**
	 * Method that returns the string representation of the hash table
	 */
    public String toString() {
       	String aux = "";
        for (int i = 0; i < associativeArray.length; i++) {
        	aux += String.format("[%d] (%d) = ", i,associativeArray[i].status) + 
        			associativeArray[i].element + " - ";
        }
        return aux;
    }

	/**
	 * returns the load factor of the hash table
	 * @return
	 */
	public double getLF() {
		int validNodes = 0;
		for (HashNode<T> hashNode : associativeArray) {
			if (hashNode.status == HashNode.VALID) {
				validNodes++;
			}
		}
		return 1.0* validNodes / associativeArray.length;
	}

	/**
	 * Method to check if the hash table is full
	 * returns true if the element is in the table, flase otherwise
	 * @param element
	 */
	public boolean search(T element) {
		int attempt = 0;
		int index = f(element, attempt);
		while (associativeArray[index].element != null && 
			getLF() != 0) {
			if (associativeArray[index].element.equals(element)) {
				return true;
			}
			attempt++;
			index = f(element, attempt);
		}
		return false;
	}

	public void remove(int i) {
	}
}
