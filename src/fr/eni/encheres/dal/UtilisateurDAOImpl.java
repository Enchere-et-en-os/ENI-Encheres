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

	// Selection d'un utilisateur dans la BDD par son ID
	private static final String SQL_SELECT_BY_ID = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, "
			+ "rue, code_postal, ville, mot_de_passe, credit, administrateur FROM utilisateurs WHERE no_utilisateur = ?";

	// insertion d'un utilisateur via le formulaire d'inscription
	private static final String SQL_INSERT_USER = "INSERT INTO utilisateurs (pseudo, nom, prenom, email,"
			+ " telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) values(?,?,?,?,?,?,?,?,?,?,?)";

	private static final String SELECT_ID_BY_PSEUDO = "SELECT no_utilisateur FROM UTILISATEURS WHERE pseudo = ?";

	/**
	 * Attributs de classe des requêtes sql
	 */
	private static final String SQL_SELECT_ALL_USER = "SELECT * from UTILISATEURS";

	/**
	 * méthode pour récupérer tous les utilisateurs en base de donnée
	 */
	public List<Utilisateur> findAllUtilisateur() throws DALException {
		List<Utilisateur> listeUtilisateur = new ArrayList<Utilisateur>();

		try (Connection conn = ConnectionProvider.getConnection()) {
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(SQL_SELECT_ALL_USER);

			Utilisateur utilisateur = null;
			while (rs.next()) {
				utilisateur = new Utilisateur(rs.getInt("columnIndex"), rs.getString("pseudo"), rs.getString("nom"),
						rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue"),
						rs.getString("codePostal"), rs.getString("ville"), rs.getString("motDePasse"),
						rs.getInt("credit"));
				listeUtilisateur.add(utilisateur);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Echec de findAllUtilisateur", e);
		}
		return listeUtilisateur;

	}

	/*
	 * @auhtor : Valentin
	 * 
	 * @param : String pseudo
	 * 
	 * Selection l'id d'un utilisateur si son param existe
	 */
	public int findPseudo(String pseudo) throws DALException {
		int id = 0;

		try (Connection conn = ConnectionProvider.getConnection()) {
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(SELECT_ID_BY_PSEUDO);
			if (rs.next()) {
				id = rs.getInt("no_utilisateur");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Echec de findPseudo", e);
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
		try (Connection conn = ConnectionProvider.getConnection()){

			PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT_USER, PreparedStatement.RETURN_GENERATED_KEYS);

			pstmt.setString(1, user.getPseudo());
			pstmt.setString(2, user.getNom());
			pstmt.setString(3, user.getPrenom());
			pstmt.setString(4, user.getEmail());
			pstmt.setString(5, user.getTelephone());
			pstmt.setString(6, user.getRue());
			pstmt.setString(7, user.getCodePostal());
			pstmt.setString(8, user.getVille());
			pstmt.setString(9, user.getMotDePasse());
			pstmt.setInt(10, 100);
			pstmt.setInt(11, 0);
			pstmt.executeUpdate();

			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				user.setId(rs.getInt(1));
			}

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
	 */
	public Utilisateur selectById(int id) throws DALException {
		ResultSet rs = null;
		Utilisateur util = null;

		try (Connection cnx = ConnectionProvider.getConnection();) {

			PreparedStatement pstmt = cnx.prepareStatement(SQL_SELECT_BY_ID);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {

				util = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"), rs.getString("nom"),
						rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue"),
						rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"),
						rs.getInt("credit"), rs.getBoolean("administrateur"));
			} else {
				throw new DALException("L'utilisateur vaut null");
			}
		} catch (SQLException e) {
			throw new DALException(
					"Erreur lors de l'éxécution de la méthode SelectById de la classe UtilisateurDAOImpl", e);
		}
		return util;

	}
}
