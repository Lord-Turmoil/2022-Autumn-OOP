package scs.att;

import scs.common.Global;
import scs.info.InfoEntry;
import scs.user.User;
import scs.util.FileUtil;

/**
 * platform.att.Attachment is the base class of platform.att.Ware and platform.att.Task, which
 * means the attachment of platform.att.Course.
 */
public abstract class Attachment extends Content {
	protected User owner;
	protected Course course;
	protected String type;
	protected String filename;

	public Attachment(String type, String id, String name, User owner, Course course) {
		super(id, name);
		this.type = type;
		this.owner = owner;
		this.course = course;
		this.filename = "";
	}

	@Override
	public abstract InfoEntry getInfo();

	public Course getCourse() {
		return course;
	}

	public User getOwner() {
		return owner;
	}

	@Override
	public String getFilename() {
		return FileUtil.joint(getDirectory(), filename);
	}

	@Override
	public String getDirectory() {
		return FileUtil.joint(course.getDirectory(), type, id);
	}
}
