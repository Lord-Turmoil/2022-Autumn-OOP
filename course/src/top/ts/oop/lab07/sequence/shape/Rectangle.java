package top.ts.oop.lab07.sequence.shape;

public class Rectangle extends Shape {
	private static IShapeFactory factory = new IShapeFactory() {
		@Override
		public Shape makeShape(double a, double b) {
			return new Rectangle(a, b);
		}
	};

	private Rectangle(double a, double b) {
		super(a, b);
	}

	public static IShapeFactory getFactoryInstance() {
		return factory;
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
