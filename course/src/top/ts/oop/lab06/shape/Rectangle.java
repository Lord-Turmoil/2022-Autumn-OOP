package top.ts.oop.lab06.shape;

public class Rectangle extends Shape {
	public Rectangle() {}

	public Rectangle(double a, double b) {
		super(a, b);
	}

	@Override
	public double calcArea() {
		return a * b;
	}

	@Override
	public String toString() {
		return "Rectangle [ " + super.toString() + " ]";
	}
}
