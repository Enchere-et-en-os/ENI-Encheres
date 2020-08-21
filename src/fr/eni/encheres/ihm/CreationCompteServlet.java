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

import org.apache.jasper.tagplugins.jstl.core.ForEach;

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
	
	static final String REGEXGENERAL = "^[\\w]{3,}$";
	static final String REGEXEMAIL = "^[\\w.-]+@[\\w.-]+\\.[a-z]{2,}$";
	static final String REGEXTEL = "^[0-9]{10}$";
	static final String REGEXPOST = "^[0-9]{5}$";

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
		String pseudo = request.getParameter("pseudo").trim();
		
//		if (!pseudo.matches(REGEXGENERAL)) {
//			String messagePseudo = "Le Pseudonyme doit contenir uniquement des caractères alphanumériques";
//			request.setAttribute("erreurPseudo", messagePseudo);
//			pseudo = "";
//			erreur = true;
//		}
		request.setAttribute("erreurPseudo", ConnexionForm.regStringValeur(pseudo, "pseudo"));
		
		try {
			if (mgr.selectByPseudo(pseudo) != null) {
				String messagePseudo = "Le Pseudonyme est déjà pris";
				request.setAttribute("erreurPseudo", messagePseudo);
				pseudo = "";
				erreur = true;
			}
		} catch (BLLException e) {
			// TODO Faire les logs
			e.printStackTrace();
		}

		String nom = request.getParameter("nom").trim();
		
//		if(!nom.matches( REGEXGENERAL )) {
//		   	String messageNom = "Veuillez entrer un Nom";
//		   	request.setAttribute("erreurNom", messageNom);
//		    nom = "";
//		  	erreur = true;
//		 }
		ConnexionForm.regStringValeur(nom, "nom");
		
		String prenom = request.getParameter("prenom").trim();
		
		if(!prenom.matches( REGEXGENERAL )) {
		   	String messagePrenom = "Veuillez entrer un Prenom";
		   	request.setAttribute("erreurPrenom", messagePrenom);
		    prenom = "";
		  	erreur = true;
		 }
		
		String email = request.getParameter("email").trim();
		
        if(!email.matches( REGEXEMAIL )) {
        	String messageEmail = "Veuillez entrer une adresse mail valide";
        	request.setAttribute("erreurEmail", messageEmail);
        	email = "";
        	erreur = true;
        }
        	
		String telephone = request.getParameter("telephone").trim();
		
		if(!telephone.matches( REGEXTEL )) {
        	String messageTel = "Veuillez entrer un numéro de téléphone valide";
        	request.setAttribute("erreurTel", messageTel);
        	telephone = "";
        	erreur = true;
        }

		String rue = request.getParameter("rue").trim();
		 
		if(!rue.matches( REGEXGENERAL )) {
		   	String messageRue = "Veuillez entrer une Rue";
		   	request.setAttribute("erreurRue", messageRue);
		    rue = "";
		  	erreur = true;
		 }
		
		String codePostal = request.getParameter("codePostal").trim();
		
		if(!codePostal.matches( REGEXPOST )) {
		   	String messagePost = "Veuillez entrer un Code Postal";
		   	request.setAttribute("erreurPost", messagePost);
		    codePostal = "";
		  	erreur = true;
		 }
		
		String ville = request.getParameter("ville").trim();
		
		if(!ville.matches( REGEXGENERAL )) {
		   	String messageVille = "Veuillez entrer une Ville";
		   	request.setAttribute("erreurVille", messageVille);
		    ville = "";
		  	erreur = true;
		 }
		
		String mdp = request.getParameter("mdp").trim();
		String confirmMdp = request.getParameter("confirmMdp").trim();
		
		//TODO regex

		if (!(mdp.contentEquals(confirmMdp))) {

			String messageConfirm = "Le mot de passe et sa confirmation sont différents";
			request.setAttribute("erreurConfirm", messageConfirm);
			erreur = true;
		}

		// TODO hash du mdp puis findallUsers pour comparer avec les autres hash
		String hashMdp= "";
			
		try {
			List<Utilisateur> list = mgr.getAllUtilisateur();
			for (Utilisateur utilisateur : list) {
				if (utilisateur.getMotDePasse() == hashMdp) {
					String messageMdp = "Choissisez un autre mot de passe";
					request.setAttribute("erreurMdp", messageMdp);
					erreur = true;
					break;
				}
			}

		} catch (BLLException e) {
			// TODO Logs Toujours
			e.printStackTrace();
		}

		if (!erreur) {
			Utilisateur newUser = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, mdp);
			try {
				mgr.insertUtilisateur(newUser);
//				HttpSession session = request.getSession();
//				session.setAttribute("pseudo", pseudo);
//				session.setAttribute("nom", pseudo);
//				session.setAttribute("prenom", pseudo);

				response.sendRedirect("/WEB-INF/pages/pageListeEncheresConnecte.jsp");
			} catch (BLLException e) {
				// TODO Faire les logs
				e.printStackTrace();
			}
		} else {
			// on remet les champs valide dans le formulaire + redirection
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
}
