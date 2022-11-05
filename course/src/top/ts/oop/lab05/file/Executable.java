package top.ts.oop.lab05.file;

import java.util.Date;
import java.util.Vector;

public class Executable extends AbstractFile {
	public Executable(String name, Date date, String content, int parent, int index) {
		super(name, date, content, parent, content.length(), index, FileType.EXECUTABLE);
	}

	public Executable(String name, Date date, int parent, int index) {
		super(name, date, "", parent, 0, index, FileType.EXECUTABLE);
	}

	@Override
	public int execute(Vector<String> args) {
		System.out.println(content);
		if (args.size() != 0) {
			System.out.println("Arguments:");
			for (String str : args) {
				System.out.println("\t" + str);
			}
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
