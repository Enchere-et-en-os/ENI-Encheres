package fr.eni.encheres.ihm;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bo.Article;

/**
 * Servlet implementation class DetailEnchereServlet
 */
@WebServlet("/Détail")
public class DetailEnchereServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArticleManager mgr = new ArticleManager();
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Article articleDetail = mgr.selectArticleByID(Integer.valueOf(request.getParameter("idArticle")));
		
		request.setAttribute("nomArticle", articleDetail.getNom());
		request.setAttribute("descritpion", articleDetail.getDescription());
		request.setAttribute("miseAprix", articleDetail.getMiseAPrix());
		request.setAttribute("debutEnchere", articleDetail.getDateDebutEncheres());
		request.setAttribute("vendeur", articleDetail.getDateFinEncheres());
		request.setAttribute("nomVendeur", articleDetail.getUtilisateurPseudo());
		
		HttpSession session = request.getSession();
		String pseudoUser = (String) session.getAttribute("pseudo");
		if(pseudoUser.contains(articleDetail.getUtilisateurPseudo()) && articleDetail.getEtatVente() == 1) {
			request.setAttribute("modifierArt", true);
		}	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("annuler") != null) {
			response.sendRedirect("ListeEncheres");
		}else if(request.getParameter("modifier") != null){
			
		}
			
				
			
		
	}

}
