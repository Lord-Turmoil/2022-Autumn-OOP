package top.ts.oop.lab07.sequence;

import top.ts.oop.lab07.shape.Shape;

import javax.sound.midi.Sequence;

public class ShapeSequence {
	private Shape[] shapes = null;
	private int size;

	/**
	 * Constructor of the object.
	 * @param size
	 */
	public ShapeSequence(int size) {
		size = Math.max(size, 0);

		// Just be it, new Shape[0]. :|
		shapes = new Shape[size];
		this.size = 0;
	}

	/**
	 * Add a shape to the sequence, but do nothing
	 * if the sequence is full.
	 * @param shape
	 */
	public void add(Shape shape) {
		if (size < shapes.length) {
			shapes[size] = shape;
			size++;
		}
	}

	/**
	 * Hmm... Just present the sequence in string.
	 * @return String form of the sequence.
	 */
	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder();

		buffer.append("[");
		for (int i = 0; i < size; i++) {
			if (i != 0) {
				buffer.append(", ");
			}
			buffer.append(shapes[i].getType());
		}
		buffer.append("]");

		return buffer.toString();
	}

	public class SequenceIterator implements IIterator {
		private ShapeSequence parent = ShapeSequence.this;
		private int pos = 0;

		@Override
		public boolean isEnd() {
			return (pos == size);
		}

		@Override
		public void moveNext() {
			if (!isEnd()) {
				pos++;
			}
		}

		@Override
		public void rewind() {
			pos = 0;
		}

		@Override
		public Shape current() {
			if (!isEnd()) {
				return shapes[pos];
			} else {
				return null;
			}
		}

		@Override
		public boolean equals(Object o) {
			if (!(o instanceof SequenceIterator)) {
				return false;
			}

			SequenceIterator it = (SequenceIterator) o;
			if (this.parent == it.parent) {
				return this.pos == it.pos;
			} else {
				return false;
			}
		}
	}
}
