package scs.command;

import scs.common.ErrorType;
import scs.util.FileUtil;

import java.util.Vector;

public class OpenFileCommand implements ICommand {
	private String srcDir;
	private String mode;

	/**
	 * Handle openFile command.
	 * @param args [filename] [<] [redirect filename]
	 * @return Handle result.
	 */
	@Override
	public ErrorType handle(Vector<String> args) {
		if (args.size() > 3) {
			return ErrorType.ARGUMENTS_ILLEGAL;
		}

		reset();

		if (args.size() == 0) {
			System.out.println("please input the path to open the file");
			return ErrorType.SUCCESS;
		}

		if (args.contains("<")) {
			mode = "<";
		}

		if (mode.equals("<")) {
			return handleRedirect(args);
		} else {
			return handleNoRedirect(args);
		}
	}

	private void reset() {
		srcDir = "";
		mode = "";
	}

	/**
	 * Handle no redirect.
	 * @param args filename
	 * @return Handle result.
	 */
	private ErrorType handleNoRedirect(Vector<String> args) {
		if (args.size() != 1) {
			return ErrorType.ARGUMENTS_ILLEGAL;
		}

		srcDir = args.elementAt(0);

		return openFile();
	}

	/**
	 * Handle redirect.
	 * @param args [filename] < redirect filename
	 * @return
	 */
	private ErrorType handleRedirect(Vector<String> args) {
		int pos = args.indexOf(mode);
		if (pos == -1) {
			return ErrorType.ARGUMENTS_ILLEGAL;
		} else if (pos == args.size() - 1) {
			System.out.println("please input the path to redirect the file");
			return ErrorType.SUCCESS;
		} else if (pos != args.size() - 2) {
			return ErrorType.ARGUMENTS_ILLEGAL;
		}

		if (args.size() == 3) {
			// srcFile < redirect
			srcDir = args.elementAt(0);
		} else {
			// < redirect
			srcDir = args.elementAt(1);
		}

		return openFile();
	}

	private ErrorType openFile() {
		if (FileUtil.showFile(srcDir) != ErrorType.SUCCESS) {
			return ErrorType.FILE_OPEN_FAILED;
		}

		return ErrorType.SUCCESS;
	}
}
