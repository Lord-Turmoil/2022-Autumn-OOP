package scs.info;

public class TaskInfoFilter implements IInfoFilter {
	@Override
	public String filer(InfoEntry entry) {
		StringBuilder buffer = new StringBuilder();

		buffer.append(entry.getEntry("ID")).append(" ");
		buffer.append(entry.getEntry("Name")).append(" ");
		buffer.append(entry.getEntry("SubmissionStatus")).append(" ");
		buffer.append(entry.getEntry("StartTime")).append(" ");
		buffer.append(entry.getEntry("EndTime"));

		return buffer.toString();
	}
}