package scs.common;

public class Error {
	public static String getDescription(ErrorType type) {
		String description = type.toString();

		/*
		 description = description.toLowerCase(Locale.ENGLISH);
		 description = description.replace('_', ' ');
		 return description;
		*/

		return description.toLowerCase().replace('_', ' ');
	}

	public static void log(ErrorType type) {
		log(getDescription(type));
	}
	public static void log(String description) {
		System.out.println("Error: " + description);
	}
}