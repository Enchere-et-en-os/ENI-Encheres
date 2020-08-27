package fr.eni.encheres.ihm;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		if(session == null) {
			System.out.println("pas de session");
			response.sendRedirect("Accueil");
		}else {	
			try {
				request.setAttribute("listeCategorie", articleManager.SelectAllCategories());
				request.setAttribute("listeArticle", articleManager.SelectAllArticles());
			} catch (BLLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getRequestDispatcher("/WEB-INF/pages/ListeEncheresConnecte.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("deconnexion") != null) {
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect("Accueil");
		}else {
		
		HttpSession session = request.getSession();
		String pseudo = (String) session.getAttribute("pseudo");
		List<Article> liste1 = new ArrayList<Article>();
		List<Article> liste2 = new ArrayList<Article>();
		List<Article> liste3 = new ArrayList<Article>();
		List<Article> listeArticle = new ArrayList<Article>();
		boolean filtres = false;
		Utilisateur utilisateur= null;
		String radio = request.getParameter("radio");
		//String categorieSelectionee = request.getParameter("selectCategorie");
		
		if(radio.contentEquals("achat")) {
			String enchereOuverte = request.getParameter("enchereOuverte");
			String enchereUtilisateur = request.getParameter("enchereUtilisateur");
			String enchereGagne = request.getParameter("enchereGagne");
			
			try {
				utilisateur = utilisateurManager.selectByPseudo(pseudo);
				System.out.println(utilisateur.getId());
			} catch (BLLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// Vérifie CheckBox Gagne
			if(enchereGagne != null) {
				filtres = true;
				
				System.out.println("je veux mes encheres gagnés !");	
				try {
					for (Article article : articleManager.selectAllByEtatVenteGagne(3,utilisateur.getId())) {
						liste3.add(article);
					}
					System.out.println("ma liste : " + liste3);
				} catch (BLLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	
				// Si enchereOuverte est coché, pas besoin de demander enchereUtilisateur
				if(enchereOuverte != null) {
					System.out.println("je veux toutes les encheres !");
					try {
						for (Article article : articleManager.selectAllByEtatVente(2)) {
							liste1.add(article);
							
						}
						System.out.println("ma liste : " + liste1);
					} catch (BLLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					enchereOuverte = null;
					enchereUtilisateur = null;
				}	
			}
			
			// Vérifie CheckBox Utilisateur
			if(enchereUtilisateur != null) {
				filtres = true;
				
				try {	
					for (Article article : articleManager.selectAllByEtatVenteUtilisateur(2,utilisateur.getId())) {
						liste2.add(article);
						
					}
					System.out.println("ma liste : " + liste2);
				} catch (BLLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("je veux mes encheres dans lesquelles je participe !");
				
				if(enchereGagne != null) {
					System.out.println("je veux mes encheres gagnés !");
					
					try {
						for (Article article : articleManager.selectAllByEtatVenteGagne(3,utilisateur.getId())) {
							liste3.add(article);	
						}
						System.out.println("ma liste : " + liste3);
					} catch (BLLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					enchereGagne = null;
				}
			}
			
			if(enchereOuverte != null) {
				filtres = true;
				
				try {
					for (Article article : articleManager.selectAllByEtatVente(2)) {
						liste1.add(article);
						
					}
					System.out.println("ma liste : " + liste1);
				} catch (BLLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println(listeArticle);
				System.out.println("je veux toutes les encheres !");
			}		
		}
		else if(radio.contentEquals("vente")) {

			String venteEnCours = request.getParameter("venteEnCours");
			String venteNonDebute = request.getParameter("venteNonDebut");
			String venteTermine = request.getParameter("venteTermine");
			
			try {
				utilisateur = utilisateurManager.selectByPseudo(pseudo);
			} catch (BLLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if(venteEnCours != null) {
				filtres = true;
				try {
					liste1 = articleManager.SelectAllEncheresByEtat(utilisateur.getId(), 2);
					System.out.println(liste2);
				} catch (BLLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				
			if(venteNonDebute != null) {
				filtres = true;
				try {
					liste2 = articleManager.SelectAllEncheresByEtat(utilisateur.getId(), 1);
					System.out.println(liste1);
				} catch (BLLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				
			if(venteTermine != null) {
				filtres = true;
				try {
					liste3 = articleManager.SelectAllEncheresByEtat(utilisateur.getId(), 3);
					System.out.println(liste3);
				} catch (BLLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}			
		}
		
		if(!filtres) {
			try {
				listeArticle = articleManager.selectAllByEtatVente(2);
			} catch (BLLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else {
			System.out.println(liste1);
			for (Article article : liste2){
				   if (!liste1.contains(article))
				      liste1.add(article);
				}
			System.out.println(liste1);
			liste1.addAll(liste3);
			System.out.println(liste1);
			listeArticle.addAll(liste1);
		}
		
		try {
			request.setAttribute("listeCategorie", articleManager.SelectAllCategories());
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("listeArticle", listeArticle);
		request.getRequestDispatcher("/WEB-INF/pages/ListeEncheresConnecte.jsp").forward(request, response);
	}
	}
}
