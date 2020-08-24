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

import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.ihm.model.ConnexionForm;

/**
 * Servlet implementation class CreationCompteServlet
 */
@WebServlet("/Inscription")
public class CreationCompteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UtilisateurManager mgr = new UtilisateurManager();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/PageCreerCompte.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean erreur = false;

//		String pseudo = request.getParameter("pseudo").trim();
//		request.setAttribute("erreurPseudo", ConnexionForm.regStringValeur(pseudo, "pseudo"));

		// TODO Savoir si un message d'erreur Ã  Ã©tÃ© levÃ©e (Constante ?)
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

//		String nom = request.getParameter("nom").trim();
//		request.setAttribute("erreurNom", ConnexionForm.regStringValeur(nom, "nom"));
//
//		String prenom = request.getParameter("prenom").trim();
//		request.setAttribute("erreurPrenom", ConnexionForm.regStringValeur(prenom, "prenom"));
//
//		String email = request.getParameter("email").trim();
//		request.setAttribute("erreurEmail", ConnexionForm.regStringValeur(email, "email"));
//
//		String telephone = request.getParameter("telephone").trim();
//		if (!telephone.isEmpty())
//			request.setAttribute("erreurTel", ConnexionForm.regStringValeur(telephone, "telephone"));
//
//		String rue = request.getParameter("rue").trim();
//		request.setAttribute("erreurRue", ConnexionForm.regStringValeur(rue, "rue"));
//
//		String codePostal = request.getParameter("codePostal").trim();
//		request.setAttribute("erreurCodePostal", ConnexionForm.regStringValeur(codePostal, "codePostal"));
//
//		String ville = request.getParameter("ville").trim();
//		request.setAttribute("erreurVille", ConnexionForm.regStringValeur(ville, "ville"));
//
//		String mdp = request.getParameter("mdp").trim();
//		String confirmMdp = request.getParameter("confirmMdp").trim();

		// TODO regex spÃ©cifique au Mdp (nbr de char/ chiffre...)

//		if (!(mdp.contentEquals(confirmMdp))) {
//
//			String messageConfirm = "Le mot de passe et sa confirmation sont diffï¿½rents";
//			request.setAttribute("erreurConfirm", messageConfirm);
//			erreur = true;
//		}

		// TODO hash du mdp puis findallUsers pour comparer avec les autres hash
//		String hashMdp = "";
//
//		try {
//			List<Utilisateur> list = mgr.getAllUtilisateur();
//			for (Utilisateur utilisateur : list) {
//				if (utilisateur.getMotDePasse() == hashMdp) {
//					String messageMdp = "Choissisez un autre mot de passe";
//					request.setAttribute("erreurMdp", messageMdp);
//					erreur = true;
//					break;
//				}
//			}
//
//		} catch (BLLException e) {
//			// TODO Logs Toujours
//			e.printStackTrace();
//		}

		
		// Vérifie si une erreur à été générée
		for (String entry : entries) {
			String erreurString = "erreur" + entry.substring(0, 1).toUpperCase() + entry.substring(1);
			if(!(erreurString.isEmpty())) {
				erreur = true;
			}
		}
		
		if (!erreur) {
			System.out.println(paramUser);
			Utilisateur newUser = new Utilisateur(paramUser);
			try {
				mgr.insertUtilisateur(newUser);
				response.sendRedirect("/WEB-INF/pages/pageListeEncheresConnecte.jsp");
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
