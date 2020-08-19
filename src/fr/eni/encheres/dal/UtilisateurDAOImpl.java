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
	private static final String INSERTUSER = "INSERT INTO utilisateurs (pseudo, nom, prenom, email,"
			+ " telephone, rue, codePostal, ville, motDePasse, credit, administrateur) values(?,?,?,?,?,?,?,?,?,?,?)";
		
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
						rs.getString("ville"), rs.getString("motDePasse"), rs.getInt("credit"));
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
	 * @param : Utilisateur
	 * 
	 * Insertion en bdd de l'utilisateur via inscription
	 * */
	public void insertUtilisateur(Utilisateur user) throws DALException{
		try (Connection conn = ConnexionProvider.getConnection()){
			System.out.println("dal");
			PreparedStatement pstmt = conn.prepareStatement(INSERTUSER, PreparedStatement.RETURN_GENERATED_KEYS);
			
			pstmt.setString(1, "e");
			pstmt.setString(2, "e");
			pstmt.setString(3, "e");
			pstmt.setString(4, "e");
			pstmt.setString(5, "e");
			pstmt.setString(6, "e");
			pstmt.setString(7, "e");
			pstmt.setString(8, "e");
			pstmt.setString(9, "e");
			pstmt.setInt(10,100);
			pstmt.setInt(11, 0);
			pstmt.executeUpdate(INSERTUSER);
			
//			pstmt.setString(1, user.getPseudo());
//			pstmt.setString(2, user.getNom());
//			pstmt.setString(3, user.getPrenom());
//			pstmt.setString(4, user.getEmail());
//			pstmt.setString(5, user.getTelephone());
//			pstmt.setString(6, user.getRue());
//			pstmt.setString(7, user.getCodePostal());
//			pstmt.setString(8, user.getVille());
//			pstmt.setString(9, user.getMotDePasse());
//			pstmt.setInt(10,100);
//			pstmt.setInt(11, 0);
//			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			//INSERTUSER = "INSERT INTO encheres.utilisateur (pseudo, nom, prenom, email,"
			//+ " telephone, rue, codePostal, ville, motDePasse) values(?,?,?,?,?,?,?,?,?,?,?)";
			
			Utilisateur utilisateur = null;
			while(rs.next()) {
				utilisateur = new Utilisateur(rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), 
						rs.getString("telephone"), rs.getString("rue"),  rs.getString("codePostal"), 
						rs.getString("ville"), rs.getString("motDePasse"));
			}
			rs.close();
			pstmt.close();
					
//			pstmt.setString(1, "e");
//			pstmt.setString(2, "e");
//			pstmt.setString(3, "e");
//			pstmt.setString(4, "e");
//			pstmt.setString(5, "e");
//			pstmt.setString(6, "e");
//			pstmt.setString(7, "e");
//			pstmt.setString(8, "e");
//			pstmt.setString(9, "e");
//			pstmt.setInt(10,100);
//			pstmt.setInt(11, 0);
		
			
		} catch (SQLException e) {
			// TODO Utiliser un log a la place
			e.printStackTrace();
			throw new DALException("Erreur lors de l'insertion", e);
		}

	}
}
