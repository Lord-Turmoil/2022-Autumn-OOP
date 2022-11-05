package top.ts.oop.lab07.shape;

public abstract class Shape {
	protected double a;
	protected double b;
	private String type;

	protected Shape(String type) {
		this(0.0, 0.0, type);
	}
	protected Shape(double a, double b, String type) {
		this.a = Math.max(a, 0.0);
		this.b = Math.max(b, 0.0);
		this.type = type;
	}

	/** calcArea
	 * Calculate area.
	 * @return Area.
	 */
	abstract public double calcArea();

	public String getType() {
		return type;
	}

	@Override
	public String toString() {
		return a + " " + b;
	}
}