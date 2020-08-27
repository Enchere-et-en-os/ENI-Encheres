package fr.eni.encheres.ihm.model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Utilisateur;

public interface ConnexionForm {

	static final String REGEXGENERAL = "^[\\w\\séèêàâùç-]{3,}$";
	static final String REGEXEMAIL = "^[\\w.-]+@[\\w.-]+\\.[a-z]{2,}$";
	static final String REGEXTEL = "^[0-9]{10}$";
	static final String REGEXPOST = "^[0-9]{5}$";
	static final String REGEXPASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[$/.,-_!]).{8,15}$";
	static final String REGEXInput = "^[-\\w\\séèêàâùç]$";

	/*
	 * @Author : Tanguy & Valentin
	 * @References : https://www.lucaswillems.com/fr/articles/25/tutoriel-pour-maitriser-les-expressions-regulieres
	 * @Param : la nom et la valeur d'un parametre de requête
	 * 
	 * @Return : String de message d'Ã©rreur
	 */

	public static String regStringValeur(String valeurParametre, String nomParametre) {
		String messageErreur = " Veuillez entrer un";
		String message = null;
		String regex;
		switch (nomParametre) {
		case "pseudo":
			regex = REGEXGENERAL;
			if (!valeurParametre.matches(regex)) {
				message = messageErreur + " pseudo valide";
			}
			break;

		case "nom":
			regex = REGEXGENERAL;
			if (!valeurParametre.matches(regex)) {
				message = messageErreur + " nom valide";
			}
			break;

		case "prenom":
			regex = REGEXGENERAL;
			if (!valeurParametre.matches(regex)) {
				message = messageErreur + " prenom valide";
			}
			break;

		case "email":
			regex = REGEXEMAIL;
			if (!valeurParametre.matches(regex)) {
				message = messageErreur + "e adresse mail valide";
			}
			break;

		case "telephone":
			regex = REGEXTEL;
			if (!valeurParametre.matches(regex)) {
				message = messageErreur + " un numéro de téléphone valide";
			}
			break;

		case "rue":
			regex = REGEXGENERAL;
			if (!valeurParametre.matches(regex)) {
				message = messageErreur + " une rue valide";
			}
			break;

		case "codePostal":
			regex = REGEXPOST;
			if (!valeurParametre.matches(regex)) {
				message = messageErreur + " un code postal valide";
			}
			break;

		case "ville":
			regex = REGEXGENERAL;
			if (!valeurParametre.matches(regex)) {
				message = messageErreur + " un nom de ville valide";
			}
			break;
			
		case "mdp":
			regex = REGEXPASSWORD;
			if(!valeurParametre.matches(regex)) {
				message = messageErreur + " un Mot de Passe contenant entre 8 et 15 "
						+ "charactères dont une minuscule, une majuscule et un charactère"
						+ " spécial parmis : $/.,-_!";
			}
			break;
		}
		return message;
	}

	/*
	 * @Auhtor : Valentin
	 * 
	 * Desc : Ajout d'attribut contenant les possibles messages d'erreurs &
	 * vérification des données rentrées dans le formulaire
	 * 
	 * @Param : Requete d'une Servlet & la liste en String des entrées du formulaire
	 * 			la liste doit suivre le même ordre que celui du constructeur Utilisateur
	 * 
	 * @Return : Utilisateur via un constructeur de type List
	 */
	public static ArrayList<String> checkForm(HttpServletRequest request, ArrayList<String> entries) {
		UtilisateurManager mgr = new UtilisateurManager();
		List<Utilisateur> listCheckUsers = null;
		ArrayList<String> paramUser = new ArrayList<String>();
		String mdp = "";
		String erreur;

		for (String entry : entries) {
			switch (entry) {

			case "telephone":
				if (!request.getParameter(entry).trim().isEmpty())
					request.setAttribute("erreurTelephone", regStringValeur(request.getParameter(entry), entry));
				paramUser.add(request.getParameter(entry).trim());
				break;

			case "mdp":
				String lastEntry = request.getParameter(entries.get(entries.size() - 1)).trim();

				if (!(request.getParameter(entry).trim().equals(lastEntry))) {
					request.setAttribute("erreurConfirm", "Le mot de passe et sa confirmation sont différents");
					entries.remove(entries.size() - 1);
					break;
				}
				// vérifie si une érreur est levé, si oui break
				erreur = "erreur" + entry.substring(0, 1).toUpperCase() + entry.substring(1);
				request.setAttribute(erreur, regStringValeur(request.getParameter(entry), entry));
				if(request.getAttribute("erreurMdp") != null) {
					
					break;
				}
				
				mdp = hashMdp(request.getParameter(entry).trim());

				try {
					listCheckUsers = mgr.getAllUtilisateur();
				} catch (BLLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for (Utilisateur utilisateur : listCheckUsers) {
					if (utilisateur.getMotDePasse().contains(mdp)) {
						request.setAttribute(erreur , "Choissisez un autre Mot de Passe");
					}

				}
					
				entries.remove(entries.size() - 1);
				break;

			default:
				
				paramUser.add(request.getParameter(entry).trim());
				erreur = "erreur" + entry.substring(0, 1).toUpperCase() + entry.substring(1);
				request.setAttribute(erreur, regStringValeur(request.getParameter(entry), entry));
				break;
			}
		}
		paramUser.add(mdp);
		return paramUser;
	}
	

	/*
	 * @Author : Valentin
	 * @References : https://www.baeldung.com/sha-256-hashing-java
	 * 
	 * @Param : String, Mot de passe entré par l'utilisateur
	 * 
	 * @Return : String, Mot de passe hashé
	 */
	public static String hashMdp(String entryMdp) {

		MessageDigest digest = null;
		
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] encodedhash = digest.digest(entryMdp.getBytes(StandardCharsets.UTF_8));
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < encodedhash.length; i++) {
			String hex = Integer.toHexString(0xff & encodedhash[i]);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}
		
		return hexString.toString();

	}

	public static String validateInput(String str, String erreur) {
		erreur = "erreur lors de la saisie";

		if(str.matches(REGEXInput)) {
			System.out.println(str);
			return str;	
		}
		return erreur;
		}
	
}