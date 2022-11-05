package top.ts.oop.lab05.file;

import java.util.Date;
import java.util.Vector;

/***
 * Link's content is the method to open the dummy.
 */
public class Link extends AbstractFile {
	int target;

	public Link(String name, Date date, String content, int parent, int index) {
		super(name, date, content, parent, 4, index, FileType.LINK);
	}

	@Override
	public void setContent(String content) {
	}

	public Link(String name, Date date, int parent, int index) {
		super(name, date, "", parent, 4, index, FileType.LINK);
	}

	public void setTarget(int index) {
		target = index;
	}

	@Override
	public int execute(Vector<String> args) {
		Platform platform = Platform.getInstance();
		AbstractFile file = platform.getFile(index);

		if (file == null) {
			return 1;
		}

		file.execute(args);

		return 0;
	}

	@Override
	public int destroy() {
		Platform platform = Platform.getInstance();

		((Directory)(platform.getFile(parent))).removeFile(getIndex());
		platform.removeFile(getIndex());

		return 0;
	}
}
