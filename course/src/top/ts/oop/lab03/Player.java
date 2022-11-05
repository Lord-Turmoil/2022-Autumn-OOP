package top.ts.oop.lab03;

class Music {
	private String name;
	private String type;

	public Music(String name, String type) {
		this.name = name;
		this.type = type;
	}

	@Override
	public String toString() {
		return "Music: " + name + " | Type: " + type;
	}

	public void printInfo() {
		System.out.println(this);
	}
}

class Mobile {
	private String brand;
	private String model;

	public Mobile(String brand, String model) {
		this.brand = brand;
		this.model = model;
	}

	@Override
	public String toString() {
		return "Mobile: " + brand + " | Model: " + model;
	}

	public void printInfo() {
		System.out.println(this);
	}
}

public class Player {
	public static void main(String[] args) {
		Music music = new Music("The Moment", "Jazz");
		Mobile mobile = new Mobile("Huawei", "Nova6");

		music.printInfo();
		mobile.printInfo();
	}
}
