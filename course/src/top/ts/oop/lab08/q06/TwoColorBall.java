package top.ts.oop.lab08.q06;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

class Ball {
	private int id;
	private String color;

	public Ball(int id, String color) {
		this.id = id;
		this.color = color;
	}

	@Override
	public String toString() {
		return color + ": " + id;
	}
}

public class TwoColorBall {
	private static final int RED_RANGE = 33;
	private static final int RED_NUM = 6;
	private static final int BLUE_RANGE = 16;
	private static final int BLUE_NUM = 1;
	private static ArrayList<Integer> redPool = new ArrayList<>();
	private static ArrayList<Integer> bluePool = new ArrayList<>();

	static {
		for (int i = 1; i <= RED_RANGE; i++) {
			redPool.add(i);
		}
		for (int i = 1; i <= BLUE_RANGE; i++) {
			bluePool.add(i);
		}
	}

	private static LinkedList<Ball> generate() {
		LinkedList<Ball> result = new LinkedList<>();

		// Generate red balls.
		Collections.shuffle(redPool);
		for (int i = 0; i < RED_NUM; i++) {
			result.add(new Ball(redPool.get(i), "Red"));
		}

		// Generate blue ball
		Collections.shuffle(bluePool);
		for (int i = 0; i < BLUE_NUM; i++) {
			result.add(new Ball(bluePool.get(i), "Blue"));
		}

		Collections.shuffle(result);

		return result;
	}

	public static void main(String[] args) {
		LinkedList<Ball> result = generate();

		for (Ball ball : result) {
			System.out.println(ball);
		}
	}
}
