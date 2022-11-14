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

public class SubmitTaskCommand implements ICommand {
	private String srcDir;
	private String taskId;
	private String mode;

	/**
	 * Handle submitTask command.
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
		} else {
			// size == 4
			srcDir = args.elementAt(0);
			taskId = args.elementAt(1);
		}

		return submitTask();
	}


	// srcDir taskId
	private ErrorType handleNoRedirect(Vector<String> args) {
		if (args.size() != 2) {
				return ErrorType.ARGUMENTS_ILLEGAL;
		}

		srcDir = args.elementAt(0);
		taskId = args.elementAt(1);

		return submitTask();
	}

	private ErrorType submitTask() {
		Platform platform = Platform.getInstance();
		User user = platform.getActiveUser();
		if (user == null) {
			return ErrorType.NOT_LOGGED_IN;
		}
		if (!user.hasPermission(Permission.SubmitTask)) {
			return ErrorType.OPERATION_NOT_ALLOWED;
		}

		Course course = platform.getActiveCourse();
		if (course == null) {
			return ErrorType.NO_COURSE_SELECTED;
		}

		// I'm not sure about this.
		if (!course.isStudent(user)) {
			return ErrorType.OPERATION_NOT_ALLOWED;
		}

		if (!course.hasTask(taskId)) {
			return ErrorType.TASK_NOT_FOUND;
		}

		if (!FileUtil.exists(srcDir)) {
			return ErrorType.FILE_OPERATION_FAILED;
		}

		// Real things starts here.
		Submission submission = new Submission(user.getId(), course.getTask(taskId));
		if (confirm(submission) == ErrorType.SUBMIT_CANCELED) {
			return ErrorType.SUBMIT_CANCELED;
		}

		return submit(user.getId(), submission);
	}

	private ErrorType confirm(Submission submission) {
		if (FileUtil.exists(submission.getFilename())) {
			System.out.println("task already exists, do you want to overwrite it? (y/n)");
			boolean response = Host.getInstance()
					.peekCommand().elementAt(0).equalsIgnoreCase("Y");
			if (!response) {
				return ErrorType.SUBMIT_CANCELED;
			}
		}

		return ErrorType.SUCCESS;
	}

	private ErrorType submit(String id, Submission submission) {
		String destDir = submission.getFilename();
		if (FileUtil.copy(srcDir, destDir) != ErrorType.SUCCESS) {
			return ErrorType.FILE_OPERATION_FAILED;
		}

		Task task = submission.getTask();

		submission.setScore(Judge.judge(task.getAnswerFilename(), srcDir));
		task.submit(id, submission);

		System.out.println("submit success");
		System.out.println("your score is: " + submission.getScoreString());

		return ErrorType.SUCCESS;
	}
}
