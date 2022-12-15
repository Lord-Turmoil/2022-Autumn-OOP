import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Count {
	public static void main(String[] args) {
		int n;
		int max = 0;
		Map<Integer, Integer> map = new TreeMap<>();
		Scanner scanner = new Scanner(System.in);

		n = scanner.nextInt();
		for (int i = 0; i < n; i++) {
			Integer num = scanner.nextInt();
			Integer value = 1;
			if (map.containsKey(num)) {
				value = map.get(num) + 1;
			}
			map.put(num, value);
			max = Math.max(max, value);
		}

		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			if (entry.getValue() == max) {
				System.out.println(entry.getKey() + " " + max);
			}
		}
	}
}
