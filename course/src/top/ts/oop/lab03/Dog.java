package top.ts.oop.lab03;

enum BarkType {
	UNKNOWN,
	BARK,
	HOWL
}

public class Dog {
	private int age;
	private String name;

	public Dog(int age) {
		this.age = age;
		System.out.println("This dog is " + age + " years ol.");
	}

	public Dog(int age, String name) {
		this(age);
		this.name = name;
		System.out.println("This dog's name is " + name + ".");
	}

	public void bark(BarkType type) {
		if (type == BarkType.BARK) {
			System.out.println("Barking...");
		} else if (type == BarkType.HOWL) {
			System.out.println("Howling...");
		} else {
			System.out.println("Woof woof...");
		}
	}

	public void bark(int times, BarkType type) {
		System.out.println("bark(int times, BarkType type)");
		System.out.print(times + " times of ");
		bark(type);
	}

	public void bark(BarkType type, int times) {
		System.out.println("bark(BarkType type, int times)");
		System.out.print(times + " times of ");
		bark(type);
	}
}

class DogTest {
	public static void main(String[] args) {
		Dog dog = new Dog(3, "Doggy");
		dog.bark(3, BarkType.BARK);
		dog.bark(BarkType.HOWL, 5);
	}
}