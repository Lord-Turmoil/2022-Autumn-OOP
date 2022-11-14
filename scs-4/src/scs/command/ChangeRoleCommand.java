package scs.command;

import scs.Platform;
import scs.permission.*;
import scs.user.*;
import scs.common.*;

import java.util.Vector;

class ChangeRoleCommand implements ICommand {
	/**
	 * Handle changeRole command.
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

		if (!user.hasPermission(Permission.ChangeRole)) {
			return ErrorType.PERMISSION_DENIED;
		}

		String message = ((Student) user).changeRole();
		if (message == null) {
			return ErrorType.PERMISSION_DENIED;
		}

		System.out.println(message);

		return ErrorType.SUCCESS;
	}
}
