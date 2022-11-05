import java.util.Vector;

/***
 * This takes care of each specific command.
 * Command will return error info but won't show it. All errors
 * will be presented by Command Host.
 */
class Command {
	protected final String cmd;

	Command(String cmd) {
		this.cmd = cmd;
	}

	public String getCommand() {
		return cmd;
	}

	/***
	 * Here, command itself is excluded from args.
	 * @param args User's arguments.
	 * @return Handle result.
	 */
	public ErrorType handle(Vector<String> args) {
		return ErrorType.SUCCESS;
	}
}

class QuitCommand extends Command {
	QuitCommand() {
		super("QUIT");
	}

	/***
	 * Handle quit command.
	 * @param args This command doesn't have arguments.
	 * @return Handle result.
	 */
	@Override
	public ErrorType handle(Vector<String> args) {
		if (args.size() > 0) {
			return ErrorType.ARGUMENTS_ILLEGAL;
		}

		System.out.println("----- Good Bye! -----");

		return ErrorType.SHUTDOWN;
	}
}

class RegisterCommand extends Command {
	RegisterCommand() {
		super("register");
	}

	/***
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
		UserType type = Inspector.checkId(id);
		if (type == UserType.Unknown) {
			return ErrorType.USER_ID_ILLEGAL;
		}
		if (platform.hasUser(id)) {
			return ErrorType.USER_ID_DUPLICATION;
		}

		// Check first and last name.
		String firstName = args.elementAt(1);	// last name
		if (!Inspector.checkName(firstName)) {
			return ErrorType.USER_NAME_ILLEGAL;
		}
		String lastName = args.elementAt(2);	// first name
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

		// All good, create new user.
		User user;
		if (type == UserType.Professor) {
			user = new Professor(id, firstName, lastName, email, password);
		} else if (type == UserType.Student) {
			user = new Student(id, firstName, lastName, email, password);
		}
		else {
			// In fact, we won't reach here.
			return ErrorType.USER_ID_ILLEGAL;
		}
		platform.register(user);

		System.out.println("register success");

		return ErrorType.SUCCESS;
	}
}

class LoginCommand extends Command {
	LoginCommand() {
		super("login");
	}

	/***
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
		if (Inspector.checkId(id) == UserType.Unknown) {
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
		platform.login(user.id);
		System.out.println(user.getGreeting());

		return ErrorType.SUCCESS;
	}
}

class PrintInfoCommand extends Command {
	PrintInfoCommand() {
		super("printInfo");
	}

	/***
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
			// Print user himself.
			System.out.println(activeUser);
		} else {
			if (!activeUser.hasPermission(Permission.PrintInfo)) {
				return ErrorType.PERMISSION_DENIED;
			}

			String id = args.elementAt(0);
			if (Inspector.checkId(id) == UserType.Unknown) {
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

class LogoutCommand extends Command {
	LogoutCommand() {
		super("logout");
	}

	@Override
	public ErrorType handle(Vector<String> args) {
		if (args.size() != 0) {
			return ErrorType.ARGUMENTS_ILLEGAL;
		}

		if (Platform.getInstance().getActiveUser() == null) {
			return ErrorType.NOT_LOGGED_IN;
		}

		// All good, go.
		Platform.getInstance().logout();
		System.out.println("Bye~");

		return ErrorType.SUCCESS;
	}
}