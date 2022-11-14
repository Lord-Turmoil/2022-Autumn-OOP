package top.ts.oop.lab08.q05;

import java.util.HashMap;
import java.util.Map;

public class EmailSplit {
	public static void main(String[] args) {
		String str = "aa@sohu.com,bb@163.com,cc@sina.com";
		Map<String, String> emailMap = new HashMap<String, String>();

		String[] sites = str.split(",");
		for (String site : sites) {
			String[] pair = site.split("@");
			emailMap.put(pair[0], pair[1]);
		}

		System.out.println(emailMap.toString());
	}
}