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
	 * Selectionne tout les articles
	 */
	public List<Article> SelectAllArticlesAvecUtilisateurEtCategorie(int utilisateurId, int categorieId) throws BLLException {
		List<Article> listeArticle = null;
		try {
			listeArticle = articleDAO.SelectAllArticlesAvecUtilisateurEtCategorie(utilisateurId, categorieId);
		} catch (DALException e) {
			System.out.println("erreur manager SelectAllArticle");
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
	
	public List<Article> selectAllArticles() throws BLLException{
		List<Article> listeArticle = null;
		
		try {
			listeArticle = articleDAO.selectAllArticles();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			System.out.println("Erreur manager SelectAllArticle");
			throw new BLLException();
		}
		return listeArticle;
	}
}
