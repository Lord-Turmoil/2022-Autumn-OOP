package top.ts.oop.lab05.file;

import java.util.Scanner;
import java.util.Vector;

/***
 * This is completely a DISASTER! It is not graceful at all! :(
 * I don't want to have another glance at it.
 */
public class Host {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		Command cmd;
		Vector<String> vector = new Vector<>();
		while (scanner.hasNextLine()) {
			Scanner line = new Scanner(scanner.nextLine());
			vector.clear();

			while (line.hasNext()) {
				vector.add(line.next());
			}

			if (vector.elementAt(0).equals("quit")) {
				break;
			}

			cmd = CommandFactory.getCommand(vector.elementAt(0));
			if (cmd == null) {
				System.out.println("Command `" + vector.elementAt(0) + "` doesn't exist");
				continue;
			}

			vector.remove(0);
			cmd.execute(vector);
		}

		System.out.println("So long, XXXXXX!");
	}
}
