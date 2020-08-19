package fr.eni.encheres.bll;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.UtilisateurDAO;


public class UtilisateurManager {
	
	private UtilisateurDAO utilisateurDAO;
	
	
	public Utilisateur selectById (int id) throws BLLException {
		Utilisateur utilisateur = null;
		try {
		utilisateur = utilisateurDAO.selectById(id);
		} catch (DALException e) {
			System.out.println("Erreur selectById manager");
			throw new BLLException();
		}
		return utilisateur;
	};
	
	
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
