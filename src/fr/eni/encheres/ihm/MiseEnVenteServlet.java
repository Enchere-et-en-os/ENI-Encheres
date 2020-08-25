package fr.eni.encheres.ihm;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bo.Article;

/**
 * Servlet implementation class MiseEnVenteServlet
 */
@WebServlet("/MiseEnVente")
public class MiseEnVenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String REGEX = "^[-\\w\\s]+$";
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Article article = null;
		HttpSession session = request.getSession();
		String identifiantDeLutilisateur = (String) session.getAttribute("pseudo");
		String motDePasseDeLutilisateur = (String) session.getAttribute("motDePasse");
		System.out.println("identifiantDeLutilisateur" + identifiantDeLutilisateur);
		System.out.println("motDePasseDeLutilisateur" + motDePasseDeLutilisateur);
		request.getRequestDispatcher("/WEB-INF/pages/VenteArticle.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//récupération des saisies 
		String article = request.getParameter("article");
		String description = request.getParameter("description");
		String miseAprix = request.getParameter("miseAprix");
		String debutEnchere = request.getParameter("debutEnchere");
		String finEnchere = request.getParameter("finEnchere");
		//retrait à faire avec la bdd
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("codePostal");
		String ville = request.getParameter("ville");
		
		testString(article);
		
		request.getRequestDispatcher("/WEB-INF/pages/VenteArticle.jsp").forward(request, response);
	}
	public static String testString(String str) {
	
		return (str.matches(REGEX)) ? str :"erreur";
	}
}
