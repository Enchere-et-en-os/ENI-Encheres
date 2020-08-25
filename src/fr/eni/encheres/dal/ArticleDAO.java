package fr.eni.encheres.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Utilisateur;

public interface ArticleDAO {

	public List<Article> SelectAllArticlesAvecUtilisateurEtCategorie(int utilisateurId, int categorieId) throws DALException;
	
	public Article insertArticle (Utilisateur utilisateur, Categorie categorie, Article article) throws SQLException, DALException;
	
	public List<Categorie> SelectAllCategories() throws DALException;
	

	public List<Article> selectAllArticles() throws DALException;

}
