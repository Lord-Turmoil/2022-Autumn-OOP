import scs.Host;

public class Test {
	public static void main(String[] args) {
		Host host = Host.getInstance();

		host.startup();
		host.run();

		// If out, then must have been shutdown.
		// host.shutdown();
	}
}