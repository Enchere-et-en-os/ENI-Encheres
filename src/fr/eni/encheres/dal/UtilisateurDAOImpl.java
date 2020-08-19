package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.Utilisateur;

public class UtilisateurDAOImpl implements UtilisateurDAO {
	
	// insertion d'un utilisateur via le formulaire d'inscription
	private static final String INSERTUSER = "INSERT INTO encheres.utilisateur (pseudo, nom, prenom, email,"
			+ " telephone, rue, codePostal, ville, motDePasse) values(?,?,?,?,?,?,?,?,?,?,?)";
		
	/**
	 * Attributs de classe des requêtes sql
	 */
	private String SELECTALLUSER = "SELECT * from UTILISATEURS";
	
	
	/**
	 * méthode pour récupérer tous les utilisateurs en base de donnée
	 */
	public List<Utilisateur> findAllUtilisateur() throws DALException {
		List<Utilisateur> listeUtilisateur = new ArrayList<Utilisateur>();
		
		try (Connection conn = ConnexionProvider.getConnection()) {
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(SELECTALLUSER);

			Utilisateur utilisateur = null;
			while(rs.next()) {
				utilisateur = new Utilisateur(rs.getInt("columnIndex"), rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), 
						rs.getString("telephone"), rs.getString("rue"),  rs.getString("codePostal"), 
						rs.getString("ville"), rs.getString("motDePasse"), rs.getInt("credit"), rs.getInt("administrateur"));
				listeUtilisateur.add(utilisateur);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Echec de findAllUtilisateur",  e);
		}
		return listeUtilisateur;
		
	}
	
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
			pstmt.setInt(10,100);
			pstmt.setInt(11, 0);
			
			
			pstmt.executeUpdate();	
			
		} catch (SQLException e) {
			// TODO Utiliser un log a la place
			e.printStackTrace();
			throw new DALException("Erreur lors de l'insertion", e);
		}

	}
}
