import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

class Inspector {
	/***
	 * Check user id and return corresponding type.
	 * @param id - User id.
	 * @return Type of user. Unknown if illegal.
	 */
	public static UserType checkUserId(String id) {
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
		int n;

		// Year enrolled.
		n = Integer.parseInt(id.substring(2, 4));
		// What? Must be braced?
		if (n <= (Global.CURRENT_YEAR - 6) || n > Global.CURRENT_YEAR) {
			return false;
		}

		// School id.
		n = Integer.parseInt(id.substring(4, 6));
		if (n < 1 || n > 43) {
			return false;
		}

		// Class id.
		n = Integer.parseInt(id.substring(6, 7));
		if (n < 1 || n > 6) {
			return false;
		}

		// Trivia.
		n = Integer.parseInt(id.substring(7));
		if (n == 0) {
			return false;
		}

		return true;
	}

	private static boolean checkSYZYId(String id) {
		// 01 2  4  6 78 9
		// SY yy ss c rr
		int n;

		// Year enrolled.
		n = Integer.parseInt(id.substring(2, 4));
		if (n <= (Global.CURRENT_YEAR - 4) || n > Global.CURRENT_YEAR) {
			return false;
		}

		// School id.
		n = Integer.parseInt(id.substring(4, 6));
		if (n < 1 || n > 43) {
			return false;
		}

		// Class id.
		n = Integer.parseInt(id.substring(6, 7));
		if (n < 1 || n > 6) {
			return false;
		}

		// Trivia.
		n = Integer.parseInt(id.substring(7));
		if (n == 0) {
			return false;
		}

		return true;
	}

	private static boolean checkUnderId(String id) {
		// 0  2  4 5  8
		// yy ss c rrr
		int n;

		// Year enrolled.
		n = Integer.parseInt(id.substring(0, 2));
		if (n <= (Global.CURRENT_YEAR - 6) || n > Global.CURRENT_YEAR) {
			return false;
		}

		// School id. [1, 43]
		n = Integer.parseInt(id.substring(2, 4));
		if (n < 1 || n > 43) {
			return false;
		}

		// Class id. [1, 6]
		n = Integer.parseInt(id.substring(4, 5));
		if (n < 1 || n > 6) {
			return false;
		}

		// Trivia. [1, 99]
		n = Integer.parseInt(id.substring(5));
		if (n == 0) {
			return false;
		}

		return true;
	}

	/***
	 * Check user's first name or last name.
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

	/***
	 * Check course ID.
	 * @param id Course ID.
	 * @return Whether is legal or not.
	 */
	public static boolean checkCourseId(String id) {
		// 0 1  3  5
		// C yy rr
		if (id == null) {
			return false;
		}

		if (!id.matches("^C\\d{4}$")) {
			return false;
		}

		int n;

		// Check year.
		n = Integer.parseInt(id.substring(1, 3));
		if (n <= (Global.CURRENT_YEAR - 6) || n > Global.CURRENT_YEAR) {
			return false;
		}

		// Check trivia.
		n = Integer.parseInt(id.substring(3));
		if (n == 0) {
			return false;
		}

		return true;
	}

	/***
	 * Check course name.
	 * @param name Course name.
	 * @return Whether is legal course name or not.
	 */
	public static boolean checkCourseName(String name) {
		if (name == null) {
			return false;
		}

		return name.matches("^\\w{6,16}$");
	}

	/***
	 * Check ware ID.
	 * @param id Ware ID.
	 * @param prefix Course ID.
	 * @return Whether is legal or not.
	 */
	public static boolean checkWareId(String id, String prefix) {
		// 0 1    5 7
		// W cccc rr
		if (id == null) {
			return false;
		}

		if (!id.matches("^W\\d{6}$")) {
			return false;
		}

		int n;

		// Check prefix
		n = Integer.parseInt(id.substring(1, 5));
		if (n != Integer.parseInt(prefix.substring(1))) {
			return false;
		}

		// Check trivia.
		n = Integer.parseInt(id.substring(5));
		if (n == 0) {
			return false;
		}

		return true;
	}

	/***
	 * Check ware name.
	 * @param name Ware name.
	 * @return Whether is legal course name or not.
	 */
	public static boolean checkWareName(String name) {
		// xxx.xxx
		if (name == null) {
			return false;
		}

		if (name.length() < 6 || name.length() > 16) {
			return false;
		}

		return name.matches("^\\w+\\.[a-zA-Z0-9]+$");
	}

	/***
	 * Check task ID.
	 * @param id Ware ID.
	 * @param prefix Course ID.
	 * @return Whether is legal or not.
	 */
	public static boolean checkTaskId(String id, String prefix) {
		// 0 1    5 7
		// T cccc rr
		if (id == null) {
			return false;
		}

		if (!id.matches("^T\\d{6}$")) {
			return false;
		}

		int n;

		// Check prefix
		n = Integer.parseInt(id.substring(1, 5));
		if (n != Integer.parseInt(prefix.substring(1))) {
			return false;
		}

		// Check trivia.
		n = Integer.parseInt(id.substring(5));
		if (n == 0) {
			return false;
		}

		return true;
	}

	/***
	 * Check task name.
	 * @param name Ware name.
	 * @return Whether is legal course name or not.
	 */
	public static boolean checkTaskName(String name) {
		// xxx.xxx
		if (name == null) {
			return false;
		}

		if (name.length() < 6 || name.length() > 16) {
			return false;
		}

		return name.matches("^\\w+\\.[a-zA-Z0-9]+$");
	}

	/***
	 * Check date.
	 * @param date String form date.
	 * @return Return date if success, null if failed.
	 */
	public static Date checkDate(String date) {
		if (date == null) {
			return null;
		}

		/*
		// 0123456789
		// yyyy-mm-dd
		int month = Integer.parseInt(date.substring(5, 7));
		int day = Integer.parseInt(date.substring(8, 10));
		if (month == 2 && day == 29) {
			if (!isLeapYear(year)) {
				return null;
			}
		}
		 */

		/*
		 * Notice that 'h' represents hour in a.m./p.m., which is between 0 and 12,
		 * while 'H' represents hour in day, which is what we want.
		 */
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
		Date ret = null;

		format.setLenient(false);
		try {
			ret = format.parse(date);
		} catch (ParseException e) {
			// Well, if failed, format will set ret to null.
			ret = null;
		}

		if (ret == null) {
			return null;
		}

		// Extra check for year.
		int year = Integer.parseInt(date.substring(0, 4));
		if (year < 1900 || year > 9999) {
			return null;
		}

		return ret;
	}

	private static boolean isLeapYear(int year) {
		if (year % 100 == 0) {
			return year % 400 == 0;
		} else {
			return year % 4 == 0;
		}
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
			System.out.println(Inspector.checkUserId(id));
		}
	}
}
