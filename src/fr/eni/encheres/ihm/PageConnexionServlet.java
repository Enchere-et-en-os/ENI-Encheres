package fr.eni.encheres.ihm;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class PageConnexionServlet
 */
@WebServlet("/Connexion")
public class PageConnexionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/pages/pageConnexion.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//Session ?
		
//		String identifiant = request.getParameter("identifiantInput");
//		String motDePasse = request.getParameter("motDePasseInput");
//		
//		request.setAttribute("identifiant", identifiant);
//		request.setAttribute("motDePasse", motDePasse);

		
		
		// Récupération des paramètres de la requête.
		String identifiant = request.getParameter("identifiantInput");
		String motDePasse = request.getParameter("motDePasseInput");
		
		// Soumission des paramètres de la requête à la couche service et récupération du résultat.
		//Utilisateur utilisateur = new Utilisateur(identifiant, motDePasse);
		HttpSession session = request.getSession();
		
		session.setAttribute("identifiant", identifiant);//samsam
		session.setAttribute("motDePasse", motDePasse);//password
		
		//session.setAttribute("utilisateur", utilisateur);
		
		identifiant = (String) session.getAttribute("identifiant");
		String motDePasseS = (String) session.getAttribute("motDePasse");
		
		
		
		System.out.println(identifiant + " " + motDePasse);
	
		// Réponse à l'utilisateur
		this.getServletContext().getRequestDispatcher("/WEB-INF/pages/pageConnexion.jsp").forward(request, response);

		
//		
//		session.setAttribute("identifiant", identifiant);
//		session.setAttribute("motDePasse", motDePasse);
//		
//		String identifiantSession = (String) session.getAttribute(identifiant);
//		String motDePasseSession = (String) session.getAttribute(motDePasse);
//		
//		System.out.println(identifiantSession);
//		System.out.println(motDePasseSession);


			
		
	}

}
