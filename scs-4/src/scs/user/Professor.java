package scs.user;

import scs.info.InfoEntry;

public class Professor extends User {
	public Professor(String id, String firstName, String lastName, String email, String password) {
		super(id, firstName, lastName, email, password, UserType.Professor);
	}

	@Override
	public String getGreeting() {
		StringBuilder buffer = new StringBuilder();

		buffer.append("Hello Professor ").append(lastName).append("~");

		return buffer.toString();
	}

	@Override
	public InfoEntry getInfo() {
		InfoEntry entry = super.getInfo();

		entry.add("Admin", UserType.Professor.toString());

		return entry;
	}
}
