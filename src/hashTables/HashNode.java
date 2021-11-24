package hashTables;

public class HashNode<T> {

    //Use of enums is better but for the subject constatns are valid
    public static int EMPTY = 0;
    public static int DELETED = 2;
    public static int VALID = 1;

    public T element;
    public int status;

    /**
     * Default constructor
     */
    public HashNode() {
        this.element = null;
        this.status = EMPTY;
    }
    
    public HashNode(T element2) {
        this.element = element2;
        this.status = EMPTY;
    }
}
