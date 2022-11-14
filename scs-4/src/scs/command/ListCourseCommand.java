package scs.command;

import scs.Platform;
import scs.info.AdminInfoFilter;
import scs.info.CourseInfoFilter;
import scs.permission.*;
import scs.user.*;
import scs.common.*;
import scs.att.*;

import java.util.TreeMap;
import java.util.Vector;

class ListCourseCommand implements ICommand {
	/**
	 * Handle listCourse command.
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

		return listCourse(user, platform.getCourses());
	}

	private ErrorType listCourse(User user, final TreeMap<String, Course> courses) {
		if (user.hasPermission(Permission.ListCourseAdmin)) {
			return listAdminCourse(user, courses);
		} else if (user.hasPermission(Permission.ListCourse)) {
			return listStudentCourse(user, courses);
		} else {
			return ErrorType.PERMISSION_DENIED;
		}
	}

	private ErrorType listAdminCourse(User user, final TreeMap<String, Course> courses) {
		CourseInfoFilter filter = new CourseInfoFilter();
		boolean flag = false;

		for (Course course : courses.values()) {
			if (course.isAdmin(user.getId())) {
				System.out.println(filter.filer(course.getInfo()));
				flag = true;
			}
		}

		if (!flag) {
			return ErrorType.COURSE_NOT_EXIST;
		}

		return ErrorType.SUCCESS;
	}

	private ErrorType listStudentCourse(User user, final TreeMap<String, Course> courses) {
		CourseInfoFilter filter = new CourseInfoFilter();
		boolean flag = false;

		for (Course course : courses.values()) {
			if (course.isStudent(user.getId())) {
				System.out.println(filter.filer(course.getInfo()));
				flag = true;
			}
		}

		if (!flag) {
			return ErrorType.COURSE_NOT_EXIST;
		}

		return ErrorType.SUCCESS;
	}
}