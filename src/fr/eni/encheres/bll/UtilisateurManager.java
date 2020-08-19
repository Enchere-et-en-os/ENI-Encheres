package fr.eni.encheres.bll;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.UtilisateurDAO;


public class UtilisateurManager {
	
	private UtilisateurDAO utilisateurDAO;
	
	public UtilisateurManager() {
		utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}
	
	
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
			utilisateurDAO.insertUtilisateur(user);
		} catch(DALException e) {
			throw new BLLException();
		}
	}
	
	public int findPseudo(String pseudo) throws BLLException{
		int i = 0;
		try {
			i = utilisateurDAO.findPseudo(pseudo);
		} catch (DALException e) {
			throw new BLLException();
		}
		return i;
	}
}
