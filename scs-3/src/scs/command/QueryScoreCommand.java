package scs.command;

import scs.Platform;
import scs.att.Course;
import scs.att.Submission;
import scs.att.Task;
import scs.common.ErrorType;
import scs.common.Global;
import scs.info.SubmissionInfoFilter;
import scs.permission.Permission;
import scs.user.User;
import scs.util.Inspector;
import scs.util.Judge;

import java.awt.dnd.DropTarget;
import java.util.Collection;
import java.util.Collections;
import java.util.TreeMap;
import java.util.Vector;

public class QueryScoreCommand implements ICommand {
	@Override
	public ErrorType handle(Vector<String> args) {
		if (args.size() > 2) {
			return ErrorType.ARGUMENTS_ILLEGAL;
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

		if (user.hasPermission(Permission.QueryScoreAdmin)) {
			return handleAdmin(args);
		} else if (user.hasPermission(Permission.QueryScore)) {
			return handleStudent(args);
		}

		return ErrorType.PERMISSION_DENIED;
	}

	private ErrorType handleAdmin(Vector<String> args) {
		Platform platform = Platform.getInstance();
		User user = platform.getActiveUser();
		Course course = platform.getActiveCourse();

		Vector<Entry> data;

		if (args.size() == 0) {
			data = DataFetcher.fetch(course);
		} else if (args.size() == 1) {
			String id = args.elementAt(0);
			if (Inspector.checkTaskId(id)) {
				if (course.hasTask(id)) {
					data = DataFetcher.fetchTask(course, id);
				} else {
					return ErrorType.TASK_NOT_FOUND;
				}
			} else {
				if (course.isStudent(id)) {
					data = DataFetcher.fetchStudent(course, id);
				} else {
					return ErrorType.STUDENT_NOT_FOUND;
				}
			}
		} else {
			String taskId = args.elementAt(0);
			String studentId = args.elementAt(1);

			if (!course.hasTask(taskId)) {
				return ErrorType.TASK_NOT_FOUND;
			}
			if (!course.isStudent(studentId)) {
				return ErrorType.STUDENT_NOT_FOUND;
			}

			data = DataFetcher.fetch(course, taskId, studentId);
		}

		show(data);

		return ErrorType.SUCCESS;
	}

	private ErrorType handleStudent(Vector<String> args) {
		if (args.size() == 2) {
			return ErrorType.PERMISSION_DENIED;
		}

		Platform platform = Platform.getInstance();
		User user = platform.getActiveUser();
		Course course = platform.getActiveCourse();

		Vector<Entry> data;

		if (args.size() == 0) {
			data = DataFetcher.fetchStudent(course, user.getId());
		} else {
			String taskId = args.elementAt(0);
			if (!course.hasTask(taskId)) {
				return ErrorType.TASK_NOT_FOUND;
			}
			data = DataFetcher.fetch(course, taskId, user.getId());
		}

		show(data);

		return ErrorType.SUCCESS;
	}

	private void show(Vector<Entry> data) {
		int resultNum = data.size();
		System.out.println("total " + data.size() + ((resultNum < 2) ? " result" : " results"));
		for (int i = 0; i < resultNum; i++) {
			Entry entry = data.elementAt(i);
			System.out.println(getPrefix(i + 1) + entry);
		}
	}

	private String getPrefix(int i) {
		return "[" + i + "] ";
	}
}

class Entry implements Comparable<Entry> {
	private Submission submission;

	@Override
	public int compareTo(Entry entry) {
		String taskIdA = submission.getTask().getId();
		double gradeA = submission.getScore();
		String idA = submission.getId();

		String taskIdB = entry.submission.getTask().getId();
		double gradeB = entry.submission.getScore();
		String idB = entry.submission.getId();

		if (taskIdA.equals(taskIdB)) {
			if (Math.abs(gradeA - gradeB) < Global.EPSILON) {
				return idA.compareTo(idB);
			} else {
				return (gradeA > gradeB) ? -1 : 1;
			}
		} else {
			return taskIdA.compareTo(taskIdB);
		}
	}

	public Entry(Submission submission) {
		this.submission = submission;
	}

	public boolean match(String taskId, String studentId) {
		return matchTaskId(taskId) && matchStudentId(studentId);
	}

	public boolean matchTaskId(String id) {
		return submission.getTask().getId().equals(id);
	}

	public boolean matchStudentId(String id) {
		return submission.getId().equals(id);
	}

	@Override
	public String toString() {
		return new SubmissionInfoFilter().filer(submission.getInfo());
	}

	public void update() {
		Task task = submission.getTask();
		submission.setScore(Judge.judge(task.getAnswerFilename(), submission.getFilename()));
	}
}

// Suppose that course is selected.
class DataFetcher {
	public static Vector<Entry> fetch(Course course, String taskId, String studentId) {
		Task task = course.getTask(taskId);
		Vector<Entry> result = new Vector<>();
		Submission submission = task.getSubmission(studentId);

		if (submission != null) {
			Entry entry = new Entry(submission);
			entry.update();
			result.add(entry);
		}

		return result;
	}

	// Fetch all.
	public static Vector<Entry> fetch(Course course) {
		Vector<Entry> result = new Vector<>();
		TreeMap<String, Task> tasks = course.getTasks();
		Vector<Submission> data;

		for (Task task : tasks.values()) {
			data = task.getSubmission();
			for (Submission submission : data) {
				Entry entry = new Entry(submission);
				entry.update();
				result.add(entry);
			}
		}

		Collections.sort(result);

		return result;
	}

	public static Vector<Entry> fetchTask(Course course, String taskId) {
		Vector<Entry> result = new Vector<>();
		Task task = course.getTask(taskId);

		if (task == null) {
			return result;
		}

		Vector<Submission> data;
		data = task.getSubmission();
		for (Submission submission : data) {
			Entry entry = new Entry(submission);
			entry.update();
			result.add(entry);
		}

		Collections.sort(result);

		return result;
	}

	public static Vector<Entry> fetchStudent(Course course, String studentId) {
		Vector<Entry> result = new Vector<>();
		TreeMap<String, Task> tasks = course.getTasks();

		for (Task task : tasks.values()) {
			Submission submission = task.getSubmission(studentId);
			if (submission != null) {
				Entry entry = new Entry(submission);
				entry.update();
				result.add(entry);
			}
		}

		Collections.sort(result);

		return result;
	}
}
