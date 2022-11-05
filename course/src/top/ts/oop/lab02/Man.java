package top.ts.oop.lab02;

public class Man {
	void sayHello() {
		System.out.print("您好，很高兴认识您");
		System.out.println("nice to meet you!");
	}
}

class PersonTest {
	public static void main(String[] args) {
		Man rover = new Man();
		rover.sayHello();
	}
}
