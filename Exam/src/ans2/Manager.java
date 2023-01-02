package ans2;

public class Manager extends Employee {
	private final Vehicle vehicle;
	private String department;

	public Manager(String name, int age, Sex sex, int salary, Vehicle vehicle, String department) {
		super(name, age, sex, salary);
		this.vehicle = vehicle;
		this.department = department;
	}

	public Manager(String name, int salary) {
		super(name, salary);
		this.vehicle = null;
		this.department = null;
	}

	public void show() {
		System.out.println("===== Manager ======");
		System.out.println("Name: " + getName());
		System.out.println("Age: " + getAge());
		System.out.println("Sex: " + getSex());
		System.out.println("Salary: " + salary);
		if (vehicle != null) {
			System.out.println("Vehicle: " + vehicle);
		}
		if (department != null) {
			System.out.println("Department: " + department);
		}
		play();
		sing();
	}
}
