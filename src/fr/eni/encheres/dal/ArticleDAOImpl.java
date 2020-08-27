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
import fr.eni.encheres.bo.Retrait;


public class ArticleDAOImpl implements ArticleDAO {

	/**
	 * Attributs de classe des requêtes sql
	 */

	private static final String SQL_SELECT_ALL_ARTICLES = "SELECT no_article, nom_article, description, date_debut_encheres,"
			+ " date_fin_encheres, prix_initial, prix_vente, etatVente, no_utilisateur, no_categorie FROM ARTICLES_VENDUS ";

	private static final String SQL_INSERT_INTO_ARTICLE = "INSERT INTO ARTICLES_VENDUS(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, etatVente, no_utilisateur, no_categorie) VALUES(?,?,?,?,?,?,?,?,?)";
	
	private static final String SQL_SELECT_ALL_CATEGORIES = "SELECT no_categorie, libelle FROM CATEGORIES";
	private static final String SQL_SELECT_ARTICLES_BY_ETAT = "SELECT * FROM ARTICLES_VENDUS as A INNER JOIN UTILISATEURS as U ON A.no_utilisateur = U.no_utilisateur " + 
	"INNER JOIN ENCHERES as E ON U.no_utilisateur = E.no_utilisateur WHERE A.etatVente = ?";
	private static final String SQL_SELECT_ARTICLES_BY_ETAT_AND_UTILISATEUR = "SELECT A.no_article, nom_article, description, date_debut_encheres, " + 
			"date_fin_encheres, prix_initial, prix_vente, etatVente, U.no_utilisateur, no_categorie FROM ARTICLES_VENDUS as A " +
			"INNER JOIN UTILISATEURS as U ON A.no_utilisateur = U.no_utilisateur " +
			"INNER JOIN ENCHERES as E ON U.no_utilisateur = E.no_utilisateur " +
			"WHERE A.etatVente = ? AND U.no_utilisateur = ?";
	private static final String SQL_SELECT_ARTICLES_BY_ETAT_AND_GAGNE = "SELECT A.no_article, nom_article, description, date_debut_encheres, " + 
			"date_fin_encheres, prix_initial, prix_vente, etatVente, E.no_utilisateur, no_categorie FROM ARTICLES_VENDUS as A " +
			"INNER JOIN UTILISATEURS as U ON A.no_utilisateur = U.no_utilisateur " +
			"INNER JOIN ENCHERES as E ON U.no_utilisateur = E.no_utilisateur " +
			"INNER JOIN UTILISATEURS as ACH ON ACH.no_utilisateur = E.no_utilisateur " +
			"WHERE A.etatVente = ? AND E.no_utilisateur = ?" ;
	private static final String SQL_SELECT_ENCHERES_BY_ETAT = "SELECT * FROM ARTICLES_VENDUS as A " +
			"INNER JOIN ENCHERES as E ON A.no_utilisateur = E.no_utilisateur " +
			"WHERE A.etatVente = ? AND E.no_utilisateur = ?";
	private static final String SQL_SELECT_ARTICLE_BY_ID = "SELECT no_article, nom_article, description, date_debut_encheres, " + 
			"date_fin_encheres, prix_initial, prix_vente, etatVente, no_utilisateur, no_categorie FROM ARTICLES_VENDUS WHERE no_article = ?";
//	private static final String SQL_FILTRE_CATEGORIE = " WHERE A.no_categorie = ?";
//	private static final String SQL_FILTRE_CATEGORIE_AND = " AND A.no_categorie = ?";
	
	 /**
	 * @author tanguy

	 * @return List<Article>
	 * @throws DALException
	 * Selectionne les articles avec les paramètres utilisateurId & categorieId
	 */
	public List<Article> SelectAllArticlesAvecUtilisateurEtCategorie(int utilisateurId, int categorieId) throws DALException {
		List<Article> listeArticles = new ArrayList<Article>();

		try (Connection conn = ConnectionProvider.getConnection()) {
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(SQL_SELECT_ALL_ARTICLES);
			Article article = null;
			while (rs.next()) {

				article = new Article(rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"),rs.getDate("date_debut_encheres").toLocalDate(), 
						rs.getDate("date_fin_encheres").toLocalDate(), rs.getInt("prix_initial"), rs.getInt("prix_vente"), rs.getInt("etatVente"), utilisateurId, categorieId);
				
				listeArticles.add(article);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Echec de SelectAllArticle", e);
		}
		return listeArticles;
	}

	
	public List<Article> selectAllArticles() throws DALException{
		List<Article> listArticles = new ArrayList<Article>();
		Article article = null;
		try(Connection conn = ConnectionProvider.getConnection()){
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(SQL_SELECT_ALL_ARTICLES);
			
			while(rs.next()) {
				article = new Article(rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"),rs.getDate("date_debut_encheres").toLocalDate(), 
						rs.getDate("date_fin_encheres").toLocalDate(), rs.getInt("prix_initial"), rs.getInt("prix_vente"), rs.getInt("etatVente"),rs.getInt("no_utilisateur"),rs.getInt("no_categorie") );
				listArticles.add(article);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Echec de SelectAllArticle", e);
		}
		
		return listArticles;
	}
	/**
	 * Auteur tanguy
	 * méthode d'insertion d'un objet en bdd
	 * @throws SQLException 
	 * @throws DALException 
	 */
	public void insertArticle (Article article, int utilisateurId, int categorieId ) throws SQLException, DALException {
//nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, etatVente, no_utilisateur, no_categorie)
		try(Connection conn =  ConnectionProvider.getConnection()) {
			
		PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT_INTO_ARTICLE, PreparedStatement.RETURN_GENERATED_KEYS);
		System.out.println("art daoImpl : " + article);
		
		pstmt.setString(1, article.getNom());
		pstmt.setString(2, article.getDescription());
		pstmt.setDate(3, java.sql.Date.valueOf( article.getDateDebutEncheres()));
		pstmt.setDate(4, java.sql.Date.valueOf( article.getDateFinEncheres()));
		pstmt.setInt(5, article.getMiseAPrix());
		pstmt.setInt(6, article.getPrixVente());
		pstmt.setInt(7, article.getEtatVente());
		pstmt.setInt(8, utilisateurId);
		pstmt.setInt(9, categorieId);
		pstmt.executeUpdate();
/**
 * new Article(article.setNom(rs.getString("nom_article")), article.setNom(rs.getString("description"))
					, article.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate()), article.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate()),
					article.setMiseAPrix(rs.getInt("prix_initial")), article.setPrixVente(rs.getInt("prix_vente")), article.setEtatVente(rs.getInt("etatVente")), utilisateurId,
					categorieID);
 */	
//  préparation pour requête SelectBY ou Update
//		ResultSet rs = pstmt.getGeneratedKeys();
//		if (rs.next()) {
//	
//		article.setNom(rs.getString("nom_article")); 
//		article.setDescription(rs.getString("description"));
//		article.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate()); 
//		article.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
//		article.setMiseAPrix(rs.getInt("prix_initial")); 
//		article.setPrixVente(rs.getInt("prix_vente"));
//		article.setEtatVente(rs.getInt("etatVente"));
//		article.setUtilisateurId(utilisateurId);
//		article.setCategorieId(categorieId);
//		}
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Echec de l'insertion d'un nouvel article", e); 
		}

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
	
	/**
	 * Selectionne tout les articles
	 * @author Samy-Lee
	 * @return List<Article>
	 * @throws DALException
	 */
	public List<Article> SelectAllArticles() throws DALException{
		List<Article> listeArticles = new ArrayList<Article>();
		
		try (Connection conn = ConnectionProvider.getConnection()) {
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(SQL_SELECT_ALL_ARTICLES);
			
			Article article = null;
			
			LocalDate dateDebutEnchere = null;
			LocalDate dateFinEnchere = null;
			
			
			while(rs.next()) {
				dateDebutEnchere = rs.getDate("date_debut_encheres").toLocalDate();
				dateFinEnchere = rs.getDate("date_fin_encheres").toLocalDate();
				article = new Article(rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"), 
						     dateDebutEnchere, dateFinEnchere, rs.getInt("prix_initial"), 
						      rs.getInt("prix_vente"), rs.getInt("etatVente"), rs.getInt("no_utilisateur") ,rs.getInt("no_categorie"));
				listeArticles.add(article);
			}
		
		} catch (SQLException e){
			e.printStackTrace();
			throw new DALException("Echec de SelectAllArticles", e);
		}
		return listeArticles;
	}
	
	public List<Article> selectAllByEtatVente(int etatVente) throws DALException{
		List<Article> listeArticles = new ArrayList<Article>();
		
		try (Connection conn = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT_ARTICLES_BY_ETAT);
			
			pstmt.setInt(1, etatVente);
			ResultSet rs = pstmt.executeQuery();
			
			Article article = null;
			LocalDate dateDebutEnchere = null;
			LocalDate dateFinEnchere = null;
			
			while(rs.next()) {
				dateDebutEnchere = rs.getDate("date_debut_encheres").toLocalDate();
				dateFinEnchere = rs.getDate("date_fin_encheres").toLocalDate();
				article = new Article(rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"), 
					     dateDebutEnchere, dateFinEnchere, rs.getInt("prix_initial"), 
					      rs.getInt("prix_vente"), rs.getInt("etatVente"), rs.getInt("no_utilisateur") ,rs.getInt("no_categorie"));
				listeArticles.add(article);
			}
			
			
		} catch (SQLException e){
			e.printStackTrace();
			throw new DALException("Echec de SelectAllArticles", e);
		}
		
		return listeArticles;
	}
	
	public List<Article> selectAllByEtatVenteUtilisateur(int etatVente, int idUtilisateur) throws DALException{
		List<Article> listeArticles = new ArrayList<Article>();
		
		try (Connection conn = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT_ARTICLES_BY_ETAT_AND_UTILISATEUR);
			
			pstmt.setInt(1, etatVente);
			pstmt.setInt(2, idUtilisateur);
			ResultSet rs = pstmt.executeQuery();
			
			Article article = null;
			LocalDate dateDebutEnchere = null;
			LocalDate dateFinEnchere = null;
			
			while(rs.next()) {
				dateDebutEnchere = rs.getDate("date_debut_encheres").toLocalDate();
				dateFinEnchere = rs.getDate("date_fin_encheres").toLocalDate();
				article = new Article(rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"), 
					     dateDebutEnchere, dateFinEnchere, rs.getInt("prix_initial"), 
					      rs.getInt("prix_vente"), rs.getInt("etatVente"), rs.getInt("no_utilisateur") ,rs.getInt("no_categorie"));
				listeArticles.add(article);
			}
			
			
		} catch (SQLException e){
			e.printStackTrace();
			throw new DALException("Echec de SelectAllArticles", e);
		}
		
		return listeArticles;
	}
	
	public List<Article> selectAllByEtatVenteGagne(int etatVente, int idUtilisateur) throws DALException{
		List<Article> listeArticles = new ArrayList<Article>();
		
		try (Connection conn = ConnectionProvider.getConnection()) {
			

			PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT_ARTICLES_BY_ETAT_AND_GAGNE);
			
			pstmt.setInt(1, etatVente);
			pstmt.setInt(2, idUtilisateur);
			ResultSet rs = pstmt.executeQuery();
		
			Article article = null;
			LocalDate dateDebutEnchere = null;
			LocalDate dateFinEnchere = null;
			
			while(rs.next()) {
				dateDebutEnchere = rs.getDate("date_debut_encheres").toLocalDate();
				dateFinEnchere = rs.getDate("date_fin_encheres").toLocalDate();
				article = new Article(rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"), 
					     dateDebutEnchere, dateFinEnchere, rs.getInt("prix_initial"), 
					      rs.getInt("prix_vente"), rs.getInt("etatVente"), rs.getInt("no_utilisateur") ,rs.getInt("no_categorie"));
				listeArticles.add(article);
			}
			
			
		} catch (SQLException e){
			e.printStackTrace();
			throw new DALException("Echec de SelectAllArticles", e);
		}
		
		return listeArticles;
	}
	
	public List<Article> SelectAllEncheresByEtat(int idUtilisateur, int etatVente) throws DALException{
		List<Article> listeArticles = new ArrayList<Article>();
		
		try (Connection conn = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT_ENCHERES_BY_ETAT);

			pstmt.setInt(1, etatVente);
			pstmt.setInt(2, idUtilisateur);
			ResultSet rs = pstmt.executeQuery();
			
			Article article = null;
			
			LocalDate dateDebutEnchere = null;
			LocalDate dateFinEnchere = null;
			
			
			while(rs.next()) {
				dateDebutEnchere = rs.getDate("date_debut_encheres").toLocalDate();
				dateFinEnchere = rs.getDate("date_fin_encheres").toLocalDate();
				article = new Article(rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"), 
						     dateDebutEnchere, dateFinEnchere, rs.getInt("prix_initial"), 
						      rs.getInt("prix_vente"), rs.getInt("etatVente"), rs.getInt("no_utilisateur") ,rs.getInt("no_categorie"));
				listeArticles.add(article);
			}
		
		} catch (SQLException e){
			e.printStackTrace();
			throw new DALException("Echec de SelectAllArticles", e);
		}
		return listeArticles;
	}
	
	public Article SelectEnchereById(int idArticle) throws DALException{
		Article article = null;
		
		try (Connection conn = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT_ARTICLE_BY_ID);

			pstmt.setInt(1, idArticle);
	
			ResultSet rs = pstmt.executeQuery();
			
			
			LocalDate dateDebutEnchere = null;
			LocalDate dateFinEnchere = null;
			
			
			if(rs.next()) {
				dateDebutEnchere = rs.getDate("date_debut_encheres").toLocalDate();
				dateFinEnchere = rs.getDate("date_fin_encheres").toLocalDate();
				article = new Article(rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"), 
						     dateDebutEnchere, dateFinEnchere, rs.getInt("prix_initial"), 
						      rs.getInt("prix_vente"), rs.getInt("etatVente"), rs.getInt("no_utilisateur") ,rs.getInt("no_categorie"));;
			}
		
		} catch (SQLException e){
			e.printStackTrace();
			throw new DALException("Echec de SelectAllArticles", e);
		}
		return article;
	}
}
