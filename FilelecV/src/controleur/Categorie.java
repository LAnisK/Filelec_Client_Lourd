package controleur;

public class Categorie {
	private int id_cat ; 
	private String nom_cat ;
	
	public Categorie(int id_cat, String nom_cat) {
		super();
		this.id_cat = id_cat;
		this.nom_cat = nom_cat;
	} 
	
	public Categorie( String nom_cat) {
		super();
		this.id_cat = 0;
		this.nom_cat = nom_cat;
	}

	public int getId_cat() {
		return id_cat;
	}

	public void setId_cat(int id_cat) {
		this.id_cat = id_cat;
	}

	public String getNom_cat() {
		return nom_cat;
	}

	public void setNom_cat(String nom_cat) {
		this.nom_cat = nom_cat;
	} 
	
	
}
