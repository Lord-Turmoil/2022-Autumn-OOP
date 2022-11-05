package top.ts.oop.lab02;

import java.util.Arrays;
import java.util.Scanner;

public class SortAndFind {
	public static void main(String[] args) {
		int[] arr = { 12, 45, 67, 89, 123, -45, 67 };

		java.util.Arrays.sort(arr);
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();

		Scanner scanner = new Scanner(System.in);
		while (true) {
			int num = scanner.nextInt();
			if (num == -1) {
				break;
			}

			int pos = Arrays.binarySearch(arr, num);
			if (pos < 0) {
				System.out.println("No such number.");
			} else {
				System.out.println("pos: " + pos);
			}
		}
	}
}
