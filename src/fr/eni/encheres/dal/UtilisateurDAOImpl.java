package fr.eni.encheres.dal;

public class UtilisateurDAOImpl implements UtilisateurDAO {
	
	// insertion d'un utilisateur via le formulaire d'inscription
	private static final String INSERT = "INSERT INTO encheres.utilisateur (pseudo, nom, prenom, email,"
			+ " telephone, rue, codePostal, ville, motDePasse) values(?,?,?,?,?,?,?,?,?)";
}
