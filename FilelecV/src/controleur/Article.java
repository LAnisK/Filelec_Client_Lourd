package controleur;

public class Article {
	private int id_article; 
	private String nom_article, description_article; 
	private float prix_article ; 
	private int stock_article, id_cat ;
	
	public Article(int id_article, String nom_article, String description_article, float prix_article,
			int stock_article, int id_cat) {
		super();
		this.id_article = id_article;
		this.nom_article = nom_article;
		this.description_article = description_article;
		this.prix_article = prix_article;
		this.stock_article = stock_article;
		this.id_cat = id_cat;
	} 
	
	public Article(  String nom_article, String description_article, float prix_article,
			int stock_article, int id_cat) {
		super();
		this.id_article = 0;
		this.nom_article = nom_article;
		this.description_article = description_article;
		this.prix_article = prix_article;
		this.stock_article = stock_article;
		this.id_cat = id_cat;
	}

	public int getId_article() {
		return id_article;
	}

	public void setId_article(int id_article) {
		this.id_article = id_article;
	}

	public String getNom_article() {
		return nom_article;
	}

	public void setNom_article(String nom_article) {
		this.nom_article = nom_article;
	}

	public String getDescription_article() {
		return description_article;
	}

	public void setDescription_article(String description_article) {
		this.description_article = description_article;
	}

	public float getPrix_article() {
		return prix_article;
	}

	public void setPrix_article(float prix_article) {
		this.prix_article = prix_article;
	}

	public int getStock_article() {
		return stock_article;
	}

	public void setStock_article(int stock_article) {
		this.stock_article = stock_article;
	}

	public int getId_cat() {
		return id_cat;
	}

	public void setId_cat(int id_cat) {
		this.id_cat = id_cat;
	}
	
	
}
