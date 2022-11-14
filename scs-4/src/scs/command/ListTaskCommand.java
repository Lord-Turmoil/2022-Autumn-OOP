package scs.command;

import scs.Platform;
import scs.info.StudentTaskInfoFilter;
import scs.info.TaskInfoFilter;
import scs.permission.*;
import scs.user.*;
import scs.common.*;
import scs.att.*;

import java.util.TreeMap;
import java.util.Vector;

class ListTaskCommand implements ICommand {
	/**
	 * Handle listTask command.
	 * @param args none
	 * @return Handle result.
	 */
	@Override
	public ErrorType handle(Vector<String> args) {
		if (args.size() != 0) {
			return ErrorType.ARGUMENTS_ILLEGAL;
		}

		Platform platform = Platform.getInstance();
		User user = platform.getActiveUser();
		if (user == null) {
			return ErrorType.NOT_LOGGED_IN;
		}

		Course course = platform.getActiveCourse();
		if (course == null) {
			return ErrorType.NO_COURSE_SELECTED;
		}

		if (user.hasPermission(Permission.ListTaskAdmin)) {
			listTaskAdmin(course.getTasks());
		} else if (user.hasPermission(Permission.ListTask)) {
			listTask(user.getId(), course.getTasks());
		} else {
			return ErrorType.PERMISSION_DENIED;
		}

		return ErrorType.SUCCESS;
	}

	private void listTask(String id, final TreeMap<String, Task> tasks) {
		StudentTaskInfoFilter filter = new StudentTaskInfoFilter();

		if (tasks.size() == 0) {
			System.out.println("total 0 task");
			return;
		}

		for (Task task : tasks.values()) {
			System.out.println(filter.filer(task.getInfo(id)));
		}
	}

	private void listTaskAdmin(final TreeMap<String, Task> tasks) {
		TaskInfoFilter filter = new TaskInfoFilter();

		if (tasks.size() == 0) {
			System.out.println("total 0 task");
			return;
		}

		for (Task task : tasks.values()) {
			System.out.println(filter.filer(task.getInfo()));
		}
	}
}
