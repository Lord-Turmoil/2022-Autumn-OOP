package top.ts.oop.lab02;

public class Question2 {
	public static void main(String[] args) {
		Student student = new Student("Tony", "21371300");

		System.out.println(student);
	}
}

class Student {
	private String name;
	private String id;

	public Student(String name, String id) {
		this.name = name;
		this.id = id;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();

		buffer.append("Name: ").append(name).append('\n');
		buffer.append("  ID: ").append(id);

		return buffer.toString();
	}
}

