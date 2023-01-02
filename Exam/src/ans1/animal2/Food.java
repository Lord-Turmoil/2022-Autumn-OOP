/**
 * For base class of all foods.
 * @author: 21371300
 */
package ans1.animal2;

enum FoodType {
	Unknown,
	Fish,
	Bone
}

interface Food {
	FoodType getFoodType();
	String getFoodName();
}
