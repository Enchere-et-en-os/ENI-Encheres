package fr.eni.encheres.bll;

import java.util.List;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.dal.ArticleDAO;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.DAOFactory;

public class ArticleManager {

	private ArticleDAO articleDAO;

	public ArticleManager() {
		articleDAO = DAOFactory.getArticleDAO();
	}

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
}
