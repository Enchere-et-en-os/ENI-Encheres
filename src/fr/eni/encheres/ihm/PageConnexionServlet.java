package fr.eni.encheres.ihm;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Utilisateur;


/**
 * Servlet implementation class PageConnexionServlet
 */
@WebServlet("/Connexion")
public class PageConnexionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String EMAIL_PATTERN =
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	UtilisateurManager mgr = new UtilisateurManager();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String pseudo = request.getParameter("pseudo");
		String motDePasse = request.getParameter("motDePasse");
		//reset à zéro si pas de session ouverte
		if( pseudo == null) pseudo = "";
		if( motDePasse == null) motDePasse = "";
		//contrôle de la session 
		HttpSession session = request.getSession( true );
		session.setAttribute("pseudo", pseudo);
		session.setAttribute("motDePasse", motDePasse);

		this.getServletContext().getRequestDispatcher("/WEB-INF/pages/pageConnexion.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			//récupération de la liste des utilisateurs en bdd et de la saisie des inputs sur la page de connexion
			List<Utilisateur> listeDutilisateur = mgr.getAllUtilisateur();
			String pseudo = request.getParameter("pseudo");
			String motDePasse = request.getParameter("motDePasse");
			String email = null;
			//création de la session
			HttpSession session = request.getSession();
			//vérif de la saisie utilisateur si pseudo est un mail ou un pseudo
			//e t filtre la saisie pour la stocker dans le pseudo
			if (pseudo.matches(EMAIL_PATTERN)) {
			 email = (String) request.getParameter("pseudo");
			}
			
			//filtre de recherche si pseudo ou si email existe dans la bdd et si ceux ci-correspondent au mot de passe enregistré en bdd
			Utilisateur utilisateurConfirmeBDD = 
				listeDutilisateur.stream().filter(u -> (u.getPseudo().contains(pseudo) || u.getEmail().contains(pseudo)) && u.getMotDePasse().contains(motDePasse))
			       .findFirst().orElse(null);
			
			if (utilisateurConfirmeBDD != null) {
				System.out.println("connecté");
				
				session.setAttribute("motDePasse", motDePasse);
				session.setAttribute("pseudo", pseudo);
				session.setAttribute("estConnecte", true);
				//récupération de l'identifiant et du mot de passe
//				String identifiantDeLutilisateur = (String) session.getAttribute("pseudo");
//				String motDePasseDeLutilisateur = (String) session.getAttribute("motDePasse");
//				System.out.println("identifiantDeLutilisateur" + identifiantDeLutilisateur);
//				System.out.println("motDePasseDeLutilisateur" + motDePasseDeLutilisateur);
				request.getRequestDispatcher("/WEB-INF/pages/pageListeEncheresConnecte.jsp").forward(request, response);
				
			} else {
				System.out.println( "pas connescté ou erreur de saisie");
				session.setAttribute("estConnecte", false);
				request.setAttribute("message", "Email / mot de passe non conforme");
				request.getRequestDispatcher("/WEB-INF/pages/pageConnexion.jsp").forward(request, response);
			}
		} catch (BLLException e) {
			e.printStackTrace();
		}
	}
}



