package top.ts.oop.lab11;

public class ThreadPrint {
	public static void main(String[] args) throws InterruptedException {
		Object a = new Object();
		Object b = new Object();
		Object c = new Object();
		Thread8 threadA = new Thread8("A", c, a);
		Thread8 threadB = new Thread8("B", a, b);
		Thread8 threadC = new Thread8("C", b, c);
		new Thread(threadA).start();
//		Thread.sleep(100);
		new Thread(threadB).start();
//		Thread.sleep(100);
		new Thread(threadC).start();
//		Thread.sleep(100);
	}
}

class Thread8 implements Runnable {
	private String name;
	private Object prev;
	private Object self;

	public Thread8(String name, Object prev, Object self) {
		this.name = name;
		this.prev = prev;
		this.self = self;
	}

	@Override
	public void run() {
		int count = 2;
		while (count > 0) {
			synchronized (prev) {
				synchronized (self) {
					System.out.println(name);
					count--;
                    self.notify();
					System.out.println("\t\t" + name + " synchronized self ends");
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				try {
					if (count == 0) {
						prev.notify();
						System.out.println("\t\t\t" + name + " prev.notify()");
					}
                    else {
						System.out.println("\t\t\t" + name + " prev.wait(before)");
						prev.wait();
						System.out.println("\t\t\t" + name + " prev.wait(after)");
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("\t\t" + name + " synchronized prev ends");
			}
		}
	}
}







