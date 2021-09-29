package algortihms;

public class Main {

	/**
	 * calls the class and the method provided as arguments and creates a file with
	 * the name given
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		TestBench.test("linear.txt", 3, 1, 50, "session1.Algorithms", "linear");
		TestBench.test("quadratic.txt", 3, 1, 50, "session1.Algorithms", "quadratic");
		TestBench.test("cubic.txt", 3, 1, 20, "session1.Algorithms", "cubic");
		TestBench.test("logarithmic.txt", 3, 1, 50, "session1.Algorithms", "logarithmic");
		TestBench.test("factorial.txt", 3, 1, 50, "session1.Algorithms", "factorial");
		TestBench.test("factorialRec.txt", 3, 1, 50, "session1.Algorithms", "factorialRec");
		TestBench.test("pow.txt", 3, 1, 50, "session1.Algorithms", "pow");
		TestBench.test("powRec1.txt", 3, 1, 12, "session1.Algorithms", "powRec1");
		TestBench.test("powRec2.txt", 3, 1, 12, "session1.Algorithms", "powRec2");
		TestBench.test("powRec3.txt", 3, 1, 12, "session1.Algorithms", "powRec3");
		TestBench.test("powRec4.txt", 3, 1, 12, "session1.Algorithms", "powRec4");
	}

}
