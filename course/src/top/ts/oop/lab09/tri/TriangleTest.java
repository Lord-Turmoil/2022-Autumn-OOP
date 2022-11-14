package top.ts.oop.lab09.tri;

import java.util.LinkedList;

public class TriangleTest {
	public static void main(String[] args) {
		LinkedList<Triangle> list = new LinkedList<>();
		Triangle triangle;
		double a = 0;
		double b = 0;
		double c = 0;
		int cnt = 0;

		for (int i = 0; i < 10; i++) {
			try {
				a = random(2, 10);
				b = random(2, 10);
				c = random(2, 10);
				triangle = new Triangle(a, b, c);
			} catch (NotTriangleException e) {
				System.out.println(a + " " + b + " " + c + " is not a triangle!");
				continue;
			}
			System.out.println(triangle);
			list.add(triangle);
			cnt++;
		}
		System.out.println(cnt + " triangle(s) added.");
	}

	private static double random(double lower, double upper) {
		return lower + Math.random() * (upper - lower);
	}
}
