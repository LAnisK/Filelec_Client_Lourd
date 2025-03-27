package controleur;

public class Client {
	private int id_client ; 
	private String nom_client, prenom_client, adresse_client, email_client ,tel_client, mdp_client, date_creation_client, url_client;
	
	public Client(int id_client, String nom_client, String prenom_client, String adresse_client, String email_client,
			String tel_client, String mdp_client, String date_creation_client, String url_client) {
		super();
		this.id_client = id_client;
		this.nom_client = nom_client;
		this.prenom_client = prenom_client;
		this.adresse_client = adresse_client;
		this.email_client = email_client;
		this.tel_client = tel_client;
		this.mdp_client = mdp_client;
		this.date_creation_client = date_creation_client;
		this.url_client = url_client;
	}
	
	public Client(  String nom_client, String prenom_client, String adresse_client, String email_client,
			String tel_client, String mdp_client, String date_creation_client, String url_client) {
		super();
		this.id_client =0; 
		this.nom_client = nom_client;
		this.prenom_client = prenom_client;
		this.adresse_client = adresse_client;
		this.email_client = email_client;
		this.tel_client = tel_client;
		this.mdp_client = mdp_client;
		this.date_creation_client = date_creation_client;
		this.url_client = url_client;
	}

	public int getId_client() {
		return id_client;
	}

	public void setId_client(int id_client) {
		this.id_client = id_client;
	}

	public String getNom_client() {
		return nom_client;
	}

	public void setNom_client(String nom_client) {
		this.nom_client = nom_client;
	}

	public String getPrenom_client() {
		return prenom_client;
	}

	public void setPrenom_client(String prenom_client) {
		this.prenom_client = prenom_client;
	}

	public String getAdresse_client() {
		return adresse_client;
	}

	public void setAdresse_client(String adresse_client) {
		this.adresse_client = adresse_client;
	}

	public String getEmail_client() {
		return email_client;
	}

	public void setEmail_client(String email_client) {
		this.email_client = email_client;
	}

	public String getTel_client() {
		return tel_client;
	}

	public void setTel_client(String tel_client) {
		this.tel_client = tel_client;
	}

	public String getMdp_client() {
		return mdp_client;
	}

	public void setMdp_client(String mdp_client) {
		this.mdp_client = mdp_client;
	}

	public String getDate_creation_client() {
		return date_creation_client;
	}

	public void setDate_creation_client(String date_creation_client) {
		this.date_creation_client = date_creation_client;
	}

	public String getUrl_client() {
		return url_client;
	}

	public void setUrl_client(String url_client) {
		this.url_client = url_client;
	}
	
	
}
 