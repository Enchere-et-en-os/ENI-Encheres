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


@WebServlet("/Inscription")
public class CreationCompteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UtilisateurManager mgr = new UtilisateurManager();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/CreerCompte.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		
		if (request.getParameter("annuler") != null) {
			response.sendRedirect("Accueil");
		}
		if(request.getParameter("envoyer") != null) {
			boolean erreur = false;
			ArrayList<String> entries = new ArrayList<String>();
			
			entries.add("pseudo");
			entries.add("nom");
			entries.add("prenom");
			entries.add("email");
			entries.add("telephone");
			entries.add("rue");
			entries.add("codePostal");
			entries.add("ville");
			entries.add("mdp");
			entries.add("confirmMdp");
					
			List<String> paramUser = ConnexionForm.checkForm(request, entries);
			paramUser.add(0, "1");
			System.out.println(paramUser);
			
			// Vérifie si une erreur à été générée
			for (String entry : entries) {
				String erreurString = "erreur" + entry.substring(0, 1).toUpperCase() + entry.substring(1);
				// TODO savoir si l'attribut erreurString retourne un objet non null
				String n = (String) request.getAttribute(erreurString);
				if(n != null){
					System.out.println(request.getAttribute(erreurString));
					erreur = true;
				}
			}
			
			if (!erreur) {
				System.out.println(paramUser.get(paramUser.size() - 1).length());
				Utilisateur newUser = new Utilisateur(paramUser);
				try {
					mgr.insertUtilisateur(newUser);
					// Création de la session
					HttpSession session = request.getSession( true );
					session.setAttribute("pseudo", newUser.getPseudo());
					request.getRequestDispatcher("/WEB-INF/pages/Accueil.jsp").forward(request, response);
				} catch (BLLException e) {
					// TODO Faire les logs
					e.printStackTrace();
				}
			} else {
				// on remet les champs valide dans le formulaire + redirection
				// TODO mÃ©thode
				request.setAttribute("pseudo", request.getParameter("pseudo"));
				request.setAttribute("prenom", request.getParameter("prenom"));
				request.setAttribute("nom", request.getParameter("nom"));
				request.setAttribute("email", request.getParameter("email"));
				request.setAttribute("telephone", request.getParameter("telephone"));
				request.setAttribute("rue", request.getParameter("rue"));
				request.setAttribute("codePostal", request.getParameter("codePostal"));
				request.setAttribute("ville", request.getParameter("ville"));
				request.getRequestDispatcher("/WEB-INF/pages/CreerCompte.jsp").forward(request, response);
			}
		}
		

	}
}
