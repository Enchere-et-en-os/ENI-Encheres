package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Utilisateur;

public class ArticleDAOImpl implements ArticleDAO {

	/**
	 * Attributs de classe des requêtes sql
	 */

	// Version requête sans "image_path"
	private static final String SQL_SELECT_ALL_ARTICLES = "SELECT no_article, nom_article, description, date_debut_encheres,"
			+ " date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie FROM ARTICLES_VENDUS ";
	private static final String SQL_INSERT_INTO_ARTICLE = "INSERT INTO ARTICLES_VENDUS VALUES(?,?,?,?,?,?,?,?,?)";


	/**
	 * Selectionne tout les articles
	 * 

	private static final String SQL_SELECT_ALL_CATEGORIES = "SELECT no_categorie, libelle FROM CATEGORIES";


	 /**

	 * @author Samy-Lee
	 * @return List<Article>
	 * @throws DALException
	 * Selectionne tout les articles
	 */
	public List<Article> SelectAllArticles() throws DALException {
		List<Article> listeArticles = new ArrayList<Article>();

		try (Connection conn = ConnectionProvider.getConnection()) {
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(SQL_SELECT_ALL_ARTICLES);

			Article article = null;

			LocalDate dateDebutEnchere = null;
			LocalDate dateFinEnchere = null;

			while (rs.next()) {
				dateDebutEnchere = rs.getDate("date_debut_encheres").toLocalDate();
				dateFinEnchere = rs.getDate("date_fin_encheres").toLocalDate();
				// Version instance sans "image_path"
				article = new Article(rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"),
						dateDebutEnchere, dateFinEnchere, rs.getInt("prix_initial"), rs.getInt("prix_vente"), null,
						null, null);
				listeArticles.add(article);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Echec de SelectAllArticle", e);
		}
		return listeArticles;
	}

	/**
	 * méthode d'insertion d'un objet en bdd
	 * @throws SQLException 
	 * @throws DALException 
	 */
	public Article insertArticle (Utilisateur utilisateur, Categorie categorie, Article article) throws SQLException, DALException {

		Article article = null;
		int utilisateurId = utilisateur.getId();
		int categorieID = categorie.getId();
		PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT_USER, PreparedStatement.RETURN_GENERATED_KEYS);
		/**
		 * /**
	
    nom_article                   VARCHAR(30) NOT NULL,
    description                   VARCHAR(300) NOT NULL,
	date_debut_encheres           DATE NOT NULL,
    date_fin_encheres             DATE NOT NULL,
    prix_initial                  INTEGER,
    prix_vente                    INTEGER,
	etatVente					  INTEGER,
    no_utilisateur                INTEGER NOT NULL,
    no_categorie                  INTEGER NOT NULL
	 * **/
//		dateDebutEnchere = rs.getDate("date_debut_encheres").toLocalDate();
//		dateFinEnchere = rs.getDate("date_fin_encheres").toLocalDate();

		pstmt.setString(1, article.getNom());
		pstmt.setString(2, article.getDescription());
		pstmt.setDate(3, java.sql.Date.valueOf( article.getDateDebutEncheres()));
		pstmt.setDate(4, java.sql.Date.valueOf( article.getDateFinEncheres()));
		pstmt.setInt(5, article.getMiseAPrix());
		pstmt.setInt(6, article.getPrixVente());
		pstmt.setInt(7, article.getEtatVente());
		pstmt.setInt(8, utilisateurId);
		pstmt.setInt(9, categorieID);

		pstmt.executeUpdate();

		ResultSet rs = pstmt.getGeneratedKeys();
		if (rs.next()) {
			article.setId(rs.getInt(1));
		}
		


		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Echec de l'insertion d'un nouvel article", e); 
		}

		return article;

	}

	
	/**
	 * @author Samy-Lee
	 * @return List<Categorie>
	 * @throws BLLException
	 * Selectionne toutes les catégories
	 */
	public List<Categorie> SelectAllCategories() throws DALException {
		List<Categorie> listeCategorie = new ArrayList<Categorie>();

		try (Connection conn = ConnectionProvider.getConnection()) {
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(SQL_SELECT_ALL_CATEGORIES);
			Categorie categorie = null;

			while (rs.next()) {
				categorie = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));
				listeCategorie.add(categorie);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Echec de SelectAllCategories", e);
		}
		return listeCategorie;
	}
}
