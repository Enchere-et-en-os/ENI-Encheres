package fr.eni.encheres.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Categorie;

public interface ArticleDAO {

	public List<Article> SelectAllArticlesAvecUtilisateurEtCategorie(int utilisateurId, int categorieId) throws DALException;
	
	public void insertArticle (Article article, int utilisateurId, int categorieId) throws SQLException, DALException;
	
	public List<Categorie> SelectAllCategories() throws DALException;
	
	public List<Article> selectAllArticles() throws DALException;
	
	public List<Article> selectAllByEtatVente(int etatVente) throws DALException;

	public List<Article> selectAllByEtatVenteUtilisateur(int etatVente, int idUtilisateur) throws DALException;
	
	public List<Article> selectAllByEtatVenteGagne(int etatVente, int idUtilisateur) throws DALException;
	
	public List<Article> SelectAllEncheresByEtat(int idUtilisateur, int etatVente) throws DALException;
}
