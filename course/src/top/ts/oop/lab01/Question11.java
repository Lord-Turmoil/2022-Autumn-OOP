package top.ts.oop.lab01;

public class Question11 {
	int a = 10; // 全局变量,下同
	double b = 20;

	public static void main(String[] args) {
		Question11 globalVar = new Question11();
		System.out.println("全局变量  a = " + globalVar.a);
		// System.out.println("全局变量  a = " + a); 错误写法
		globalVar.print();
		System.out.println("全局变量变化后  a = " + globalVar.a);
	}

	public void print() {
		System.out.println("在print()中, 全局变量　a = " + a + ", b = " + b);
		a = 30;
		System.out.println("在print()中, 全局变量　a = " + a + ", b = " + b);
	}
}