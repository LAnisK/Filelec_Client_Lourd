package controleur;

public class Image {
	private int  id_image ;
	private String nom_image, url_image; 
	private int id_article ;
	
	public Image(int id_image, String nom_image, String url_image, int id_article) {
		super();
		this.id_image = id_image;
		this.nom_image = nom_image;
		this.url_image = url_image;
		this.id_article = id_article;
	} 
	
	public Image(  String nom_image, String url_image, int id_article) {
		super();
		this.id_image = 0;
		this.nom_image = nom_image;
		this.url_image = url_image;
		this.id_article = id_article;
	}

	public int getId_image() {
		return id_image;
	}

	public void setId_image(int id_image) {
		this.id_image = id_image;
	}

	public String getNom_image() {
		return nom_image;
	}

	public void setNom_image(String nom_image) {
		this.nom_image = nom_image;
	}

	public String getUrl_image() {
		return url_image;
	}

	public void setUrl_image(String url_image) {
		this.url_image = url_image;
	}

	public int getId_article() {
		return id_article;
	}

	public void setId_article(int id_article) {
		this.id_article = id_article;
	} 
	
}
