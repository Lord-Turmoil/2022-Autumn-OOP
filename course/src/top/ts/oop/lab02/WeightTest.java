package top.ts.oop.lab02;

public class WeightTest {
	public static void main(String[] args) {
		Person person = new Person("Wang", 90);

		System.out.println(person);
		person.loseWeight(5);
		System.out.println(person);
		person.eat(15);
		System.out.println(person);
		person.loseWeight(10);
		System.out.println(person);
	}
}

class Person {
	private String name;
	private int weight;

	public Person(String name, int weight) {
		this.name = name;
		this.weight = weight;
	}

	public void eat(int w) {
		System.out.println("Eating to put on " + w + " kilograms...");
		this.weight += w;
		if (this.weight > 100) {
			System.out.println("Die of fat...");
		}
	}

	public void loseWeight(int w) {
		System.out.println("Working to lose " + w + " kilograms...");
		this.weight -= w;
		if (this.weight <= 0) {
			this.weight = 0;
			System.out.println("Starved to death...");
		}
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();

		buffer.append("  Name: ").append(name).append('\n');
		buffer.append("Weight: ").append(weight).append(" kg");

		return buffer.toString();
	}
}