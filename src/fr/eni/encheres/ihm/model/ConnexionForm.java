package fr.eni.encheres.ihm.model;

public interface ConnexionForm {
	
	static final String REGEXGENERAL = "^[\\w]{3,}$";
	static final String REGEXEMAIL = "^[\\w.-]+@[\\w.-]+\\.[a-z]{2,}$";
	static final String REGEXTEL = "^[0-9]{10}$";
	static final String REGEXPOST = "^[0-9]{5}$";
	
	
	public static String regStringValeur( String valeurParametre, String nomParametre) {
		String messageErreur = " Veuillez entrer un";
		String message = null;
		String regex;
		switch (nomParametre) {
			case "pseudo" : 
				regex = REGEXGENERAL;
				if(!valeurParametre.matches(regex)) {
					message = messageErreur + " pseudo valide" ;
				}
				break;
			case "nom" : 
				regex = REGEXGENERAL;
				if(!valeurParametre.matches(regex)) {
					message = messageErreur + " nom valide" ;
				}
				break;
			case "prenom" : 
				regex = REGEXGENERAL;
				if(!valeurParametre.matches(regex)) {
					message = messageErreur + " prenom valide" ;
				}
		}
		return message;
	}
}
