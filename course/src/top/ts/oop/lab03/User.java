package top.ts.oop.lab03;

public class User {
	public static int userCount = 0;
	public User() {
		++userCount;
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("bye");
		--userCount;
	}

	public static void main(String[] args) {
		System.out.println(User.userCount); // 1
		User u = new User();
		System.out.println(User.userCount); // 2
		u = null;
		System.out.println(User.userCount); // 3
		System.gc();

		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			System.out.println("What?");
		}

		System.out.println(User.userCount); // 4
	}
}
