package controleur;

public class Commande {
	private int id_commande; 
	private int id_client; 
	private String date_commande, statut;
	private float montant_total; 
	private String adresse_livraison;
	public Commande(int id_commande, int id_client, String date_commande, String statut, float montant_total,
			String adresse_livraison) {
		super();
		this.id_commande = id_commande;
		this.id_client = id_client;
		this.date_commande = date_commande;
		this.statut = statut;
		this.montant_total = montant_total;
		this.adresse_livraison = adresse_livraison;
	} 
	public Commande( int id_client, String date_commande, String statut, float montant_total,
			String adresse_livraison) {
		super();
		this.id_commande = 0;
		this.id_client = id_client;
		this.date_commande = date_commande;
		this.statut = statut;
		this.montant_total = montant_total;
		this.adresse_livraison = adresse_livraison;
	}
	public int getId_commande() {
		return id_commande;
	}
	public void setId_commande(int id_commande) {
		this.id_commande = id_commande;
	}
	public int getId_client() {
		return id_client;
	}
	public void setId_client(int id_client) {
		this.id_client = id_client;
	}
	public String getDate_commande() {
		return date_commande;
	}
	public void setDate_commande(String date_commande) {
		this.date_commande = date_commande;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
	public float getMontant_total() {
		return montant_total;
	}
	public void setMontant_total(float montant_total) {
		this.montant_total = montant_total;
	}
	public String getAdresse_livraison() {
		return adresse_livraison;
	}
	public void setAdresse_livraison(String adresse_livraison) {
		this.adresse_livraison = adresse_livraison;
	} 
	
}
