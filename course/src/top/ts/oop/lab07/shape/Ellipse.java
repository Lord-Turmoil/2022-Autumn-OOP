package top.ts.oop.lab07.shape;

public class Ellipse extends Shape {
	/**
	 * You know, anonymous class... cannot realise type cast, since
	 * it is referred by a parent class. :(
	 */
	private static IShapeFactory factory = new IShapeFactory() {
		@Override
		public Shape makeShape(double a, double b) {
			return new Ellipse(a, b);
		}
	};

	private Ellipse(double a, double b) {
		super(a, b, "ellipse");
	}

	public static IShapeFactory getFactoryInstance() {
		return factory;
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
