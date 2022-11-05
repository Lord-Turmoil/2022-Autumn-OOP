package top.ts.oop.lab03;

enum Sex {
	Male,
	Female
}

public class Person {
	private String name;
	private int age;
	private Sex sex;

	public Person(String name, int age, Sex sex) {
		this.name = name;
		this.age = age;
		this.sex = sex;
	}

	protected void setAge(int age) {
		if (age >= 0 && age <= 130) {
			this.age = age;
		}
	}

	protected int getAge() {
		return age;
	}

	public void work() {
		System.out.println("working...");
	}

	public void showAge() {
		System.out.println("I'm " + age + " years old.");
	}
}

class PersonTest {
	public static void main(String[] args) {
		Person alice = new Person("Alice", 17, Sex.Female);
		alice.setAge(18);
		System.out.println(alice.getAge());

		Person bob = new Person("Bob", 18, Sex.Male);
		bob.setAge(17);

		System.out.println(bob.getAge());
	}
}