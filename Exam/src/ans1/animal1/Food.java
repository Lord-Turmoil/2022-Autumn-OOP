/**
 * For base class of all foods.
 * @author: 21371300
 */
package ans1.animal1;

enum FoodType {
	Unknown,
	Fish,
	Bone
}

abstract class Food {
	private FoodType type;

	protected Food(FoodType type) {
		this.type = type;
	}

	FoodType getType() {
		return type;
	}

	@Override
	public String toString() {
		return type.toString();
	}
}
