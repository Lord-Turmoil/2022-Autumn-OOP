package scs.att;

import scs.common.Global;
import scs.info.InfoEntry;
import scs.user.User;
import scs.util.FileUtil;

public class Ware extends Attachment {
	public Ware(String id, String name, User owner, Course course) {
		super("wares", id, name, owner, course);
		filename = id + "_" + name;
	}

	@Override
	public InfoEntry getInfo() {
		InfoEntry entry = new InfoEntry();

		entry.add("ID", id);
		entry.add("Name", name);

		return entry;
	}

	@Override
	public String getDirectory() {
		return FileUtil.joint(course.getDirectory(), type);
	}
}