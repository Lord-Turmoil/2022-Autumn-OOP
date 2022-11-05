package scs.command;

import scs.Platform;
import scs.permission.*;
import scs.user.*;
import scs.util.*;
import scs.common.*;
import scs.att.*;

import java.util.Vector;

class RemoveCourseCommand implements ICommand {
	/**
	 * Handle removeCourse command.
	 * @param args id
	 * @return Handle result.
	 */
	@Override
	public ErrorType handle(Vector<String> args) {
		if (args.size() != 1) {
			return ErrorType.ARGUMENTS_ILLEGAL;
		}

		Platform platform = Platform.getInstance();
		User user = platform.getActiveUser();
		if (user == null) {
			return ErrorType.NOT_LOGGED_IN;
		}

		if (!user.hasPermission(Permission.RemoveCourse)) {
			return ErrorType.PERMISSION_DENIED;
		}

		String id = args.elementAt(0);
		if (!Inspector.checkCourseId(id)) {
			return ErrorType.COURSE_ID_ILLEGAL;
		}

		Course course = platform.getCourse(id);
		if (course == null) {
			return ErrorType.COURSE_ID_NOT_EXIST;
		}
		if (!course.isAdmin(user.getId())) {
			return ErrorType.COURSE_ID_NOT_EXIST;
		}

		platform.removeCourse(course.getId());

		System.out.println("remove course success");

		return ErrorType.SUCCESS;
	}
}
