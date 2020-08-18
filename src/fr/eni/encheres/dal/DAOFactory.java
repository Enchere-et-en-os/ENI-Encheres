package fr.eni.encheres.dal;

public class DAOFactory {
	
	public static UtilisateurDAOImpl getUtilisateurDAO() {
		return new UtilisateurDAOImpl();
	}
}
