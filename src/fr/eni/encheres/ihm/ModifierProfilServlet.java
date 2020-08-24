package fr.eni.encheres.ihm;

import java.io.IOException;
import java.util.ArrayList;
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
		Utilisateur utilDemande = null;
		
		try {
			utilDemande = mgr.selectByPseudo((String) session.getAttribute("pseudo"));
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO MÃ©thode
		request.setAttribute("pseudo", utilDemande.getPseudo());
		request.setAttribute("prenom", utilDemande.getPrenom());
		request.setAttribute("nom", utilDemande.getNom());
		request.setAttribute("email", utilDemande.getEmail());
		request.setAttribute("telephone", utilDemande.getTelephone());
		request.setAttribute("rue", utilDemande.getRue());
		request.setAttribute("codePostal", utilDemande.getCodePostal());
		request.setAttribute("ville", utilDemande.getVille());
		
		request.getRequestDispatcher("/WEB-INF/pages/ModifierProfil.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		if (request.getParameter("annuler") != null) {
			request.getRequestDispatcher("/WEB-INF/pages/Profil.jsp").forward(request, response);
		}
		if (request.getParameter("enregistrer") != null) {

			// TODO vérifier les données de la requête (factorisation Inscription ?)
			String id = (String) session.getAttribute("id");

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
				request.setAttribute("messageUpdate", "Les modifications ont bien Ã©tÃ© enregistrÃ©es");
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
