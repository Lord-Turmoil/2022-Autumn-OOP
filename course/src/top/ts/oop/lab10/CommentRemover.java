package top.ts.oop.lab10;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CommentRemover {
	enum Status {
		NONE,
		SLASH_ONE,
		SINGLE_COMMENT,
		MULTIPLE_COMMENT,
		MULTIPLE_COMMENT_END
	}

	private static void write(FileWriter out, int ch) throws IOException {
		if (ch != 0) {
			out.write(ch);
		}
	}

	public static void removeComments(String inputPath, String outPath) throws IOException {
		File infile = new File(inputPath);
		File outFile = new File(outPath);

		FileReader in = new FileReader(infile);
		FileWriter out = new FileWriter(outFile);

		int cur, pre;
		int ch;
		cur = pre = 0;
		Status status = Status.NONE;
		while ((ch = in.read()) != -1) {
			switch (status) {
				case NONE -> {
					if (ch == '/') {
						status = Status.SLASH_ONE;
					}
				}
				case SLASH_ONE -> {
					if (ch == '/') {
						status = Status.SINGLE_COMMENT;
						ch = cur = 0;
					} else if (ch == '*') {
						status = Status.MULTIPLE_COMMENT;
						ch = cur = 0;
					} else {
						status = Status.NONE;
					}
				}
				case SINGLE_COMMENT -> {
					if (ch == '\n') {
						status = Status.NONE;
					} else {
						ch = 0;
					}
				}
				case MULTIPLE_COMMENT -> {
					if (ch == '*') {
						status = Status.MULTIPLE_COMMENT_END;
					} else {
						ch = 0;
					}
				}
				case MULTIPLE_COMMENT_END -> {
					if (ch == '/') {
						status = Status.NONE;
						cur = ch = 0;
					} else if (ch == '*') {
						status = Status.MULTIPLE_COMMENT_END;
						cur = 0;
					} else {
						status = Status.MULTIPLE_COMMENT;
						cur = ch = 0;
					}
				}
			}
			write(out, pre);
			pre = cur;
			cur = ch;
		}

		in.close();
		out.close();
	}

	public static void main(String[] args) {
		try {
			removeComments("hello.txt", "out.txt");
		} catch (IOException e) {
			System.out.println("NO!");
		}
	}
}
