package top.ts.oop.lab06.shape;

public class Ellipse extends Shape {
	public Ellipse() {}

	public Ellipse(double a, double b) {
		super(a, b);
	}

	@Override
	public double calcArea() {
		return Math.PI * a * b;
	}

	@Override
	public String toString() {
		return "Ellipse [ " + super.toString() + " ]";
	}
}
