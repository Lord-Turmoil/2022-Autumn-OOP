package scs.command;

import scs.common.ErrorType;
import java.util.Vector;

/**
 * This takes care of each specific command.
 * platform.command.Command will return error info but won't show it. All errors
 * will be presented by platform.command.Command platform.Host.
 */
public interface ICommand {
	/**
	 * Here, command itself is excluded from args.
	 * @param args User's arguments.
	 * @return Handle result.
	 */
	ErrorType handle(Vector<String> args);
}


