package controleur;

public class Ligne {
	private int id_ligne; 
	private int id_article; 
	
	private int quantite ; 
	private float sous_total; 
	private float prix_unitaire ; 
	private int id_commande;
	public Ligne(int id_ligne, int id_article, int quantite, float sous_total, float prix_unitaire, int id_commande) {
		super();
		this.id_ligne = id_ligne;
		this.id_article = id_article;
		this.quantite = quantite;
		this.sous_total = sous_total;
		this.prix_unitaire = prix_unitaire;
		this.id_commande = id_commande;
	} 
	
	public Ligne(  int id_article, int quantite, float sous_total, float prix_unitaire, int id_commande) {
		super();
		this.id_ligne = 0;
		this.id_article = id_article;
		this.quantite = quantite;
		this.sous_total = sous_total;
		this.prix_unitaire = prix_unitaire;
		this.id_commande = id_commande;
	}

	public int getId_ligne() {
		return id_ligne;
	}

	public void setId_ligne(int id_ligne) {
		this.id_ligne = id_ligne;
	}

	public int getId_article() {
		return id_article;
	}

	public void setId_article(int id_article) {
		this.id_article = id_article;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public float getSous_total() {
		return sous_total;
	}

	public void setSous_total(float sous_total) {
		this.sous_total = sous_total;
	}

	public float getPrix_unitaire() {
		return prix_unitaire;
	}

	public void setPrix_unitaire(float prix_unitaire) {
		this.prix_unitaire = prix_unitaire;
	}

	public int getId_commande() {
		return id_commande;
	}

	public void setId_commande(int id_commande) {
		this.id_commande = id_commande;
	}
	
}
