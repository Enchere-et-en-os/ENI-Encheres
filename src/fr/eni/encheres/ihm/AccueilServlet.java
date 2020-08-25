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
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
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
		System.out.println("Entrée dans doPost");
		String articleRecherche = request.getParameter("barreRechercheArticle");
		String categorieSelectionee = request.getParameter("selectCategorie");
		
		List<Article> listeArticle;
		List<Article> listeArticleFiltree = new ArrayList<Article>();
		
		System.out.println("Avant Try");

		try {
			
			System.out.println("Dans Try");
			listeArticle = articleManager.SelectAllArticles();
			
				for (Article article: listeArticle) {
					System.out.println("Dans for et avant switch");
					System.out.println(article.getCategorie());
					switch (categorieSelectionee) {
				case "1": 
						  	System.out.println("après case 1");
				            if (article.getCategorie().getId() == 1) {
				            	System.out.println("dans le if");
				            	listeArticleFiltree.add(article);
				            	request.setAttribute("listeArticle", listeArticleFiltree );
				            }
				
					break;
					
					
				}
			}
			
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Avant Request Dispatcher");
		request.getRequestDispatcher("/WEB-INF/pages/Accueil.jsp").forward(request, response);

	}

}
