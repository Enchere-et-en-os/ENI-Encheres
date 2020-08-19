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
	
	private static final String SQL_SELECT_BY_ID = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, "
			+ "rue, codePostal, ville, motDePasse FROM utilisateurs WHERE no_utilisateur = ?";
		
	/**
	 * Attributs de classe des requ�tes sql
	 */
	private String SELECTALLUSER = "SELECT * from UTILISATEURS";
	
	
	/**
	 * m�thode pour r�cup�rer tous les utilisateurs en base de donn�e
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
	
	/*
	 * @auhtor : Samy-Lee
	 * 
	 * @param : id
	 * 
	 * Selection en bdd de l'utilisateur par son id
	 * */
	public Utilisateur selectById (int id) throws DALException {
		ResultSet rs = null;
		Utilisateur util = null;
		
		
		try(Connection cnx = ConnexionProvider.getConnection(); 
				PreparedStatement pstmt = cnx.prepareStatement(SQL_SELECT_BY_ID);){
			
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				util = new Utilisateur (rs.getInt("no_utilisateur"), rs.getString("pseudo"),  rs.getString("nom"),  
						rs.getString("prenom"),  rs.getString("email"),  rs.getString("telephone"),  rs.getString("rue"), 
						rs.getString("codePostal"),  rs.getString("ville"),  rs.getString("motDePasse"), rs.getInt("motDePasse") );
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return util;
		
	}
}
