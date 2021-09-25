package session1;

public class Algorithms {

	public static void linear(long n) {
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
		for (long i = n; i >= 1; i /= 2) {
			System.out.println("executing job: " + i);
			TestBench.doNothing(i);
		}
	}

	public static long factorial(long n) {
		long factorial = 1;
		for (long i = n; i > 0; i--) {
			factorial *= i;
		}
		return factorial;
	}

	public static long factorialRec(long n) {
		return n == 0 ? 1 : n * factorialRec(n - 1);
	}

	public static long pow(long n) {
		long pow = 1;
		for (long i = n; i > 0; i--) {
			pow *= 2;
		}
		return pow;
	}

	public static long powRec1(long n) {
		System.out.print("\nworking on " + n);
		return n == 0 ? 1 : powRec1(n - 1) + powRec1(n - 1);
	}

	public static long powRec2(long n) {
		return n == 0 ? 1 : powRec2(n - 1) * 2;
	}

	public static long powRec3(long n) {
		return n == 0 ? 1 : n % 2 == 0 ? powRec3(n / 2) * powRec3(n / 2) : 2 * powRec3(n / 2) * powRec3(n / 2);
	}

	public static long powRec4(long n) {
		if (n == 0) {
			return 1;
		} else {
			long pow = (long) Math.pow(powRec4(n / 2), 2);
			if (n % 2 == 0) {
				return pow;
			} else {
				return pow * 2;
			}
		}
	}

}
