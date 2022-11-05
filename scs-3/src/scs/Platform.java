package scs;

import scs.att.*;
import scs.common.ErrorType;
import scs.user.User;

import java.util.HashMap;
import java.util.TreeMap;

public class Platform {
	private static Platform instance = null;

	private HashMap<String, User> users = new HashMap<>();
	private TreeMap<String, Course> courses = new TreeMap<>();

	/*
	 * This record all assistants. The first value is the assistant's
	 * id, the second one is how many courses he is responsible for.
	 */
	private HashMap<String, Integer> assistants = new HashMap<>();

	private User activeUser = null;
	private Course activeCourse = null;

	private Platform() {}

	public static Platform getInstance() {
		if (instance == null)
			instance = new Platform();
		return instance;
	}

	public boolean hasUser(String id) {
		return users.containsKey(id);
	}

	public User getActiveUser() {
		return activeUser;
	}

	public User getUser(String id) {
		return users.get(id);
	}

	/*
	 * Register and log related methods. All premises MUST be checked
	 * before.
	 */
	public ErrorType register(User user) {
		if (user == null) {
			return ErrorType.OTHERS;
		}

		if (hasUser(user.getId())) {
			return ErrorType.USER_ID_DUPLICATION;
		}

		users.put(user.getId(), user);

		return ErrorType.SUCCESS;
	}

	public ErrorType removeUser(String id) {
		if (!hasUser(id)) {
			return ErrorType.USER_ID_NOT_EXIST;
		}

		users.remove(id);

		return ErrorType.SUCCESS;
	}

	public boolean hasCourse(String id) {
		return courses.containsKey(id);
	}

	public Course getActiveCourse() {
		return activeCourse;
	}

	public Course getCourse(String id) {
		return courses.get(id);
	}

	public ErrorType addCourse(Course course) {
		if (course == null) {
			return ErrorType.OTHERS;
		}

		if (hasCourse(course.getId())) {
			return ErrorType.COURSE_ID_DUPLICATION;
		}

		courses.put(course.getId(), course);

		return ErrorType.SUCCESS;
	}

	public ErrorType removeCourse(String id) {
		if (!hasCourse(id)) {
			return ErrorType.COURSE_ID_NOT_EXIST;
		}

		courses.remove(id);

		return ErrorType.SUCCESS;
	}

	public final TreeMap<String, Course> getCourses() {
		return courses;
	}

	public boolean isAssistant(String id) {
		return assistants.containsKey(id);
	}

	public void addAssistant(String id) {
		if (!assistants.containsKey(id)) {
			assistants.put(id, 1);
		} else {
			Integer n = assistants.get(id);
			assistants.replace(id, n, n + 1);
		}
	}

	public void removeAssistant(String id) {
		if (!assistants.containsKey(id)) {
			System.out.println("Karabast!");
			return;
		}

		Integer n = assistants.get(id);
		if (n == 1) {
			assistants.remove(id);
		} else {
			assistants.replace(id, n, n - 1);
		}
	}

	public ErrorType login(String id) {
		if (activeUser != null) {
			return ErrorType.ALREADY_LOGGED_IN;
		}

		activeUser = getUser(id);
		activeUser.onLogin();

		return ErrorType.SUCCESS;
	}

	public ErrorType logout() {
		if (activeUser == null) {
			return ErrorType.NOT_LOGGED_IN;
		}

		activeUser.onLogout();
		activeUser = null;

		activeCourse = null;

		return ErrorType.SUCCESS;
	}

	public ErrorType selectCourse(String id) {
		activeCourse = getCourse(id);

		return ErrorType.SUCCESS;
	}

	public void clearSelectedCourse() {
		activeCourse = null;
	}
}