package fr.eni.encheres.bll;

import java.sql.SQLException;
import java.util.List;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.ArticleDAO;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAOFactory;

public class ArticleManager {

	private ArticleDAO articleDAO;

	public ArticleManager() {
		articleDAO = DAOFactory.getArticleDAO();
	}

	/**
	 * @author Samy-Lee
	 * @return List<Article>
	 * @throws BLLException
	 * Selectionne tout les articles avec Utilisateur et catégorie
	 */
	public List<Article> SelectAllArticlesAvecUtilisateurEtCategorie(Utilisateur u, Categorie c) throws BLLException {
		List<Article> listeArticle = null;
		try {
			listeArticle = articleDAO.SelectAllArticlesAvecUtilisateurEtCategorie(u, c);
		} catch (DALException e) {
			System.out.println("erreur manager SelectAllArticlesAvecUtilisateurEtCategorie");
			throw new BLLException();
		}
		return listeArticle;

	}
	/**
	 * @author tjouvin
	 * @throws BLLException 
	 * @throws SQLException 
	 * @return article
	 */
	public Article insertArticle (Utilisateur utilisateur, Categorie categorie, Article article) throws BLLException, SQLException {
		Article art = null;
		try {
			art = articleDAO.insertArticle(utilisateur, categorie, article);
		} catch (DALException e) {
			System.out.println("erreur lors de l'insertion de l'article");
			throw new BLLException();
		}
		return art;
	}
	/**
	 * @author Samy-Lee
	 * @return List<Categorie>
	 * @throws BLLException
	 * Selectionne toutes les catégories
	 */
	public List<Categorie> SelectAllCategories() throws BLLException {
		
		List<Categorie> listeCategorie = null;
		
		try {
			listeCategorie = articleDAO.SelectAllCategories();
		} catch (DALException e) {
			System.out.println("Erreur manager SelectAllCategorie");
			throw new BLLException();
		}
		return listeCategorie;	
		
	}
	
	/**
	 * @author Samy-Lee
	 * @return List<Article>
	 * @throws BLLException
	 * Selectionne tout les articles avec Utilisateur et catégorie
	 */
	public List<Article> SelectAllArticles() throws BLLException {
		List<Article> listeArticle = null;
		try {
			listeArticle = articleDAO.SelectAllArticles();
		} catch (DALException e) {
			System.out.println("erreur manager SelectAllArticles");
			throw new BLLException();
		}
		return listeArticle;

	}
}
