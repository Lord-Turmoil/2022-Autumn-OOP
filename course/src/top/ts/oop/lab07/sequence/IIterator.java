package top.ts.oop.lab07.sequence;

import top.ts.oop.lab07.shape.Shape;

public interface IIterator {

	boolean isEnd();
	void moveNext();
	void rewind();
	Shape current();
	boolean equals(Object o);
}
