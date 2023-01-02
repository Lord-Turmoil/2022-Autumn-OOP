/**
 * Bone is a kind of food.
 * @author: 21371300
 */

package ans1.animal2;

public class Bone implements Food {
	@Override
	public FoodType getFoodType() {
		return FoodType.Bone;
	}

	@Override
	public String getFoodName() {
		return FoodType.Bone.toString();
	}
}
