package fr.eni.encheres.ihm;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class AfficherProfilServlet
 */
@WebServlet("/Profil2")
public class AfficherProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UtilisateurManager mgr = new UtilisateurManager();
		
		// TODO recup�rer le nom de l'utilisateur choisit
		String pseudoProfil = request.getParameter("pseudo");
		try {
			Utilisateur util = mgr.selectByPseudo(pseudoProfil);
			request.setAttribute("pseudo", util.getPseudo());
			request.setAttribute("nom", util.getNom());
			request.setAttribute("prenom", util.getPrenom());
			request.setAttribute("email", util.getEmail());
			request.setAttribute("telephone", util.getTelephone());
			request.setAttribute("rue", util.getRue());
			request.setAttribute("codePostal", util.getCodePostal());
			request.setAttribute("ville", util.getVille());
			
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/WEB-INF/pages/pageProfil.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
