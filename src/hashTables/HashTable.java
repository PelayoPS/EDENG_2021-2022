package hashTables;

import java.util.ArrayList;
import java.util.Iterator;

public class HashTable<T> {
    
    //Use of enums is better but for the subject constatns are valid
    public static int LINEAR_PROBING = 0;
    public static int QUADRATIC_PROBING = 1;

    private int B;
    private int redispersionType;
    private double maxLoadFactor;
    private HashNode<T>[] associativeArray;



	public HashTable(int size, int redispersionType2, double maxLoadFactor2) {
		
		this.B = size;
		this.redispersionType = redispersionType2;
		this.maxLoadFactor = maxLoadFactor2;
		this.associativeArray = new HashNode[size];
		for (int i = 0; i < associativeArray.length; i++) {
        	associativeArray[i] = new HashNode<T>();
        }
	}

	public int f(T element, int attempt) {
    	return redispersionType == LINEAR_PROBING ? 
    			(Math.abs(element.hashCode()) + attempt) % B
    			:
    			(Math.abs(element.hashCode()) + attempt * attempt) % B;     
    }
	
	public void add(T element) {
		
	}
	
    public String toString() {
       	String aux = "";
        for (int i = 0; i < associativeArray.length; i++) {
        	aux += String.format("[%d] (%d) = ", i,associativeArray[i].status) + 
        			associativeArray[i].element + " - ";
        }
        return aux;
    }

}
