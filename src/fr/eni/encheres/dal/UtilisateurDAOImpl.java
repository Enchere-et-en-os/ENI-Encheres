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
	 * Attributs de classe des requ�tes sql
	 */
	private static final String SQL_SELECT_ALL_USER = "SELECT (pseudo, nom, prenom, email,\"\r\n" + 
			"			+ \" telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) FROM utilisateurs";
	private static final String SQL_SELECT_EMAIL_PASSWORD_PSEUDO = "SELECT (pseudo, nom, prenom, email,\"\r\n" + 
			"			+ \" telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) FROM utilisateurs "
			+ "WHERE email = ? or password = ? or pseudo=?";
	private static final String SQL_INSERT_USER = "INSERT INTO utilisateurs (pseudo, nom, prenom, email,"
			+ " telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) values(?,?,?,?,?,?,?,?,?,?,?)";
	
	/**
	 * m�thode pour r�cup�rer tous les utilisateurs en base de donn�e
	 */
	public List<Utilisateur> findAllUtilisateur() throws DALException {
		List<Utilisateur> listeUtilisateur = new ArrayList<Utilisateur>();
		
		try (Connection conn = ConnectionProvider.getConnection()) {
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(SQL_SELECT_ALL_USER);

			Utilisateur utilisateur = null;
			while(rs.next()) {
				utilisateur = new Utilisateur(
						rs.getInt("columnIndex"), rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), 
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
	/**
	 * m�thode pour r�cup�rer un utilisateur avec son password et son pseudo
	 * pour effectuer les contr�les login/logoff
	 * @throws DALException 
	 */
	public Utilisateur checkLogin(String email, String mot_de_passe, String pseudo) throws SQLException, ClassNotFoundException, DALException {
		
		try (Connection conn = ConnectionProvider.getConnection()) {
			PreparedStatement pStmt = conn.prepareStatement(SQL_SELECT_EMAIL_PASSWORD_PSEUDO);
			
			pStmt.setString(1, email);
			pStmt.setString(2, mot_de_passe);
			pStmt.setString(3, pseudo);
			
			System.out.println("pStmt" + pStmt);
			
			ResultSet rs = pStmt.executeQuery();
			
			System.out.println("rs"+ rs);
			
			Utilisateur utilisateur = null;
			if(rs.next()) {
				utilisateur = new Utilisateur(
						rs.getString("pseudo"), rs.getString("nom"),
						rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue"),
						rs.getString("codePostal"),rs.getString("ville"), rs.getString("motDePasse"), rs.getInt("credit"));
			}
			
			System.out.println("utilisateur dale :" + utilisateur);
			return utilisateur;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Echec de la v�rification",  e);
		}
	}
	/*
	 * @auhtor : Valentin
	 * 
	 * @param : Utilisateur
	 * 
	 * Insertion en bdd de l'utilisateur via inscription
	 * */
	public void insertUtilisateur(Utilisateur user) throws DALException{
		System.out.println("coucou");
		try (Connection conn = ConnectionProvider.getConnection()){
			System.out.println("dal");
			PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT_USER, Statement.RETURN_GENERATED_KEYS);
			
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
			pstmt.setBoolean(11, false);
			
			pstmt.executeUpdate();
			
			ResultSet rsKey = pstmt.getGeneratedKeys();
			if(rsKey.next()) {
				user.setId(rsKey.getInt(1));
			}
			
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
		
		} catch (SQLException e) {
			// TODO Utiliser un log a la place
			e.printStackTrace();
			throw new DALException("Erreur lors de l'insertion", e);
		}

	}
}
