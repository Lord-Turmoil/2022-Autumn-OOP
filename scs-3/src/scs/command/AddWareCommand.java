package scs.command;

import scs.Platform;
import scs.permission.*;
import scs.user.*;
import scs.util.*;
import scs.common.*;
import scs.att.*;

import java.util.Vector;

class AddWareCommand implements ICommand {
	/**
	 * Handle addWare command
	 * @param args id, name
	 * @return Handle result.
	 */
	@Override
	public ErrorType handle(Vector<String> args) {
		if (args.size() != 2) {
			return ErrorType.ARGUMENTS_ILLEGAL;
		}

		Platform platform = Platform.getInstance();
		User user = platform.getActiveUser();
		if (user == null) {
			return ErrorType.NOT_LOGGED_IN;
		}
		if (!user.hasPermission(Permission.AddWare)) {
			return ErrorType.PERMISSION_DENIED;
		}

		Course course = platform.getActiveCourse();
		if (course == null) {
			return ErrorType.NO_COURSE_SELECTED;
		}

		String id = args.elementAt(0);
		if (!Inspector.checkWareId(id, course.getId())) {
			return ErrorType.WARE_ID_ILLEGAL;
		}

		String name = args.elementAt(1);
		if (!Inspector.checkWareName(name)) {
			return ErrorType.WARE_NAME_ILLEGAL;
		}

		ErrorType error = addWare(new Ware(id, name, user, course));

		if (error == ErrorType.SUCCESS) {
			System.out.println("add ware success");
		}

		return error;
	}

	private ErrorType addWare(Ware ware) {
		Platform platform = Platform.getInstance();
		Course course = platform.getActiveCourse();
		ErrorType error;

		if (!FileUtil.exists(ware.getName())) {
			return ErrorType.WARE_FILE_DOES_NOT_EXIST;
		}

		// Resolve conflict by deleting old ware.
		if (course.hasWare(ware.getId())) {
			Ware oldWare = course.getWare(ware.getId());
			course.removeWare(oldWare);

			error = FileUtil.delete(oldWare.getFilename());
			if (error != ErrorType.SUCCESS) {
				return ErrorType.WARE_FILE_OPERATION_FAILED;
			}
		}

		// Add new ware and move file.

		error = FileUtil.copy(ware.getName(), ware.getFilename());
		if (error != ErrorType.SUCCESS) {
			return ErrorType.WARE_FILE_OPERATION_FAILED;
		}
		course.addWare(ware);

		return ErrorType.SUCCESS;
	}
}
