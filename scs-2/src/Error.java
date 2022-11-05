import java.util.Locale;

enum ErrorType {
	SUCCESS,
	// log related
	LOGIN_FIRST,
	NOT_LOGGED_IN,
	ALREADY_LOGGED_IN,

	// legal related
	USER_ID_ILLEGAL,
	USER_ID_DUPLICATION,
	ADMIN_ID_DUPLICATION,
	USER_ID_NOT_EXIST,
	USER_NAME_ILLEGAL,
	EMAIL_ADDRESS_ILLEGAL,
	PASSWORD_ILLEGAL,
	PASSWORDS_INCONSISTENT,
	WRONG_PASSWORD,

	COURSE_ID_ILLEGAL,
	COURSE_ID_DUPLICATION,
	COURSE_NAME_ILLEGAL,
	COURSE_ID_NOT_EXIST,

	WARE_ID_ILLEGAL,
	WARE_ID_DUPLICATION,
	WARE_NAME_ILLEGAL,
	WARE_ID_NOT_EXIST,
	TASK_ID_ILLEGAL,
	TASK_ID_DUPLICATION,
	TASK_NAME_ILLEGAL,
	TASK_TIME_ILLEGAL,
	TASK_ID_NOT_EXIST,

	NO_COURSE_SELECTED,

	COMMAND_NOT_FOUND,
	PERMISSION_DENIED,
	ARGUMENTS_ILLEGAL,

	OTHERS,
	SHUTDOWN
}

class Error {
	public static String getDescription(ErrorType type) {
		String description = type.toString();

		/*
		 description = description.toLowerCase(Locale.ENGLISH);
		 description = description.replace('_', ' ');
		 return description;
		*/

		return description.toLowerCase().replace('_', ' ');
	}
}

class ErrorTest {
	public static void main(String[] args) {
		for (ErrorType type : ErrorType.values()) {
			System.out.println(Error.getDescription(type));
		}
	}
}