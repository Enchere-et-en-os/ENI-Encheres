package fr.eni.encheres.ihm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bll.BLLException;
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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			request.setAttribute("listeArticle", articleManager.SelectAllArticles());
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

		// !!! DOPOST EN COURS DE CONSTRUCTION !!!

		String articleRecherche = request.getParameter("barreRechercheArticle");
		String categorieSelectionee = request.getParameter("selectCategorie");

		List<Article> listeArticle;
		List<Article> listeArticleFiltree = new ArrayList<Article>();

		try {
			request.setAttribute("listeCategorie", articleManager.SelectAllCategories());

			listeArticle = articleManager.SelectAllArticles();

			if (articleRecherche.equalsIgnoreCase("")) {

				for (Article article : listeArticle) {

					switch (categorieSelectionee) {
					case "1":

						if (article.getCategorieId() == 1) {

							listeArticleFiltree.add(article);
							request.setAttribute("listeArticle", listeArticleFiltree);

						}

						break;
					case "2":

						if (article.getCategorieId() == 2) {

							listeArticleFiltree.add(article);
							request.setAttribute("listeArticle", listeArticleFiltree);
						}

						break;
					case "3":

						if (article.getCategorieId() == 3) {

							listeArticleFiltree.add(article);
							request.setAttribute("listeArticle", listeArticleFiltree);
						}

						break;
					case "4":

						if (article.getCategorieId() == 4) {

							listeArticleFiltree.add(article);
							request.setAttribute("listeArticle", listeArticleFiltree);
						}

						break;

					default:
						request.setAttribute("listeArticle", listeArticle);

					}
				}
			} else {
				for (Article article : listeArticle) {

					boolean estArticleRecherche = false;
					estArticleRecherche = articleRecherche.equalsIgnoreCase(article.getNom());

					if (estArticleRecherche) {

						switch (categorieSelectionee) {
						case "1":

							if (article.getCategorieId() == 1) {

								listeArticleFiltree.add(article);
								request.setAttribute("listeArticle", listeArticleFiltree);

							}

							break;
						case "2":

							if (article.getCategorieId() == 2) {

								listeArticleFiltree.add(article);
								request.setAttribute("listeArticle", listeArticleFiltree);
							}

							break;
						case "3":

							if (article.getCategorieId() == 3) {

								listeArticleFiltree.add(article);
								request.setAttribute("listeArticle", listeArticleFiltree);
							}

							break;
						case "4":

							if (article.getCategorieId() == 4) {

								listeArticleFiltree.add(article);
								request.setAttribute("listeArticle", listeArticleFiltree);
							}

							break;

						default:

							request.setAttribute("listeArticle", listeArticleFiltree);

						}
					}
				}
			}
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.getRequestDispatcher("/WEB-INF/pages/Accueil.jsp").forward(request, response);

	}

}
