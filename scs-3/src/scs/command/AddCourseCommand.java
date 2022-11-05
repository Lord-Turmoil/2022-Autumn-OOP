package scs.command;

import scs.Platform;
import scs.permission.*;
import scs.user.*;
import scs.util.*;
import scs.common.*;
import scs.att.*;

import java.util.Vector;

class AddCourseCommand implements ICommand {
	/**
	 * Handle addCourse command.
	 * @param args id, name
	 * @return Handle result.
	 */
	@Override
	public ErrorType handle(Vector<String> args) {
		if (args.size() != 2) {
			return ErrorType.ARGUMENTS_ILLEGAL;
		}

		Platform platform = Platform.getInstance();
		User user = platform.getActiveUser();
		if (user == null) {
			return ErrorType.NOT_LOGGED_IN;
		}

		if (!user.hasPermission(Permission.AddCourse)) {
			return ErrorType.PERMISSION_DENIED;
		}

		String id = args.elementAt(0);
		if (!Inspector.checkCourseId(id)) {
			return ErrorType.COURSE_ID_ILLEGAL;
		}
		if (platform.hasCourse(id)) {
			return ErrorType.COURSE_ID_DUPLICATION;
		}

		String name = args.elementAt(1);
		if (!Inspector.checkCourseName(name)) {
			return ErrorType.COURSE_NAME_ILLEGAL;
		}

		platform.addCourse(new Course(id, name, user.getId()));

		System.out.println("add course success");

		return ErrorType.SUCCESS;
	}
}