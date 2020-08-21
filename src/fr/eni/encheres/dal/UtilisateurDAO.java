package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.Utilisateur;

public interface UtilisateurDAO {

	public List<Utilisateur> findAllUtilisateur() throws DALException;
	
	void insertUtilisateur(Utilisateur user) throws DALException;
	
	public Utilisateur selectByPseudo(String pseudo) throws DALException;

	public Utilisateur selectById(int id) throws DALException;

}
