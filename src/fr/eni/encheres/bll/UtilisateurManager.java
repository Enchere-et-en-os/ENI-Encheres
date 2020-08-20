package fr.eni.encheres.bll;

import java.sql.SQLException;
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

	public  List<Utilisateur> getAllUtilisateur() throws BLLException{
		List<Utilisateur> listeUtilisateur = null;
		try {
			listeUtilisateur = utilisateurDAO.findAllUtilisateur();
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException();
		}
		return listeUtilisateur;
	}
		
	public Utilisateur checkLogin(String email, String mot_de_passe, String pseudo) throws ClassNotFoundException, SQLException, BLLException {
		Utilisateur utilisateur = null;
		try {
			utilisateur = utilisateurDAO.checkLogin(email, mot_de_passe, pseudo);
			System.out.println("manager :"+utilisateur);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException();
		}
		return utilisateur;
	}
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
