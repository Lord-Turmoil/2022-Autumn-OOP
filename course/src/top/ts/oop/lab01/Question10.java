package top.ts.oop.lab01;

public class Question10 {
	public static void main(String[] args) {
		Question10 localVar = new Question10();
		// System.out.println("局部变量 a = " + localVar.a); 引用错误，下同
		localVar.print();
		// System.out.println("局部变量变化后  a = " + localVar.a);
	}

	public void print() {
		int a = 10; // 局部变量，下同
		double b = 20;
		System.out.println("在print()中的局部变量　a = " + a + ", b = " + b);
		a = 30;
		System.out.println("在print()中的局部变量　a = " + a + ", b = " + b);
	}
}
