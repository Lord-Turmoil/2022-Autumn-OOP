/**
 * Dog is a frightening animal.
 * @author: 21371300
 */

package ans1.animal1;

public class Dog extends Animal {
	public Dog() {
		super(AnimalType.Dog);
	}

	@Override
	void eat(Food food) {
		StringBuilder builder = new StringBuilder();

		builder.append(this);
		if (food.getType() != FoodType.Bone) {
			builder.append(" doesn't like ");
			builder.append(food);
			builder.append(".");
		} else {
			builder.append(" loves eating ");
			builder.append(food);
			builder.append(" with teeth.");
		}

		System.out.println(builder);
	}
}
