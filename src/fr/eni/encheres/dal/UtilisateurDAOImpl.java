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

	/**
	 * Attributs de classe des requêtes sql
	 */
	private String SELECTALLUSER = "SELECT * from UTILISATEURS";
	
	
	/**
	 * méthode pour récupérer tous les utilisateurs en base de donnée
	 */
	public List<Utilisateur> findAllUtilisateur() throws DALException{
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
}
