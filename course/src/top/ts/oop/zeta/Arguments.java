package top.ts.oop.zeta;

public class Arguments {
	public static void main(String[] args) {
		Arguments arguments = new Arguments();
		Integer[] arr = {0, 1, 2, 3};
		Integer num = 66;
		Arg arg = new Arg(66);

		System.out.println("----- Array -----");
		arguments.printArray(arr);
		arguments.modifyArray(arr, 5);
		arguments.printArray(arr);

		System.out.println("----- Integer -----");
		arguments.printElement(num);
		arguments.modifyElement(num, 33);
		arguments.printElement(num);

		System.out.println("----- Custom Class -----");
		arguments.printElement(arg);
		arguments.modifyElement(arg, new Arg(99));
		arguments.printElement(arg);
	}

	public <T> void modifyArray(T[] arr, T val) {
		T temp = arr[0];
		arr[0] = arr[1];
		arr[1] = temp;
	}

	public <T> void modifyElement(T elem, T val) {
		elem = val;
	}

	public <T> void printArray(T[] arr) {
		for (int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}

	public <T> void printElement(T arr) {
		System.out.println(arr);
	}
}

class Arg {
	private Integer value;

	Arg(Integer val) {
		value = val;
	}

	public void setValue(Integer val) {
		value = val;
	}

	@Override
	public String toString() {
		return value.toString();
	}
}