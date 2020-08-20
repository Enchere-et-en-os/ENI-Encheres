package fr.eni.encheres.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.encheres.bo.Utilisateur;

public interface UtilisateurDAO {
	
	public List<Utilisateur> findAllUtilisateur() throws DALException;
	
	public Utilisateur checkLogin(String email, String mot_de_passe, String pseudo) throws SQLException, ClassNotFoundException, DALException;
	
	void insertUtilisateur(Utilisateur user) throws DALException;
	

	public Utilisateur selectByPseudo(String pseudo) throws DALException;

	public Utilisateur selectById (int id) throws DALException;

	
}
