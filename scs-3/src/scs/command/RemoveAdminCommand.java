package scs.command;

import scs.Platform;
import scs.permission.*;
import scs.user.*;
import scs.util.*;
import scs.common.*;
import scs.att.*;

import java.util.Vector;

class RemoveAdminCommand implements ICommand {
	/**
	 * Handle removeAdmin command.
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
		if (!user.hasPermission(Permission.RemoveAdmin)) {
			return ErrorType.PERMISSION_DENIED;
		}

		Course course = platform.getActiveCourse();
		if (course == null) {
			return ErrorType.NO_COURSE_SELECTED;
		}

		String id = args.elementAt(0);
		if (Inspector.checkUserId(id) == UserType.Unknown) {
			return ErrorType.USER_ID_ILLEGAL;
		}
		if (!platform.hasUser(id)) {
			return ErrorType.USER_ID_NOT_EXIST;
		}
		if (!course.isAdmin(id)) {
			return ErrorType.USER_ID_NOT_EXIST;
		}

		course.removeAdmin(id);
		System.out.println("remove admin success");

		return ErrorType.SUCCESS;
	}
}
