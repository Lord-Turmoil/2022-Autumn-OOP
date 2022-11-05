import java.util.HashSet;
import java.util.Iterator;
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
	public String getInfo() {
		StringBuffer buffer = new StringBuffer();

		buffer.append("[ID:" + id + "]");
		buffer.append(" [Name:" + name + "]");
		buffer.append(" [TeacherNum:" + teacherNum + "]");
		buffer.append(" [AssistantNum:" + assistantNum + "]");
		buffer.append(" [StudentNum:" + students.size() + "]");

		return buffer.toString();
	}

	// Check if a user is an administrator of this course.
	public boolean isAdmin(String id) {
		return admins.contains(id);
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

	public void listAdmin() {
		Iterator<String> it = admins.iterator();
		Platform platform = Platform.getInstance();
		while (it.hasNext()) {
			String id = it.next();
			System.out.println(platform.getUser(id).getInfo());
		}
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

	public ErrorType removeWare(String id) {
		if (!wares.containsKey(id)) {
			return ErrorType.WARE_ID_NOT_EXIST;
		}

		wares.remove(id);

		return ErrorType.SUCCESS;
	}

	public void listWare() {
		for (Ware ware : wares.values()) {
			System.out.println(ware.getInfo());
		}
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

	public ErrorType removeTask(String id) {
		if (!tasks.containsKey(id)) {
			return ErrorType.TASK_ID_NOT_EXIST;
		}

		tasks.remove(id);

		return ErrorType.SUCCESS;
	}

	public void listTask() {
		for (Task task : tasks.values()) {
			System.out.println(task.getInfo());
		}
	}

	public ErrorType addStudent(String id) {
		if (students.contains(id)) {
			return ErrorType.USER_ID_DUPLICATION;
		}

		students.add(id);

		return ErrorType.SUCCESS;
	}

	public boolean hasStudent(String id) {
		return students.contains(id);
	}

	public Student getStudent(String id) {
		return (Student) Platform.getInstance().getUser(id);
	}

	public ErrorType removeStudent(String id) {
		if (!students.contains(id)) {
			return ErrorType.USER_ID_NOT_EXIST;
		}

		students.remove(id);

		return ErrorType.SUCCESS;
	}

	public void listStudent() {
		Iterator<String> it = students.iterator();
		Platform platform = Platform.getInstance();
		while (it.hasNext()) {
			String id = it.next();
			System.out.println(((Student) platform.getUser(id)).getStudentInfo());
		}
	}
}