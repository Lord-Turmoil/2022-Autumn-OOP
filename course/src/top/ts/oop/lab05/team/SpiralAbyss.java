package top.ts.oop.lab05.team;

import java.util.ArrayList;

public class SpiralAbyss extends Room {
	private ArrayList<Person> partner = new ArrayList<>();

	public SpiralAbyss() {
	}

	public SpiralAbyss(int maxPlayerNum) {
		super(maxPlayerNum);
	}

	public boolean addPartner(Person person) {
		if (partner.size() < getMaxPlayerNum()) {
			partner.add(person);
			System.out.println("Player " + person.getName() + " added.");
			return true;
		} else {
			System.out.println("Sorry, too many players!");
			return false;
		}
	}

	public boolean play() {
		if (partner.size() == 0) {
			System.out.println("No players!");
			return false;
		}

		System.out.println("Game started!");
		for (Person person : partner) {
			person.play();
		}
		System.out.println("Game ended.");

		return true;
	}

	public void listPartners() {
		if (partner.size() == 0) {
			System.out.println("No partners.");
		}

		System.out.println("Players:");
		for (Person person : partner) {
			System.out.println(person);
		}
	}
}