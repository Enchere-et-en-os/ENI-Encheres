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
 * Servlet implementation class CreationCompteServlet
 */
@WebServlet("/Inscription")
public class CreationCompteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UtilisateurManager mgr = new UtilisateurManager();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/pages/PageCreerCompte.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean erreur = false;
		String pseudo = request.getParameter("pseudo").trim();
		
		// vérification du pseudo : Unicité + alphanumérique
		String regExpPseudo = "^\\w$";
		if (!pseudo.matches(regExpPseudo)) {
			String messagePseudo = "Le Pseudonyme doit contenir uniquement des caractères alphanumériques";
			request.setAttribute("erreurPseudo", messagePseudo);
			pseudo = "";
			erreur = true;
		}

		try {
			if(mgr.findPseudo(pseudo) > 0) {
				String messagePseudo = "Le Pseudonyme est déjà pris";
				request.setAttribute("erreurPseudo", messagePseudo);
				pseudo = "";
				erreur = true;
			}
		} catch (BLLException e) {
			// TODO Faire les logs
			System.err.println("mgr");
		}
		
		String nom = request.getParameter("nom").trim();
		String prenom = request.getParameter("prenom").trim();
		String email = request.getParameter("email").trim();
		
		// vérification de l'email via expression régulière
		String regExpEmail = "^[\\w.-]+@[\\w.-]+\\.[a-z]{2,}$";
        if(!email.matches( regExpEmail )) {
        	String messageEmail = "Veuillez entrer une adresse mail valide";
        	request.setAttribute("erreurEmail", messageEmail);
        	email = "";
        	erreur = true;
        }
        	
		String telephone = request.getParameter("telephone").trim();
		String rue = request.getParameter("rue").trim();
		String codePostal = request.getParameter("codePostal").trim();
		String ville = request.getParameter("ville").trim();
		String mdp = request.getParameter("mdp").trim();
		String confirmMdp = request.getParameter("confirmMdp").trim();
		
		if (mdp != confirmMdp) {
			String messageConfirm = "Le mot de passe et sa confirmation sont différentes";
			request.setAttribute("erreurConfirm", messageConfirm);
			erreur = true;
		}
		
		// hashage du mdp puis findallUsers pour comparer avec les autres hash
		else {
			String hashMdp = "";
			List<Utilisateur> list = mgr.fi
		}
		
		
		if(!erreur) {
			Utilisateur newUser = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, mdp);
			try {
				mgr.insertUtilisateur(newUser);
				// TODO rediriger vers liste enchères
				request.getRequestDispatcher("/WEB-INF/pages/PageCreerCompte.jsp").forward(request, response);
			} catch (BLLException e) {
				// TODO Faire les logs
				System.err.println("mgr");
			}
		} else // on remet les champs valide dans le formulaire + redirection 
			request.setAttribute("pseudo", pseudo);
			request.setAttribute("prenom", prenom);
			request.setAttribute("nom", nom);
			request.setAttribute("email", email);
			request.setAttribute("telephone", telephone);
			request.setAttribute("rue", rue);
			request.setAttribute("codePostal", codePostal);
			request.setAttribute("ville", ville);
			request.getRequestDispatcher("/WEB-INF/pages/PageCreerCompte.jsp").forward(request, response);		
	}

}
