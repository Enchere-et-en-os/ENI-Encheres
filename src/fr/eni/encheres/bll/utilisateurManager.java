package fr.eni.encheres.bll;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.UtilisateurDAO;


public class utilisateurManager {
	
	private UtilisateurDAO utilisateurDAO;
	
	
	
	public void insertUtilisateur(Utilisateur user) throws BLLException{
		try {
			System.out.println(user.toString());
			utilisateurDAO.insertUtilisateur(user.getPseudo(), user.getNom(), user.getPrenom(), user.getEmail(),
					user.getTelephone(), user.getRue(), user.getCodePostal(), user.getVille(), user.getMotDePasse());
		} catch(DALException e) {
			throw new BLLException();
		}
	}
}
