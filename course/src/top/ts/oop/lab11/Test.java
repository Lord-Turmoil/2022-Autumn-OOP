package top.ts.oop.lab11;


class Print {
	private int flag = 1;//信号量。当值为1时打印数字，当值为2时打印字母
	private int count = 1;

	public synchronized void printNum() {
		if (flag != 1) {
			//打印数字
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.print(2 * count - 1);
		System.out.println(2 * count);
		flag = 2;
		notify();
	}

	public synchronized void printChar() {
		System.out.println("CHAR " + this);
		if (flag != 2) {
			//打印字母
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println((char) (count - 1 + 'A'));
		count++;//当一轮循环打印完之后，计数器加1
		flag = 1;
		notify();
	}
}

public class Test {
	public static void main(String[] args) {
		Print print = new Print();
		new Thread(() -> {
			for (int i = 0; i < 26; i++) {
				print.printNum();
			}
		}).start();
		new Thread(() -> {
			for (int i = 0; i < 26; i++) {
				print.printChar();
			}
		}).start();
	}
}
