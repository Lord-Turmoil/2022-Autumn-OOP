package ans2;

public class Bus implements Vehicle {
	@Override
	public void start() {
		System.out.println("Bus started!");
	}

	@Override
	public void stop() {
		System.out.println("Bus stopped.");
	}

	@Override
	public String toString() {
		return "Bus";
	}
}
