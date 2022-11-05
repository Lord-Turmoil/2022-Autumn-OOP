package top.ts.oop.lab06.shape;

public class ShapeFactory {
	public Shape makeShape(ShapeType type, double a, double b) {
		switch (type) {
			case ELLIPSE -> {
				return new Ellipse(a, b);
			}
			case RECTANGLE -> {
				return new Rectangle(a, b);
			}
			case RHOMBUS -> {
				return new Rhombus(a, b);
			}
			default -> {
				return null;
			}
		}
	}

	public Shape randomNextShape() {
		int id = (int) (Math.random() * ShapeType.values().length);
		return makeShape(ShapeType.values()[id], Math.random() * 100, Math.random() * 100);
	}

	public static void main(String[] args) {
		Shape[] shapes = new Shape[5];

		ShapeFactory factory = new ShapeFactory();

		for (int i = 0; i < 5; i++) {
			shapes[i] = factory.randomNextShape();
		}

		for (Shape shape : shapes) {
			System.out.println(shape);
		}
	}
}
