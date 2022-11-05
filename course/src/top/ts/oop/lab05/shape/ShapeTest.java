package top.ts.oop.lab05.shape;

public class ShapeTest {
	public static void main(String[] args) {
		Shape shape = new Rectangle(5, 10);
		System.out.println("Area of " + shape + " is " + shape.calcArea());

		shape = new Rhombus(20, 10);
		System.out.println("Area of " + shape + " is " + shape.calcArea());

		shape = new Ellipse(4, 5);
		System.out.println("Area of " + shape + " is " + shape.calcArea());

		shape = new Rectangle(-10, -5);
		System.out.println("Area of " + shape + " is " + shape.calcArea());
	}
}
