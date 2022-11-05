package top.ts.oop.lab02;

public class PICalculator {
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);

		n = Math.max(n, 0);
		System.out.println("n = " + n);
		System.out.println(getPI(n));
	}

	public static double getPI(int n) {
		double pi = 0.0;

		for (int i = 0; i <= n; i++) {
			if (i % 2 == 0) {
				pi += 1.0 / (2.0 * (double)i + 1.0);
			}
			else {
				pi -= 1.0 / (2.0 * (double)i + 1.0);
			}
		}

		return pi * 4.0;
	}
}
