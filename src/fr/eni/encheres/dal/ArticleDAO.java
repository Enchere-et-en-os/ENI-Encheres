package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.Article;

public interface ArticleDAO {

	public List<Article> SelectAllArticles() throws DALException;
}
