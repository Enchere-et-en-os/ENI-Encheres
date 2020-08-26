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
		String pseudo = (String) session.getAttribute("pseudo");
		List<Article> listeArticle = new ArrayList<Article>();
		boolean filtres = false;


		List<String> listParam = new ArrayList<String>();
		String radio = request.getParameter("radio");
		String categorieSelectionee = request.getParameter("selectCategorie");
		
		if(radio.contentEquals("achat")) {
			String enchereOuverte = request.getParameter("enchereOuverte");
			String enchereUtilisateur = request.getParameter("enchereUtilisateur");
			String enchereGagne = request.getParameter("enchereGagne");
			System.out.println(enchereOuverte + " " + enchereUtilisateur + " " + enchereGagne);
			
			
			// Vérifie Partie Gagne
			if(enchereGagne != null) {
				System.out.println("je veux mes encheres gagnés !");
				// Si enchereOuverte est coché, pas besoin de demander enchereUtilisateur
				if(enchereOuverte != null) {
					System.out.println("je veux toutes les encheres !");
					//enchereOuverteVerfier(listeArticle, enchereOuverte, filtres);
					enchereOuverte = null;
					enchereUtilisateur = null;
				}
				//enchereOuverteVerfier(listeArticle, enchereGagne, filtres);
			}
			
			// Vérifie Partie Utilisateur
			if(enchereUtilisateur != null) {
				System.out.println("je veux mes encheres dans lesquelles je participe !");
				if(enchereGagne != null) {
					System.out.println("je veux mes encheres gagnés !");
					//enchereOuverteVerfier(listeArticle, enchereGagne, filtres);
					enchereGagne = null;
				}
				//enchereOuverteVerfier(listeArticle, enchereUtilisateur, filtres);
			}
			
			
			
			if(enchereOuverte != null)
				System.out.println("je veux toutes les encheres !");
				//enchereOuverteVerfier(listeArticle, enchereOuverte, filtres);
			
//			enchereOuverteVerfier(listeArticle, enchereUtilisateur, filtres);
//			enchereOuverteVerfier(listeArticle, enchereOuverte, filtres);
//			enchereOuverteVerfier(listeArticle, enchereGagne, filtres);
			

			
			
			
			
			
			listParam.add(categorieSelectionee);
			listParam.add(enchereOuverte);
			listParam.add(enchereUtilisateur);
			listParam.add(enchereGagne);
			
			
			//request.getRequestDispatcher("/WEB-INF/pages/ListeEncheresConnecte.jsp").forward(request, response);
		}
		else if(radio.contentEquals("vente")) {

			String venteEnCours = request.getParameter("venteEnCours");
			String venteNonDebute = request.getParameter("venteNonDebut");
			String venteTermine = request.getParameter("venteTermine");
			
			if(venteEnCours != null) {
				listParam.add(venteEnCours);
				filtres = true;
			}
				
			if(venteNonDebute != null) {
				listParam.add(venteNonDebute);
				filtres = true;
			}
				
			if(venteTermine != null) {
				listParam.add(venteTermine);
				filtres = true;
			}	
			
			listParam.add(categorieSelectionee);
			
			//request.getRequestDispatcher("/WEB-INF/pages/ListeEncheresConnecte.jsp").forward(request, response);
		}
		
		if(!filtres) {
			doGet(request,response);
		}
	}
	
	public List<Article> enchereOuverteVerfier(List<Article> liste, String enchere, boolean filtres){
		 
			switch (enchere) {
			// ajoute toutes les encheres en cours
			case "enchereOuverte":
					System.out.println("dans ma méthode : " + enchere);
					try {
						// selectionne les articles en cours
						liste = articleManager.SelectAllArticles();
					} catch (BLLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			// ajoute les encheres de l'utilisateur
			case "enchereUtilisateur":
				try {
					Utilisateur utilisateur = utilisateurManager.selectByPseudo("lenaik68");
					//listeArticle = articleManager.
				} catch (BLLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			// ajoute les encheres gagnées
			case "enchereGagne":
					filtres = true;
					System.out.println("gagne");
					try {
						Utilisateur utilisateur = utilisateurManager.selectByPseudo("lenaik68");
					} catch (BLLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		
		return liste;	
	}

}
