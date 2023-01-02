/**
 * A small test for demo.
 * @author: 21371300
 */

package ans1.animal2;

public class Test {
	public static void main(String[] args) {
		Feeder feeder = new Feeder();

		feeder.feed(new Cat(), new Fish());
		feeder.feed(new Dog(), new Bone());
		feeder.feed(new Fish(), new Fish());
		feeder.feed(new Cat(), new Bone());
		feeder.feed(new Dog(), new Fish());
		feeder.feed(new Fish(), new Bone());
	}
}
