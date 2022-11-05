/**
 * Default init value.
 */

package top.ts.oop.lab01;

public class Question7 {
	private byte	aByte;
	private char	aChar;
	private boolean aBoolean;
	private short	aShort;
	private int		anInt;
	private long	aLong;
	private float	aFloat;
	private double	aDouble;

	public static void main(String[] args) {
		Question7 obj = new Question7();
		obj.show();
	}

	public void show() {
		print("byte",		aByte);
		print("char", 		aChar);
		print("boolean", 	aBoolean);
		print("short", 	aShort);
		print("int", 		anInt);
		print("long", 		aLong);
		print("float", 	aFloat);
		print("double", 	aDouble);
	}

	public <T> void print(String type, T val) {
		System.out.printf("%10s: ", type);
		System.out.println(val);
	}
}
