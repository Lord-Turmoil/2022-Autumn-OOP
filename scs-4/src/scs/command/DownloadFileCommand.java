package scs.command;

import scs.Platform;
import scs.att.Attachment;
import scs.att.Course;
import scs.common.Error;
import scs.common.ErrorType;
import scs.permission.Permission;
import scs.user.User;
import scs.util.FileUtil;
import scs.util.Inspector;

import java.nio.file.Paths;
import java.util.Vector;

public class DownloadFileCommand implements ICommand {
	private String saveDir;
	private String attachmentId;
	private String mode;
	private String destDir;

	/**
	 * Handle downloadFile command.
	 * @param args [save dir] id [>|>>] [redirect dir]
	 * @return Handle result.
	 */
	@Override
	public ErrorType handle(Vector<String> args) {
		if (args.size() < 2) {
			return ErrorType.ARGUMENTS_ILLEGAL;
		}

		reset();

		if (args.contains(">")) {
			mode = ">";
		} else if (args.contains(">>")) {
			mode = ">>";
		}

		if (mode.equals("")) {
			return handleNoRedirect(args);
		} else {
			return handleRedirect(args);
		}
	}

	private void reset() {
		saveDir = "";
		attachmentId = "";
		mode = "";
		destDir = "";
	}

	/**
	 * Handle no direct command.
	 * @param args save dir, task or ware id
	 * @return Handle result.
	 */
	private ErrorType handleNoRedirect(Vector<String> args) {
		if (args.size() != 2) {
			return ErrorType.ARGUMENTS_ILLEGAL;
		}

		saveDir = args.elementAt(0);
		attachmentId = args.elementAt(1);

		if (!FileUtil.isValid(saveDir)) {
			return ErrorType.FILE_OPERATION_FAILED;
		}

		Platform platform = Platform.getInstance();
		User user = platform.getActiveUser();
		if (user == null) {
			return ErrorType.NOT_LOGGED_IN;
		}

		Course course = platform.getActiveCourse();
		if (course == null) {
			return ErrorType.NO_COURSE_SELECTED;
		}

		Attachment attachment;
		if (course.hasWare(attachmentId)) {
			attachment = course.getWare(attachmentId);
		} else if (course.hasTask(attachmentId)) {
			attachment = course.getTask(attachmentId);
		} else {
			return ErrorType.FILE_NOT_FOUND;
		}

		if (FileUtil.copy(attachment.getFilename(), saveDir) != ErrorType.SUCCESS) {
			return ErrorType.FILE_OPERATION_FAILED;
		}

		FileUtil.showFile(saveDir);

		return ErrorType.SUCCESS;
	}

	/**
	 * Hmm... handle with redirect.
	 * @param args [save dir] id > dir
	 * @return Handle result.
	 */
	private ErrorType handleRedirect(Vector<String> args) {
		int pos = args.indexOf(mode);
		if (pos == -1) {
			return ErrorType.ARGUMENTS_ILLEGAL;
		}
		else if (pos == args.size() - 1) {
			System.out.println("please input the path to redirect the file");
			return ErrorType.SUCCESS;
		}
		else if (pos != args.size() - 2) {
			return ErrorType.ARGUMENTS_ILLEGAL;
		}

		destDir = args.elementAt(args.size() - 1);
		boolean append = !mode.equals(">");

		if (args.size() == 3) {
			// id > dest
			attachmentId = args.elementAt(0);
		} else if (args.size() == 4) {
			// src id > dest
			saveDir = args.elementAt(0);
			attachmentId = args.elementAt(1);
			if (Paths.get(saveDir).equals(Paths.get(destDir))) {
				System.out.println("input file is output file");
				return ErrorType.SUCCESS;
			}
		} else {
			FileUtil.writeString(destDir, Error.getDescription(ErrorType.ARGUMENTS_ILLEGAL), append);
			return ErrorType.SUCCESS;
		}

		ErrorType error = handleRedirect();
		if (error != ErrorType.SUCCESS) {
			FileUtil.writeString(destDir, Error.getDescription(error), append);
		}

		return ErrorType.SUCCESS;
	}

	private ErrorType handleRedirect() {
		Platform platform = Platform.getInstance();
		User user = platform.getActiveUser();
		boolean append = !mode.equals(">");

		if (user == null) {
			return ErrorType.NOT_LOGGED_IN;
		}
		if (!user.hasPermission(Permission.DownloadFile)) {
			return ErrorType.PERMISSION_DENIED;
		}

		Course course = platform.getActiveCourse();
		if (course == null) {
			return ErrorType.NO_COURSE_SELECTED;
		}

		Attachment attachment;
		if (course.hasWare(attachmentId)) {
			attachment = course.getWare(attachmentId);
		} else if (course.hasTask(attachmentId)) {
			attachment = course.getTask(attachmentId);
		} else {
			return ErrorType.FILE_NOT_FOUND;
		}

		return redirect(attachment);
	}

	private ErrorType redirect(Attachment attachment) {
		boolean append = !mode.equals(">");

		if (FileUtil.write(attachment.getFilename(), destDir, append) != ErrorType.SUCCESS) {
			// FileUtil.writeString(destDir, Error.getDescription(ErrorType.FILE_OPERATION_FAILED), append);
			return ErrorType.FILE_OPERATION_FAILED;
		}

		if (!saveDir.equals("")) {
			if (FileUtil.write(attachment.getFilename(), saveDir, false) != ErrorType.SUCCESS) {
				// FileUtil.writeString(destDir, Error.getDescription(ErrorType.FILE_OPERATION_FAILED), append);
				return ErrorType.FILE_OPERATION_FAILED;
			}
		}

		return ErrorType.SUCCESS;
	}
}
