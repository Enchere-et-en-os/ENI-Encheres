package fr.eni.encheres.bo;

import java.time.LocalDate;

public class Article {
	private int id;
	private String nom;
	private String description;
	private LocalDate dateDebutEncheres;
	private LocalDate dateFinEncheres;
	private int miseAPrix;
	private int prixVente;
	private int etatVente;
	private Utilisateur utilisateur;
	private Categorie categorie;
	private int utilisateurId;
	private int categorieId;
	private Retrait retrait;


	public Article(int id, String nom, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres,
	int miseAPrix, int prixVente, int etatVente, int utilisateurId, int categorieId) {
	this.id = id;
	this.nom = nom;
	this.description = description;
	this.dateDebutEncheres = dateDebutEncheres;
	this.dateFinEncheres = dateFinEncheres;
	this.miseAPrix = miseAPrix;
	this.prixVente = prixVente;
	this.etatVente = etatVente;
	this.utilisateurId = utilisateurId;
	this.categorieId = categorieId;
	}
		
	
	public Article(String nom, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres,
			int miseAPrix, int prixVente, int etatVente, int utilisateurId, int categorieId) {
		super();
		this.nom = nom;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.utilisateurId = utilisateurId;
		this.categorieId = categorieId;
		
	}

	public Article(String nom, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres, int miseAPrix,
		int prixVente, int etatVente, Utilisateur utilisateur, Categorie categorie, int utilisateurId,
		int categorieId) {
	super();
	this.nom = nom;
	this.description = description;
	this.dateDebutEncheres = dateDebutEncheres;
	this.dateFinEncheres = dateFinEncheres;
	this.miseAPrix = miseAPrix;
	this.prixVente = prixVente;
	this.etatVente = etatVente;
	this.utilisateur = utilisateur;
	this.categorie = categorie;
	this.utilisateurId = utilisateurId;
	this.categorieId = categorieId;
}
	
	public Article(String nom, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres, int miseAPrix,
			 Retrait retrait) {
		//new Article(nomArticle, description, miseAprix, debutEnchere, finEnchere, retrait ));
		this.nom = nom;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		setRetrait(retrait);
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDateDebutEncheres() {
		return dateDebutEncheres;
	}

	public void setDateDebutEncheres(LocalDate dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}

	public LocalDate getDateFinEncheres() {
		return dateFinEncheres;
	}

	public void setDateFinEncheres(LocalDate dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}

	public int getMiseAPrix() {
		return miseAPrix;
	}

	public void setMiseAPrix(int miseAPrix) {
		this.miseAPrix = miseAPrix;
	}

	public int getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}

	public int getEtatVente() {
		return etatVente;
	}

	public void setEtatVente(int etatVente) {
		this.etatVente = etatVente;
	}
	

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public int getUtilisateurId() {
		return utilisateurId;
	}


	public void setUtilisateurId(int utilisateurId) {
		this.utilisateurId = utilisateurId;
	}


	public int getCategorieId() {
		return categorieId;
	}


	public void setCategorieId(int categorieId) {
		this.categorieId = categorieId;
	}


	public Retrait getRetrait() {
		return retrait;
	}


	public void setRetrait(Retrait retrait) {
		this.retrait = retrait;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", nom=" + nom + ", description=" + description + ", dateDebutEncheres="
				+ dateDebutEncheres + ", dateFinEncheres=" + dateFinEncheres + ", miseAPrix=" + miseAPrix
				+ ", prixVente=" + prixVente + ", etatVente=" + etatVente + ", utilisateur=" + utilisateur
				+ ", categorie=" + categorie + ", utilisateurId=" + utilisateurId + ", categorieId=" + categorieId
				+ ", retrait=" + retrait + "]";
	}

}
