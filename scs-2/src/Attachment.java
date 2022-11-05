/***
 * Attachment is the base class of Ware and Task, which
 * means the attachment of Course.
 */
class Attachment extends Content {
	protected User owner;
	protected Course course;

	public Attachment(String id, String name, User owner, Course course) {
		super(id, name);
		this.owner = owner;
		this.course = course;
	}

	@Override
	public String getInfo() {
		return "This is a stupid attachment.";
	}

	public Course getCourse() {
		return course;
	}

	public User getOwner() {
		return owner;
	}
}
