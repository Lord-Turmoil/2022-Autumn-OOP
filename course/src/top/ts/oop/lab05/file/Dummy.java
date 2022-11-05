package top.ts.oop.lab05.file;

import java.util.Date;
import java.util.Vector;

public class Dummy extends AbstractFile {
	// Open method;
	private String method = "";

	public Dummy(String name, Date date, String content, int parent, int index) {
		super(name, date, content, parent, content.length(), index, FileType.DUMMY);
	}

	public Dummy(String name, Date date, int parent, int index) {
		super(name, date, "", parent, 0, index, FileType.DUMMY);
	}

	public void setMethod(String method) {
		this.method = method;
	}

	@Override
	public int execute(Vector<String> args) {
		if (args.size() != 1) {
			System.out.println("How?");
			return -123;
		}

		if (!args.elementAt(0).equals(method)) {
			System.out.println("Cannot open this file.");
			return 2;
		}

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
