package t1;

import java.util.*;

public class Unique {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		TreeSet<Integer> setA = new TreeSet<>();
		TreeSet<Integer> setB = new TreeSet<>();

		int n = scanner.nextInt();
		for (int i = 0; i < n; i++) {
			setA.add(scanner.nextInt());
		}
		int m = scanner.nextInt();
		for (int i = 0; i < m; i++) {
			setB.add(scanner.nextInt());
		}

		TreeSet<Integer> set = new TreeSet<>();
		for (Integer i : setA) {
			if (setB.contains(i)) {
				setB.remove(i);
			} else {
				set.add(i);
			}
		}
		for (Integer i : setB) {
			set.add(i);
		}

		ArrayList<Integer> list = new ArrayList<>();
		for (Integer i : set) {
			list.add(i);
		}
		Collections.reverse(list);
		for (Integer i : list) {
			System.out.print(i + " ");
		}
		System.out.println("");
	}
}
