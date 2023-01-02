/**
 * Fish is an animal and can also be a kind of food
 * @author: 21371300
 */

package ans1.animal2;

public class Fish extends Animal implements Food {
	public Fish() {
		super(AnimalType.Fish);
	}

	@Override
	void eat(Food food) {
		StringBuilder builder = new StringBuilder();

		if (food.getFoodType() != FoodType.Fish) {
			builder.append(this);
			builder.append(" doesn't like ");
			builder.append(food.getFoodName());
			builder.append(".");
		} else {
			builder.append("Big ");
			builder.append(this);
			builder.append(" loves eating small ");
			builder.append(food.getFoodName());
			builder.append(" with mouth?");
		}

		System.out.println(builder);
	}

	@Override
	public FoodType getFoodType() {
		return FoodType.Fish;
	}

	@Override
	public String getFoodName() {
		return FoodType.Fish.toString();
	}
}