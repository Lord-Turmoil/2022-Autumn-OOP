package top.ts.oop.lab03;

import java.util.Scanner;

public class Inspector {
	public static void main(String[] args) {
		String string = getInput();

		if (string == null) {
			System.out.println("Nothing to check...");
			return;
		}

		if (isUnsigned(string)) {
			System.out.println("Is unsigned number without leading zero.");
			if (isPalindrome(string)) {
				System.out.println("Is palindrome number.");
			} else {
				System.out.println("Is not palindrome number!");
			}
		} else {
			System.out.println("Is not unsigned number without leading zero!");
		}
	}

	private static String getInput() {
		Scanner scanner = new Scanner(System.in);

		if (scanner.hasNextLine()) {
			return scanner.nextLine();
		} else {
			return null;
		}
	}

	private static boolean isUnsigned(String string) {
		if (string == null) {
			return false;
		}

		if (!string.matches("^[0-9]+$")) {
			return false;
		}

		if (string.matches("^0+[0-9]+$")) {
			return false;
		}

		return true;
	}

	private static boolean isPalindrome(String number) {
		if (number == null) {
			return false;
		}

		char[] str = number.toCharArray();
		for (int i = 0, j = str.length - 1; i < j; i++, j--) {
			if (str[i] != str[j]) {
				return false;
			}
		}

		return true;
	}
}
