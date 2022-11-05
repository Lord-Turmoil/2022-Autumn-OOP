package scs.command;

import scs.Platform;
import scs.permission.*;
import scs.user.*;
import scs.util.*;
import scs.common.*;
import scs.att.*;

import java.util.Vector;

class AddStudentCommand implements ICommand {
	/**
	 * Handle addStudent command.
	 * @param args id, id, ...
	 * @return Handle result.
	 */
	public ErrorType handle(Vector<String> args) {
		if (args.size() == 0) {
			return ErrorType.ARGUMENTS_ILLEGAL;
		}

		Platform platform = Platform.getInstance();
		User user = platform.getActiveUser();
		if (user == null) {
			return ErrorType.NOT_LOGGED_IN;
		}
		if (!user.hasPermission(Permission.AddStudent)) {
			return ErrorType.PERMISSION_DENIED;
		}

		Course course = platform.getActiveCourse();
		if (course == null) {
			return ErrorType.NO_COURSE_SELECTED;
		}

		// Check all arguments first.
		for (String id : args) {
			UserType type = Inspector.checkUserId(id);
			if (type == UserType.Unknown) {
				return ErrorType.USER_ID_ILLEGAL;
			}
			if (!platform.hasUser(id)) {
				return ErrorType.USER_ID_NOT_EXIST;
			}
			if (type == UserType.Professor) {
				/*
				 * This is a little tricky, since this is not a
				 * standard error. So it is resolved internal. :(
				 */
				System.out.println("I'm professor rather than student!");
				return ErrorType.SUCCESS;
			}
		}

		// All done, add all.
		for (String id : args) {
			course.addStudent(id);
		}

		System.out.println("add student success");

		return ErrorType.SUCCESS;
	}
}
