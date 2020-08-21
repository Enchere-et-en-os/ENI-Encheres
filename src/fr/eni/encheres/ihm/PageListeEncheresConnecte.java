package fr.eni.encheres.ihm;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bll.BLLException;

/**
 * Servlet implementation class PageListeEncheresConnecte
 */
@WebServlet("/PageListeEncheresConnecte")
public class PageListeEncheresConnecte extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleManager articleManager = new ArticleManager();

   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		try {
			request.setAttribute("listeArticle", articleManager.SelectAllArticles());
			
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/pages/pageListeEncheresConnecte.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
