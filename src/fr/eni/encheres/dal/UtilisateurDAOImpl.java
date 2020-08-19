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
	
	// Selection d'un utilisateur dans la BDD par son ID
	private static final String SQL_SELECT_BY_ID = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, "
			+ "rue, code_postal, ville, mot_de_passe, credit, administrateur FROM utilisateurs WHERE no_utilisateur = ?";
	
	// insertion d'un utilisateur via le formulaire d'inscription
	private static final String SQL_INSERT_USER = "INSERT INTO utilisateurs (pseudo, nom, prenom, email,"
			+ " telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) values(?,?,?,?,?,?,?,?,?,?,?)";

	/**
	 * Attributs de classe des requ�tes sql
	 */
	private String SQL_SELECT_ALL_USER = "SELECT * from UTILISATEURS";
	
	
	/**
	 * m�thode pour r�cup�rer tous les utilisateurs en base de donn�e
	 */
	public List<Utilisateur> findAllUtilisateur() throws DALException {
		List<Utilisateur> listeUtilisateur = new ArrayList<Utilisateur>();
		
		try (Connection conn = ConnexionProvider.getConnection()) {
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(SQL_SELECT_ALL_USER);

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
			PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT_USER, PreparedStatement.RETURN_GENERATED_KEYS);
			
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
			pstmt.executeUpdate(SQL_INSERT_USER);
			
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
		
		//connexion � la base de donn�e
		try(Connection cnx = ConnexionProvider.getConnection(); 
				//
				PreparedStatement pstmt = cnx.prepareStatement(SQL_SELECT_BY_ID);){
			//no_utilisateur == id
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			//r�cup�re les valeurs
			
			if(rs.next()) {
				
				util = new Utilisateur (rs.getInt("no_utilisateur"), rs.getString("pseudo"),  rs.getString("nom"),  
						rs.getString("prenom"),  rs.getString("email"),  rs.getString("telephone"),  rs.getString("rue"), 
						rs.getString("code_postal"),  rs.getString("ville"),  rs.getString("mot_de_passe"), rs.getInt("credit"), rs.getBoolean("administrateur") );

			}

		} catch (SQLException e) {
			throw new DALException("Erreur lors de l'�x�cution de la m�thode SelectById de la classe UtilisateurDAOImpl", e);
		}
		return util;
	}
}
