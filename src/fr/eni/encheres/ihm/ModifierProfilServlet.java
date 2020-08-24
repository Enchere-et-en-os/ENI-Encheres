package fr.eni.encheres.ihm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
import fr.eni.encheres.ihm.model.IConsulterPoints;
import fr.eni.encheres.ihm.model.ISupprimerCompte;

/**
 * Servlet implementation class ModifierProfilServlet
 */
@WebServlet("/ModificationProfil")
public class ModifierProfilServlet extends HttpServlet implements ISupprimerCompte, IConsulterPoints {
	private static final long serialVersionUID = 1L;

	UtilisateurManager mgr = new UtilisateurManager();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		HttpSession session = request.getSession(true);
		
		// TODO Méthode
		request.setAttribute("pseudo", session.getAttribute("pseudo"));
		request.setAttribute("prenom", session.getAttribute("prenom"));
		request.setAttribute("nom", session.getAttribute("nom"));
		request.setAttribute("email", session.getAttribute("email"));
		request.setAttribute("telephone", session.getAttribute("telephone"));
		request.setAttribute("rue", session.getAttribute("rue"));
		request.setAttribute("codePostal", session.getAttribute("codePostal"));
		request.setAttribute("ville", session.getAttribute("ville"));

		request.getRequestDispatcher("/WEB-INF/pages/ModifierProfil.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		if (request.getParameter("annuler") != null) {
			request.getRequestDispatcher("/WEB-INF/pages/Profil.jsp").forward(request, response);
		}
		if (request.getParameter("enregistrer") != null) {

			// TODO v�rifier les donn�es de la requ�te (factorisation Inscription ?)
			String id = (String) session.getAttribute("id");

//			String pseudo = request.getParameter("pseudo").trim();
//			request.setAttribute("erreurPseudo", ConnexionForm.regStringValeur(pseudo, "pseudo"));

//			String nom = request.getParameter("nom");
//			String prenom = request.getParameter("prenom");
//			String email = request.getParameter("email");
//			String telephone = request.getParameter("telephone");
//			String rue = request.getParameter("rue");
//			String codePostal = request.getParameter("codePostal");
//			String ville = request.getParameter("ville");
//			String nouveauMdp = request.getParameter("nouveauMdp");
			ArrayList<String> entries = new ArrayList<String>();
			
			entries.add("pseudo");
			entries.add("nom");
			entries.add("prenom");
			entries.add("telephone");
			entries.add("email");
			entries.add("rue");
			entries.add("codePostal");
			entries.add("ville");
			entries.add("mdp");
			entries.add("confirmMdp");
			System.out.println(entries.get(0));
			System.out.println(entries.get(entries.size() - 1));
			
			List<String> paramUser = ConnexionForm.checkForm(request, entries);
			paramUser.add(0, id);

			Utilisateur user = new Utilisateur(paramUser);
			try {
				mgr.updateById(user);
				request.setAttribute("messageUpdate", "Les modifications ont bien été enregistrées");
			} catch (BLLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getRequestDispatcher("/WEB-INF/pages/Profil.jsp").forward(request, response);
		}
		if (request.getParameter("supprimer") != null) {
			try {
				mgr.deleteById((Integer) session.getAttribute("id"));
			} catch (BLLException e) {
				e.printStackTrace();
			}
			request.getRequestDispatcher("/WEB-INF/pages/Accueil.jsp").forward(request, response);
		}
	}

}
