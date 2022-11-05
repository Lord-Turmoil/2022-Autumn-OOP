/***
 * Content is the base class of Course and Attachment.
 * Content has owner, id and name.
 */
class Content implements IInfo {
	protected String id;
	protected String name;

	public Content(String id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public String getInfo() {
		return "This is a stupid content.";
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}