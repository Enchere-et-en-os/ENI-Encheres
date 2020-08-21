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
import fr.eni.encheres.ihm.model.IConsulterPoints;
import fr.eni.encheres.ihm.model.ISupprimerCompte;

/**
 * Servlet implementation class ModifierProfilServlet
 */
@WebServlet("/ModificationProfil")
public class ModifierProfilServlet extends HttpServlet implements ISupprimerCompte, IConsulterPoints{
	private static final long serialVersionUID = 1L;
	
	UtilisateurManager mgr = new UtilisateurManager();
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		request.setAttribute("pseudo", session.getAttribute("pseudo"));
		request.setAttribute("prenom", session.getAttribute("prenom"));
		request.setAttribute("nom", session.getAttribute("nom"));
		request.setAttribute("email", session.getAttribute("email"));
		request.setAttribute("telephone", session.getAttribute("telephone"));
		request.setAttribute("rue", session.getAttribute("rue"));
		request.setAttribute("codePostal", session.getAttribute("codePostal"));
		request.setAttribute("ville", session.getAttribute("ville"));
		
		request.getRequestDispatcher("/WEB-INF/pages/pageModifierProfil.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		if(request.getParameter("annuler") != null) {
			request.getRequestDispatcher("/WEB-INF/pages/pageProfil.jsp").forward(request, response);
		}
		if(request.getParameter("enregistrer") != null) {
			
			// TODO vérifier les données de la requête (factorisation Inscription ?)
			int id = (Integer) session.getAttribute("id");
			String pseudo = request.getParameter("pseudo");
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String email = request.getParameter("email");
			String telephone = request.getParameter("telephone");
			String rue = request.getParameter("rue");
			String codePostal = request.getParameter("codePostal");
			String ville = request.getParameter("ville");
			String nouveauMdp = request.getParameter("nouveauMdp");
			
			Utilisateur user = new Utilisateur(id, pseudo, nom, prenom, email, telephone, rue, codePostal, ville, nouveauMdp);
			try {
				mgr.updateById(user);
			} catch (BLLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getRequestDispatcher("/WEB-INF/pages/pageProfil.jsp").forward(request, response);
		}
		if(request.getParameter("supprimer") != null) {
			request.getRequestDispatcher("/WEB-INF/pages/Accueil.jsp").forward(request, response);
		}
	}

}
