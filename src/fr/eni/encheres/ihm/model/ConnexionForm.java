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
			regex = REGEXGENERAL;
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
	public static List<String> checkForm(HttpServletRequest request, List<String> entries) {
		List<String> paramUser = new ArrayList<String>();
		String tel = null;
		String mdp = null;
		String erreur;

		for (String entry : entries) {
			switch (entry) {

			case "telephone":
				if (!entry.isEmpty())
					request.setAttribute("erreurTel", ConnexionForm.regStringValeur(entry, entry));
				tel = request.getParameter(entry).trim();
				break;

			case "mdp":
				if (!(entry.contentEquals(entries.get(entries.size())))) {
					request.setAttribute("erreurConfirm", "Le mot de passe et sa confirmation sont diff�rents");
				} else
					mdp = checkMdp(request.getParameter(entry).trim());
				if (mdp.isEmpty()) {
					request.setAttribute("erreurMdp", "Choissisez un autre mot de passe");
				}
				entries.remove(entries.size());
				break;

			default:
				paramUser.add(request.getParameter(entry).trim());
				erreur = "erreur" + entry.substring(0, 1).toUpperCase() + entry.substring(1);
				request.setAttribute(erreur, ConnexionForm.regStringValeur(entry, entry));
				break;
			}
		}
		paramUser.add(3, tel);
		paramUser.add(mdp);
		return paramUser;
	}

	/*
	 * @Author : Valentin
	 * 
	 * @Param : String, Mot de passe voulu par l'utilisateur
	 * 
	 * @Return : String, Mot de passe hashé après moult vérifications
	 */
	public static String checkMdp(String entryMdp) {

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