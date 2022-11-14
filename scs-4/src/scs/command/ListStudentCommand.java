package scs.command;

import scs.Platform;
import scs.info.StudentInfoFilter;
import scs.permission.*;
import scs.user.*;
import scs.common.*;
import scs.att.*;

import java.util.Iterator;
import java.util.TreeSet;
import java.util.Vector;

class ListStudentCommand implements ICommand {
	/**
	 * Handle listStudent command.
	 * @param args none
	 * @return Handle result.
	 */
	public ErrorType handle(Vector<String> args) {
		if (args.size() != 0) {
			return ErrorType.ARGUMENTS_ILLEGAL;
		}

		Platform platform = Platform.getInstance();
		User user = platform.getActiveUser();
		if (user == null) {
			return ErrorType.NOT_LOGGED_IN;
		}
		if (!user.hasPermission(Permission.ListStudent)) {
			return ErrorType.PERMISSION_DENIED;
		}

		Course course = platform.getActiveCourse();
		if (course == null) {
			return ErrorType.NO_COURSE_SELECTED;
		}

		listStudent(course.getStudents());

		return ErrorType.SUCCESS;
	}

	private void listStudent(final TreeSet<String> students) {
		Iterator<String> it = students.iterator();
		Platform platform = Platform.getInstance();
		StudentInfoFilter filter = new StudentInfoFilter();

		while (it.hasNext()) {
			String id = it.next();
			System.out.println(filter.filer(((Student) platform.getUser(id)).getInfo()));
		}
	}
}
