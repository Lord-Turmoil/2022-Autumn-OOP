package ans2;

enum Sex {
	Unknown,
	Male,
	Female
}

public abstract class Role {
	private String name;
	private int age;
	private Sex sex;

	protected Role(String name, int age, Sex sex) {
		this.name = name;
		this.age = age;
		this.sex = sex;
	}

	protected Role(String name) {
		this.name = name;
		this.age = 0;
		this.sex = Sex.Unknown;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		if (age >= 0) {
			this.age = age;
		} else {
			// Some extra warnings.
		}
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public abstract void play();
}
