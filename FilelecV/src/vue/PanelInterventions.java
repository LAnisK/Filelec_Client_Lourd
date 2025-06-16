package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.Client;
import controleur.Commande;
import controleur.Controleur;
import controleur.Intervention;
import controleur.Tableau;
import controleur.Technicien;

public class PanelInterventions extends PanelPrincipal implements ActionListener
{
	private JPanel panelForm = new  JPanel (); 
	private JButton btAnnuler = new JButton("Annuler"); 
	private JButton btValider= new JButton("Valider");
	
	private JButton btSupprimer= new JButton("Supprimer");
	 
     
	private JComboBox<String> txtIDClient = new JComboBox<String>(); 
    private JComboBox<String> txtIDTechnicien = new JComboBox<String>(); 
    private JComboBox<String> txtIDCommande = new JComboBox<String>(); 
	private JTextField txtRapport = new JTextField(); 
	private JTextField txtDateDebut = new JTextField(); 
	private JTextField txtDateFin = new JTextField();
	
	private JComboBox<String> txtStatut = new JComboBox<String>();
	
	private JTable tableInterventions ;
	private Tableau tableauInterventions ;
	
	private JLabel lbNBInterventions = new JLabel();
	
	private JTextField txtFiltre = new JTextField(); 
	private JButton btFiltrer = new JButton("Filtrer"); 
	
	public PanelInterventions () {
		super ("Gestion des Interventions."); 
		
		//Placement du formulaire 
		this.panelForm.setBackground(new Color(0, 123, 255));
		this.panelForm.setLayout(new GridLayout(8, 2));
		this.panelForm.setBounds(20, 80, 300, 300);
		this.panelForm.add(new JLabel("Rapport Intervention : ")); 
		this.panelForm.add(this.txtRapport); 
		
		this.panelForm.add(new JLabel("Date Début : ")); 
		this.panelForm.add(this.txtDateDebut); 
		
		this.panelForm.add(new JLabel("Date De Fin : ")); 
		this.panelForm.add(this.txtDateFin);
		
		this.panelForm.add(new JLabel("Le Client : ")); 
		this.panelForm.add(this.txtIDClient);
		
		this.panelForm.add(new JLabel("Le Technicien : ")); 
		this.panelForm.add(this.txtIDTechnicien);
		
		this.panelForm.add(new JLabel("La Commande : ")); 
		this.panelForm.add(this.txtIDCommande);
		
		this.panelForm.add(new JLabel("Statut : ")); 
		this.panelForm.add(this.txtStatut);
		this.txtStatut.addItem("Terminer");
		this.txtStatut.addItem("En cours");
		this.txtStatut.addItem("Annuler");
		
		this.panelForm.add(this.btAnnuler); 
		this.panelForm.add(this.btValider);
		
		this.add(this.panelForm); 
		
		//rendre les boutons ecoutables 
		this.btAnnuler.addActionListener(this);
		this.btValider.addActionListener(this);
		
		//installation de la JTable Clients 
		String entetes [] = {"Id Inter","Rapport", "Date Début", "Date Fin", "Client", "Technicien", "Commande", "Statut"};
		
		this.tableauInterventions = new Tableau (this.obtenirDonnees("") , entetes);
		
		this.tableInterventions = new JTable(this.tableauInterventions);
		JScrollPane uneScroll = new JScrollPane(this.tableInterventions); 
		uneScroll.setBounds(360, 80, 580, 340);
		this.add(uneScroll); 
		
		this.btSupprimer.setBounds(80, 400, 200, 40);
		this.add(this.btSupprimer);
		this.btSupprimer.addActionListener(this);
		this.btSupprimer.setVisible(false);
		
		//rendre les lignes de la table ecoutables sur click de la souris 
		this.tableInterventions.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}
			
			@Override
			public void mouseExited(MouseEvent e) {	
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int numLigne = 0; 
				if (e.getClickCount() >=1) {
					numLigne = tableInterventions.getSelectedRow(); 
					btSupprimer.setVisible(true);
					txtRapport.setText(tableauInterventions.getValueAt(numLigne, 1).toString());
					txtDateDebut.setText(tableauInterventions.getValueAt(numLigne, 2).toString());
					txtDateFin.setText(tableauInterventions.getValueAt(numLigne, 3).toString());
					
					//on change le texte du bouton modifier 
					btValider.setText("Modifier");
				}
			}
		});
		
		//installation du nb de interventions 
		this.lbNBInterventions.setText("Nombre de interventions présents dans la BDD : " + tableauInterventions.getRowCount());
		Font unePolice = new Font ("Arial",Font.BOLD, 15); 
		this.lbNBInterventions.setFont(unePolice);

		this.lbNBInterventions.setBounds(450, 420, 400, 40);
		this.add(this.lbNBInterventions); 
		
		//installation du filtre 
		this.txtFiltre.setBounds(650, 50, 150, 20);
		this.add(this.txtFiltre); 
		this.btFiltrer.setBounds(810, 50, 100, 20);
		this.add(this.btFiltrer);
		this.btFiltrer.addActionListener(this);
		
		//remplir les id  
		remplirIDs();
		
	}
	
	public void remplirIDs () {
		txtIDClient.removeAllItems();
		ArrayList<Client> lesClients = Controleur.selectAllClients(); 
		for (Client unClient : lesClients) {
			txtIDClient.addItem(unClient.getId_client()+"-" +unClient.getNom_client());
		}
		
		txtIDCommande.removeAllItems();
		ArrayList<Commande> lesCommandes = Controleur.selectAllCommandes(); 
		for (Commande uneCommande : lesCommandes) {
			txtIDCommande.addItem(uneCommande.getId_commande()+"-" +uneCommande.getDate_commande());
		}
		
		txtIDTechnicien.removeAllItems();
		ArrayList<Technicien> lesTechniciens = Controleur.selectAllTechniciens(); 
		for (Technicien unTechnicien : lesTechniciens) {
			txtIDTechnicien.addItem(unTechnicien.getId_technicien()+"-" +unTechnicien.getNom_technicien());
		}
	}
	
	public Object [][] obtenirDonnees (String filtre){
		ArrayList<Intervention> lesInterventions ;
		if (filtre.equals("")) {
			lesInterventions = Controleur.selectAllInterventions();
		}else {
			lesInterventions = Controleur.selectLikeInterventions(filtre); 
		}
		
		Object [][] matrice = new  Object [lesInterventions.size()][8]; 
		 int i = 0; 
		 for (Intervention uneIntervention : lesInterventions) {
			 matrice[i][0] = uneIntervention.getId_intervention();
			 matrice[i][1] = uneIntervention.getRapport();
			 matrice[i][2] = uneIntervention.getDate_debut_inter();
			 matrice[i][3] = uneIntervention.getDate_fin_inter();
			 matrice[i][4] = uneIntervention.getId_client(); 
			 matrice[i][5] = uneIntervention.getId_technicien(); 
			 matrice[i][6] = uneIntervention.getId_commande();
			 matrice[i][7] = uneIntervention.getStatut_inter();
			 i++;
		 }
		 return matrice ;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		 
		if (e.getSource() == this.btAnnuler) {
			 
			this.txtRapport.setText("");
			this.txtDateDebut.setText("");
			this.txtDateFin.setText("");
			this.btValider.setText("Valider");
			this.btSupprimer.setVisible(false);
		}
		else if (e.getSource() == this.btValider && this.btValider.getText().equals("Valider")) {
			//on récupere les données 
			String dateDebut = this.txtDateDebut.getText(); 
			String dateFin = this.txtDateFin.getText(); 
			
			String rapport = this.txtRapport.getText(); 
			String statut = this.txtStatut.getSelectedItem().toString();
			
			String tab[] = this.txtIDClient.getSelectedItem().toString().split("-"); 
			int idclient = Integer.parseInt(tab[0]); 
			
			tab = this.txtIDCommande.getSelectedItem().toString().split("-"); 
			int idcommande = Integer.parseInt(tab[0]); 
			
			tab = this.txtIDTechnicien.getSelectedItem().toString().split("-"); 
			int idtechnicien = Integer.parseInt(tab[0]); 
			
			if (dateDebut.equals("") || dateFin.equals("") ||  rapport.equals(""))
			{
				JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs");
			}else {
					//on instancie la classe Intervention 
					Intervention uneIntervention = new Intervention(rapport,dateDebut, dateFin, idclient, idtechnicien, idcommande, statut); 
					
					//on insere dans la BDD 
					Controleur.insertIntervention(uneIntervention);
					
					//On affiche message de confirmation 
					JOptionPane.showMessageDialog(this, "Insertion réussie de l'intervention");
					
					//actualisation de l'affichage 
					this.tableauInterventions.setDonnees(this.obtenirDonnees(""));
					this.lbNBInterventions.setText("Nombre des interventions présents dans la BDD : " + tableauInterventions.getRowCount());
					
					//on vide les champs 
					this.txtRapport.setText("");
					this.txtDateDebut.setText("");
					this.txtDateFin.setText("");
					 
					 
					this.btValider.setText("Valider");
					this.btSupprimer.setVisible(false);
				}
			
		}
		else if (e.getSource() == this.btSupprimer) {
			
			//on récupere l'idcommande 
			int numLigne , idintervention ; 
			numLigne = tableInterventions.getSelectedRow(); 
			idintervention = Integer.parseInt(this.tableauInterventions.getValueAt(numLigne, 0).toString()); 
			
			//on supprime le intervention 
			Controleur.deleteIntervention(idintervention);
			
			//On affiche message de confirmation 
			JOptionPane.showMessageDialog(this, "Suppression réussie de l'intervention");
			
			//actualisation de l'affichage 
			this.tableauInterventions.setDonnees(this.obtenirDonnees(""));
			this.lbNBInterventions.setText("Nombre des interventions présentes dans la BDD : " + tableauInterventions.getRowCount());
			
			 
			
			//on vide les champs 
			this.txtRapport.setText("");
			this.txtDateDebut.setText("");
			this.txtDateFin.setText("");
			 
			 
			this.btValider.setText("Valider");
			this.btSupprimer.setVisible(false);
		}
		else if (e.getSource() == this.btValider && this.btValider.getText().equals("Modifier")) {
			//on recupere l'id du intervention 
			int numLigne , idintervention ; 
			numLigne = tableInterventions.getSelectedRow(); 
			idintervention = Integer.parseInt(this.tableauInterventions.getValueAt(numLigne, 0).toString());
			
			//on recupere les donnees  
			String dateDebut = this.txtDateDebut.getText(); 
			String dateFin = this.txtDateFin.getText(); 
			
			String rapport = this.txtRapport.getText();
			String statut = this.txtStatut.getSelectedItem().toString();
			
			String tab[] = this.txtIDClient.getSelectedItem().toString().split("-"); 
			int idclient = Integer.parseInt(tab[0]); 
			
			tab = this.txtIDCommande.getSelectedItem().toString().split("-"); 
			int idcommande = Integer.parseInt(tab[0]); 
			
			tab = this.txtIDTechnicien.getSelectedItem().toString().split("-"); 
			int idtechnicien = Integer.parseInt(tab[0]); 
			
			if (dateDebut.equals("") || dateFin.equals("") ||  rapport.equals(""))
			{
				JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs");
			}else {
					//on instancie la classe Intervention 
					Intervention uneIntervention = new Intervention(idintervention, rapport,dateDebut, dateFin, idclient, idtechnicien, idcommande, statut); 
					
					//on insere dans la BDD 
					Controleur.updateIntervention(uneIntervention);
					
			
					// on actualise l'affichage 
					this.tableauInterventions.setDonnees(this.obtenirDonnees(""));
				}
			
			//on vide les champs 
			this.txtRapport.setText("");
			this.txtDateDebut.setText("");
			this.txtDateFin.setText("");
			 
			 
			this.btValider.setText("Valider");
			this.btSupprimer.setVisible(false);
		}
		else if (e.getSource() == this.btFiltrer) {
			//récupérer le filtre 
			String filtre = this.txtFiltre.getText(); 
			
			//filtrer les clients avec un LIKE.
			this.tableauInterventions.setDonnees(this.obtenirDonnees(filtre));
		}
	}
}









