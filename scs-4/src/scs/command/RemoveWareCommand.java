package scs.command;

import scs.Platform;
import scs.permission.*;
import scs.user.*;
import scs.util.*;
import scs.common.*;
import scs.att.*;

import java.util.Vector;

class RemoveWareCommand implements ICommand {
	/**
	 * Handle removeWare command.
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
		if (!user.hasPermission(Permission.RemoveWare)) {
			return ErrorType.PERMISSION_DENIED;
		}

		Course course = platform.getActiveCourse();
		if (course == null) {
			return ErrorType.NO_COURSE_SELECTED;
		}

		String id = args.elementAt(0);
		if (!Inspector.checkWareId(id, course.getId())) {
			return ErrorType.WARE_NOT_FOUND;
		}
		if (!course.hasWare(id)) {
			return ErrorType.WARE_NOT_FOUND;
		}

		Ware ware = course.getWare(id);
		if (!ware.getCourse().isAdmin(user.getId())) {
			return ErrorType.WARE_NOT_FOUND;
		}

		ErrorType error = removeWare(ware);
		if (error == ErrorType.SUCCESS) {
			System.out.println("remove ware success");
		}

		return error;
	}

	private ErrorType removeWare(Ware ware) {
		Platform platform = Platform.getInstance();
		Course course = platform.getActiveCourse();

		course.removeWare(ware.getId());

		ErrorType error = FileUtil.delete(ware.getFilename());
		if (error != ErrorType.SUCCESS) {
			return ErrorType.DELETE_FILE_FAILED;
		}

		return ErrorType.SUCCESS;
	}
}
