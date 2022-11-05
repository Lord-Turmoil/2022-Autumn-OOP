package top.ts.oop.lab02;

public class Square {
	public static void main(String[] args) {
		int size = Integer.parseInt(args[0]);
		int cnt = 1;

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.printf("%d ", cnt++);
			}
			System.out.println();
		}
		System.out.println();
	}
}
