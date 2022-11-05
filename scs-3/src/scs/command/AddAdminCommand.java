package scs.command;

import scs.Platform;
import scs.common.ErrorType;
import scs.att.*;

import scs.permission.Permission;
import scs.user.User;
import scs.user.UserType;
import scs.util.Inspector;

import java.util.Vector;

class AddAdminCommand implements ICommand {
	/**
	 * Handle addAdmin command.
	 * @param args id, id, id, ...
	 * @return Handle result.
	 */
	@Override
	public ErrorType handle(Vector<String> args) {
		if (args.size() == 0) {
			return ErrorType.ARGUMENTS_ILLEGAL;
		}

		Platform platform = Platform.getInstance();
		User user = platform.getActiveUser();
		if (user == null) {
			return ErrorType.NOT_LOGGED_IN;
		}
		if (!user.hasPermission(Permission.AddAdmin)) {
			return ErrorType.PERMISSION_DENIED;
		}

		Course course = platform.getActiveCourse();
		if (course == null) {
			return ErrorType.NO_COURSE_SELECTED;
		}

		// Check all arguments first.
		for (String id : args) {
			if (Inspector.checkUserId(id) == UserType.Unknown) {
				return ErrorType.USER_ID_ILLEGAL;
			} else if (!platform.hasUser(id)) {
				return ErrorType.USER_ID_NOT_EXIST;
			}
		}

		// All good, add them all.
		for (String id : args) {
			course.addAdmin(id);
		}

		System.out.println("add admin success");

		return ErrorType.SUCCESS;
	}
}