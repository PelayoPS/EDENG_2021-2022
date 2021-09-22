package session1;

public class Algorithms {

	public static void linear (long n) {
		for (int i = 0; i <= n; i++) {
			System.out.println("executing job: " + i);
			TestBench.doNothing(i);
		}
	}
	
	public static void quadratic(long n) {
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= n; j++) {
				System.out.println("executing job: " + i);
				TestBench.doNothing(j);
			}
		}
	}
	
	public static void cubic(long n) {
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= n; j++) {
				for (int k = 0; k <= n; k++) {
					System.out.println("executing job: " + i);
					TestBench.doNothing(k);
				}
			}
		}
	}
	
	public static void logarithmic(long n) {
		for (long i = n; i >= 1; i/=2) {
			System.out.println("executing job: " + i);
			TestBench.doNothing(i);
		}
	}
}
