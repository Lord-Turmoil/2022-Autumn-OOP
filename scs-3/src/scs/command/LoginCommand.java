package scs.command;

import scs.Platform;
import scs.user.*;
import scs.util.*;
import scs.common.*;
import java.util.Vector;

class LoginCommand implements ICommand {
	/**
	 * Handle login command.
	 * @param args id, password
	 * @return Handle result.
	 */
	@Override
	public ErrorType handle(Vector<String> args) {
		if (args.size() != 2) {
			return ErrorType.ARGUMENTS_ILLEGAL;
		}

		Platform platform = Platform.getInstance();
		if (platform.getActiveUser() != null) {
			return ErrorType.ALREADY_LOGGED_IN;
		}

		// Check id.
		String id = args.elementAt(0);
		if (Inspector.checkUserId(id) == UserType.Unknown) {
			return ErrorType.USER_ID_ILLEGAL;
		}

		User user = platform.getUser(id);
		if (user == null) {
			return ErrorType.USER_ID_NOT_EXIST;
		}

		// Check password.
		String password = args.elementAt(1);
		if (!user.getPassword().equals(password)) {
			return ErrorType.WRONG_PASSWORD;
		}

		// All good, here we go.
		platform.login(user.getId());
		System.out.println(user.getGreeting());

		return ErrorType.SUCCESS;
	}
}
