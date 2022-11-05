public class Test {
	public static void main(String[] args) {
		Host host = new Host();

		host.startup();
		host.run();
		host.shutdown();
	}
}
