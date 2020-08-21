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
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException();
		}
		return utilisateur;
	}
	
	public void insertUtilisateur(Utilisateur user) throws BLLException{
		try {
			utilisateurDAO.insertUtilisateur(user);
		} catch(DALException e) {
			throw new BLLException();
		}
	}
	/**
	 * @author Samy-Lee
	 * @param id
	 * @return Utilisateur
	 * @throws BLLException
	 * 
	 * Selectionne un utilisateur par son ID à l'aide du manager
	 */
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
	
	public Utilisateur selectByPseudo(String pseudo) throws BLLException{
		Utilisateur util = null;
		try {
			util = utilisateurDAO.selectByPseudo(pseudo);
		} catch (DALException e) {
			throw new BLLException();
		}
		return util;
	}
}
