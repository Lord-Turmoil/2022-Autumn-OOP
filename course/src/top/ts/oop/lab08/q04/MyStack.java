package top.ts.oop.lab08.q04;

//MyStack.java
import java.util.LinkedList;

public class MyStack<T> {
	private LinkedList<T> values = new LinkedList<T>();

	public void push(T t) {
		values.push(t);	// push to front
	}

	public T pull() {
		return values.pop();
	}

	public T peek() {
		return values.peekFirst();
	}

	public boolean isEmpty() {
		return values.isEmpty();
	}

	public static void main(String[] args) {
		MyStack<Integer> stack = new MyStack<>();

		for (int i = 0; i < 5; i++) {
			stack.push(i);
		}

		while (!stack.isEmpty()) {
			System.out.print(stack.peek() + " ");
			stack.pull();
		}
	}
}