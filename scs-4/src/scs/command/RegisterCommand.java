package scs.command;

import scs.Platform;
import scs.user.*;
import scs.util.*;
import scs.common.*;
import java.util.Vector;

class RegisterCommand implements ICommand {
	/**
	 * Handle register command.
	 * @param args id, lastName, firstName, email, password, confirm password (6)
	 * @return Handle result.
	 */
	@Override
	public ErrorType handle(Vector<String> args) {
		if (args.size() != 6) {
			return ErrorType.ARGUMENTS_ILLEGAL;
		}

		Platform platform = Platform.getInstance();

		// Already logged in?
		if (platform.getActiveUser() != null) {
			return ErrorType.ALREADY_LOGGED_IN;
		}

		// Check ID.
		String id = args.elementAt(0);
		UserType type = Inspector.checkUserId(id);
		if (type == UserType.Unknown) {
			return ErrorType.USER_ID_ILLEGAL;
		}
		if (platform.hasUser(id)) {
			return ErrorType.USER_ID_DUPLICATION;
		}

		// Check first and last name.
		String firstName = args.elementAt(1);    // last name
		if (!Inspector.checkName(firstName)) {
			return ErrorType.USER_NAME_ILLEGAL;
		}
		String lastName = args.elementAt(2);    // first name
		if (!Inspector.checkName(lastName)) {
			return ErrorType.USER_NAME_ILLEGAL;
		}

		// Check email.
		String email = args.elementAt(3);
		if (!Inspector.checkEmail(email)) {
			return ErrorType.EMAIL_ADDRESS_ILLEGAL;
		}

		// Check password.
		String password = args.elementAt(4);
		if (!Inspector.checkPassword(password)) {
			return ErrorType.PASSWORD_ILLEGAL;
		}
		if (!password.equals(args.elementAt(5))) {
			return ErrorType.PASSWORDS_INCONSISTENT;
		}

		// All good, create new platform.user.
		User user;
		if (type == UserType.Professor) {
			user = new Professor(id, firstName, lastName, email, password);
		} else if (type == UserType.Student) {
			user = new Student(id, firstName, lastName, email, password);
		} else {
			// In fact, we won't reach here.
			return ErrorType.USER_ID_ILLEGAL;
		}
		platform.register(user);

		System.out.println("register success");

		return ErrorType.SUCCESS;
	}
}
