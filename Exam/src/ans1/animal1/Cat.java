/**
 * Cat is a lovely animal.
 * @author: 21371300
 */

package ans1.animal1;

public class Cat extends Animal {
	public Cat() {
		super(AnimalType.Cat);
	}

	@Override
	void eat(Food food) {
		StringBuilder builder = new StringBuilder();

		builder.append(this);
		if (food.getType() != FoodType.Fish) {
			builder.append(" doesn't like ");
			builder.append(food);
			builder.append(".");
		} else {
			builder.append(" loves eating ");
			builder.append(food);
			builder.append(" with claws.");
		}

		System.out.println(builder);
	}
}
