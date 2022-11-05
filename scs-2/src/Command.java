import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.PropertyPermission;
import java.util.Vector;

/***
 * This takes care of each specific command.
 * Command will return error info but won't show it. All errors
 * will be presented by Command Host.
 */
class Command {
	protected final String cmd;

	Command(String cmd) {
		this.cmd = cmd;
	}

	public String getCommand() {
		return cmd;
	}

	/***
	 * Here, command itself is excluded from args.
	 * @param args User's arguments.
	 * @return Handle result.
	 */
	public ErrorType handle(Vector<String> args) {
		return ErrorType.SUCCESS;
	}
}

class QuitCommand extends Command {
	QuitCommand() {
		super("QUIT");
	}

	/***
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

		return ErrorType.SHUTDOWN;
	}
}

class RegisterCommand extends Command {
	RegisterCommand() {
		super("register");
	}

	/***
	 * Handle register command.
	 * @param args id, lastName, firstName, email, password, confirm password (6)
	 * @return Handle result.
	 */
	@Override
	public ErrorType handle(Vector<String> args) {
		if (args.size() != 6) {
			return ErrorType.ARGUMENTS_ILLEGAL;
		}

		Platform platform = Platform.getInstance();

		// Already logged in?
		if (platform.getActiveUser() != null) {
			return ErrorType.ALREADY_LOGGED_IN;
		}

		// Check ID.
		String id = args.elementAt(0);
		UserType type = Inspector.checkUserId(id);
		if (type == UserType.Unknown) {
			return ErrorType.USER_ID_ILLEGAL;
		}
		if (platform.hasUser(id)) {
			return ErrorType.USER_ID_DUPLICATION;
		}

		// Check first and last name.
		String firstName = args.elementAt(1);	// last name
		if (!Inspector.checkName(firstName)) {
			return ErrorType.USER_NAME_ILLEGAL;
		}
		String lastName = args.elementAt(2);	// first name
		if (!Inspector.checkName(lastName)) {
			return ErrorType.USER_NAME_ILLEGAL;
		}

		// Check email.
		String email = args.elementAt(3);
		if (!Inspector.checkEmail(email)) {
			return ErrorType.EMAIL_ADDRESS_ILLEGAL;
		}

		// Check password.
		String password = args.elementAt(4);
		if (!Inspector.checkPassword(password)) {
			return ErrorType.PASSWORD_ILLEGAL;
		}
		if (!password.equals(args.elementAt(5))) {
			return ErrorType.PASSWORDS_INCONSISTENT;
		}

		// All good, create new user.
		User user;
		if (type == UserType.Professor) {
			user = new Professor(id, firstName, lastName, email, password);
		} else if (type == UserType.Student) {
			user = new Student(id, firstName, lastName, email, password);
		}
		else {
			// In fact, we won't reach here.
			return ErrorType.USER_ID_ILLEGAL;
		}
		platform.register(user);

		System.out.println("register success");

		return ErrorType.SUCCESS;
	}
}

class LoginCommand extends Command {
	LoginCommand() {
		super("login");
	}

	/***
	 * Handle login command.
	 * @param args id, password
	 * @return Handle result.
	 */
	@Override
	public ErrorType handle(Vector<String> args) {
		if (args.size() != 2) {
			return ErrorType.ARGUMENTS_ILLEGAL;
		}

		Platform platform = Platform.getInstance();
		if (platform.getActiveUser() != null) {
			return ErrorType.ALREADY_LOGGED_IN;
		}

		// Check id.
		String id = args.elementAt(0);
		if (Inspector.checkUserId(id) == UserType.Unknown) {
			return ErrorType.USER_ID_ILLEGAL;
		}

		User user = platform.getUser(id);
		if (user == null) {
			return ErrorType.USER_ID_NOT_EXIST;
		}

		// Check password.
		String password = args.elementAt(1);
		if (!user.getPassword().equals(password)) {
			return ErrorType.WRONG_PASSWORD;
		}

		// All good, here we go.
		platform.login(user.id);
		System.out.println(user.getGreeting());

		return ErrorType.SUCCESS;
	}
}

class PrintInfoCommand extends Command {
	PrintInfoCommand() {
		super("printInfo");
	}

	/***
	 * Handle printInfo command.
	 * @param args [id]
	 * @return Handle result.
	 */
	@Override
	public ErrorType handle(Vector<String> args) {
		if ((args.size() != 0) && (args.size() != 1)) {
			return ErrorType.ARGUMENTS_ILLEGAL;
		}

		Platform platform = Platform.getInstance();
		User activeUser = platform.getActiveUser();

		if (activeUser == null) {
			return ErrorType.LOGIN_FIRST;
		}

		if (args.size() == 0) {
			// Print user himself.
			System.out.println(activeUser);
		} else {
			if (!activeUser.hasPermission(Permission.PrintInfo)) {
				return ErrorType.PERMISSION_DENIED;
			}

			String id = args.elementAt(0);
			if (Inspector.checkUserId(id) == UserType.Unknown) {
				return ErrorType.USER_ID_ILLEGAL;
			}
			if (!platform.hasUser(id)) {
				return ErrorType.USER_ID_NOT_EXIST;
			}
			System.out.println(platform.getUser(id));
		}

		return ErrorType.SUCCESS;
	}
}

class LogoutCommand extends Command {
	LogoutCommand() {
		super("logout");
	}

	/***
	 * Logout command.
	 * @param args none
	 * @return Handle result.
	 */
	@Override
	public ErrorType handle(Vector<String> args) {
		if (args.size() != 0) {
			return ErrorType.ARGUMENTS_ILLEGAL;
		}

		if (Platform.getInstance().getActiveUser() == null) {
			return ErrorType.NOT_LOGGED_IN;
		}

		// All good, go.
		Platform.getInstance().logout();
		System.out.println("Bye~");

		return ErrorType.SUCCESS;
	}
}

/*
 * MySCS-2
 */

class AddCourseCommand extends Command {
	public AddCourseCommand() {
		super("addCourse");
	}

	/***
	 * Handle addCourse command.
	 * @param args id, name
	 * @return Handle result.
	 */
	@Override
	public ErrorType handle(Vector<String> args) {
		if (args.size() != 2) {
			return ErrorType.ARGUMENTS_ILLEGAL;
		}

		Platform platform = Platform.getInstance();
		User user = platform.getActiveUser();
		if (user == null) {
			return ErrorType.NOT_LOGGED_IN;
		}

		if (!user.hasPermission(Permission.AddCourse)) {
			return ErrorType.PERMISSION_DENIED;
		}

		String id = args.elementAt(0);
		if (!Inspector.checkCourseId(id)) {
			return ErrorType.COURSE_ID_ILLEGAL;
		}
		if (platform.hasCourse(id)) {
			return ErrorType.COURSE_ID_DUPLICATION;
		}

		String name = args.elementAt(1);
		if (!Inspector.checkCourseName(name)) {
			return ErrorType.COURSE_NAME_ILLEGAL;
		}

		platform.addCourse(new Course(id, name, user.getId()));

		System.out.println("add course success");

		return ErrorType.SUCCESS;
	}
}

class RemoveCourseCommand extends Command {
	public RemoveCourseCommand() {
		super("removeCourse");
	}

	/***
	 * Handle removeCourse command.
	 * @param args id
	 * @return Handle result.
	 */
	@Override
	public ErrorType handle(Vector<String> args) {
		if (args.size() != 1) {
			return ErrorType.ARGUMENTS_ILLEGAL;
		}

		Platform platform = Platform.getInstance();
		User user = platform.getActiveUser();
		if (user == null) {
			return ErrorType.NOT_LOGGED_IN;
		}

		if (!user.hasPermission(Permission.RemoveCourse)) {
			return ErrorType.PERMISSION_DENIED;
		}

		String id = args.elementAt(0);
		if (!Inspector.checkCourseId(id)) {
			return ErrorType.COURSE_ID_ILLEGAL;
		}

		Course course = platform.getCourse(id);
		if (course == null) {
			return ErrorType.COURSE_ID_NOT_EXIST;
		}
		if (!course.isAdmin(user.getId())) {
			return ErrorType.COURSE_ID_NOT_EXIST;
		}

		platform.removeCourse(course.getId());

		System.out.println("remove course success");

		return ErrorType.SUCCESS;
	}
}
class ListCourseCommand extends Command {
	public ListCourseCommand() {
		super("listCourse");
	}

	/***
	 * Handle listCourse command.
	 * @param args none
	 * @return Handle result.
	 */
	@Override
	public ErrorType handle(Vector<String> args) {
		if (args.size() != 0) {
			return ErrorType.ARGUMENTS_ILLEGAL;
		}

		Platform platform = Platform.getInstance();
		User user = platform.getActiveUser();
		if (user == null) {
			return ErrorType.NOT_LOGGED_IN;
		}

		if (!user.hasPermission(Permission.ListCourse)) {
			return ErrorType.PERMISSION_DENIED;
		}

		if (!platform.listCourse(user.getId())) {
			System.out.println("course not exist");
		}

		return ErrorType.SUCCESS;
	}
}

class SelectCourseCommand extends Command {
	public SelectCourseCommand() {
		super("selectCourse");
	}

	/***
	 * Handle selectCourse command.
	 * @param args id
	 * @return Handle result.
	 */
	@Override
	public ErrorType handle(Vector<String> args) {
		if (args.size() != 1) {
			return ErrorType.ARGUMENTS_ILLEGAL;
		}

		Platform platform = Platform.getInstance();
		User user = platform.getActiveUser();
		if (user == null) {
			return ErrorType.NOT_LOGGED_IN;
		}

		if (!user.hasPermission(Permission.SelectCourse)) {
			return ErrorType.PERMISSION_DENIED;
		}

		String id = args.elementAt(0);
		if (!Inspector.checkCourseId(id)) {
			return ErrorType.COURSE_ID_ILLEGAL;
		}

		Course course = platform.getCourse(id);
		if (course == null) {
			return ErrorType.COURSE_ID_NOT_EXIST;
		}
		if (!course.isAdmin(user.getId())) {
			return ErrorType.COURSE_ID_NOT_EXIST;
		}

		platform.selectCourse(id);

		System.out.println("select course success");

		return ErrorType.SUCCESS;
	}
}

class AddAdminCommand extends Command {
	public AddAdminCommand() {
		super("addAdmin");
	}

	/***
	 * Handle addAdmin command.
	 * @param args id, id, id, ...
	 * @return Handle result.
	 */
	@Override
	public ErrorType handle(Vector<String> args) {
		if (args.size() == 0) {
			return ErrorType.ARGUMENTS_ILLEGAL;
		}

		Platform platform = Platform.getInstance();
		User user = platform.getActiveUser();
		if (user == null) {
			return ErrorType.NOT_LOGGED_IN;
		}
		if (!user.hasPermission(Permission.AddAdmin)) {
			return ErrorType.PERMISSION_DENIED;
		}

		Course course = platform.getActiveCourse();
		if (course == null) {
			return ErrorType.NO_COURSE_SELECTED;
		}

		// Check all arguments first.
		for (String id : args) {
			if (Inspector.checkUserId(id) == UserType.Unknown) {
				return ErrorType.USER_ID_ILLEGAL;
			} else if (!platform.hasUser(id)) {
				return ErrorType.USER_ID_NOT_EXIST;
			}
		}

		// All good, add them all.
		for (String id : args) {
			course.addAdmin(id);
		}

		System.out.println("add admin success");

		return ErrorType.SUCCESS;
	}
}

class RemoveAdmin extends Command {
	public RemoveAdmin() {
		super("removeAdmin");
	}

	/***
	 * Handle removeAdmin command.
	 * @param args id
	 * @return Handle result.
	 */
	@Override
	public ErrorType handle(Vector<String> args) {
		if (args.size() != 1) {
			return ErrorType.ARGUMENTS_ILLEGAL;
		}

		Platform platform = Platform.getInstance();
		User user = platform.getActiveUser();
		if (user == null) {
			return ErrorType.NOT_LOGGED_IN;
		}
		if (!user.hasPermission(Permission.RemoveAdmin)) {
			return ErrorType.PERMISSION_DENIED;
		}

		Course course = platform.getActiveCourse();
		if (course == null) {
			return ErrorType.NO_COURSE_SELECTED;
		}

		String id = args.elementAt(0);
		if (Inspector.checkUserId(id) == UserType.Unknown) {
			return ErrorType.USER_ID_ILLEGAL;
		}
		if (!platform.hasUser(id)) {
			return ErrorType.USER_ID_NOT_EXIST;
		}
		if (!course.isAdmin(id)) {
			return ErrorType.USER_ID_NOT_EXIST;
		}

		course.removeAdmin(id);
		System.out.println("remove admin success");

		return ErrorType.SUCCESS;
	}
}

class ListAdminCommand extends Command {
	public ListAdminCommand() {
		super("ListAdmin");
	}

	/***
	 * Handle listAdmin command.
	 * @param args none
	 * @return Return handle result.
	 */
	@Override
	public ErrorType handle(Vector<String> args) {
		if (args.size() != 0) {
			return ErrorType.ARGUMENTS_ILLEGAL;
		}

		Platform platform = Platform.getInstance();
		User user = platform.getActiveUser();
		if (user == null) {
			return ErrorType.NOT_LOGGED_IN;
		}
		if (!user.hasPermission(Permission.ListAdmin)) {
			return ErrorType.PERMISSION_DENIED;
		}

		Course course = platform.getActiveCourse();
		if (course == null) {
			return ErrorType.NO_COURSE_SELECTED;
		}

		course.listAdmin();

		return ErrorType.SUCCESS;
	}
}

class ChangeRoleCommand extends Command {
	public ChangeRoleCommand() {
		super("changeRole");
	}

	/***
	 * Handle changeRole command.
	 * @param args none
	 * @return Handle result.
	 */
	@Override
	public ErrorType handle(Vector<String> args) {
		if (args.size() != 0) {
			return ErrorType.ARGUMENTS_ILLEGAL;
		}

		Platform platform = Platform.getInstance();
		User user = platform.getActiveUser();
		if (user == null) {
			return ErrorType.NOT_LOGGED_IN;
		}

		if (!user.hasPermission(Permission.ChangeRole)) {
			return ErrorType.PERMISSION_DENIED;
		}

		String message = ((Student) user).changeRole();
		if (message == null) {
			return ErrorType.PERMISSION_DENIED;
		}

		System.out.println(message);

		return ErrorType.SUCCESS;
	}
}
class AddWareCommand extends Command {
	public AddWareCommand() {
		super("addWare");
	}

	/***
	 * Handle addWare command
	 * @param args id, name
	 * @return Handle result.
	 */
	@Override
	public ErrorType handle(Vector<String> args) {
		if (args.size() != 2) {
			return ErrorType.ARGUMENTS_ILLEGAL;
		}

		Platform platform = Platform.getInstance();
		User user = platform.getActiveUser();
		if (user == null) {
			return ErrorType.NOT_LOGGED_IN;
		}
		if (!user.hasPermission(Permission.AddWare)) {
			return ErrorType.PERMISSION_DENIED;
		}

		Course course = platform.getActiveCourse();
		if (course == null) {
			return ErrorType.NO_COURSE_SELECTED;
		}

		String id = args.elementAt(0);
		if (!Inspector.checkWareId(id, course.getId())) {
			return ErrorType.WARE_ID_ILLEGAL;
		}
		if (course.hasWare(id)) {
			return ErrorType.WARE_ID_DUPLICATION;
		}

		String name = args.elementAt(1);
		if (!Inspector.checkWareName(name)) {
			return ErrorType.WARE_NAME_ILLEGAL;
		}

		course.addWare(new Ware(id, name, user, course));

		System.out.println("add ware success");

		return ErrorType.SUCCESS;
	}
}

class RemoveWareCommand extends Command {
	public RemoveWareCommand() {
		super("removeWare");
	}

	/***
	 * Handle removeWare command.
	 * @param args id
	 * @return Handle result.
	 */
	@Override
	public ErrorType handle(Vector<String> args) {
		if (args.size() != 1) {
			return ErrorType.ARGUMENTS_ILLEGAL;
		}

		Platform platform = Platform.getInstance();
		User user = platform.getActiveUser();
		if (user == null) {
			return ErrorType.NOT_LOGGED_IN;
		}
		if (!user.hasPermission(Permission.RemoveWare)) {
			return ErrorType.PERMISSION_DENIED;
		}

		Course course = platform.getActiveCourse();
		if (course == null) {
			return ErrorType.NO_COURSE_SELECTED;
		}

		String id = args.elementAt(0);
		if (!Inspector.checkWareId(id, course.getId())) {
			return ErrorType.WARE_ID_ILLEGAL;
		}
		if (!course.hasWare(id)) {
			return ErrorType.WARE_ID_NOT_EXIST;
		}

		Ware ware = course.getWare(id);
		if (!ware.getCourse().isAdmin(user.getId())) {
			return ErrorType.WARE_ID_NOT_EXIST;
		}

		course.removeWare(id);

		System.out.println("remove ware success");

		return ErrorType.SUCCESS;
	}
}

class ListWareCommand extends Command {
	public ListWareCommand() {
		super("listWare");
	}

	/***
	 * Handle listWare command.
	 * @param args none
	 * @return Handle result.
	 */
	@Override
	public ErrorType handle(Vector<String> args) {
		if (args.size() != 0) {
			return ErrorType.ARGUMENTS_ILLEGAL;
		}

		Platform platform = Platform.getInstance();
		User user = platform.getActiveUser();
		if (user == null) {
			return ErrorType.NOT_LOGGED_IN;
		}
		if (!user.hasPermission(Permission.ListWare)) {
			return ErrorType.PERMISSION_DENIED;
		}

		Course course = platform.getActiveCourse();
		if (course == null) {
			return ErrorType.NO_COURSE_SELECTED;
		}

		course.listWare();

		return ErrorType.SUCCESS;
	}
}

class AddTaskCommand extends Command {
	public AddTaskCommand() {
		super("addTask");
	}

	/***
	 * Handle addTask command.
	 * @param args id, name, startTime, endTime
	 * @return Handle result.
	 */
	@Override
	public ErrorType handle(Vector<String> args) {
		if (args.size() != 4) {
			return ErrorType.ARGUMENTS_ILLEGAL;
		}

		Platform platform = Platform.getInstance();
		User user = platform.getActiveUser();
		if (user == null) {
			return ErrorType.NOT_LOGGED_IN;
		}
		if (!user.hasPermission(Permission.AddTask)) {
			return ErrorType.PERMISSION_DENIED;
		}

		Course course = platform.getActiveCourse();
		if (course == null) {
			return ErrorType.NO_COURSE_SELECTED;
		}

		String id = args.elementAt(0);
		if (!Inspector.checkTaskId(id, course.getId())) {
			return ErrorType.TASK_ID_ILLEGAL;
		}
		if (course.hasTask(id)) {
			return ErrorType.TASK_ID_DUPLICATION;
		}

		String name = args.elementAt(1);
		if (!Inspector.checkTaskName(name)) {
			return ErrorType.TASK_NAME_ILLEGAL;
		}

		String startTimeStr = args.elementAt(2);
		Date startTime = Inspector.checkDate(startTimeStr);
		if (startTime == null) {
			return ErrorType.TASK_TIME_ILLEGAL;
		}
		String endTimeStr = args.elementAt(3);
		Date endTime = Inspector.checkDate(endTimeStr);
		if (endTime == null) {
			return ErrorType.TASK_TIME_ILLEGAL;
		}
		if (!startTime.before(endTime)) {
			return ErrorType.TASK_TIME_ILLEGAL;
		}

		course.addTask(new Task(id, name, user, course, startTime, endTime));

		System.out.println("add task success");

		return ErrorType.SUCCESS;
	}
}

class RemoveTaskCommand extends Command {
	public RemoveTaskCommand() {
		super("removeTask");
	}

	/***
	 * Handle removeTask command.
	 * @param args id
	 * @return Handle result.
	 */
	@Override
	public ErrorType handle(Vector<String> args) {
		if (args.size() != 1) {
			return ErrorType.ARGUMENTS_ILLEGAL;
		}

		Platform platform = Platform.getInstance();
		User user = platform.getActiveUser();
		if (user == null) {
			return ErrorType.NOT_LOGGED_IN;
		}
		if (!user.hasPermission(Permission.RemoveTask)) {
			return ErrorType.PERMISSION_DENIED;
		}

		Course course = platform.getActiveCourse();
		if (course == null) {
			return ErrorType.NO_COURSE_SELECTED;
		}

		String id = args.elementAt(0);
		if (!Inspector.checkTaskId(id, course.getId())) {
			return ErrorType.TASK_ID_ILLEGAL;
		}
		if (!course.hasTask(id)) {
			return ErrorType.TASK_ID_NOT_EXIST;
		}

		Task task = course.getTask(id);
		if (!task.getCourse().isAdmin(user.getId())) {
			return ErrorType.TASK_ID_NOT_EXIST;
		}

		course.removeTask(id);

		System.out.println("remove task success");

		return ErrorType.SUCCESS;
	}
}

class ListTaskCommand extends Command {
	public ListTaskCommand() {
		super("listTask");
	}

	/***
	 * Handle listTask command.
	 * @param args none
	 * @return Handle result.
	 */
	@Override
	public ErrorType handle(Vector<String> args) {
		if (args.size() != 0) {
			return ErrorType.ARGUMENTS_ILLEGAL;
		}

		Platform platform = Platform.getInstance();
		User user = platform.getActiveUser();
		if (user == null) {
			return ErrorType.NOT_LOGGED_IN;
		}
		if (!user.hasPermission(Permission.ListTask)) {
			return ErrorType.PERMISSION_DENIED;
		}

		Course course = platform.getActiveCourse();
		if (course == null) {
			return ErrorType.NO_COURSE_SELECTED;
		}

		course.listTask();

		return ErrorType.SUCCESS;
	}
}

class AddStudentCommand extends Command {
	public AddStudentCommand() {
		super("addStudent");
	}

	/***
	 * Handle addStudent command.
	 * @param args id, id, ...
	 * @return Handle result.
	 */
	public ErrorType handle(Vector<String> args) {
		if (args.size() == 0) {
			return ErrorType.ARGUMENTS_ILLEGAL;
		}

		Platform platform = Platform.getInstance();
		User user = platform.getActiveUser();
		if (user == null) {
			return ErrorType.NOT_LOGGED_IN;
		}
		if (!user.hasPermission(Permission.AddStudent)) {
			return ErrorType.PERMISSION_DENIED;
		}

		Course course = platform.getActiveCourse();
		if (course == null) {
			return ErrorType.NO_COURSE_SELECTED;
		}

		// Check all arguments first.
		for (String id : args) {
			UserType type = Inspector.checkUserId(id);
			if (type == UserType.Unknown) {
				return ErrorType.USER_ID_ILLEGAL;
			}
			if (!platform.hasUser(id)) {
				return ErrorType.USER_ID_NOT_EXIST;
			}
			if (type == UserType.Professor) {
				/*
				 * This is a little tricky, since this is not a
				 * standard error. So it is resolved internal. :(
				 */
				System.out.println("I'm professor rather than student!");
				return ErrorType.SUCCESS;
			}
		}

		// All done, add all.
		for (String id : args) {
			course.addStudent(id);
		}

		System.out.println("add student success");

		return ErrorType.SUCCESS;
	}
}

class RemoveStudentCommand extends Command {
	public RemoveStudentCommand() {
		super("removeStudent");
	}

	/***
	 * Handle removeStudent command.
	 * @param args id
	 * @return Handle result.
	 */
	@Override
	public ErrorType handle(Vector<String> args) {
		if (args.size() != 1) {
			return ErrorType.ARGUMENTS_ILLEGAL;
		}

		Platform platform = Platform.getInstance();
		User user = platform.getActiveUser();
		if (user == null) {
			return ErrorType.NOT_LOGGED_IN;
		}
		if (!user.hasPermission(Permission.RemoveStudent)) {
			return ErrorType.PERMISSION_DENIED;
		}

		Course course = platform.getActiveCourse();
		if (course == null) {
			return ErrorType.NO_COURSE_SELECTED;
		}

		String id = args.elementAt(0);
		if (Inspector.checkUserId(id) == UserType.Unknown) {
			return ErrorType.USER_ID_ILLEGAL;
		}
		if (!platform.hasUser(id)) {
			return ErrorType.USER_ID_NOT_EXIST;
		}
		if (!course.hasStudent(id)) {
			return ErrorType.USER_ID_NOT_EXIST;
		}

		course.removeStudent(id);

		System.out.println("remove student success");

		return ErrorType.SUCCESS;
	}
}

class ListStudentCommand extends Command {
	public ListStudentCommand() {
		super("listStudent");
	}

	/***
	 * Handle listStudent command.
	 * @param args none
	 * @return Handle result.
	 */
	public ErrorType handle(Vector<String> args) {
		if (args.size() != 0) {
			return ErrorType.ARGUMENTS_ILLEGAL;
		}

		Platform platform = Platform.getInstance();
		User user = platform.getActiveUser();
		if (user == null) {
			return ErrorType.NOT_LOGGED_IN;
		}
		if (!user.hasPermission(Permission.ListStudent)) {
			return ErrorType.PERMISSION_DENIED;
		}

		Course course = platform.getActiveCourse();
		if (course == null) {
			return ErrorType.NO_COURSE_SELECTED;
		}

		course.listStudent();

		return ErrorType.SUCCESS;
	}
}