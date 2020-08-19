package fr.eni.encheres.bll;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.UtilisateurDAO;


public class utilisateurManager {
	
	private UtilisateurDAO utilisateurDAO;
	
	
	
	public void insertUtilisateur(Utilisateur user) throws BLLException{
		try {
			System.out.println(user.toString());
			utilisateurDAO.insertUtilisateur(user);
		} catch(DALException e) {
			System.out.println("oui");
			throw new BLLException();
		}
	}
}
