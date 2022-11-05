package scs.command;

import scs.Host;
import scs.Platform;
import scs.att.Course;
import scs.att.Submission;
import scs.att.Task;
import scs.common.ErrorType;
import scs.permission.Permission;
import scs.user.User;
import scs.util.FileUtil;
import scs.util.Judge;

import java.util.Vector;

public class AddAnswerCommand implements ICommand {
	private String srcDir;
	private String taskId;
	private String mode;

	/**
	 * Handle addAnswer command.
	 * @param args [srcDir] taskId [<] [redirect]
	 * @return
	 */
	@Override
	public ErrorType handle(Vector<String> args) {
		if (args.size() < 2) {
			return ErrorType.ARGUMENTS_ILLEGAL;
		}

		reset();

		if (args.contains("<")) {
			mode = "<";
			return handleRedirect(args);
		} else {
			return handleNoRedirect(args);
		}
	}

	private void reset() {
		srcDir = "";
		taskId = "";
		mode = "";
	}

	// [srcDir] taskId < redirect
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
			srcDir = args.elementAt(2);	// redirect
			taskId = args.elementAt(0);
		} else if (args.size() == 4) {
			srcDir = args.elementAt(0);
			taskId = args.elementAt(1);
		} else {
			return ErrorType.ARGUMENTS_ILLEGAL;
		}

		return addAnswer();
	}


	// srcDir taskId
	private ErrorType handleNoRedirect(Vector<String> args) {
		if (args.size() != 2) {
			return ErrorType.ARGUMENTS_ILLEGAL;
		}

		srcDir = args.elementAt(0);
		taskId = args.elementAt(1);

		return addAnswer();
	}

	private ErrorType addAnswer() {
		Platform platform = Platform.getInstance();
		User user = platform.getActiveUser();
		if (user == null) {
			return ErrorType.NOT_LOGGED_IN;
		}
		if (!user.hasPermission(Permission.AddAnswer)) {
			return ErrorType.PERMISSION_DENIED;
		}

		Course course = platform.getActiveCourse();
		if (course == null) {
			return ErrorType.NO_COURSE_SELECTED;
		}

		// I'm not sure about this.
		if (!course.isAdmin(user)) {
			return ErrorType.PERMISSION_DENIED;
		}

		if (!course.hasTask(taskId)) {
			return ErrorType.TASK_NOT_FOUND;
		}

		if (!FileUtil.exists(srcDir)) {
			return ErrorType.FILE_OPERATION_FAILED;
		}

		return submit(course.getTask(taskId));
	}

	private ErrorType submit(Task task) {
		String destDir = task.getAnswerFilename();
		if (FileUtil.copy(srcDir, destDir) != ErrorType.SUCCESS) {
			return ErrorType.FILE_OPERATION_FAILED;
		}

		System.out.println("add answer success");

		return ErrorType.SUCCESS;
	}
}
