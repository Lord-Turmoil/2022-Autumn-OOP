package top.ts.oop.lab05.b;

import top.ts.oop.lab05.a.Child;

public class GrandChild extends Child {
	public static void main(String[] args){
		GrandChild g = new GrandChild();
		// g.cm1();	Error: Cannot inherit private member.
		// g.cm2(); Error: 	Cannot inherit default member.
		// 					Default member cannot be accessed outside package.
		g.cm3();
		g.cm4();
		// g.fm1();	Error: Cannot inherit private member.
		// g.fm2();	Error: 	Cannot inherit default member.
		// 					Default member cannot be accessed outside package.
		g.fm3();
		g.fm4();
    }
}
