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


import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Utilisateur;


/**
 * Servlet implementation class PageListeEncheresConnecte
 */
@WebServlet("/ListeEncheres")
public class PageListeEncheresConnecte extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleManager articleManager = new ArticleManager();
	private UtilisateurManager utilisateurManager = new UtilisateurManager();

   //selectALL Utilisateur

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//HttpSession session = request.getSession();
		Categorie cat = new Categorie(2,"Véhciule");
		Utilisateur jean = new Utilisateur(21, "jeanJean", "jean", "dupont", "jean@dupont.com", "0123456789", "rue", "44000", "Nantes", "123456");
		
		
		try {
			request.setAttribute("listeCategorie", articleManager.SelectAllCategories());
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			request.setAttribute("listeArticle", articleManager.SelectAllArticlesAvecUtilisateurEtCategorie(jean.getId(), cat.getId()));
			
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("/WEB-INF/pages/ListeEncheresConnecte.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<Article> listeArticle = new ArrayList<Article>();
		String pseudo = (String) session.getAttribute("pseudo");
		Utilisateur utilisateur = null;
		boolean boxChecked = false;


		List<String> listParam = new ArrayList<String>();
		String radio = request.getParameter("radio");
		String categorieSelectionee = request.getParameter("selectCategorie");
		
		if(radio.contentEquals("achat")) {
			String enchereOuverte = request.getParameter("enchereOuverte");
			String enchereUtilisateur = request.getParameter("enchereUtilisateur");
			String enchereGagne = request.getParameter("enchereGagne");
			System.out.println(enchereUtilisateur);
			
			// ajoute la liste des encheres de l'utilisateur
			if(enchereUtilisateur != null){
				boxChecked = true;
				System.out.println("utilisateur");
				// ajoute toutes les encheres
				if(!(enchereOuverte.isEmpty())){
					System.out.println("ouverte");
					try {
						// selectionne les articles en cours
						listeArticle = articleManager.SelectAllArticles();
					} catch (BLLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} 
				else {
					try {
						utilisateur = utilisateurManager.selectByPseudo(pseudo);
						//listeArticle = articleManager.
					} catch (BLLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
			
			if(enchereOuverte != null){
				boxChecked = true;
				System.out.println("ouverte");
				try {
					utilisateur = utilisateurManager.selectByPseudo(pseudo);
					//listeArticle = articleManager.
				} catch (BLLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
			
			// ajoute la liste des encheres gagnés
			if(enchereGagne != null) {
				boxChecked = true;
				try {
					utilisateur = utilisateurManager.selectByPseudo(pseudo);
				} catch (BLLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(!boxChecked) {
				request.getRequestDispatcher("/WEB-INF/pages/ListeEncheresConnecte.jsp").forward(request, response);
			}
			
			listParam.add(categorieSelectionee);
			listParam.add(enchereOuverte);
			listParam.add(enchereUtilisateur);
			listParam.add(enchereGagne);
			
			//articleManager.
			
			request.getRequestDispatcher("/WEB-INF/pages/ListeEncheresConnecte.jsp").forward(request, response);
		}
		else if(radio.contentEquals("vente")) {

			String venteEnCours = request.getParameter("venteEnCours");
			String venteNonDebut = request.getParameter("venteNonDebut");
			String venteTermine = request.getParameter("venteTermine");
			
			listParam.add(categorieSelectionee);
			listParam.add(venteEnCours);
			listParam.add(venteNonDebut);
			listParam.add(venteTermine);
			
			request.getRequestDispatcher("/WEB-INF/pages/ListeEncheresConnecte.jsp").forward(request, response);
		}


		
		System.out.println(listParam);
		
		//filtre de recherche si pseudo ou si email existe dans la bdd et si ceux ci-correspondent au mot de passe enregistré en bdd
//		Article utilisateurConfirmeBDD = 
//			listeArticle.stream().filter(u -> (u.getPseudo().contains(pseudo) || u.getEmail().contains(pseudo)) && u.getMotDePasse().contains(motDePasse))
//		       .findFirst().orElse(null);
		
		
	}

}
