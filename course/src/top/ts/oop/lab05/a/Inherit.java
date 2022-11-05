package top.ts.oop.lab05.a;

public class Inherit {
    public static void main(String[] args){
		Parent p = new Parent();
		// p.fm1();	Error: Cannot access private member.
		p.fm2();
		p.fm3();
		p.fm4();
		Child c = new Child();
		// c.cm1();	Error: Cannot access private member.
		c.cm2();
		c.cm3();
		c.cm4();
		// c.fm1();	Error: Cannot inherit private member.
		c.fm2();
		c.fm3();
		c.fm4();
    }
}