import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

class FileUtil {
	static Vector<String> list = new Vector<>();
	static Vector<String> buffer = new Vector<>();

	public static void write(String string) {
		buffer.add(string);
	}

	public static void flush() {
		flush(buffer, "");
	}

	public static void flush(Vector<String> vector, String suffix) {
		Date date = new Date();
		String filename = "data/" + date.getTime() + suffix + ".txt";

		System.out.println(filename);

		if (!exists(filename)) {
			try {
				mkdir(filename);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(filename, false));
			for (String line : vector) {
				out.write(line);
				out.newLine();
			}
			out.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		vector.clear();
	}

	public static void showDirectoryOrFile(String dir) {
		File file = new File(dir);
		if (file.isFile() && file.exists()) {
			showFile(dir);
		} else if (file.isDirectory() && file.exists()) {
			showDirectory(dir);
		}

		flush(list, "_dir");
	}

	private static void showFile(String filename) {
		File file = new File(filename);

		if (file.isFile() && file.exists()) {
			list.add(file.getAbsolutePath());
		}
	}

	private static void showDirectory(String dir) {
		File directory = new File(dir);

		if (directory.isDirectory() && directory.exists()) {
			File[] files = directory.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isFile()) {
					showFile(files[i].getAbsolutePath());
				} else {
					showDirectory(files[i].getAbsolutePath());
				}
			}
		}
	}

	private static boolean isValid(String dir) {
		try {
			Paths.get(dir);
		} catch (InvalidPathException e) {
			return false;
		}

		return true;
	}

	private static boolean exists(String dir) {
		if (!isValid(dir)) {
			return false;
		}

		return Files.exists(Paths.get(dir), LinkOption.NOFOLLOW_LINKS);
	}

	public static void mkdir(String filename) throws IOException {
		Path dir = Paths.get(filename).toAbsolutePath();
		File file = new File(dir.toString());
		File parent = file.getParentFile();

		if (!parent.exists()) {
			parent.mkdirs();
		}

		file.createNewFile();
	}
}
