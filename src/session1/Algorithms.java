package session1;

public class Algorithms {

	public static void linear (long n) {
		for (int i = 0; i <= n; i++) {
			System.out.println("executing job: " + i);
			TestBench.doNothing(i);
		}
	}
}
