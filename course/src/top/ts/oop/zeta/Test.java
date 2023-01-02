package top.ts.oop.zeta;
public class Test {
	public static int a;
	public static void fun(String s) {
		System.out.print("s");
	}
	public static void fun(Object o) {
		System.out.print("o");
	}
	public static void main(String[] args) {
		fun(null);
		String s = null;
		Object o = s;
		fun(o); // fun((Object)null)
		fun(s); // fun((String)null
		A.getA();
	}
}

class A {
	private static A a = new A();

	private A() {
		System.out.println("Hello");
	}

	public static A getA() {
		return a;
	}
}