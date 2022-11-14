package scs.att;

import scs.common.Global;
import scs.info.IInfo;
import scs.info.InfoEntry;
import scs.util.FileUtil;

/**
 * platform.att.Content is the base class of platform.att.Course and platform.att.Attachment.
 * platform.att.Content has owner, id and name.
 */
abstract class Content implements IInfo {
	protected String id;
	protected String name;

	public Content(String id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public abstract InfoEntry getInfo();

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getFilename() {
		return "";
	}

	public String getDirectory() {
		return "";
	}
}