package top.ts.oop.lab11;

class Printer {
	// false: print num, true: print character
	private boolean semaphore = false;
	private int count = 0;

	public static final int BOUND = 26;

	public synchronized void printNumber() {
		if (semaphore) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		int num = count * 2 + 1;
		System.out.print(num);
		System.out.print(num + 1);

		semaphore = true;
		this.notify();
	}

	public synchronized void printCharacter() {
		if (!semaphore) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		char ch = (char) ('A' + count);
		System.out.print(ch);
		if (count < BOUND - 1) {
			System.out.print(" ");
		}
		semaphore = false;

		count++;

		this.notify();
	}
}

public class Pair {
	public static void main(String[] args) {
		Printer printer = new Printer();

		Thread threadNum = new Thread(() -> {
			for (int i = 0; i < Printer.BOUND; i++) {
				printer.printNumber();
			}
		});
		Thread threadChar = new Thread(() -> {
			for (int i = 0; i < Printer.BOUND; i++) {
				printer.printCharacter();
			}
		});

		threadNum.start();
		threadChar.start();



		try {
			threadNum.join();
			threadChar.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("\nDone.");
	}
}
