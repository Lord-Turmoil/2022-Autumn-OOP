package top.ts.oop.lab06.shape;

public class Rhombus extends Shape {
	public Rhombus() {}

	public Rhombus(double a, double b) {
		super(a, b);
	}

	@Override
	public double calcArea() {
		return a * b * 0.5;
	}

	@Override
	public String toString() {
		return "Rhombus [ " + super.toString() + " ]";
	}
}