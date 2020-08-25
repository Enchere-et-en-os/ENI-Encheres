package fr.eni.encheres.ihm.model;

public class Utils {
	private static final String REGEX = "^[-\\w\\s]+$";
	
	public static String testString(String str) {
		
		if(str.matches(REGEX)) {
			System.out.println(str);
			return str;	
		}
		System.out.println("pas match");
		return "erreur";
	}
}
