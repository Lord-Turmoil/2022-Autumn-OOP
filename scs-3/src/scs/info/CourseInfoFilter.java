package scs.info;

public class CourseInfoFilter implements IInfoFilter {
	@Override
	public String filer(InfoEntry entry) {
		StringBuilder buffer = new StringBuilder();

		buffer.append(entry.getEntry("ID")).append(" ");
		buffer.append(entry.getEntry("Name")).append(" ");
		buffer.append(entry.getEntry("TeacherNum")).append(" ");
		buffer.append(entry.getEntry("AssistantNum")).append(" ");
		buffer.append(entry.getEntry("StudentNum"));

		return buffer.toString();
	}
}
