package top.ts.oop.lab05.team;

public class Person {
	private String name;
	private String petPhrase;

	public Person(String name, String petPhrase) {
		this.name = name;
		this.petPhrase = petPhrase;
	}

	public String getName() {
		return name;
	}

	public void play() {
		System.out.println(name + ": " + petPhrase);
	}

	@Override
	public String toString() {
		return "Player: " + name;
	}
}
