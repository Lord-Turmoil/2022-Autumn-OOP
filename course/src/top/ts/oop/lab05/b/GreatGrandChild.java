package top.ts.oop.lab05.b;

public class GreatGrandChild extends GrandChild {
	public static void main(String[] args){
		GreatGrandChild g = new GreatGrandChild();
		// g.cm1();	Error: Cannot inherit private member.
		// g.cm2(); Error: Cannot inherit default member.
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
