package top.ts.oop.lab07.sequence.shape;

public abstract class Shape {
	protected double a;
	protected double b;

	protected Shape() {
		this(0.0, 0.0);
	}
	protected Shape(double a, double b) {
		this.a = Math.max(a, 0.0);
		this.b = Math.max(b, 0.0);
	}

	/** calcArea
	 * Calculate area.
	 * @return Area.
	 */
	abstract public double calcArea();

	@Override
	public String toString() {
		return a + " " + b;
	}
}