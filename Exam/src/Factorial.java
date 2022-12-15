import java.math.BigInteger;
import java.util.Scanner;

public class Factorial {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		BigInteger num = scanner.nextBigInteger();
		BigInteger result = BigInteger.ONE;

		while (num.compareTo(BigInteger.ZERO) > 0) {
			result = result.multiply(num);
			num = num.subtract(BigInteger.ONE);
		}

		System.out.println(result);
	}
}
