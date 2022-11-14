package scs.command;

import scs.common.*;
import scs.util.FileUtil;

import java.util.Vector;

class QuitCommand implements ICommand {
	/**
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

		FileUtil.delete(Global.ROOT_DIR);

		return ErrorType.SHUTDOWN;
	}
}
