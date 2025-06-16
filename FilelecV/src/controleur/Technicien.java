package controleur;

public class Technicien {
	private int id_technicien ; 
	private String nom_technicien , prenom_technicien , email_technicien , mdp_technicien , telephone_technicien ,  role_technicien;
	
	public Technicien(int id_technicien, String nom_technicien, String prenom_technicien, String email_technicien,
			String mdp_technicien, String telephone_technicien, String role_technicien) {
		super();
		this.id_technicien = id_technicien;
		this.nom_technicien = nom_technicien;
		this.prenom_technicien = prenom_technicien;
		this.email_technicien = email_technicien;
		this.mdp_technicien = mdp_technicien;
		this.telephone_technicien = telephone_technicien;
		this.role_technicien = role_technicien;
	}
	
	public Technicien( String nom_technicien, String prenom_technicien, String email_technicien,
			String mdp_technicien, String telephone_technicien, String role_technicien) {
		super();
		this.id_technicien = 0;
		this.nom_technicien = nom_technicien;
		this.prenom_technicien = prenom_technicien;
		this.email_technicien = email_technicien;
		this.mdp_technicien = mdp_technicien;
		this.telephone_technicien = telephone_technicien;
		this.role_technicien = role_technicien;
	}

	public int getId_technicien() {
		return id_technicien;
	}

	public void setId_technicien(int id_technicien) {
		this.id_technicien = id_technicien;
	}

	public String getNom_technicien() {
		return nom_technicien;
	}

	public void setNom_technicien(String nom_technicien) {
		this.nom_technicien = nom_technicien;
	}

	public String getPrenom_technicien() {
		return prenom_technicien;
	}

	public void setPrenom_technicien(String prenom_technicien) {
		this.prenom_technicien = prenom_technicien;
	}

	public String getEmail_technicien() {
		return email_technicien;
	}

	public void setEmail_technicien(String email_technicien) {
		this.email_technicien = email_technicien;
	}

	public String getMdp_technicien() {
		return mdp_technicien;
	}

	public void setMdp_technicien(String mdp_technicien) {
		this.mdp_technicien = mdp_technicien;
	}

	public String getTelephone_technicien() {
		return telephone_technicien;
	}

	public void setTelephone_technicien(String telephone_technicien) {
		this.telephone_technicien = telephone_technicien;
	}

	public String getRole_technicien() {
		return role_technicien;
	}

	public void setRole_technicien(String role_technicien) {
		this.role_technicien = role_technicien;
	}
	
	
}
