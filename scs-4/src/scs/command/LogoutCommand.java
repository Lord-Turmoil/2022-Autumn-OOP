package scs.command;

import scs.Platform;
import scs.common.*;

import java.util.Vector;

class LogoutCommand implements ICommand {
	/**
	 * Logout command.
	 * @param args none
	 * @return Handle result.
	 */
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
