package modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
	private String serveur , bdd, user, mdp ; 
	private Connection maConnexion ;
	
	public Connexion(String serveur, String bdd, String user, String mdp) {
		
		this.serveur = serveur;
		this.bdd = bdd;
		this.user = user;
		this.mdp = mdp;
		this.maConnexion = null;
	} 
	
	public void chargerPilote () {
		try {
			//verifier que le pilote est charge dans le projet
			Class.forName("com.mysql.cj.jdbc.Driver") ;
		}
		catch(ClassNotFoundException exp) {
			System.out.println("Le pilote JDBC est introuvable.");
		}
	}
	public void seConnecter () {
		String url ="jdbc:mysql://"+this.serveur+"/"+this.bdd; 
		try {
			this.maConnexion= DriverManager.getConnection(url, this.user, this.mdp);
		}
		catch(SQLException exp) {
			System.out.println("Erreur de connexion a : " + url);
			exp.printStackTrace();
		}
	}
	public void seDeConnecter () {
		try {
			if (this.maConnexion != null) {
				this.maConnexion.close();
			}
		}
		catch(SQLException exp) {
			System.out.println("Erreur de fermeture de la connexion");
		}
	}
	public Connection getMaConnexion () {
		return this.maConnexion; 
	}
	
	
}
