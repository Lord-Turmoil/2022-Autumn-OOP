package top.ts.oop.lab02;

import javax.print.DocFlavor;

public class Catter {
	public static void main(String[] args) {
		System.out.println(strscat("a", "b", "c", "", "e"));
		System.out.println(strscat("str"));
		System.out.println(strscat(new String[]{"a", "b"}));
	}

	public static String strscat(String... str) {
		StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < str.length; i++) {
			buffer.append(str[i]);
		}

		return buffer.toString();
	}

//	public static String strscat(String[] args) {
//		return String("123");
//	}
}
