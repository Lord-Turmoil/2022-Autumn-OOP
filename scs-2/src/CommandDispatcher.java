/***
 * This will create corresponding command based on
 * the current input.
 */
class CommandDispatcher {
	public static Command dispatch(String arg) {
		if (arg == null) {
			return null;
		}

		/*
		 * Work with lambda. :?
		 */
		switch (arg) {
			case "QUIT" -> {
				return new QuitCommand();
			}
			case "register" -> {
				return new RegisterCommand();
			}
			case "login" -> {
				return new LoginCommand();
			}
			case "printInfo" -> {
				return new PrintInfoCommand();
			}
			case "logout" -> {
				return new LogoutCommand();
			}
			case "addCourse" -> {
				return new AddCourseCommand();
			}
			case "removeCourse" -> {
				return new RemoveCourseCommand();
			}
			case "listCourse" -> {
				return new ListCourseCommand();
			}
			case "selectCourse" -> {
				return new SelectCourseCommand();
			}
			case "addAdmin" -> {
				return new AddAdminCommand();
			}
			case "removeAdmin" -> {
				return new RemoveAdmin();
			}
			case "listAdmin" -> {
				return new ListAdminCommand();
			}
			case "changeRole" -> {
				return new ChangeRoleCommand();
			}
			case "addWare" -> {
				return new AddWareCommand();
			}
			case "removeWare" -> {
				return new RemoveWareCommand();
			}
			case "listWare" -> {
				return new ListWareCommand();
			}
			case "addTask" -> {
				return new AddTaskCommand();
			}
			case "removeTask" -> {
				return new RemoveTaskCommand();
			}
			case "listTask" -> {
				return new ListTaskCommand();
			}
			case "addStudent" -> {
				return new AddStudentCommand();
			}
			case "removeStudent" -> {
				return new RemoveStudentCommand();
			}
			case "listStudent" -> {
				return new ListStudentCommand();
			}
			default -> {
				return null;
			}
		}
	}
}