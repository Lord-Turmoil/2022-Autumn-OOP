package top.ts.oop.lab06.rhombus;

public class D implements ID {
	@Override
	public void printInfo() {
		System.out.println(IB.share);
	}

	@Override
	public void showInfo() {
		System.out.println(IC.share);
	}
}
