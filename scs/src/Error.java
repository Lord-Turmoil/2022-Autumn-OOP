import java.util.Locale;

enum ErrorType {
	SUCCESS,
	LOGIN_FIRST,
	NOT_LOGGED_IN,
	ALREADY_LOGGED_IN,
	USER_ID_ILLEGAL,
	USER_ID_DUPLICATION,
	USER_ID_NOT_EXIST,
	USER_NAME_ILLEGAL,
	EMAIL_ADDRESS_ILLEGAL,
	PASSWORD_ILLEGAL,
	PASSWORDS_INCONSISTENT,
	WRONG_PASSWORD,
	PERMISSION_DENIED,
	ARGUMENTS_ILLEGAL,
	COMMAND_NOT_FOUND,

	SHUTDOWN
}

class Error {
	public static String getDescription(ErrorType type) {
		String description = type.toString();

		description = description.toLowerCase(Locale.ENGLISH);
		description = description.replace('_', ' ');

		return description;
	}
}

class ErrorTest {
	public static void main(String[] args) {
		for (ErrorType type : ErrorType.values()) {
			System.out.println(Error.getDescription(type));
		}
	}
}