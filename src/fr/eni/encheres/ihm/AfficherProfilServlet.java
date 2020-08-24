package fr.eni.encheres.ihm;

import java.io.IOException;
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
 * Servlet implementation class AfficherProfilServlet
 */
@WebServlet("/Profil")
public class AfficherProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		/*
		 * @Author : Valentin
		 * 
		 * Vérifie si le parametre pseudo désigne un utilisateur existant
		 * Si il n'existe pas, afficher la page de son profil
		 * Sinon page 404 
		 */
		UtilisateurManager mgr = new UtilisateurManager();

		// TODO recupérer le nom de l'utilisateur choisit
		Utilisateur util;
		int memeProfil = 0;

		HttpSession session = request.getSession();
		
		try {
			util = mgr.selectByPseudo(request.getParameter("pseudo"));
			if(util == null){
				if(session == null) {
					// TODO redirection page 404
					response.sendRedirect("erreur404");
				} else {
					request.setAttribute("pseudo", session.getAttribute("pseudo"));
					request.setAttribute("nom", session.getAttribute("nom"));
					request.setAttribute("prenom", session.getAttribute("prenom"));
					request.setAttribute("email", session.getAttribute("email"));
					request.setAttribute("telephone", session.getAttribute("telephone"));
					request.setAttribute("rue", session.getAttribute("rue"));
					request.setAttribute("codePostal", session.getAttribute("codePostal"));
					request.setAttribute("ville", session.getAttribute("ville"));
					memeProfil = 1;
				}
			} else {
				request.setAttribute("pseudo", util.getPseudo());
				request.setAttribute("nom", util.getNom());
				request.setAttribute("prenom", util.getPrenom());
				request.setAttribute("email", util.getEmail());
				request.setAttribute("telephone", util.getTelephone());
				request.setAttribute("rue", util.getRue());
				request.setAttribute("codePostal", util.getCodePostal());
				request.setAttribute("ville", util.getVille());
			}
			
			
			// Vérifie si le pseudo du profil affiché == psuedo stocké en session
//			if(session.getAttribute("pseudo").equals(util.getPseudo())) {
//				// création d'un bool pour savoir si la jsp doit afficher la bouton modifier
//				profilUtilisateur = true;
//			}

		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.getRequestDispatcher("/WEB-INF/pages/Profil.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/pages/ModifierProfil.jsp").forward(request, response);
	}

}
