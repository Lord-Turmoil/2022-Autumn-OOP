package scs.command;

import scs.Platform;
import scs.permission.*;
import scs.user.*;
import scs.util.*;
import scs.common.*;
import java.util.Vector;

class PrintInfoCommand implements ICommand {
	/**
	 * Handle printInfo command.
	 * @param args [id]
	 * @return Handle result.
	 */
	@Override
	public ErrorType handle(Vector<String> args) {
		if ((args.size() != 0) && (args.size() != 1)) {
			return ErrorType.ARGUMENTS_ILLEGAL;
		}

		Platform platform = Platform.getInstance();
		User activeUser = platform.getActiveUser();

		if (activeUser == null) {
			return ErrorType.LOGIN_FIRST;
		}

		if (args.size() == 0) {
			// Print platform.user himself.
			System.out.println(activeUser);
		} else {
			if (!activeUser.hasPermission(Permission.PrintInfo)) {
				return ErrorType.PERMISSION_DENIED;
			}

			String id = args.elementAt(0);
			if (Inspector.checkUserId(id) == UserType.Unknown) {
				return ErrorType.USER_ID_ILLEGAL;
			}
			if (!platform.hasUser(id)) {
				return ErrorType.USER_ID_NOT_EXIST;
			}
			System.out.println(platform.getUser(id));
		}

		return ErrorType.SUCCESS;
	}
}
