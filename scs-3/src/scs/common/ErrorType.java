package scs.common;

public enum ErrorType {
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
	COURSE_NOT_EXIST,

	WARE_ID_ILLEGAL,
	WARE_ID_DUPLICATION,
	WARE_NAME_ILLEGAL,
	WARE_NOT_FOUND,
	WARE_FILE_DOES_NOT_EXIST,
	WARE_FILE_OPERATION_FAILED,

	TASK_ID_ILLEGAL,
	TASK_ID_DUPLICATION,
	TASK_NAME_ILLEGAL,
	TASK_TIME_ILLEGAL,
	TASK_NOT_FOUND,
	TASK_FILE_NOT_FOUND,

	NO_COURSE_SELECTED,
	STUDENT_NOT_FOUND,

	COMMAND_NOT_FOUND,
	PERMISSION_DENIED,
	OPERATION_NOT_ALLOWED,
	ARGUMENTS_ILLEGAL,

	DELETE_FILE_FAILED,
	MOVE_FILE_FAILED,
	COPY_FILE_FAILED,
	FILE_OPERATION_FAILED,
	FILE_NOT_FOUND,
	FILE_OPEN_FAILED,
	UNEXPECTED_ERROR,

	// Custom error types.
	INVALID_DIRECTORY,
	INVALID_FILENAME,
	FAILED_TO_CREATE_DIRECTORY,
	FAILED_TO_CREATE_FILE,
	ANSWER_LINE_MISMATCH,
	EMPTY_ANSWER,
	SUBMIT_CANCELED,

	OTHERS,
	SHUTDOWN
}