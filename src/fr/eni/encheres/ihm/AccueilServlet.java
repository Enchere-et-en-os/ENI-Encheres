package fr.eni.encheres.ihm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ConnexionServlet
 */
@WebServlet("/Accueil")
public class AccueilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleManager articleManager = new ArticleManager();
	private UtilisateurManager utilisateurManager = new UtilisateurManager();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			List<Article> listeArticle = articleManager.SelectAllArticles();
			Utilisateur u = null;
			String uPseudo = null;
			for (Article article : listeArticle) {
				u = utilisateurManager.selectById(article.getUtilisateurId());
				uPseudo = u.getPseudo();
				article.setUtilisateurPseudo(uPseudo);
			}
			request.setAttribute("listeArticle", listeArticle);
			request.setAttribute("listeCategorie", articleManager.SelectAllCategories());
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("/WEB-INF/pages/Accueil.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String articleRecherche = request.getParameter("barreRechercheArticle");
		int categorieSelectionee = Integer.parseInt(request.getParameter("selectCategorie"));

		List<Article> listeArticle;
		List<Article> listeArticleFiltree = new ArrayList<Article>();

		try {
			request.setAttribute("listeCategorie", articleManager.SelectAllCategories());
			listeArticle = articleManager.SelectAllArticles();

			Utilisateur u = null;
			String uPseudo = null;

			for (Article article : listeArticle) {
				u = utilisateurManager.selectById(article.getUtilisateurId());
				uPseudo = u.getPseudo();
				article.setUtilisateurPseudo(uPseudo);
				boolean estArticleRecherche = false;
				if (articleRecherche.equalsIgnoreCase("")) {

					if (categorieSelectionee == article.getCategorieId()) {

						listeArticleFiltree.add(article);
						request.setAttribute("listeArticle", listeArticleFiltree);

					} else if (categorieSelectionee == -1) {
						listeArticleFiltree.add(article);
						request.setAttribute("listeArticle", listeArticleFiltree);

					}

				} else {
					estArticleRecherche = article.getNom().contains(articleRecherche);
					if (estArticleRecherche
							&& ((categorieSelectionee == article.getCategorieId()) || categorieSelectionee == -1)) {
						listeArticleFiltree.add(article);
						request.setAttribute("listeArticle", listeArticleFiltree);

					}
				}
				request.setAttribute("barreRechercheArticle", articleRecherche);

			}
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.getRequestDispatcher("/WEB-INF/pages/Accueil.jsp").forward(request, response);

	}

}
