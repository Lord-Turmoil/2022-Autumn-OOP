package top.ts.oop.lab07.shape;

/**
 * Test the factory by creating one shape of each type.
 */
public class ShapeTest {
	public static void main(String[] args) {
		Rectangle rectangle = (Rectangle) Rectangle.getFactoryInstance().makeShape(2.0, 4.0);
		Ellipse ellipse = (Ellipse) Ellipse.getFactoryInstance().makeShape(3.0, 6.0);
		Rhombus rhombus = (Rhombus) Rhombus.getFactoryInstance().makeShape(5.0, 7.0);

		System.out.println(rectangle);
		System.out.println(rectangle.calcArea());
		System.out.println(ellipse);
		System.out.println(ellipse.calcArea());
		System.out.println(rhombus);
		System.out.println(rhombus.calcArea());
	}
}
