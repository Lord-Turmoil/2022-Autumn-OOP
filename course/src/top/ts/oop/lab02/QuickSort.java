package top.ts.oop.lab02;

import java.util.Scanner;
import java.util.stream.StreamSupport;

public class QuickSort {
	public static void main(String[] args) {
		int n;
		int[] arr = new int[32];

		Scanner scanner = new Scanner(System.in);
		n = scanner.nextInt();
		for (int i = 0; i < n; i++) {
			arr[i] = scanner.nextInt();
		}

		arr = qsort(arr, n);
		for (int i = 0; i < n; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static int[] qsort(int[] arr, int n) {
		if (arr == null)
			return null;
		else if (arr.length == 0)
			return arr;

		quickSort(arr, 0, n - 1);

		return arr;
	}

	private static void quickSort(int[] arr, int left, int right) {
		if (right - left < 1)
			return;

		int pivot = partition(arr, left, right);
		if (pivot > left) {
			quickSort(arr, left, pivot - 1);
		}
		if (pivot < right) {
			quickSort(arr, pivot + 1, right);
		}
	}

	private static int partition(int[] arr, int left, int right) {
		int i, j;

		i = left;
		j = left + 1;
		for (int k = left; k < right; k++) {
			if (arr[j] < arr[left]) {
				i++;
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
			j++;
		}
		int temp = arr[left];
		arr[left] = arr[i];
		arr[i] = temp;

		return i;
	}
}
