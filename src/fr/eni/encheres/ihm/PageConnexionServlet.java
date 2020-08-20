package fr.eni.encheres.ihm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.UtilisateurDAO;


/**
 * Servlet implementation class PageConnexionServlet
 */
@WebServlet("/Connexion")
public class PageConnexionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    private UtilisateurDAO utilisateurDao;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PageConnexionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//récupérer une session
				HttpSession session = request.getSession();
				String identifiantDutilisateur = (String) session.getAttribute("identifiant");
				
				//supprimer la session : déconnexion
				//session.invalidate();
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/pages/pageConnexion.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destPage = "pageConnexion.jsp";
		try {
			String identifiant = request.getParameter("identifiant");
			String motDePasse = request.getParameter("motDePasse");
			String pseudo = request.getParameter("pseudo");
			//créer une règle pour vérifier si pseudo ou email rentré
			List<Utilisateur> listeDutilisateur = utilisateurDao.findAllUtilisateur();
			
		//	Utilisateur utilisateur = utilisateurDao.checkLogin(identifiant, motDePasse, pseudo);
			
			destPage = "pageConnexion.jsp";
			
			//mets en mémoire dans la partie mémoire session le nom et le prénom
			//if (utilisateur != null) {
				
				HttpSession session = request.getSession();
				session.setAttribute("identifiant", identifiant);
				session.setAttribute("motDePasse", motDePasse);
				session.setAttribute("pseudo", pseudo);
				session.setAttribute("listeDutilisateur", listeDutilisateur);
				//session.setAttribute("utilisateur", utilisateur);
				
				destPage = "Acceuil.jsp";
				//récupération de l'identifiant et du mot de passe
				String identifiantDeLutilisateur = (String) session.getAttribute("identifiant");
				String motDePasseDeLutilisateur = (String) session.getAttribute("motDePasse");
//			} else {
//				 String message = "Email / mot de passe non conforme";
//				request.setAttribute("message", message);
//			}
		} catch (DALException e) {
			e.printStackTrace();
		}
	
		this.getServletContext().getRequestDispatcher(destPage).forward(request, response);

		
//		String identifiant = request.getParameter("identifiantInput");
//		String motDePasse = request.getParameter("motDePasseInput");
//		
//		request.setAttribute("identifiant", identifiant);
//		request.setAttribute("motDePasse", motDePasse);
		
		HttpSession session = request.getSession();
		
		String identifiant = request.getParameter("identifiantInput");
		String motDePasse = request.getParameter("motDePasseInput");
		
		session.setAttribute("identifiant", identifiant);
		session.setAttribute("motDePasse", motDePasse);
		session.getAttribute(identifiant);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/pages/pageConnexion.jsp").forward(request, response);

	}

}
