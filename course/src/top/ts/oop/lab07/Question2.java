package top.ts.oop.lab07;

interface Inter {
	void show();
}

class Outer1 {
	public static Inter method() {
		return new Inter() {
			@Override
			public void show() {
				System.out.println("DuluDulu");
			}
		};
	}
}

public class Question2 {
	public static void main(String[] args) {
		Outer1.method().show();
	}
}
