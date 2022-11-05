package top.ts.oop.lab05.b;

import top.ts.oop.lab05.a.*;

public class Inherit {
    public static void main(String[] args){
		Parent p = new Parent();
		// p.fm1();	Error: Cannot access private member.
		// p.fm2(); Error: Cannot access non-public member outside package.
		// p.fm3();	Error: Cannot access protected member.
		p.fm4();
		Child c = new Child();
		// c.cm1();	Error: Cannot access private member.
		// c.cm2(); Error: Cannot access non-public member outside package.
		// c.cm3(); Error: Cannot access protected member.
		c.cm4();
		// c.fm1(); Error: Cannot inherit private member.
		// c.fm2();	Error: Cannot access non-public member outside package.
		// c.fm3(); Error: Cannot access protected member.
		c.fm4();
		GrandChild g = new GrandChild();
		// g.cm1();	Error: Cannot inherit private member.
		// g.cm2(); Error: 	Cannot inherit default member.
		// 					Default member cannot be accessed outside package.
		// g.cm3();	Error: Cannot access protected member.
		g.cm4();
		// g.fm1();	Error: Cannot access private member.
		// g.fm2();	Error: Cannot inherit default member.
		// 					Default member cannot be accessed outside package.
		// g.fm3();	Error: Cannot access protected member.
		g.fm4();
    }
}