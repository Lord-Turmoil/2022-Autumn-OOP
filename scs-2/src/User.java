enum UserType {
	Unknown,
	Student,
	Professor,
	Assistant
}

class User implements IInfo {
	protected String id;
	protected String firstName;
	protected String lastName;
	protected String email;
	protected String password;
	protected UserType type;

	public User(String id, String firstName, String lastName, String email, String password, UserType type) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.type = type;
	}

	User(UserType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();

		buffer.append("Name: ").append(firstName).append(" ").append(lastName).append("\n");
		buffer.append("ID: ").append(id).append("\n");
		buffer.append("Type: ").append(type).append("\n");
		buffer.append("Email: ").append(email);

		return buffer.toString();
	}

	@Override
	public String getInfo() {
		return "I am a dumb user.";
	}

	public String getGreeting() {
		return "I'm a dumb user.";
	}

	public boolean hasPermission(Permission permission) {
		PermissionControl control = PermissionManager.getPermissionControl(type);

		if (control == null) {
			return false;
		}

		return control.hasPermission(permission);
	}

	public void onLogin() {}

	public void onLogout() {}

	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public UserType getType() {
		return type;
	}
}

class Student extends User {
	public Student(String id, String firstName, String lastName, String email, String password) {
		super(id, firstName, lastName, email, password, UserType.Student);
	}

	@Override
	public String getInfo() {
		StringBuffer buffer = new StringBuffer();

		buffer.append("[ID:" + id + "]");
		buffer.append(" [Name:" + lastName + " " + firstName + "]");

		// Oh, man! This is terrible! :(
		buffer.append(" [Type:" + UserType.Assistant + "]");

		buffer.append(" [Email:" + email + "]");

		return buffer.toString();
	}

	public String getStudentInfo() {
		StringBuffer buffer = new StringBuffer();

		buffer.append("[ID:" + id + "]");
		buffer.append(" [Name:" + lastName + " " + firstName + "]");
		buffer.append(" [Email:" + email + "]");

		return buffer.toString();
	}

	@Override
	public String getGreeting() {
		StringBuffer buffer = new StringBuffer();

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

		return "change into " + type + " success";
	}
}

class Professor extends User {
	public Professor(String id, String firstName, String lastName, String email, String password) {
		super(id, firstName, lastName, email, password, UserType.Professor);
	}

	@Override
	public String getInfo() {
		StringBuffer buffer = new StringBuffer();

		buffer.append("[ID:" + id + "]");
		buffer.append(" [Name:" + lastName + " " + firstName + "]");
		buffer.append(" [Type:" + type + "]");
		buffer.append(" [Email:" + email + "]");

		return buffer.toString();
	}

	@Override
	public String getGreeting() {
		StringBuffer buffer = new StringBuffer();

		buffer.append("Hello Professor ").append(lastName).append("~");

		return buffer.toString();
	}
}