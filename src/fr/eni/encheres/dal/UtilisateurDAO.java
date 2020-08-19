package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.Utilisateur;

public interface UtilisateurDAO {
	
	public List<Utilisateur> findAllUtilisateur() throws DALException;
	
	void insertUtilisateur(String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String mdp) throws DALException;
	
}
