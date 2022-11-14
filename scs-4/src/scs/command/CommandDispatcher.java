package scs.command;

/**
 * This will create corresponding command based on
 * the current input.
 */
public class CommandDispatcher {
	public static ICommand dispatch(String arg) {
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
				return new RemoveAdminCommand();
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
			case "downloadFile" -> {
				return new DownloadFileCommand();
			}
			case "openFile" -> {
				return new OpenFileCommand();
			}
			case "submitTask" -> {
				return new SubmitTaskCommand();
			}
			case "addAnswer" -> {
				return new AddAnswerCommand();
			}
			case "queryScore" -> {
				return new QueryScoreCommand();
			}
			case "requestVM" -> {
				return new RequestVMCommand();
			}
			case "startVM" -> {
				return new StartVMCommand();
			}
			case "clearVM" -> {
				return new ClearVMCommand();
			}
			case "logVM" -> {
				return new LogVMCommand();
			}
			case "uploadVM" -> {
				return new UploadVMCommand();
			}
			case "downloadVM" -> {
				return new DownloadVMCommand();
			}

			default -> {
				return null;
			}
		}
	}
}