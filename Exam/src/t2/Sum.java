package t2;

import java.math.BigInteger;
import java.util.Scanner;

public class Sum {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		BigInteger n = scanner.nextBigInteger();
		BigInteger ans = BigInteger.ZERO;

		BigInteger k = BigInteger.ONE;
		while (k.compareTo(n) <= 0) {
			ans = ans.add(sum(k));
			k = k.add(BigInteger.ONE);
		}

		BigInteger two = BigInteger.ONE.add(BigInteger.ONE);
		System.out.println(ans.divide(two));
	}

	private static BigInteger sum(BigInteger n) {
		return BigInteger.ONE.add(n).multiply(n);
	}
}
