package scs.info;

public class AdminInfoFilter implements IInfoFilter {
	@Override
	public String filer(InfoEntry entry) {
		StringBuilder buffer = new StringBuilder();

		buffer.append(entry.getEntry("ID")).append(" ");
		buffer.append(entry.getEntry("Name")).append(" ");
		buffer.append(entry.getEntry("Admin", "Type")).append(" ");
		buffer.append(entry.getEntry("Email"));

		return buffer.toString();
	}
}
