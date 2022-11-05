package scs.att;

import scs.Platform;
import scs.common.ErrorType;
import scs.common.Global;
import scs.info.InfoEntry;
import scs.user.Student;
import scs.user.User;
import scs.user.UserType;
import scs.util.FileUtil;

import java.util.TreeMap;
import java.util.TreeSet;

public class Course extends Content {
	private TreeMap<String, Ware> wares = new TreeMap<>();
	private TreeMap<String, Task> tasks = new TreeMap<>();
	private TreeSet<String> students = new TreeSet<>();
	private TreeSet<String> admins = new TreeSet<>();
	private int teacherNum;
	private int assistantNum;

	public Course(String id, String name, String admin) {
		super(id, name);
		admins.add(admin);	// The very first administrator.
		teacherNum = 1;
		assistantNum = 0;
	}

	@Override
	public InfoEntry getInfo() {
		InfoEntry entry = new InfoEntry();

		entry.add("ID", id).add("Name", name);
		entry.add("TeacherNum", teacherNum);
		entry.add("AssistantNum", assistantNum);
		entry.add("StudentNum", students.size());

		return entry;
	}

	// Check if a platform.user is an administrator of this course.
	public boolean isAdmin(String id) {
		return admins.contains(id);
	}

	public boolean isAdmin(User user) {
		return isAdmin(user.getId());
	}

	public ErrorType addAdmin(String id) {
		if (admins.contains(id)) {
			return ErrorType.ADMIN_ID_DUPLICATION;
		}

		admins.add(id);
		if (Platform.getInstance().getUser(id).getType() != UserType.Professor) {
			Platform.getInstance().addAssistant(id);
			assistantNum++;
		} else {
			teacherNum++;
		}

		return ErrorType.SUCCESS;
	}

	public ErrorType removeAdmin(String id) {
		if (!admins.contains(id)) {
			return ErrorType.USER_ID_NOT_EXIST;
		}

		admins.remove(id);
		if (Platform.getInstance().getUser(id).getType() != UserType.Professor) {
			Platform.getInstance().removeAssistant(id);
			assistantNum--;
		} else {
			teacherNum--;
		}

		if (admins.size() == 0) {
			// This shouldn't happen. :(
			System.out.println("\tKarabast!");
		}

		return ErrorType.SUCCESS;
	}

	public TreeSet<String> getAdmins() {
		return admins;
	}

	public ErrorType addWare(Ware ware) {
		if (wares.containsKey(ware.getId())) {
			return ErrorType.WARE_ID_DUPLICATION;
		}

		wares.put(ware.getId(), ware);

		return ErrorType.SUCCESS;
	}

	public boolean hasWare(String id) {
		return wares.containsKey(id);
	}

	public Ware getWare(String id) {
		return wares.get(id);
	}

	public TreeMap<String, Ware> getWares() {
		return wares;
	}

	public ErrorType removeWare(String id) {
		if (!wares.containsKey(id)) {
			return ErrorType.WARE_NOT_FOUND;
		}

		wares.remove(id);

		return ErrorType.SUCCESS;
	}

	public ErrorType removeWare(Ware ware) {
		return removeWare(ware.getId());
	}

	public ErrorType addTask(Task task) {
		if (wares.containsKey(task.getId())) {
			return ErrorType.TASK_ID_DUPLICATION;
		}

		tasks.put(task.getId(), task);

		return ErrorType.SUCCESS;
	}

	public boolean hasTask(String id) {
		return tasks.containsKey(id);
	}

	public Task getTask(String id) {
		return tasks.get(id);
	}

	public TreeMap<String, Task> getTasks() {
		return tasks;
	}

	public ErrorType removeTask(String id) {
		if (!tasks.containsKey(id)) {
			return ErrorType.TASK_NOT_FOUND;
		}

		tasks.remove(id);

		return ErrorType.SUCCESS;
	}

	public ErrorType removeTask(Task task) {
		return removeTask(task.getId());
	}

	public ErrorType addStudent(String id) {
		if (students.contains(id)) {
			return ErrorType.USER_ID_DUPLICATION;
		}

		students.add(id);

		return ErrorType.SUCCESS;
	}

	public boolean isStudent(String id) {
		return students.contains(id);
	}

	public boolean isStudent(User user) {
		return isStudent(user.getId());
	}

	public Student getStudent(String id) {
		return (Student) Platform.getInstance().getUser(id);
	}

	public int getStudentNum() {
		return students.size();
	}

	public TreeSet<String> getStudents() {
		return students;
	}

	public ErrorType removeStudent(String id) {
		if (!students.contains(id)) {
			return ErrorType.USER_ID_NOT_EXIST;
		}

		students.remove(id);

		return ErrorType.SUCCESS;
	}

	@Override
	public String getDirectory() {
		return FileUtil.joint(Global.ROOT_DIR, id);
	}
}