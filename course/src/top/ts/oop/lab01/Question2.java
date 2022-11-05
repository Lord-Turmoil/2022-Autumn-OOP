package top.ts.oop.lab01;

import java.util.Scanner;

public class Question2 {
	private final String id = "21371300";
	private final String name = "Liu Zhengyao";

	public static void main(String[] args) {
		Question2 obj = new Question2();

		System.out.println(obj.toString());

		while (obj.read()) {
			System.out.println("Hello world!");
		}

		System.out.println("----- Good Bye! -----");
	}

	@Override
	public String toString() {
		return ("  ID: " + id + "\n" + "Name: " + name);
	}

	public boolean read() {
		Scanner scanner = new Scanner(System.in);
		String str;

		System.out.print("Please enter something: ");
		str = scanner.nextLine();

		// No == or !=
		return !str.equals("QUIT");
	}
}
