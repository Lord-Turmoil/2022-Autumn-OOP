package top.ts.oop.lab07.shape;

public class Rhombus extends Shape {
	private static IShapeFactory factory = new IShapeFactory() {
		@Override
		public Shape makeShape(double a, double b) {
			return new Rhombus(a, b);
		}
	};

	private Rhombus(double a, double b) {
		super(a, b, "rhombus");
	}

	public static IShapeFactory getFactoryInstance() {
		return factory;
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