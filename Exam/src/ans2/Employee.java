package ans2;

public class Employee extends Role implements Showable  {
	static final String ID;
	int salary;

	public Employee(String name, int age, Sex sex, int salary, String ID) {
		super(name, age, sex);
		this.salary = salary;
		this.ID = ID;
	}

	public Employee(String name, int salary) {
		super(name);
		this.salary = salary;
	}

	@Override
	public void play() {
		System.out.println(getName() + " is playing.");
	}

	final void sing() {
		System.out.println(getName() + " is singing.");
	}

	@Override
	public void show() {
		System.out.println("===== Employee ======");
		System.out.println("Name: " + getName());
		System.out.println("Age: " + getAge());
		System.out.println("Sex: " + getSex());
		System.out.println("Salary: " + salary);
		System.out.println("ID: " + ID);
		play();
		sing();
	}
}
