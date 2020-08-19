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
	private static final String INSERT_USER = "INSERT INTO utilisateurs (pseudo, nom, prenom, email,"
			+ " telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) values(?,?,?,?,?,?,?,?,?,?,?)";
		
	private static final String SELECT_ID_BY_PSEUDO = "SELECT no_utilisateur FROM UTILISATEURS WHERE pseudo = ?";
	
	/**
	 * Attributs de classe des requêtes sql
	 */
	private String SELECTALLUSER = "SELECT * from UTILISATEURS";
	
	
	/**
	 * méthode pour récupérer tous les utilisateurs en base de donnée
	 */
	public List<Utilisateur> findAllUtilisateur() throws DALException {
		List<Utilisateur> listeUtilisateur = new ArrayList<Utilisateur>();
		
		try (Connection conn = ConnectionProvider.getConnection()) {
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
	 * @param : String pseudo
	 * 
	 * Selection l'id d'un utilisateur si son param existe
	 * */
	public int findPseudo(String pseudo) throws DALException{
		int id = 0;
		
		try (Connection conn = ConnectionProvider.getConnection()) {
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(SELECT_ID_BY_PSEUDO);
			if(rs.next()) {
				id = rs.getInt("no_utilisateur");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Echec de findPseudo",  e);
		}
		return id;
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
			PreparedStatement pstmt = conn.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
			
			pstmt.setString(1, user.getPseudo());
			pstmt.setString(2, user.getNom());
			pstmt.setString(3, user.getPrenom());
			pstmt.setString(4, user.getEmail());
			pstmt.setString(5, user.getTelephone());
			pstmt.setString(6, user.getRue());
			pstmt.setString(7, user.getCodePostal());
			pstmt.setString(8, user.getVille());
			pstmt.setString(9, user.getMotDePasse());
			pstmt.setInt(10,100);
			pstmt.setInt(11, 0);
			pstmt.executeUpdate();
			
			pstmt.executeUpdate();
			
			ResultSet rsKey = pstmt.getGeneratedKeys();
			if(rsKey.next()) {
				user.setId(rsKey.getInt(1));
			}
			rsKey.close();

		} catch (SQLException e) {
			// TODO Utiliser un log a la place
			e.printStackTrace();
			throw new DALException("Erreur lors de l'insertion", e);
		}

	}
}
