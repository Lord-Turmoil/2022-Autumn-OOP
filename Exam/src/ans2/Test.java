package ans2;

import java.util.ArrayList;

public class Test {
	public static void main(String[] args) {
		int i = 3;


		ArrayList<Employee> list = new ArrayList<>();
		list.add(new Employee("A", 1, Sex.Male, 123, "123"));
		list.add(new Employee("B", 3, Sex.Male, 1234, "456"));
		list.add(new Employee("C", 1235));
		list.add(new Employee("D", 2, Sex.Female, 12365, "789"));
		list.add(new Employee("E", 6, Sex.Male, 1, "101"));

		list.add(new Manager("a", 56));
		list.add(new Manager("b", 56));
		list.add(new Manager("c", 3, Sex.Male, 1234567, new Car(), "BUAA"));
		list.add(new Manager("d", 56));
		list.add(new Manager("e", 56));
		list.add(new Manager("f", 3, Sex.Female, 1237, new Bus(), "Building"));

		for (Employee r : list) {
			r.show();
		}
	}
}
