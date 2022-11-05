/***
 * This will create corresponding command based on
 * the current input.
 */
class CommandDispatcher {
	public static Command dispatch(String arg) {
		if (arg == null) {
			return null;
		}

		if (arg.equals("QUIT")) {
			return new QuitCommand();
		} else if (arg.equals("register")) {
			return new RegisterCommand();
		} else if (arg.equals("login")) {
			return new LoginCommand();
		} else if (arg.equals("printInfo")) {
			return new PrintInfoCommand();
		} else if (arg.equals("logout")) {
			return new LogoutCommand();
		} else {
			return null;
		}
	}
}