package fr.eni.encheres.ihm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Utilisateur;

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
		Categorie cat = new Categorie(2,"Véhciule");
		Utilisateur jean = new Utilisateur(21, "jeanJean", "jean", "dupont", "jean@dupont.com", "0123456789", "rue", "44000", "Nantes", "123456");

		try {
			request.setAttribute("listeArticle", articleManager.SelectAllArticlesAvecUtilisateurEtCategorie(jean.getId(), cat.getId()));
			
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("/WEB-INF/pages/ListeEncheresConnecte.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/ListeEncheresConnecte.jsp").forward(request, response);
	}

}
