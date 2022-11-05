package top.ts.oop.lab05.file;

import java.util.Date;
import java.util.Vector;

public class Platform {
	static Platform instance = new Platform();

	Vector<AbstractFile> files = new Vector<>();
	Directory curDirectory;

	private Platform() {
		curDirectory = new Directory("C:", new Date(), -1, 0);
		files.add(curDirectory);
	}

	public static Platform getInstance() {
		return instance;
	}

	Directory getWorkingDirectory() {
		return curDirectory;
	}

	void setWorkingDirectory(Directory file) {
		curDirectory = file;
	}

	AbstractFile getFile(int index) {
		if (index >= files.size()) {
			return null;
		}

		if (index < 0) {
			return null;
		}

		return files.elementAt(index);
	}

	AbstractFile getFile(String dir) {
		for (AbstractFile file : files) {
			if (file == null) {
				continue;
			}
			if (file.getDirectory().equals(dir)) {
				return file;
			}
		}

		return null;
	}

	public AbstractFile createFile(String name, FileType type) {
		AbstractFile file;
		int index = files.size();

		for (int i = 0; i < files.size(); i++) {
			if (files.elementAt(i) == null) {
				index = i;
				break;
			}
		}
		if (index == files.size()) {
			files.add(null);
		}

		switch (type) {
			case DIRECTORY -> {
				file = new Directory(name, new Date(), curDirectory.getIndex(), index);
			}
			case LINK -> {
				file = new Link(name, new Date(), curDirectory.getIndex(), index);
			}
			case EXECUTABLE -> {
				file = new Executable(name, new Date(), curDirectory.getIndex(), index);
			}
			case DUMMY -> {
				file = new Dummy(name, new Date(), curDirectory.getIndex(), index);
			}
			default -> {
				file = null;
			}
		}
		files.setElementAt(file, index);

		return file;
	}

	public int removeFile(int index) {
		if (index == 0) {
			System.out.println("Cannot remove C:");
			return 3;
		}

		AbstractFile file = getFile(index);
		if (file == null) {
			return 4;
		}

		return file.destroy();
	}
}
