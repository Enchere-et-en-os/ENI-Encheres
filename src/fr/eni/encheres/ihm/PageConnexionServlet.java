package fr.eni.encheres.ihm;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



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
