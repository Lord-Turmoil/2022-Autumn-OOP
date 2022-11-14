package scs.command;

import scs.Platform;
import scs.info.AdminInfoFilter;
import scs.info.ProtectAdminInfoFilter;
import scs.permission.*;
import scs.user.*;
import scs.common.*;
import scs.att.*;

import java.util.Iterator;
import java.util.TreeSet;
import java.util.Vector;

class ListAdminCommand implements ICommand {
	/**
	 * Handle listAdmin command.
	 * @param args none
	 * @return Return handle result.
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
		if (!user.hasPermission(Permission.ListAdmin)) {
			return ErrorType.PERMISSION_DENIED;
		}

		Course course = platform.getActiveCourse();
		if (course == null) {
			return ErrorType.NO_COURSE_SELECTED;
		}

		return listAdmin(user, course.getAdmins());
	}

	private ErrorType listAdmin(User user, final TreeSet<String> admins) {
		if (user.hasPermission(Permission.ListAdminAdmin)) {
			listAdmin(admins);
		} else if (user.hasPermission(Permission.ListAdmin)) {
			listProtectAdmin(admins);
		} else {
			return ErrorType.PERMISSION_DENIED;
		}

		return ErrorType.SUCCESS;
	}

	private void listAdmin(final TreeSet<String> admins) {
		Iterator<String> it = admins.iterator();
		Platform platform = Platform.getInstance();
		AdminInfoFilter filter = new AdminInfoFilter();

		while (it.hasNext()) {
			String id = it.next();
			System.out.println(filter.filer(platform.getUser(id).getInfo()));
		}
	}

	private void listProtectAdmin(final TreeSet<String> admins) {
		Iterator<String> it = admins.iterator();
		Platform platform = Platform.getInstance();
		ProtectAdminInfoFilter filter = new ProtectAdminInfoFilter();

		while (it.hasNext()) {
			String id = it.next();
			System.out.println(filter.filer(platform.getUser(id).getInfo()));
		}
	}
}
