package controleur;

public class Intervention {
	private int id_intervention ; 
	private String rapport , date_debut_inter, date_fin_inter; 
	private String statut_inter;
	private int id_client, id_technicien, id_commande;
	
	public Intervention(int id_intervention, String rapport, String date_debut_inter, String date_fin_inter,
			int id_client, int id_technicien, int id_commande, String statut_inter) {
		super();
		this.id_intervention = id_intervention;
		this.rapport = rapport;
		this.date_debut_inter = date_debut_inter;
		this.date_fin_inter = date_fin_inter;
		this.id_client = id_client;
		this.id_technicien = id_technicien;
		this.id_commande = id_commande;
		this.statut_inter = statut_inter;
	}
	
	public Intervention( String rapport, String date_debut_inter, String date_fin_inter,
			int id_client, int id_technicien, int id_commande, String statut_inter) {
		super();
		this.id_intervention = 0;
		this.rapport = rapport;
		this.date_debut_inter = date_debut_inter;
		this.date_fin_inter = date_fin_inter;
		this.id_client = id_client;
		this.id_technicien = id_technicien;
		this.id_commande = id_commande;
		this.statut_inter = statut_inter;
	}

	public int getId_intervention() {
		return id_intervention;
	}

	public void setId_intervention(int id_intervention) {
		this.id_intervention = id_intervention;
	}

	public String getRapport() {
		return rapport;
	}

	public void setRapport(String rapport) {
		this.rapport = rapport;
	}

	public String getDate_debut_inter() {
		return date_debut_inter;
	}

	public void setDate_debut_inter(String date_debut_inter) {
		this.date_debut_inter = date_debut_inter;
	}

	public String getDate_fin_inter() {
		return date_fin_inter;
	}

	public void setDate_fin_inter(String date_fin_inter) {
		this.date_fin_inter = date_fin_inter;
	}

	public int getId_client() {
		return id_client;
	}

	public void setId_client(int id_client) {
		this.id_client = id_client;
	}

	public int getId_technicien() {
		return id_technicien;
	}

	public void setId_technicien(int id_technicien) {
		this.id_technicien = id_technicien;
	}

	public int getId_commande() {
		return id_commande;
	}

	public void setId_commande(int id_commande) {
		this.id_commande = id_commande;
	}
	
	public String getStatut_inter() {
		return statut_inter;
	}

	public void setStatut_inter(String statut_inter) {
		this.statut_inter = statut_inter;
	}
}
