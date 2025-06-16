package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JComboBox;

import controleur.Article;
import controleur.Client;
import controleur.Commande;
import controleur.Intervention;
import controleur.Technicien;
 

public class Modele {
	private static Connexion uneConnexion = new Connexion ("localhost","filelec","root","");
	
	/********************* Gestion des Clients ************************/
	public static void insertClient (Client unClient) {
		String requete ="insert into client values (null, '" 
				+ unClient.getNom_client()+"' , '" + unClient.getPrenom_client() +"' , '" +unClient.getAdresse_client()
				+ "' , '" + unClient.getEmail_client() + "' , '" + unClient.getTel_client()+"' , '" + unClient.getMdp_client()
				+"' , curdate(), '" + unClient.getUrl_client() +"' , '" + unClient.getType_client()+"', curdate());";
		executerRequete(requete);
	}
	
	public static void deleteClient(int idclient) {
		String requete ="call deleteClient("+idclient+"); ";
		executerRequete(requete);
	}
	s
	public static void updateClient (Client unClient) {
		String requete ="update  client set nom_client= '" 
				+ unClient.getNom_client()+"' , prenom_client='" + unClient.getPrenom_client() +"' ,adresse_client= '" +unClient.getAdresse_client()
				+ "' , email_client='" + unClient.getEmail_client() + "' , tel_client='" + unClient.getTel_client()+"' ,mdp_client= '" + 
				unClient.getMdp_client()+ "' , url_client='" + unClient.getUrl_client()+ "' , type_client='" + unClient.getType_client()
				+ "' , date_mdp=curdate();";
		executerRequete(requete);
	}
	public static ArrayList<Client> selectAllClients(){
		ArrayList<Client> lesClients = new ArrayList<Client>(); 
		String requete ="select * from client;";
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			ResultSet lesResultats = unStat.executeQuery(requete); //retourne des resultats 
			while(lesResultats.next()) {
				Client unClient = new Client(
						lesResultats.getInt("id_client"), lesResultats.getString("nom_client"), 
						lesResultats.getString("prenom_client"), lesResultats.getString("adresse_client"),
						lesResultats.getString("email_client"), lesResultats.getString("tel_client"), 
						lesResultats.getString("mdp_client"), lesResultats.getString("date_creation_client"), 
						lesResultats.getString("url_client"), lesResultats.getString("type_client"), lesResultats.getString("date_mdp")
						);
				lesClients.add(unClient);
			}
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return lesClients ;
	}
	
	public static ArrayList<Client> selectLikeClients(String filtre){
		ArrayList<Client> lesClients = new ArrayList<Client>(); 
		String requete ="select * from client where nom_client like '%"+filtre+"%' or prenom_client like '%"+filtre+"%' or adresse_client like '%"+filtre+"%' or email_client like '%"+filtre+"%' or tel_client like '%"+filtre+"%';";
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			ResultSet lesResultats = unStat.executeQuery(requete); //retourne des resultats 
			while(lesResultats.next()) {
				Client unClient = new Client(
						lesResultats.getInt("id_client"), lesResultats.getString("nom_client"), 
						lesResultats.getString("prenom_client"), lesResultats.getString("adresse_client"),
						lesResultats.getString("email_client"), lesResultats.getString("tel_client"), 
						lesResultats.getString("mdp_client"), lesResultats.getString("date_creation_client"), 
						lesResultats.getString("url_client"), lesResultats.getString("type_client"), lesResultats.getString("date_mdp")
						);
				lesClients.add(unClient);
			}
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return lesClients ;
	}
	
	public static  Client selectWhereClient (int idclient ){
		Client unClient = null;
		String requete ="select * from client where id_client = " + idclient + ";";
		try { 
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			ResultSet lesResultats = unStat.executeQuery(requete); //retourne des resultats 
			if(lesResultats.next()) {
				 unClient = new Client(
						 lesResultats.getInt("id_client"), lesResultats.getString("nom_client"), 
							lesResultats.getString("prenom_client"), lesResultats.getString("adresse_client"),
							lesResultats.getString("email_client"), lesResultats.getString("tel_client"), 
							lesResultats.getString("mdp_client"), lesResultats.getString("date_creation_client"), 
							lesResultats.getString("url_client"), lesResultats.getString("type_client"), lesResultats.getString("date_mdp")
							);
			}
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return unClient ;
	}
 
	/********************* Gestion des Articles ************************/
	public static void insertArticle (Article unArticle) {
		String requete ="insert into article values (null, '" 
				+ unArticle.getNom_article()+"' , '" + unArticle.getDescription_article() +"' , '" +unArticle.getPrix_article()
				+ "' , '" + unArticle.getStock_article() + "' , '" + unArticle.getId_cat()+ "');";
		executerRequete(requete);
	}
	
	public static void deleteArticle(int idarticle) {
		String requete ="delete from article where id_article = "+idarticle+"; ";
		executerRequete(requete);
	}
	
	public static void updateArticle(Article unArticle) {
		String requete ="update  article set nom_article= '" 
				+ unArticle.getNom_article()+"' , description_article='" + unArticle.getDescription_article() 
				+"' ,prix_article= '" +unArticle.getPrix_article()
				+ "' , stock_article='" + unArticle.getStock_article() + "' , id_cat='" + unArticle.getId_cat()
				+"'  where id_article = " +unArticle.getId_article()+";";
		executerRequete(requete);
	}
	public static ArrayList<Article> selectAllArticles(){
		ArrayList<Article> lesArticles = new ArrayList<Article>(); 
		String requete ="select * from 	article;";
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			ResultSet lesResultats = unStat.executeQuery(requete); //retourne des resultats 
			while(lesResultats.next()) {
				Article unArticle = new Article(
						lesResultats.getInt("id_article"), lesResultats.getString("nom_Article"), 
						lesResultats.getString("description_article"), lesResultats.getFloat("prix_article"),
						lesResultats.getInt("stock_article"),   
						lesResultats.getInt("id_cat")
						);
				lesArticles.add(unArticle);
			}
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return lesArticles ;
	}
	
	public static ArrayList<Article> selectLikeArticles(String filtre){
		ArrayList<Article> lesArticles = new ArrayList<Article>(); 
		String requete ="select * from article where nom_article like '%"+filtre+"%' or description_article like '%"+filtre+"%' or prix_article like '%"+filtre+"%' or stock_article like '%"+filtre+"%'";
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			ResultSet lesResultats = unStat.executeQuery(requete); //retourne des resultats 
			while(lesResultats.next()) {
				Article unArticle = new Article(
						lesResultats.getInt("id_article"), lesResultats.getString("nom_Article"), 
						lesResultats.getString("description_article"), lesResultats.getFloat("prix_article"),
						lesResultats.getInt("stock_article"),   
						lesResultats.getInt("id_cat")
						);
				lesArticles.add(unArticle);
			}
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return lesArticles ;
	}
	
	public static  Article selectWhereArticle (int idclient ){
		Article unArticle = null;
		String requete ="select * from article where id_article = " + idclient + ";";
		try { 
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			ResultSet lesResultats = unStat.executeQuery(requete); //retourne des resultats 
			if(lesResultats.next()) {
				unArticle = new Article(
						 lesResultats.getInt("id_article"), lesResultats.getString("nom_Article"), 
							lesResultats.getString("description_article"), lesResultats.getFloat("prix_article"),
							lesResultats.getInt("stock_article"),   
							lesResultats.getInt("id_cat")
							);
			}
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return unArticle;
	}
	
	/************************ Gestion des techniciens *************/
	 
	 
    public static void insertTechnicien(Technicien unTechnicien) {
        String requete = "INSERT INTO technicien VALUES (null, '"
                + unTechnicien.getNom_technicien() + "', '"
                + unTechnicien.getPrenom_technicien() + "', '"
                + unTechnicien.getEmail_technicien() + "', '"
                + unTechnicien.getMdp_technicien() + "', '"
                + unTechnicien.getTelephone_technicien() + "', '"
                + unTechnicien.getRole_technicien() + "');";
        executerRequete(requete);
    }


    public static void deleteTechnicien(int idTechnicien) {
        String requete = "DELETE FROM technicien WHERE id_technicien = " + idTechnicien + ";";
        executerRequete(requete);
    }

    
    public static void updateTechnicien(Technicien unTechnicien) {
        String requete = "UPDATE technicien SET "
                + "nom_technicien = '" + unTechnicien.getNom_technicien() + "', "
                + "prenom_technicien = '" + unTechnicien.getPrenom_technicien() + "', "
                + "email_technicien = '" + unTechnicien.getEmail_technicien() + "', "
                + "mdp_technicien = '" + unTechnicien.getMdp_technicien() + "', "
                + "telephone_technicien = '" + unTechnicien.getTelephone_technicien() + "', "
                + "role_technicien = '" + unTechnicien.getRole_technicien() + "' "
                + "WHERE id_technicien = " + unTechnicien.getId_technicien() + ";";
        executerRequete(requete);
    }

    
    public static ArrayList<Technicien> selectAllTechniciens() {
        ArrayList<Technicien> lesTechniciens = new ArrayList<>();
        String requete = "SELECT * FROM technicien;";
        try {
            uneConnexion.seConnecter();
            Statement unStat = uneConnexion.getMaConnexion().createStatement();
            ResultSet lesResultats = unStat.executeQuery(requete);

            while (lesResultats.next()) {
                Technicien unTechnicien = new Technicien(
                        lesResultats.getInt("id_technicien"),
                        lesResultats.getString("nom_technicien"),
                        lesResultats.getString("prenom_technicien"),
                        lesResultats.getString("email_technicien"),
                        lesResultats.getString("mdp_technicien"),
                        lesResultats.getString("telephone_technicien"),
                        lesResultats.getString("role_technicien")
                );
                lesTechniciens.add(unTechnicien);
            }

            unStat.close();
            uneConnexion.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'exécution de la requête : " + requete);
        }
        return lesTechniciens;
    }

    
    public static ArrayList<Technicien> selectLikeTechniciens(String filtre) {
        ArrayList<Technicien> lesTechniciens = new ArrayList<>();
        String requete = "SELECT * FROM technicien WHERE "
                + "nom_technicien LIKE '%" + filtre + "%' OR "
                + "prenom_technicien LIKE '%" + filtre + "%' OR "
                + "email_technicien LIKE '%" + filtre + "%' OR "
                + "telephone_technicien LIKE '%" + filtre + "%';";
        try {
            uneConnexion.seConnecter();
            Statement unStat = uneConnexion.getMaConnexion().createStatement();
            ResultSet lesResultats = unStat.executeQuery(requete);

            while (lesResultats.next()) {
                Technicien unTechnicien = new Technicien(
                        lesResultats.getInt("id_technicien"),
                        lesResultats.getString("nom_technicien"),
                        lesResultats.getString("prenom_technicien"),
                        lesResultats.getString("email_technicien"),
                        lesResultats.getString("mdp_technicien"),
                        lesResultats.getString("telephone_technicien"),
                        lesResultats.getString("role_technicien")
                );
                lesTechniciens.add(unTechnicien);
            }

            unStat.close();
            uneConnexion.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'exécution de la requête : " + requete);
        }
        return lesTechniciens;
    }

   
    public static Technicien selectWhereTechnicien(int idTechnicien) {
        Technicien unTechnicien = null;
        String requete = "SELECT * FROM technicien WHERE id_technicien = " + idTechnicien + ";";
        try {
            uneConnexion.seConnecter();
            Statement unStat = uneConnexion.getMaConnexion().createStatement();
            ResultSet lesResultats = unStat.executeQuery(requete);

            if (lesResultats.next()) {
                unTechnicien = new Technicien(
                        lesResultats.getInt("id_technicien"),
                        lesResultats.getString("nom_technicien"),
                        lesResultats.getString("prenom_technicien"),
                        lesResultats.getString("email_technicien"),
                        lesResultats.getString("mdp_technicien"),
                        lesResultats.getString("telephone_technicien"),
                        lesResultats.getString("role_technicien")
                );
            }

            unStat.close();
            uneConnexion.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'exécution de la requête : " + requete);
        }
        return unTechnicien;
    }
    
    
    public static Technicien selectWhereTechnicien(String email, String mdp, String role) {
        Technicien unTechnicien = null;
        String requete = "SELECT * FROM technicien WHERE "
            + "email_technicien = '" + email + "' "
            + "AND mdp_technicien = '" + mdp + "' " + "AND role_technicien = '" + role + "';"; // Ajout du rôle dans la requête

        try {
            uneConnexion.seConnecter();
            Statement unStat = uneConnexion.getMaConnexion().createStatement();
            ResultSet lesResultats = unStat.executeQuery(requete);

            if (lesResultats.next()) {
                unTechnicien = new Technicien(
                    lesResultats.getInt("id_technicien"),
                    lesResultats.getString("nom_technicien"),
                    lesResultats.getString("prenom_technicien"),
                    lesResultats.getString("email_technicien"),
                    lesResultats.getString("mdp_technicien"),
                    lesResultats.getString("telephone_technicien"),
                    lesResultats.getString("role_technicien")
                );
            }
            
            unStat.close();
            uneConnexion.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur SQL: " + exp.getMessage());
            System.out.println("Requête: " + requete);
        }
        return unTechnicien;
    }
    
	/************************ GESTION DES INTERVENTIONS **************/
	public static void insertIntervention (Intervention uneIntervention) {
		String requete ="insert into intervention values (null, '"
				+ uneIntervention.getRapport()+"' , '" + uneIntervention.getDate_debut_inter() +"' , '"+uneIntervention.getDate_fin_inter()+"','" +uneIntervention.getId_client()
				+ "' , '" + uneIntervention.getId_technicien()+"', '"+ uneIntervention.getId_commande()+"' ,'"+ uneIntervention.getStatut_inter() +"');";
		executerRequete(requete);
	}
	public static ArrayList<Intervention> selectAllInterventions(){
		ArrayList<Intervention> lesInterventions = new ArrayList<Intervention>();
		String requete ="select * from intervention;";
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement();
			ResultSet lesResultats = unStat.executeQuery(requete); //retourne des resultats
			while(lesResultats.next()) {
				Intervention uneIntervention = new Intervention(
						lesResultats.getInt("id_intervention"), lesResultats.getString("rapport"),
						lesResultats.getString("date_debut_inter"), lesResultats.getString("date_fin_inter"),
						lesResultats.getInt("id_client"), lesResultats.getInt("id_technicien"),lesResultats.getInt("id_commande"), lesResultats.getString("statut_inter")
						);
				lesInterventions.add(uneIntervention);
			}
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return lesInterventions ;
	}
	public static void deleteIntervention(int id_intervention) {
		String requete ="call deleteIntervention("+id_intervention+"); ";
		executerRequete(requete);
	}
	
	public static void updateIntervention (Intervention uneIntervention) {
		String requete ="update  intervention set rapport= '"
				+ uneIntervention.getRapport()+"' , date_debut_inter='" + uneIntervention.getDate_debut_inter() +"' ,date_fin_inter= '" +uneIntervention.getDate_fin_inter()
				+ "' , id_client='" + uneIntervention.getId_client() + "' , id_technicien='" + uneIntervention.getId_technicien()+"' ,id_commande= '"+uneIntervention.getId_commande()+ "';";
		executerRequete(requete);
	}
	public static ArrayList<Intervention> selectLikeInterventions(String filtre){
		ArrayList<Intervention> lesInterventions = new ArrayList<Intervention>();
		String requete ="select * from intervention where id_intervention like '%"+filtre+"%' or rapport like '%"+filtre+"%' or date_debut_inter like '%"+filtre+"%' or date_fin_inter like '%"+filtre+"%' or id_client like '%"+filtre+"%' or id_technicien like '%"+filtre+"%' or id_commande like '%"+filtre+"%';";
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement();
			ResultSet lesResultats = unStat.executeQuery(requete); //retourne des resultats
			while(lesResultats.next()) {
				Intervention uneIntervention = new Intervention(
						lesResultats.getInt("id_intervention"), lesResultats.getString("rapport"),
						lesResultats.getString("date_debut_inter"), lesResultats.getString("date_fin_inter"),
						lesResultats.getInt("id_client"), lesResultats.getInt("id_technicien"),
						lesResultats.getInt("id_commande"), lesResultats.getString("statut_inter")
						);
				lesInterventions.add(uneIntervention);
			}
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return lesInterventions ;
	}
	
	public static  Intervention selectWhereIntervention (int id_intervention ){
		Intervention uneIntervention = null;
		String requete ="select * from client where id_intervention = " + id_intervention + ";";
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement();
			ResultSet lesResultats = unStat.executeQuery(requete); //retourne des resultats
			if(lesResultats.next()) {
				uneIntervention = new Intervention(
						lesResultats.getInt("id_intervention"), lesResultats.getString("rapport"),
							lesResultats.getString("date_debut_inter"), lesResultats.getString("date_fin_inter"),
							lesResultats.getInt("id_client"), lesResultats.getInt("id_technicien"),
							lesResultats.getInt("id_commande"), lesResultats.getString("statut_inter") 
							);
			}
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return uneIntervention ;
	}
	
	/************************* Gestion des commandes ****************/
	
	public static void insertCommande (Commande uneCommande) {
		String requete ="insert into commande values (null, '"
				+ uneCommande.getId_client()+"' , '" + uneCommande.getDate_commande() + "' , '" +uneCommande.getStatut()
				+ "' , '" + uneCommande.getMontant_total() + "' , '" + uneCommande.getAdresse_livraison()+"');";
		executerRequete(requete);
	}
	
	public static void deleteCommande(int id_commande) {
		String requete ="call deleteCommande("+id_commande+"); ";
		executerRequete(requete);
	}
	
	public static void updateCommande (Commande uneCommande) {
		String requete ="update  commande set date_commande ='" + uneCommande.getDate_commande() +"' ,statut = '" +uneCommande.getStatut()
				+ "' , montant_total ='" + uneCommande.getMontant_total() + "' , adresse_livraison ='" + uneCommande.getAdresse_livraison()+"' where id_commande = " + uneCommande.getId_commande() + ";";
		executerRequete(requete);
	}
	public static ArrayList<Commande> selectAllCommandes(){
		ArrayList<Commande> lesCommandes = new ArrayList<Commande>();
		String requete ="select * from commande;";
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement();
			ResultSet lesResultats = unStat.executeQuery(requete); //retourne des resultats
			while(lesResultats.next()) {
				Commande uneCommande = new Commande(
						lesResultats.getInt("id_commande"), lesResultats.getInt("id_client"),
						lesResultats.getString("date_commande"), lesResultats.getString("statut"),
						lesResultats.getFloat("montant_total"), lesResultats.getString("adresse_livraison")
						);
				lesCommandes.add(uneCommande);
			}
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return lesCommandes ;
	}
	
	public static ArrayList<Commande> selectLikeCommandes(String filtre){
		ArrayList<Commande> lesCommandes = new ArrayList<Commande>();
		String requete ="select * from commande where date_commande like '%"+filtre+"%' or statut like '%"+filtre+"%' or montant_total like '%"+filtre+"%' or adresse_livraison like '%"+filtre+"%';";
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement();
			ResultSet lesResultats = unStat.executeQuery(requete); //retourne des resultats
			while(lesResultats.next()) {
				Commande uneCommande = new Commande(
						lesResultats.getInt("id_commande"), lesResultats.getInt("id_client"),
						lesResultats.getString("date_commande"), lesResultats.getString("statut"),
						lesResultats.getFloat("montant_total"), lesResultats.getString("adresse_livraison")
						);
				lesCommandes.add(uneCommande);
			}
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return lesCommandes ;
	}
	
	public static  Commande selectWhereCommande (int id_commande ){
		Commande uneCommande = null;
		String requete ="select * from commande where id_commande = " + id_commande + ";";
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement();
			ResultSet lesResultats = unStat.executeQuery(requete); //retourne des resultats
			if(lesResultats.next()) {
				uneCommande = new Commande(
						lesResultats.getInt("id_commande"), lesResultats.getInt("id_client"),
						lesResultats.getString("date_commande"), lesResultats.getString("statut"),
						lesResultats.getFloat("montant_total"), lesResultats.getString("adresse_livraison")
						);
			}
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return uneCommande ;
	}
	
	/************************ Autres méthodes **********************/
	public static void executerRequete (String requete) {
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			unStat.execute(requete); //ne retroune aucun resultat 
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
	}
	
	public static int count(String table) {
		int nb = 0; 
		String requete ="select  count(*) as nb from " + table + " ;";
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			ResultSet unResultat = unStat.executeQuery(requete); 
			if (unResultat.next()) {
				nb = unResultat.getInt("nb");
			}
			unStat.close();
			uneConnexion.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de la requete : " + requete);
		}
		return nb ; 
	}
	 
	
}














