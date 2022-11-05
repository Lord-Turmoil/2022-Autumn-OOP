package scs.command;

import scs.Platform;
import scs.permission.*;
import scs.user.*;
import scs.util.*;
import scs.common.*;
import scs.att.*;

import java.util.Vector;

class RemoveTaskCommand implements ICommand {
	/**
	 * Handle removeTask command.
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
		if (!user.hasPermission(Permission.RemoveTask)) {
			return ErrorType.PERMISSION_DENIED;
		}

		Course course = platform.getActiveCourse();
		if (course == null) {
			return ErrorType.NO_COURSE_SELECTED;
		}

		String id = args.elementAt(0);
		if (!course.hasTask(id)) {
			return ErrorType.TASK_NOT_FOUND;
		}

		Task task = course.getTask(id);
		if (!task.getCourse().isAdmin(user.getId())) {
			return ErrorType.TASK_NOT_FOUND;
		}

		ErrorType error = removeTask(task);

		if (error != ErrorType.SUCCESS) {
			return error;
		}

		System.out.println("remove task success");

		return ErrorType.SUCCESS;
	}

	private ErrorType removeTask(Task task) {
		Platform platform = Platform.getInstance();
		Course course = platform.getActiveCourse();

		course.removeTask(task.getId());

		ErrorType error = FileUtil.delete(task.getFilename());
		if (error != ErrorType.SUCCESS) {
			return ErrorType.DELETE_FILE_FAILED;
		}

		return ErrorType.SUCCESS;
	}
}
