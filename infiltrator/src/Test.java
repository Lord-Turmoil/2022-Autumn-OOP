import java.io.File;
import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
		peekInput();
		// peekDir();
	}

	public static void peekInput() {
		Scanner scanner = new Scanner(System.in);

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			FileUtil.write(line);
			if (line.equals("QUIT")) {
				break;
			}
		}

		FileUtil.flush();
	}

	public static void peekDir() {
		FileUtil.showDirectoryOrFile("./");
	}
}
