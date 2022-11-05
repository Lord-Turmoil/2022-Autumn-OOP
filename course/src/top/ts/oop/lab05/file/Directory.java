package top.ts.oop.lab05.file;

import java.util.Date;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicBoolean;

public class Directory extends AbstractFile {
	private int dirNum;
	private int fileNum;
	private Vector<AbstractFile> files = new Vector<>();

	public Directory(String name, Date date, int parent, Integer index) {
		super(name, date, "", parent, 0, index, FileType.DIRECTORY);
	}

	@Override
	public void setContent(String content) {
	}

	@Override
	public String getInfo() {
		StringBuffer buffer = new StringBuffer();

		buffer.append(super.getInfo()).append("\n");
		buffer.append("  Folder Num: ").append(dirNum).append("\n");
		buffer.append("    File Num: ").append(fileNum);

		return buffer.toString();
	}

	public void addItem(AbstractFile file) {
		if (file.getType() == FileType.DIRECTORY) {
			dirNum++;
		} else {
			fileNum++;
		}

		files.add(file);
	}

	public Vector<AbstractFile> getItem() {
		return files;
	}

	public AbstractFile getFile(String name) {
		for (AbstractFile file : files) {
			if (file.getName().equals(name)) {
				return file;
			}
		}

		return null;
	}

	public int showFile(String name) {
		for (AbstractFile file : files) {
			if (file.getName().equals(name)) {
				System.out.println(file.getInfo());
				return 0;
			}
		}

		return -1;	// I don't care.
	}

	public void removeFile(int index) {
		for (AbstractFile file : files) {
			if (file.getIndex() == index) {
				if (file.type == FileType.DIRECTORY) {
					dirNum--;
				} else {
					fileNum--;
				}
				files.remove(file);
				break;
			}
		}
	}

	public void listItem() {
		for (AbstractFile file : files) {
			System.out.println(file.getName());
		}
	}

	@Override
	public int execute(Vector<String> args) {
		Platform.getInstance().setWorkingDirectory(this);

		return 0;
	}

	@Override
	public int destroy() {
		Platform platform = Platform.getInstance();

		for (AbstractFile file : files) {
			file.destroy();
		}

		files.clear();

		((Directory)(platform.getFile(parent))).removeFile(getIndex());
		platform.removeFile(getIndex());

		return 0;
	}
}