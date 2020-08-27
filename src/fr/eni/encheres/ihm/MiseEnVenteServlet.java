package fr.eni.encheres.ihm;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.ihm.model.ConnexionForm;

/**
 * Servlet implementation class MiseEnVenteServlet
 */
@WebServlet("/MiseEnVente")
public class MiseEnVenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleManager mger = new ArticleManager();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String pseudoDeLutilisateur = (String) session.getAttribute("pseudo");
		int idProfilLutilisateur = (int) session.getAttribute("id");
		request.getRequestDispatcher("/WEB-INF/pages/VenteArticle.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		
		String erreur = null;
		
		int idProfilLutilisateur = (int) session.getAttribute("id");
		
		//récupération des saisies 
		String nomArticle = request.getParameter("nomArticle").trim();
		String description = request.getParameter("description").trim();
		int categorie = Integer.parseInt(request.getParameter("categorie").trim());
		
		String miseAprix = request.getParameter("miseAprix").trim();
		String debutEnchere = request.getParameter("debutEnchere").trim();
		String finEnchere = request.getParameter("finEnchere").trim();
		//retrait à faire avec la bdd
		String rue = request.getParameter("rue").trim();
		String codePostal = request.getParameter("codePostal").trim();
		String ville = request.getParameter("ville").trim();
		Retrait retrait= new Retrait(rue, codePostal, ville);
		retrait.setRue(rue);
		retrait.setCodePostal(codePostal);
		retrait.setVille(ville);
		System.out.println("retrait :" + retrait);
		try {

			//vérification de la saisie
			ConnexionForm.validateInput(nomArticle, erreur );
			//erreur 
			ConnexionForm.validateInput(description, erreur );
			ConnexionForm.validateInput(miseAprix, erreur );
			ConnexionForm.validateInput(debutEnchere, erreur );
			ConnexionForm.validateInput(finEnchere, erreur );
			//retrait
			ConnexionForm.validateInput(rue, erreur );
			ConnexionForm.validateInput(codePostal, erreur );
			ConnexionForm.validateInput(ville, erreur );
			
			//formatage de date et de la mise à prix pour la requête sql
			DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			LocalDate dateDebut = LocalDate.parse(debutEnchere, format);
			LocalDate dateFin = LocalDate.parse(finEnchere, format);
			int miseaPrix = Integer.parseInt(miseAprix);
			
			//attributs session
			session.setAttribute("nomArticle", nomArticle);
			System.out.println(nomArticle);
			session.setAttribute("description", description);
			session.setAttribute("miseaPrix", miseaPrix);
			session.setAttribute("debutEnchere", debutEnchere);
			session.setAttribute("finEnchere", finEnchere);
			session.setAttribute("rue", rue);
			session.setAttribute("codePostal", codePostal);
			session.setAttribute("ville", ville);
			
			 mger.insertArticle(
					new Article(nomArticle, description, dateDebut , dateFin, miseaPrix), idProfilLutilisateur, categorie);
		} catch (Exception e) {
			session.setAttribute("erreur", "erreur de saisie");
		}
		
		request.getRequestDispatcher("/WEB-INF/pages/VenteArticle.jsp").forward(request, response);
	}
}
