package fr.eni.encheres.dal;

public interface UtilisateurDAO {
	
	void insertUtilisateur(String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String mdp) throws DALException;
	
}
