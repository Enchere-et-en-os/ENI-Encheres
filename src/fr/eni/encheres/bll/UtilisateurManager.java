package fr.eni.encheres.bll;

import java.util.List;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.UtilisateurDAO;


public class UtilisateurManager {
	
	private UtilisateurDAO utilisateurDAO;
	
	
	/**
	 * constructeur utilisateurManager
	 */
	public UtilisateurManager() {
		utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}

	public  List<Utilisateur> getAllUtilisateur() {
		List<Utilisateur> listeUtilisateur = null;
		try {
			listeUtilisateur = utilisateurDAO.findAllUtilisateur();
		} catch (DALException e) {

			e.printStackTrace();
		}
		return listeUtilisateur;
	}
		
	
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
