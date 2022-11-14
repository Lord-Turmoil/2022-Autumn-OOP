package scs.command;

import scs.Platform;
import scs.info.WareInfoFilter;
import scs.permission.*;
import scs.user.*;
import scs.common.*;
import scs.att.*;

import java.util.TreeMap;
import java.util.Vector;

class ListWareCommand implements ICommand {
	/**
	 * Handle listWare command.
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
		if (!user.hasPermission(Permission.ListWare)) {
			return ErrorType.PERMISSION_DENIED;
		}

		Course course = platform.getActiveCourse();
		if (course == null) {
			return ErrorType.NO_COURSE_SELECTED;
		}

		listWare(course.getWares());

		return ErrorType.SUCCESS;
	}

	private void listWare(final TreeMap<String, Ware> wares) {
		WareInfoFilter filter = new WareInfoFilter();

		if (wares.values().size() == 0) {
			System.out.println("total 0 ware");
		} else {
			for (Ware ware : wares.values()) {
				System.out.println(filter.filer(ware.getInfo()));
			}
		}
	}
}
