package top.ts.oop.lab06.shape;

public abstract class Shape {
	protected double a;
	protected double b;

	public Shape() {
		this(0.0, 0.0);
	}
	public Shape(double a, double b) {
		this.a = Math.max(a, 0.0);
		this.b = Math.max(b, 0.0);
	}

	/** calcArea
	 * 计算形状的面积
	 * @return 面积
	 */
	abstract public double calcArea();

	/* 其他必要的方法，比如 getter 和 setter */
	@Override
	public String toString() {
		return a + " " + b;
	}
}