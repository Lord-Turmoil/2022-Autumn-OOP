package scs.info;

public class SubmissionInfoFilter implements IInfoFilter {
	@Override
	public String filer(InfoEntry entry) {
		StringBuilder buffer = new StringBuilder();

		buffer.append(entry.getEntry("ID")).append(" ");
		buffer.append(entry.getEntry("Name")).append(" ");
		buffer.append(entry.getEntry("Task_ID")).append(" ");
		buffer.append(entry.getEntry("Score"));

		return buffer.toString();
	}
}
