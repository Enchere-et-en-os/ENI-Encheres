package fr.eni.encheres.ihm;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.ihm.model.ConnexionForm;

/**
 * Servlet implementation class MiseEnVenteServlet
 */
@WebServlet("/MiseEnVente")
public class MiseEnVenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String REGEX = "^[-\\w\\s]+$";
	private ArticleManager mger = new ArticleManager();
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
		String erreur = null;
		Utilisateur u = null ;
		Categorie c = null;

		int utilisateurId = u.getId();
		int categorieId = c.getId();
		
		try {
			//récupération des saisies 
			String nomArticle = request.getParameter("nomArticle");
			String description = request.getParameter("description");
			String categorie = request.getParameter("categorie");
			System.out.println("categorie :" + categorie);
			String miseAprix = request.getParameter("miseAprix");
			String debutEnchere = request.getParameter("debutEnchere");
			String finEnchere = request.getParameter("finEnchere");
			//retrait à faire avec la bdd
			String rue = request.getParameter("rue");
			String codePostal = request.getParameter("codePostal");
			String ville = request.getParameter("ville");
			
			//vérification de la saisie
			ConnexionForm.validateInput(nomArticle, erreur );
			ConnexionForm.validateInput(description, erreur );
			ConnexionForm.validateInput(miseAprix, erreur );
			ConnexionForm.validateInput(debutEnchere, erreur );
			ConnexionForm.validateInput(finEnchere, erreur );
			ConnexionForm.validateInput(rue, erreur );
			ConnexionForm.validateInput(codePostal, erreur );
			ConnexionForm.validateInput(ville, erreur );
			
			//attributs session
			session.setAttribute("nomArticle", nomArticle);
			session.setAttribute("description", description);
			session.setAttribute("miseAprix", miseAprix);
			session.setAttribute("debutEnchere", debutEnchere);
			session.setAttribute("rue", rue);
			session.setAttribute("codePostal", codePostal);
			session.setAttribute("ville", ville);
			System.out.println("ville :" + ville);
			
			System.out.println("erreur" + erreur);
			System.out.println("nomArticle :" + nomArticle);
			System.out.println("ville :" + ville);
			
			Article article = mger.insertArticle(utilisateurId, categorieId, 
					new Article(nomArticle, description, miseAprix, debutEnchere, rue, codePostal, ville));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		request.getRequestDispatcher("/WEB-INF/pages/VenteArticle.jsp").forward(request, response);
	}
//	public static String testString(String str) {
//	
//		return (str.matches(REGEX)) ? str :"erreur";
//	}
}
