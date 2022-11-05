package top.ts.oop.lab05.file;

import java.util.Date;
import java.util.Stack;
import java.util.Vector;

enum FileType {
	DIRECTORY,
	EXECUTABLE,
	LINK,
	DUMMY
}

abstract class AbstractFile implements IInfo {
	protected String name;
	protected Date date;
	protected String content;
	protected int parent;
	protected FileType type;
	protected int size;
	protected int index;

	public AbstractFile(String name, Date date, String content, int parent, int size, int index, FileType type) {
		this.name = name;
		this.date = date;
		this.content = content;
		this.parent = parent;
		this.type = type;
		this.size = size;
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public FileType getType() {
		return type;
	}

	public int getIndex() {
		return index;
	}

	public int getParent() {
		return parent;
	}

	public void setContent(String content) {
		this.content = content;
		this.size = content.length();
	}

	@Override
	public String getInfo() {
		StringBuffer buffer = new StringBuffer();

		buffer.append("        Type: ").append(type).append("\n");
		buffer.append("        Name: ").append(name).append("\n");
		buffer.append("Date Created: ").append(date).append("\n");
		buffer.append("        Size: ").append(size).append(" byte(s)\n");
		buffer.append("          ID: ").append(index);

		return buffer.toString();
	}

	@Override
	public String getDirectory() {
		StringBuffer buffer = new StringBuffer();
		Stack<String> stack = new Stack<>();
		Platform platform = Platform.getInstance();

		stack.push(name);
		int p = parent;
		while (p != -1) {
			AbstractFile file = platform.getFile(p);
			stack.push(file.getName());
			p = file.getParent();
		}

		buffer.append(stack.pop());
		while (!stack.empty()) {
			buffer.append("\\").append(stack.pop());
		}

		return buffer.toString();
	}

	abstract public int execute(Vector<String> args);
	abstract public int destroy();
}
