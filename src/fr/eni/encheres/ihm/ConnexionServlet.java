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
import fr.eni.encheres.ihm.model.ConnexionForm;


/**
 * Servlet implementation class ConnexionServlet
 */
@WebServlet("/Connexion")
public class ConnexionServlet extends HttpServlet {
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
		String id = request.getParameter("id");
		
		//reset à zéro si pas de session ouverte
		if( pseudo == null) pseudo = "";
		if( motDePasse == null) motDePasse = "";
		//contrôle de la session 
		HttpSession session = request.getSession( true );
		session.setAttribute("pseudo", pseudo);
		session.setAttribute("motDePasse", motDePasse);
		session.setAttribute("id", id);

		this.getServletContext().getRequestDispatcher("/WEB-INF/pages/Connexion.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//création de la session
			HttpSession session = request.getSession();
		try {
			//récupération de la liste des utilisateurs en bdd et de la saisie des inputs sur la page de connexion
			List<Utilisateur> listeDutilisateur = mgr.getAllUtilisateur();
			
			String erreur = "t'as fait une faute d'orthographe";
			String pseudo = ConnexionForm.validateInput(request.getParameter("pseudo").trim(), erreur ) ;
			String mdp = request.getParameter("mdp").trim();

			
			//vérification du mot de passe
			ConnexionForm.regStringValeur( mdp, "mdp");
			
			//vérif de la saisie utilisateur si pseudo est un mail ou un pseudo
			//e t filtre la saisie pour la stocker dans le pseudo
			if (pseudo.matches(EMAIL_PATTERN)) {
			String email = null;
			 email = (String) request.getParameter("pseudo");
			}
		
			String  mdp1 = ConnexionForm.hashMdp(mdp);

			//filtre de recherche si pseudo ou si email existe dans la bdd et si ceux ci-correspondent au mot de passe enregistré en bdd
			Utilisateur utilisateurConfirmeBDD = 
				listeDutilisateur.stream().filter(
			u -> (u.getPseudo().contains(pseudo) || u.getEmail().contains(pseudo))  && u.getMotDePasse().contains(mdp1)) 
			       .findFirst().orElse(null);
			
			if (utilisateurConfirmeBDD != null) {
				int id= utilisateurConfirmeBDD.getId();
				System.out.println("connecté");
				session.setAttribute("pseudo", pseudo);
				session.setAttribute("id",id);
				session.setAttribute("estConnecte", true);
				response.sendRedirect("ListeEncheres");
				
			} else {
				
				session.setAttribute("estConnecte", false);
				request.setAttribute("erreur", "t'as fait une faute d'orthographe, man'");
				request.getRequestDispatcher("/WEB-INF/pages/Connexion.jsp").forward(request, response);
			}
		} catch (BLLException e) {
			e.printStackTrace();
		}
	}
}



