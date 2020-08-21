package fr.eni.encheres.ihm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.UtilisateurDAO;


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
		try {
			//récupération des utilisateurs en bdd
			
			List<Utilisateur> listeDutilisateur = mgr.getAllUtilisateur();
			//récupérer une session
			HttpSession session = request.getSession();
			String identifiantDutilisateur = (String) session.getAttribute("identifiant");
			System.out.println(listeDutilisateur);
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		//supprimer la session : déconnexion
		//session.invalidate();
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/pages/pageConnexion.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			List<Utilisateur> listeDutilisateur = mgr.getAllUtilisateur();
			String pseudo = request.getParameter("pseudo");
			String motDePasse = request.getParameter("motDePasse");
			String email = null;
			
			//vérif de la saisie utilisateur si pseudo est un mail ou un pseudo
			if (pseudo.matches(EMAIL_PATTERN)) {
			 email = (String) request.getParameter("pseudo");
			}
			//filtre de recherche si pseudo ou si email
			Utilisateur utilisateurConfirmeBDD = listeDutilisateur.stream().filter(u -> u.getPseudo().contains(pseudo) || u.getEmail().contains(pseudo))
				       .findFirst().orElse(null);
			
			System.out.println("filtre" + utilisateurConfirmeBDD );

			if (utilisateurConfirmeBDD != null) {
				
				HttpSession session = request.getSession();
				session.setAttribute("motDePasse", motDePasse);
				session.setAttribute("pseudo", pseudo);
				session.setAttribute("listeDutilisateur", listeDutilisateur);
				
				//récupération de l'identifiant et du mot de passe
				String identifiantDeLutilisateur = (String) session.getAttribute("pseudo");
				String motDePasseDeLutilisateur = (String) session.getAttribute("motDePasse");
				System.out.println("identifiantDeLutilisateur : " + identifiantDeLutilisateur);
				System.out.println("motDePasseDeLutilisateur : " + motDePasseDeLutilisateur);
				response.sendRedirect("/WEB-INF/pages/Accueil.jsp" );
			} else {
				 String message = "Email / mot de passe non conforme";
				request.setAttribute("message", message);
			}
		} catch (BLLException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("/WEB-INF/pages/pageConnexion.jsp");

		}
	}


