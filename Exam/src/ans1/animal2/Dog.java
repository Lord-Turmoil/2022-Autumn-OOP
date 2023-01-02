/**
 * Dog is a frightening animal.
 * @author: 21371300
 */

package ans1.animal2;

public class Dog extends Animal {
	public Dog() {
		super(AnimalType.Dog);
	}

	@Override
	void eat(Food food) {
		StringBuilder builder = new StringBuilder();

		builder.append(this);
		if (food.getFoodType() != FoodType.Bone) {
			builder.append(" doesn't like ");
			builder.append(food.getFoodName());
			builder.append(".");
		} else {
			builder.append(" loves eating ");
			builder.append(food.getFoodName());
			builder.append(" with teeth.");
		}

		System.out.println(builder);
	}
}
