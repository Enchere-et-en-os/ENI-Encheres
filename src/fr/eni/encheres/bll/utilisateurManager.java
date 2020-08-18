package fr.eni.encheres.bll;

import java.util.List;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.UtilisateurDAO;

/**
 * manager de l'utilisateur
 * @author tjouvin2020
 *
 */
public class utilisateurManager {
	
	private UtilisateurDAO utilisateurDAO;
	
	public utilisateurManager() {
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
}
