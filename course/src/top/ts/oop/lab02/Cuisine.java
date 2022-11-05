package top.ts.oop.lab02;

import java.util.Vector;

public class Cuisine {
	public static void main(String[] args) {
		Food cuisine = new Food();
		Collector aunt = new Collector("Aunt", "rice");
		Collector mom = new Collector("Mom", "noodles");
		Collector me = new Collector("Me", "salad");
		Cook dad = new Cook("Dad", "Pan");
		Cook uncle = new Cook("Uncle", "Grill");

		System.out.println(aunt);
		System.out.println(mom);
		System.out.println(me);
		System.out.println(dad);
		System.out.println(uncle);
		System.out.println("---------------------");
		aunt.work(cuisine);
		mom.work(cuisine);
		me.work(cuisine);
		System.out.println("---------------------");
		dad.work(cuisine);
		uncle.work(cuisine);
		System.out.println("---------------------");
		System.out.println(cuisine);
	}
}

class Food {
	private Vector<String> item = new Vector<>();
	private Vector<String> method = new Vector<>();

	public void addItem(String item) {
		this.item.addElement(item);
	}

	public void addMethod(String method) {
		this.method.addElement(method);
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();

		buffer.append(item).append(" are cooked with ").append(method);

		return buffer.toString();
	}
}

class People {
	protected String name;
	protected String job;

	public People(String name, String job) {
		this.name = name;
		this.job = job;
	}

	public void work(Food food) {
		System.out.println("Do nothing...");
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();

		buffer.append("\t Name: ").append(name);
		buffer.append("\t Job: ").append(job);

		return buffer.toString();
	}
}

class Collector extends People {
	protected String item;

	public Collector(String name, String food) {
		super(name, "Collector");
		this.item = food;
	}

	@Override
	public void work(Food food) {
		System.out.println(name + " is collecting " + item + "...");
		food.addItem(item);
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();

		buffer.append(super.toString()).append("\t Item: ").append(item);

		return buffer.toString();
	}
}

class Cook extends People {
	protected String method;

	Cook(String name, String method) {
		super(name, "Cook");
		this.method = method;
	}

	@Override
	public void work(Food food) {
		System.out.println(name + " is cooking with " + method + "...");
		food.addMethod(method);
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();

		buffer.append(super.toString()).append("\t Method: ").append(method);

		return buffer.toString();
	}
}