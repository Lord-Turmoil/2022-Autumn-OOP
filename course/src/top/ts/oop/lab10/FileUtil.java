package top.ts.oop.lab10;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtil {
	public static void copyFile (String sourceFile, String targetFile) throws IOException {
		Files.copy(Paths.get(sourceFile), Paths.get(targetFile));
	}

	public static void copyDirectory(String sourceDir, String targetDir) throws IOException {
		File dest = new File(targetDir);
		File src = new File(sourceDir);

		if (src.isFile()) {
			copyFile(sourceDir, targetDir);
			return;
		}

		if (!dest.exists()) {
			dest.mkdirs();
		}

		File[] files = src.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile()) {
				copyFile(files[i].getAbsolutePath(), Paths.get(targetDir, files[i].getName()).toString());
			} else {
				copyDirectory(files[i].getAbsolutePath(), Paths.get(targetDir, files[i].getName()).toString());
			}
		}
	}

	public static void main(String[] args) {
		try {
			copyDirectory("a", "aa");
		} catch (IOException e) {
			System.out.println("No!");
		}
	}
}
