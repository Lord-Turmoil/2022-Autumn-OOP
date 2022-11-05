import java.util.Scanner;
import java.util.Vector;

/***
 * This is the main process of the program.
 */
public class Host {
	private boolean isRunning = false;
	private Scanner scanner = new Scanner(System.in);
	Vector<String> arguments = new Vector<>();

	public void startup() {
		isRunning = true;
	}

	public void shutdown() {
		isRunning = false;
	}

	public void run() {
		if (!isRunning) {
			System.out.println("Start up first!");
			return;
		}

		while (isRunning) {
			if (!receiveCommand())
				break;
			handleCommand();
		}
	}

	private boolean receiveCommand() {
		// Here, if a new scanner is created, previous input will
		// be ignored.
		// Scanner scanner = new Scanner(System.in);
		String arg;

		if (scanner.hasNextLine()) {
			Scanner line = new Scanner(scanner.nextLine());
			while (line.hasNext()) {
				arg = line.next();
				arguments.add(arg);
			}
		} else {
			return false;
		}

		return true;
	}

	private void handleCommand() {
		if (arguments.isEmpty()) {
			return;
		}

		Command command = CommandDispatcher.dispatch(arguments.firstElement());
		if (command == null) {
			System.out.println("command '" + arguments.firstElement() + "' not found");
			arguments.clear();
			return;
		}
		arguments.remove(0);

		ErrorType error = command.handle(arguments);
		if (error == ErrorType.SHUTDOWN) {
			shutdown();
		} else if (error != ErrorType.SUCCESS) {
			System.out.println(Error.getDescription(error));
		}

		arguments.clear();
	}
}
