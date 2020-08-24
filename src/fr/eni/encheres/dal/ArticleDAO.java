package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Categorie;

public interface ArticleDAO {

	public List<Article> SelectAllArticles() throws DALException;
	
	public List<Categorie> SelectAllCategories() throws DALException;
}
