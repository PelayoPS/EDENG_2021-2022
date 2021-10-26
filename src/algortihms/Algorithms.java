package algortihms;

/**
 * @author Pelayo
 *
 */
public class Algorithms {

	/**
	 * algorithm of linear complexity
	 * 
	 * @param n
	 */
	public static void linear(long n) {
		for (int i = 0; i <= n; i++) {
			System.out.println("executing job: " + i);
			TestBench.doNothing(i);
		}
	}

	/**
	 * algorithm of quadratic complexity
	 * 
	 * @param n
	 */
	public static void quadratic(long n) {
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= n; j++) {
				System.out.println("executing job: " + i);
				TestBench.doNothing(j);
			}
		}
	}

	/**
	 * algorithm of cubic complexity
	 * 
	 * @param n
	 */
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

	/**
	 * algorithm of logarithmic complexity
	 * 
	 * @param n
	 */
	public static void logarithmic(long n) {
		for (long i = n; i >= 1; i /= 2) {
			System.out.println("executing job: " + i);
			TestBench.doNothing(i);
		}
	}

	/**
	 * algorithm to calculate the factorial of a number using an iterative version
	 * 
	 * @param n
	 * @return
	 */
	public static long factorial(long n) {
		long factorial = 1;
		for (long i = n; i > 0; i--) {
			factorial *= i;
			System.out.println("executing job: " + i);
			TestBench.doNothing(i);
		}
		return factorial;
	}

	/**
	 * algorithm to calculate the power of a number using an recursive version
	 * 
	 * @param n
	 * @return
	 */
	public static long factorialRec(long n) {
		TestBench.doNothing(n);
		System.out.println("executing job: " + n);
		return n == 0 ? 1 : n * factorialRec(n - 1);
	}

	/**
	 * algorithm to calculate the power of a number using an iterative version
	 * 
	 * @param n
	 * @return
	 */
	public static long pow(long n) {
		long pow = 1;
		for (long i = n; i > 0; i--) {
			pow *= 2;
			System.out.println("executing job: " + i);
			TestBench.doNothing(i);
		}
		return pow;
	}

	/**
	 * algorithm to calculate the power of a number using an recursive version
	 * 
	 * @param n
	 * @return
	 */
	public static long powRec1(long n) {
		System.out.print("\nworking on " + n);
		TestBench.doNothing(n);
		return n == 0 ? 1 : powRec1(n - 1) + powRec1(n - 1);
	}

	/**
	 * algorithm to calculate the power of a number using an recursive version
	 * 
	 * @param n
	 * @return
	 */
	public static long powRec2(long n) {
		System.out.println("executing job: " + n);
		TestBench.doNothing(n);
		return n == 0 ? 1 : powRec2(n - 1) * 2;
	}

	/**
	 * algorithm to calculate the power of a number using an recursive version
	 * 
	 * @param n
	 * @return
	 */
	public static long powRec3(long n) {
		System.out.println("executing job: " + n);
		TestBench.doNothing(n);
		return n == 0 ? 1 : n % 2 == 0 ? powRec3(n / 2) * powRec3(n / 2) : 2 * powRec3(n / 2) * powRec3(n / 2);
	}

	/**
	 * algorithm to calculate the power of a number using an recursive version
	 * 
	 * @param n
	 * @return
	 */
	public static long powRec4(long n) {
		if (n == 0) {
			System.out.println("executing job: " + n);
			TestBench.doNothing(n);
			return 1;
		} else {
			System.out.println("executing job: " + n);
			TestBench.doNothing(n);
			long pow = (long) Math.pow(powRec4(n / 2), 2);
			if (n % 2 == 0) {
				return pow;
			} else {
				return pow * 2;
			}
		}
	}

}
