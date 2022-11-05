public class Ware extends Attachment {
	public Ware(String id, String name, User owner, Course course) {
		super(id, name, owner, course);
	}

	@Override
	public String getInfo() {
		StringBuffer buffer = new StringBuffer();

		buffer.append("[ID:" + id + "]");
		buffer.append(" [Name:" + name + "]");

		return buffer.toString();
	}
}