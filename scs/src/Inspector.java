import java.util.Scanner;

class Inspector {
	/***
	 * Check user id and return corresponding type.
	 * @param id - User id.
	 * @return Type of user. Unknown if illegal.
	 */
	public static UserType checkId(String id) {
		if (id == null) {
			return UserType.Unknown;
		}

		// ^...$ for strict match. :)
		if (id.matches("^\\d{5}$")) {
			if (!id.matches("0{5}")) {
				return UserType.Professor;
			}
		}

		if (id.matches("^BY\\d{7}$")) {
			if (checkBYId(id)) {
				return UserType.Student;
			}
		} else if (id.matches("^(S|Z)Y\\d{7}$")) {
			if (checkSYZYId(id)) {
				return UserType.Student;
			}
		} else if (id.matches("^\\d{8}$")) {
			if (checkUnderId(id)) {
				return UserType.Student;
			}
		}

		return UserType.Unknown;
	}

	private static boolean checkBYId(String id) {
		// 01 2  4  6 78 9
		// BY yy ss c rr
		String str;
		int n;

		// Year enrolled.
		str = id.substring(2, 4);
		n = Integer.parseInt(str);
		// What? Must be braced?
		if (n <= (Global.CURRENT_YEAR - 6) || n > Global.CURRENT_YEAR) {
			return false;
		}

		// School id.
		str = id.substring(4, 6);
		n = Integer.parseInt(str);
		if (n < 1 || n > 43) {
			return false;
		}

		// Class id.
		str = id.substring(6, 7);
		n = Integer.parseInt(str);
		if (n < 1 || n > 6) {
			return false;
		}

		// Trivia.
		str = id.substring(7);
		n = Integer.parseInt(str);
		if (n == 0) {
			return false;
		}

		return true;
	}

	private static boolean checkSYZYId(String id) {
		// 01 2  4  6 78 9
		// SY yy ss c rr
		String str;
		int n;

		// Year enrolled.
		str = id.substring(2, 4);
		n = Integer.parseInt(str);
		if (n <= (Global.CURRENT_YEAR - 4) || n > Global.CURRENT_YEAR) {
			return false;
		}

		// School id.
		str = id.substring(4, 6);
		n = Integer.parseInt(str);
		if (n < 1 || n > 43) {
			return false;
		}

		// Class id.
		str = id.substring(6, 7);
		n = Integer.parseInt(str);
		if (n < 1 || n > 6) {
			return false;
		}

		// Trivia.
		str = id.substring(7);
		n = Integer.parseInt(str);
		if (n == 0) {
			return false;
		}

		return true;
	}

	private static boolean checkUnderId(String id) {
		// 0  2  4 5  8
		// yy ss c rrr
		String str;
		int n;

		// Year enrolled.
		str = id.substring(0, 2);
		n = Integer.parseInt(str);
		if (n <= (Global.CURRENT_YEAR - 6) || n > Global.CURRENT_YEAR) {
			return false;
		}

		// School id.
		str = id.substring(2, 4);
		n = Integer.parseInt(str);
		if (n < 1 || n > 43) {
			return false;
		}

		// Class id.
		str = id.substring(4, 5);
		n = Integer.parseInt(str);
		if (n < 1 || n > 6) {
			return false;
		}

		// Trivia.
		str = id.substring(5);
		n = Integer.parseInt(str);
		if (n == 0) {
			return false;
		}

		return true;
	}

	/***
	 * Check user first name or last name.
	 * @param name User name.
	 * @return Whether is legal name or not.
	 */
	public static boolean checkName(String name) {
		if (name == null) {
			return false;
		}

		return name.matches("^[A-Z][a-z]{0,19}$");
	}

	/***
	 * Check Email.
	 * @param email User's email.
	 * @return Whether is legal email or not.
	 */
	public static boolean checkEmail(String email) {
		if (email == null) {
			return false;
		}

		// ^\\w+@\\w+(.\\w+)+$;
		// Notice that . and \. are different!
		return email.matches("^\\w+@\\w+(\\.\\w+)+$");
	}

	/***
	 * Check user's password.
	 * @param password User's password.
	 * @return Whether is legal password or not.
	 */
	public static boolean checkPassword(String password) {
		if (password == null) {
			return false;
		}

		return password.matches("^[a-zA-Z][_0-9a-zA-Z]{7,15}$");
	}
}

class InspectorTest {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String id;

		while (scanner.hasNext()) {
			id = scanner.next();
			if (id.equals("q")) {
				break;
			}
			System.out.print(id + ": ");
			System.out.println(Inspector.checkId(id));
		}
	}
}
