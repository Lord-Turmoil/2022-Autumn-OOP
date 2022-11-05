package scs.info;

public class StudentTaskInfoFilter implements IInfoFilter {
	@Override
	public String filer(InfoEntry entry) {
		StringBuilder buffer = new StringBuilder();

		buffer.append(entry.getEntry("ID")).append(" ");
		buffer.append(entry.getEntry("Name")).append(" ");
		buffer.append(entry.getEntry("Status")).append(" ");
		buffer.append(entry.getEntry("StartTime")).append(" ");
		buffer.append(entry.getEntry("EndTime"));

		return buffer.toString();
	}
}
