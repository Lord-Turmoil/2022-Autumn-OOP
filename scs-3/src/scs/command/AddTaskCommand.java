package scs.command;

import scs.Platform;
import scs.permission.*;
import scs.user.*;
import scs.util.*;
import scs.common.*;
import scs.att.*;

import java.util.Date;
import java.util.Vector;

class AddTaskCommand implements ICommand {
	/**
	 * Handle addTask command.
	 * @param args id, name, startTime, endTime
	 * @return Handle result.
	 */
	@Override
	public ErrorType handle(Vector<String> args) {
		if (args.size() != 4) {
			return ErrorType.ARGUMENTS_ILLEGAL;
		}

		Platform platform = Platform.getInstance();
		User user = platform.getActiveUser();
		if (user == null) {
			return ErrorType.NOT_LOGGED_IN;
		}
		if (!user.hasPermission(Permission.AddTask)) {
			return ErrorType.PERMISSION_DENIED;
		}

		Course course = platform.getActiveCourse();
		if (course == null) {
			return ErrorType.NO_COURSE_SELECTED;
		}

		String id = args.elementAt(0);
		if (!Inspector.checkTaskId(id, course.getId())) {
			return ErrorType.TASK_ID_ILLEGAL;
		}

		String name = args.elementAt(1);
		if (!Inspector.checkTaskName(name)) {
			return ErrorType.TASK_NAME_ILLEGAL;
		}

		String startTimeStr = args.elementAt(2);
		Date startTime = Inspector.checkDate(startTimeStr);
		if (startTime == null) {
			return ErrorType.TASK_TIME_ILLEGAL;
		}
		String endTimeStr = args.elementAt(3);
		Date endTime = Inspector.checkDate(endTimeStr);
		if (endTime == null) {
			return ErrorType.TASK_TIME_ILLEGAL;
		}
		if (!startTime.before(endTime)) {
			return ErrorType.TASK_TIME_ILLEGAL;
		}

		ErrorType error = addTask(new Task(id, name, user, course, startTime, endTime));
		if (error != ErrorType.SUCCESS) {
			return error;
		}

		System.out.println("add task success");

		return ErrorType.SUCCESS;
	}

	private ErrorType addTask(Task task) {
		Platform platform = Platform.getInstance();
		Course course = platform.getActiveCourse();
		ErrorType error;

		if (!FileUtil.exists(task.getName())) {
			return ErrorType.TASK_FILE_NOT_FOUND;
		}

		// Resolve conflict by deleting old task.
		if (course.hasTask(task.getId())) {
			Task oldTask = course.getTask(task.getId());
			course.removeTask(oldTask);

			error = FileUtil.delete(oldTask.getFilename());
			if (error != ErrorType.SUCCESS) {
				return ErrorType.FILE_OPERATION_FAILED;
			}
		}

		// Add new task and move file.
		error = FileUtil.copy(task.getName(), task.getFilename());
		if (error != ErrorType.SUCCESS) {
			return ErrorType.FILE_OPERATION_FAILED;
		}
		course.addTask(task);

		return ErrorType.SUCCESS;
	}
}
