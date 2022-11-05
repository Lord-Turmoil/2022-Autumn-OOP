enum UserType {
	Unknown,
	Student,
	Professor
}

class User {
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

	public String getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public UserType getType() {
		return type;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

class Student extends User {
	public Student(String id, String firstName, String lastName, String email, String password) {
		super(id, firstName, lastName, email, password, UserType.Student);
	}

	@Override
	public String getGreeting() {
		StringBuffer buffer = new StringBuffer();

		buffer.append("Hello ").append(firstName).append("~");

		return buffer.toString();
	}
}

class Professor extends User {
	public Professor(String id, String firstName, String lastName, String email, String password) {
		super(id, firstName, lastName, email, password, UserType.Professor);
	}

	@Override
	public String getGreeting() {
		StringBuffer buffer = new StringBuffer();

		buffer.append("Hello Professor ").append(lastName).append("~");

		return buffer.toString();
	}
}