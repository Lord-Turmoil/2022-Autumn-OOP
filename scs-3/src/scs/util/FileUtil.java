package scs.util;

import scs.common.ErrorType;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class FileUtil {
	public static String joint(String... str) {
		String dir = str[0];

		for (int i = 1; i < str.length; i++) {
			String s = str[i];
			if (s.startsWith("/")) {
				s = s.substring(1, s.length());
			}
			if (s.endsWith("/")) {
				s = s.substring(0, s.length() - 1);
			}

			dir = dir + "/" + s;
		}

		return dir;
	}

	public static boolean isDirectory(String dir) {
		if (!isValid(dir)) {
			return false;
		}

		return Files.isDirectory(Paths.get(dir), LinkOption.NOFOLLOW_LINKS);
	}

	public static boolean isFile(String dir) {
		if (!isValid(dir)) {
			return false;
		}

		return Files.isRegularFile(Paths.get(dir), LinkOption.NOFOLLOW_LINKS);
	}

	public static boolean isValid(String dir) {
		try {
			Paths.get(dir);
		} catch (InvalidPathException e) {
			return false;
		}

		return true;
	}

	public static boolean exists(String dir) {
		if (!isValid(dir)) {
			return false;
		}

		return Files.exists(Paths.get(dir), LinkOption.NOFOLLOW_LINKS);
	}

	public static ErrorType delete(String dir) {
		if (!isValid(dir)) {
			return ErrorType.INVALID_DIRECTORY;
		}

		// System.out.println("Deleting " + dir);

		deleteDirectoryOrFile(dir);

		return ErrorType.SUCCESS;
	}

	private static void deleteDirectoryOrFile(String dir) {
		File file = new File(dir);
		if (file.isFile() && file.exists()) {
			deleteFile(dir);
		} else if (file.isDirectory() && file.exists()) {
			deleteDirectory(dir);
		}
	}
	private static void deleteFile(String filename) {
		File file = new File(filename);
		//路径是个文件且不为空时删除文件
		if (file.isFile() && file.exists()) {
			file.delete();
		}
	}

	private static void deleteDirectory(String dir) {
		File directory = new File(dir);

		if (directory.isDirectory() && directory.exists()) {
			File[] files = directory.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isFile()) {
					deleteFile(files[i].getAbsolutePath());
				} else {
					deleteDirectory(files[i].getAbsolutePath());
				}
			}
			directory.delete();
		}
	}

	public static ErrorType copy(String src, String dest) {
		if (!isFile(src)) {
			return ErrorType.INVALID_FILENAME;
		}

		// System.out.println("Copying " + src + " to " + dest);

		try {
			mkdir(dest);
			Files.copy(
					Paths.get(src),
					Paths.get(dest),
					StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			return ErrorType.COPY_FILE_FAILED;
		}

		return ErrorType.SUCCESS;
	}

	public static ErrorType showFile(String dir) {
		if (!isFile(dir)) {
			return ErrorType.INVALID_FILENAME;
		}

		List<String> lines;

		try {
			lines = Files.readAllLines(Paths.get(dir));
		} catch (IOException e) {
			return ErrorType.FILE_OPERATION_FAILED;
		}

		for (String line : lines) {
			System.out.println(line.trim());
		}

		return ErrorType.SUCCESS;
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

	public static ErrorType write(String src, String dest, boolean append) {
		if (!isFile(src) || !isValid(dest)) {
			return ErrorType.INVALID_FILENAME;
		}

		if (!exists(dest)) {
			try {
				mkdir(dest);
			} catch (IOException e) {
				return ErrorType.FAILED_TO_CREATE_FILE;
			}
		}

		List<String> lines;
		try {
			lines = Files.readAllLines(Paths.get(src));
		} catch (IOException e) {
			return ErrorType.FILE_OPERATION_FAILED;
		}

		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(dest, append));
			for (String line : lines) {
				out.write(line);
				out.newLine();
			}
			out.close();
		} catch (IOException e) {
			return ErrorType.FILE_OPERATION_FAILED;
		}

		return ErrorType.SUCCESS;
	}

	public static ErrorType writeString(String dest, String content, boolean append) {
		if (!isValid(dest)) {
			return ErrorType.INVALID_FILENAME;
		}

		if (!exists(dest)) {
			try {
				Files.createFile(Paths.get(dest));
			} catch (IOException e) {
				return ErrorType.FILE_OPERATION_FAILED;
			}
		}

		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(dest, append));
			out.write(content);
			out.newLine();
			out.close();
		} catch (IOException e) {
			return ErrorType.FILE_OPERATION_FAILED;
		}

		return ErrorType.SUCCESS;
	}
}