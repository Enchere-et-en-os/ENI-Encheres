package fr.eni.encheres.ihm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bll.ArticleManager;

/**
 * Servlet implementation class PageListeEncheresConnecte
 */
@WebServlet("/ListeEncheres")
public class PageListeEncheresConnecte extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleManager articleManager = new ArticleManager();

   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String identifiantDeLutilisateur = (String) session.getAttribute("pseudo");
		String motDePasseDeLutilisateur = (String) session.getAttribute("motDePasse");
		System.out.println("identifiantDeLutilisateur" + identifiantDeLutilisateur);
		System.out.println("motDePasseDeLutilisateur" + motDePasseDeLutilisateur);
		
//		try {
//			request.setAttribute("listeArticle", articleManager.SelectAllArticles());
//			
//		} catch (BLLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		request.getRequestDispatcher("/WEB-INF/pages/ListeEncheresConnecte.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/ListeEncheresConnecte.jsp").forward(request, response);
	}

}
