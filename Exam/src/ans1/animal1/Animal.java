/**
 * For base class of all animals.
 * @author: 21371300
 */
package ans1.animal1;

enum AnimalType {
	Unknown,
	Dog,
	Cat
}

public abstract class Animal {
	private AnimalType type;

	protected Animal(AnimalType type) {
		this.type = type;
	}

	abstract void eat(Food food);

	@Override
	public String toString() {
		return type.toString();
	}
}
