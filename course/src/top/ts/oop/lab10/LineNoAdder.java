package top.ts.oop.lab10;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class LineNoAdder {
	public static void addLineNo(String inputPath,String outPath) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get(inputPath));
		BufferedWriter out = new BufferedWriter(new FileWriter(outPath));
		int no = 1;
		for (String line : lines) {
			line = String.format("%d ", no) + line;
			out.write(line);
			out.newLine();
			no++;
		}
		out.close();
	}

	public static void main(String[] args) {
		try {
			addLineNo("hello.txt", "out.txt");
		} catch (IOException e) {
			System.out.println("No!");
		}
	}
}
