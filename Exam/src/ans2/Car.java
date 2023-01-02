package ans2;

public class Car implements Vehicle {
	@Override
	public void start() {
		System.out.println("Car started!");
	}

	@Override
	public void stop() {
		System.out.println("Car stopped.");
	}

	@Override
	public String toString() {
		return "Car";
	}
}
