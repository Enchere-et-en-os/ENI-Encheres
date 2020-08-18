package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UtilisateurDAOImpl implements UtilisateurDAO {
	
	// insertion d'un utilisateur via le formulaire d'inscription
	private static final String INSERTUSER = "INSERT INTO encheres.utilisateur (pseudo, nom, prenom, email,"
			+ " telephone, rue, codePostal, ville, motDePasse) values(?,?,?,?,?,?,?,?,?)";
	
	
	/*
	 * @auhtor : Valentin
	 * 
	 * @param : nom, prenom, pseudo, email, telephone, rue,
	 * ville, codePostal, mdp
	 * 
	 * Insertion en bdd de l'utilisateur via inscription
	 * */
	public void insertUtilisateur(String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String mdp) throws DALException{
		try (Connection conn = ConnexionProvider.getConnection()){
			PreparedStatement pstmt = conn.prepareStatement(INSERTUSER);
			pstmt.setString(1, pseudo);
			pstmt.setString(2, nom);
			pstmt.setString(3, prenom);
			pstmt.setString(4, email);
			pstmt.setString(5, telephone);
			pstmt.setString(6, rue);
			pstmt.setString(7, codePostal);
			pstmt.setString(8, ville);
			pstmt.setString(9, mdp);
			
			pstmt.executeUpdate();	
			
		} catch (SQLException e) {
			// TODO Utiliser un log a la place
			e.printStackTrace();
		}
	}
}
