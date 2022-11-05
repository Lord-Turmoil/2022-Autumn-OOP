package top.ts.oop.lab05.file;

import java.util.Vector;

abstract class Command {
	abstract int execute(Vector<String> args);
}

class CreateDirectoryCmd extends Command {
	@Override
	public int execute(Vector<String> args) {
		if (args.size() != 1) {
			System.out.println("Args illegal.");
			return -5;
		}

		Platform platform = Platform.getInstance();
		Directory pwd = platform.getWorkingDirectory();

		String name = args.elementAt(0);
		Directory newDir = (Directory) platform.createFile(name, FileType.DIRECTORY);

		pwd.addItem(newDir);

		return 0;
	}
}

class ShowDirectoryCmd extends Command {
	@Override
	public int execute(Vector<String> args) {
		if (args.size() != 0) {
			System.out.println("Args illegal.");
			return -5;
		}

		Directory pwd = Platform.getInstance().getWorkingDirectory();

		System.out.println(pwd.getDirectory());

		return 0;
	}
}

class ShowFileCmd extends Command {
	@Override
	public int execute(Vector<String> args) {
		if (args.size() != 1) {
			System.out.println("Args illegal.");
			return -5;
		}

		Directory pwd = Platform.getInstance().getWorkingDirectory();

		return pwd.showFile(args.elementAt(0));
	}
}

class ChangeDirectoryCmd extends Command {
	@Override
	public int execute(Vector<String> args) {
		if (args.size() != 1) {
			System.out.println("Args illegal.");
			return -5;
		}

		Directory pwd = Platform.getInstance().getWorkingDirectory();
		AbstractFile dir;
		String tar = args.elementAt(0);

		if ("..".equals(tar)) {
			dir = Platform.instance.getFile(pwd.getParent());
		} else {
			dir = pwd.getFile(tar);
		}

		if (dir == null) {
			System.out.println("Directory doesn't exist!");
			return -2;
		}

		if (dir.getType() != FileType.DIRECTORY) {
			System.out.println("Not a directory!");
			return -3;
		}

		return dir.execute(new Vector<>());
	}
}

class ExecuteCommand extends Command {
	@Override
	public int execute(Vector<String> args) {
		if (args.size() < 1) {
			System.out.println("Args illegal.");
			return -5;
		}

		Directory pwd = Platform.getInstance().getWorkingDirectory();

		AbstractFile file = pwd.getFile(args.elementAt(0));
		if (file == null) {
			System.out.println("File doesn't exist!");
			return -2;
		}

		if (file.getType() == FileType.DIRECTORY) {
			System.out.println("Not a file!");
			return -3;
		}

		args.remove(0);
		return file.execute(args);
	}
}

class CreateLinkCmd extends Command {
	@Override
	public int execute(Vector<String> args) {
		if (args.size() != 2) {
			System.out.println("Args illegal.");
			return -5;
		}

		Platform platform = Platform.getInstance();
		Directory pwd = platform.getWorkingDirectory();

		String name = args.elementAt(0);
		int index = Integer.parseInt(args.elementAt(1));

		Link newLink = (Link) platform.createFile(name, FileType.LINK);
		newLink.setTarget(index);

		pwd.addItem(newLink);

		return 0;
	}
}

class CreateExecutableCmd extends Command {
	@Override
	public int execute(Vector<String> args) {
		if (args.size() != 2) {
			System.out.println("Args illegal.");
			return -5;
		}

		Platform platform = Platform.getInstance();
		Directory pwd = platform.getWorkingDirectory();

		String name = args.elementAt(0);
		String content = args.elementAt(1);

		Executable newExe = (Executable) platform.createFile(name, FileType.EXECUTABLE);
		newExe.setContent(content);

		pwd.addItem(newExe);

		return 0;
	}
}

class CreateDummyCmd extends Command {
	@Override
	public int execute(Vector<String> args) {
		if (args.size() != 3) {
			System.out.println("Args illegal.");
			return -5;
		}

		Platform platform = Platform.getInstance();
		Directory pwd = platform.getWorkingDirectory();

		String name = args.elementAt(0);
		String content = args.elementAt(1);
		String method = args.elementAt(2);

		Dummy newDum = (Dummy) platform.createFile(name, FileType.DUMMY);
		newDum.setContent(content);
		newDum.setMethod(method);

		pwd.addItem(newDum);

		return 0;
	}
}

class RemoveFileCmd extends Command {
	@Override
	public int execute(Vector<String> args) {
		if (args.size() != 1) {
			System.out.println("Args illegal.");
			return -5;
		}

		Platform platform = Platform.getInstance();
		Directory pwd = platform.getWorkingDirectory();

		String name = args.elementAt(0);
		AbstractFile file = pwd.getFile(name);
		if (file == null) {
			return 66;    // idk. :(
		}

		file.destroy();

		return 0;
	}
}

class ListCmd extends Command {
	@Override
	public int execute(Vector<String> args) {
		if (args.size() != 0) {
			System.out.println("Args illegal.");
			return -5;
		}

		Directory pwd = Platform.getInstance().getWorkingDirectory();

		pwd.listItem();

		return 0;
	}

}
class CommandFactory {
	static Command getCommand(String cmd) {
		switch (cmd) {
			case "mkdir" -> {
				return new CreateDirectoryCmd();
			}
			case "touch" -> {
				return new CreateDummyCmd();
			}
			case "ln" -> {
				return new CreateLinkCmd();
			}
			case "new" -> {
				return new CreateExecutableCmd();
			}
			case "ls" -> {
				return new ListCmd();
			}
			case "pwd" -> {
				return new ShowDirectoryCmd();
			}
			case "stat" -> {
				return new ShowFileCmd();
			}
			case "exec" -> {
				return new ExecuteCommand();
			}
			case "rm" -> {
				return new RemoveFileCmd();
			}
			case "cd" -> {
				return new ChangeDirectoryCmd();
			}
			default -> {
				return null;
			}
		}
	}
}