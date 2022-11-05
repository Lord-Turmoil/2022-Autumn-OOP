package scs.user;

import scs.info.IInfo;
import scs.info.InfoEntry;
import scs.permission.*;

public class User implements IInfo {
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
		StringBuilder buffer = new StringBuilder();

		buffer.append("Name: ").append(firstName).append(" ").append(lastName).append("\n");
		buffer.append("ID: ").append(id).append("\n");
		buffer.append("Type: ").append(type).append("\n");
		buffer.append("Email: ").append(email);

		return buffer.toString();
	}

	@Override
	public InfoEntry getInfo() {
		InfoEntry entry = new InfoEntry();

		entry.add("ID", id);
		entry.add("Name", getName());
		entry.add("Type", type.toString());
		entry.add("Email", email);

		return entry;
	}

	public String getName() {
		return lastName + " " + firstName;
	}

	public String getGreeting() {
		return "I'm a dumb platform.user.";
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