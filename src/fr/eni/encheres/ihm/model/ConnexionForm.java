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

	static final String REGEXGENERAL = "^[\\w]{3,}$";
	static final String REGEXEMAIL = "^[\\w.-]+@[\\w.-]+\\.[a-z]{2,}$";
	static final String REGEXTEL = "^[0-9]{10}$";
	static final String REGEXPOST = "^[0-9]{5}$";
	static final String REGEXPASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[$/.,-_!]).{8.15}$";

	/*
	 * @Author : Tanguy & Valentin
	 * 
	 * @Param : la nom et la valeur d'un parametre de requête
	 * 
	 * @Return : String de message d'érreur
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
						+ "charact�res dont une minuscule, une majuscule et un charact�re"
						+ " sp�cial parmis : $/.,-_!";
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
	 * 
	 * @Return : Utilisateur via un constructeur de type List
	 */
	public static ArrayList<String> checkForm(HttpServletRequest request, ArrayList<String> entries) {
		ArrayList<String> paramUser = new ArrayList<String>();
		String mdp = "";
		String erreur;

		for (String entry : entries) {
			switch (entry) {

			case "telephone":
				System.out.println(request.getParameter(entry).trim());
				if (!request.getParameter(entry).trim().isEmpty())
					request.setAttribute("erreurTelephone", regStringValeur(request.getParameter(entry), entry));
				paramUser.add(request.getParameter(entry).trim());
				break;

			case "mdp":
				String lastEntry = request.getParameter(entries.get(entries.size() - 1)).trim();
				if (!(request.getParameter(entry).trim().equals(lastEntry))) {
					request.setAttribute("erreurConfirm", "Le mot de passe et sa confirmation sont diff�rents");
					entries.remove(entries.size() - 1);
					break;
				}
				
				mdp = hashkMdp(request.getParameter(entry).trim());
				if (mdp.isEmpty()) {
					request.setAttribute("erreurMdp", "Choissisez un autre mot de passe");
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
		System.out.println(paramUser);
		return paramUser;
	}
	

	/*
	 * @Author : Valentin
	 * 
	 * @Param : String, Mot de passe voulu par l'utilisateur
	 * 
	 * @Return : String, Mot de passe hashé après moult vérifications
	 */
	public static String hashkMdp(String entryMdp) {

		String mdp = null;
		MessageDigest digest = null;
		UtilisateurManager mgr = new UtilisateurManager();

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

		mdp = hexString.toString();
		List<Utilisateur> list = null;

		try {
			list = mgr.getAllUtilisateur();
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Utilisateur utilisateur : list) {
			if (utilisateur.getMotDePasse() == mdp) {
				mdp = null;
			}

		}

		return mdp;
		// TODO méthode pour remove les attributs inutiles
		// de préférence pour les messages (erreurs / confirmation)

		// TODO remplacer les (xxx == null) par xxx.isEmpty()
	}
}