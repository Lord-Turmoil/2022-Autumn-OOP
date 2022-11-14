package top.ts.oop.lab09.tri;

public class Triangle {
	private double a, b, c;

	public Triangle(double a, double b, double c) throws NotTriangleException {
		if (!isTriangle(a, b, c)) {
			throw new NotTriangleException();
		}

		this.a = a;
		this.b = b;
		this.c = c;
	}

	private boolean isTriangle(double a, double b, double c) {
		if (!((a + b > c) && (b + c > a) && (c + a > b))) {
			return false;
		}
		return true;
	}

	public double getArea() {
		double p = (a + b + c) / 2;
		return Math.sqrt(p * (p - a) * (p - b) * (p - c));
	}

	public String showInfo() {
		StringBuilder buffer = new StringBuilder();

		buffer.append("Triangle: ");
		buffer.append(a).append(" ").append(b).append(" ").append(c);

		return buffer.toString();
	}

	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder(showInfo());

		buffer.append(" Area:").append(getArea());

		return buffer.toString();
	}
}
