package top.ts.oop.lab07.sequence;

import top.ts.oop.lab07.shape.Ellipse;
import top.ts.oop.lab07.shape.Rectangle;
import top.ts.oop.lab07.shape.Rhombus;
import top.ts.oop.lab07.shape.Shape;

/**
 * Well, test the ShapeSequence.
 * 1. Test toString method.
 *    Create one sequence, add something to it.
 * 2. Test iterator.
 *    Create an iterator from the sequence, and
 *    traverse the sequence by it.
 * 3. Test iterator comparison.
 *    a. Create another iterator from the sequence, and
 *       place them at both the same or different position.
 *    b. Then, create another sequence, and compare
 *       iterators from difference sequence.
 */
public class SequenceTest {
	public static void main(String[] args) {
		// Step 1:
		ShapeSequence seqA = new ShapeSequence(10);

		seqA.add(Rectangle.getFactoryInstance().makeShape(3, 4));
		seqA.add(Rhombus.getFactoryInstance().makeShape(3, 4));
		seqA.add(Ellipse.getFactoryInstance().makeShape(3, 4));
		seqA.add(Rhombus.getFactoryInstance().makeShape(3, 4));
		seqA.add(Rectangle.getFactoryInstance().makeShape(3, 4));
		System.out.println(seqA);

		// Step 2:
		ShapeSequence.SequenceIterator itA1 = seqA.new SequenceIterator();
		while (!itA1.isEnd()) {
			System.out.println(itA1.current());
			itA1.moveNext();
		}

		// Step 3.a:
		ShapeSequence.SequenceIterator itA2 = seqA.new SequenceIterator();

		if (itA1.equals(itA2)) {
			System.out.println("itA1 equals to itA2");
		} else {
			System.out.println("itA1 doesn't equal to itA2");
		}
		itA1.rewind();
		if (itA1.equals(itA2)) {
			System.out.println("itA1 equals to itA2");
		} else {
			System.out.println("itA1 doesn't equal to itA2");
		}

		// Step 3.b
		ShapeSequence seqB = new ShapeSequence(5);
		ShapeSequence.SequenceIterator itB = seqB.new SequenceIterator();

		if (itA1.equals(itB)) {
			System.out.println("itA1 equals to itB");
		} else {
			System.out.println("itA1 doesn't equal to itB");
		}
	}
}
