import javax.net.ssl.SSLContext;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Unique {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Set<Integer> setA = new TreeSet<>();
		Set<Integer> setB = new TreeSet<>();

		int n =  scanner.nextInt();
		for (int i = 0; i < n; i++) {
			setA.add(scanner.nextInt());
		}
		int m = scanner.nextInt();
		for (int i = 0; i < m; i++) {
			setB.add(scanner.nextInt());
		}

		// System.out.println(setA);
		// 10
		//56 70 -12 100 7 89 -12 100 56 1001
		//9
		//100 56 70 89 -12 100 7 12 100System.out.println(setB);

		boolean flag = true;
		if (setA.size() != setB.size()) {
			flag = false;
		} else {
			Iterator<Integer> itA = setA.iterator();
			Iterator<Integer> itB = setB.iterator();

			while (itA.hasNext() && itB.hasNext()) {
				if (!itA.next().equals(itB.next())) {
					flag = false;
					break;
				}
			}
		}

		System.out.println(flag ? "1" : "0");

		for (Integer num : setA) {
			System.out.print(num + " ");
		}

	}
}
