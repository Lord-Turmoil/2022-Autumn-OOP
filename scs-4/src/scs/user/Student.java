package scs.user;

import scs.Platform;
import scs.info.InfoEntry;

public class Student extends User {
	public Student(String id, String firstName, String lastName, String email, String password) {
		super(id, firstName, lastName, email, password, UserType.Student);
	}

	@Override
	public InfoEntry getInfo() {
		InfoEntry entry = super.getInfo();

		entry.add("Student", UserType.Student.toString());
		entry.add("Admin", UserType.Assistant.toString());

		return entry;
	}

	@Override
	public String getGreeting() {
		StringBuilder buffer = new StringBuilder();

		buffer.append("Hello ").append(firstName).append("~");

		return buffer.toString();
	}

	@Override
	public void onLogin() {
		// Assistant will log in as ordinary student.
		type = UserType.Student;
	}

	public String changeRole() {
		// Won't change if not in assistant list.
		if (!Platform.getInstance().isAssistant(id)) {
			return null;
		}

		if (type == UserType.Student) {
			type = UserType.Assistant;
		} else {
			type = UserType.Student;
		}

		Platform.getInstance().clearSelectedCourse();

		return "change into " + type + " success";
	}
}