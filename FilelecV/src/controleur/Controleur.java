package controleur;

import java.util.ArrayList;

import modele.Modele;

public class Controleur {

	/************************ Gestion des clients ************/
	public static void insertClient(Client unClient) {
		//on va controler les données 
		
		//on va appeler le modele 
		Modele.insertClient(unClient);
	}
	public static void deleteClient(int idclient) {
		Modele.deleteClient(idclient);
	}
	
	public static void updateClient(Client unClient) {
		Modele.updateClient(unClient);
	}
	
	public static ArrayList<Client> selectAllClients(){
		return Modele.selectAllClients();
	}
	
	public static ArrayList<Client> selectLikeClients(String filtre){
		return Modele.selectLikeClients(filtre);
	}
	
	public static Client selectWhereClient(int idclient) {
		return Modele.selectWhereClient(idclient);
	}
	
	/**************** Gestion des techniciens ***************/
	public static Technicien selectWhereTechnicien(String email, String mdp, String role) {
		//controler l'email et le mdp 
		return Modele.selectWhereTechnicien(email, mdp, role); 
	}
	
	public static void insertTechnicien(Technicien unTechnicien) {
		//on va controler les données 
		
		//on va appeler le modele 
		Modele.insertTechnicien(unTechnicien);
	}
	public static void deleteTechnicien(int idTechnicien) {
		Modele.deleteTechnicien(idTechnicien);
	}
	
	public static void updateTechnicien(Technicien unTechnicien) {
		Modele.updateTechnicien(unTechnicien);
	}
	
	public static ArrayList<Technicien> selectAllTechniciens(){
		return Modele.selectAllTechniciens();
	}
	
	public static ArrayList<Technicien> selectLikeTechniciens(String filtre){
		return Modele.selectLikeTechniciens(filtre);
	}
	
	public static Technicien selectWhereTechnicien(int idTechnicien) {
		return Modele.selectWhereTechnicien(idTechnicien);
	}
	/************************ Gestion des Interventions ************/
	public static void insertIntervention(Intervention uneIntervention) {
		//on va controler les données 
		
		//on va appeler le modele 
		Modele.insertIntervention(uneIntervention);
	}
	public static void deleteIntervention(int idIntervention) {
		Modele.deleteIntervention(idIntervention);
	}
	
	public static void updateIntervention(Intervention uneIntervention) {
		Modele.updateIntervention(uneIntervention);
	}
	
	public static ArrayList<Intervention> selectAllInterventions(){
		return Modele.selectAllInterventions();
	}
	
	public static ArrayList<Intervention> selectLikeInterventions(String filtre){
		return Modele.selectLikeInterventions(filtre);
	}
	
	public static Intervention selectWhereIntervention(int idIntervention) {
		return Modele.selectWhereIntervention(idIntervention);
	}
	
	/************************ Gestion des Articles ************/
	public static void insertArticle(Article unArticle) {
		//on va controler les données 
		
		//on va appeler le modele 
		Modele.insertArticle(unArticle);
	}
	public static void deleteArticle(int idArticle) {
		Modele.deleteArticle(idArticle);
	}
	
	public static void updateArticle(Article unArticle) {
		Modele.updateArticle(unArticle);
	}
	
	public static ArrayList<Article> selectAllArticles(){
		return Modele.selectAllArticles();
	}
	
	public static ArrayList<Article> selectLikeArticles(String filtre){
		return Modele.selectLikeArticles(filtre);
	}
	
	public static Article selectWhereArticle(int idArticle) {
		return Modele.selectWhereArticle(idArticle);
	}
	
	/************************ Gestion des Commandes ************/
	public static void insertCommande(Commande uneCommande) {
		//on va controler les données 
		
		//on va appeler le modele 
		Modele.insertCommande(uneCommande);
	}
	public static void deleteCommande(int idCommande) {
		Modele.deleteClient(idCommande);
	}
	
	public static void updateCommande(Commande unCommande) {
		Modele.updateCommande(unCommande);
	}
	
	public static ArrayList<Commande> selectAllCommandes(){
		return Modele.selectAllCommandes();
	}
	
	public static ArrayList<Commande> selectLikeCommandes(String filtre){
		return Modele.selectLikeCommandes(filtre);
	}
	
	public static Commande selectWhereCommande(int idCommande) {
		return Modele.selectWhereCommande(idCommande);
	}
}
