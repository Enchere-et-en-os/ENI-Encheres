package fr.eni.encheres.bll;

import java.util.List;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Categorie;
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
	 */
	public List<Article> SelectAllArticles() throws BLLException {

		List<Article> listeArticle = null;

		try {
			listeArticle = articleDAO.SelectAllArticles();
		} catch (DALException e) {
			System.out.println("erreur manager SelectAllArticle");
			throw new BLLException();
		}
		return listeArticle;

	}
	
	/**
	 * @author Samy-Lee
	 * @return List<Categorie>
	 * @throws BLLException
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
}
