package top.ts.oop.lab02;

public class Star {
	public static void main(String[] args) {
		int size = Integer.parseInt(args[0]);
		int mid = size / 2;

		for (int i = 0; i < size; i++) {
			int offset = Math.abs(mid - i);
			int num = size - offset * 2;
			for (int j = 0; j < offset; j++) {
				System.out.print(' ');
			}
			for (int j = 0; j < num; j++) {
				System.out.print('*');
			}
			System.out.println();
		}
	}
}
