package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controleur.Filelec;
 

public class VueGenerale extends JFrame implements ActionListener
{
	private JPanel panelMenu = new JPanel(); 
	private JButton btQuitter = new JButton("Quitter");
	private JButton btClients = new JButton("Clients");
	private JButton btTechniciens = new JButton("Techniciens");
	private JButton btCommandes = new JButton("Commandes");
	private JButton btInterventions= new JButton("Interventions");
	private JButton btArticles = new JButton("Articles");
	private JButton btProfil = new JButton("Profil");
	
	//creation des panels 
	private static PanelProfil unPanelProfil = new PanelProfil(); 
	private static PanelClients unPanelClients = new PanelClients(); 
	private static PanelTechniciens unPanelTechniciens = new PanelTechniciens();
	private static PanelCommandes unPanelCommandes = new PanelCommandes();
	private static PanelInterventions unPanelInterventions = new PanelInterventions();
	private static PanelArticles unPanelArticles = new PanelArticles();
	
	
	public VueGenerale() {
		this.setTitle("Application Filelec 2025");
		this.setBounds(100, 100, 1000, 600);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null); 
		this.getContentPane().setBackground(new Color(104, 76, 68));
		
		//construction du Panel Menu 
		this.panelMenu.setBackground(new Color(104, 76, 68));
		this.panelMenu.setBounds(50, 10, 900, 40);
		this.panelMenu.setLayout(new GridLayout(1,7));
		
		this.panelMenu.add(this.btProfil); 
		this.panelMenu.add(this.btClients); 
		this.panelMenu.add(this.btTechniciens);
		this.panelMenu.add(this.btCommandes);
		this.panelMenu.add(this.btInterventions);
		this.panelMenu.add(this.btArticles);
		this.panelMenu.add(this.btQuitter);
		this.add(this.panelMenu); 
		
		//rendre les boutons ecoutables 
		this.btProfil.addActionListener(this);
		this.btClients.addActionListener(this);
		this.btTechniciens.addActionListener(this);
		this.btCommandes.addActionListener(this);
		this.btInterventions.addActionListener(this);
		this.btArticles.addActionListener(this);
		this.btQuitter.addActionListener(this);
		
		//Ajoute des panels dans la fenetre
		this.add(unPanelProfil);
		this.add(unPanelClients); 
		this.add(unPanelTechniciens); 
		this.add(unPanelCommandes); 
		this.add(unPanelInterventions); 
		this.add(unPanelArticles); 
		
		this.setVisible(false);
	}
	
	public void afficherPanel (int choix) {
		unPanelProfil.setVisible(false);
		unPanelClients.setVisible(false);
		unPanelTechniciens.setVisible(false);
		unPanelCommandes.setVisible(false);
		unPanelInterventions.setVisible(false);
		unPanelArticles.setVisible(false);
		switch (choix) {
		case 1 : unPanelClients.setVisible(true); break;
		case 2 : unPanelTechniciens.setVisible(true); break;
		case 3 : unPanelCommandes.setVisible(true); break;
		case 4 : unPanelInterventions.setVisible(true); break;
		case 5 : 
			 
			unPanelArticles.setVisible(true); break;
		case 0 : unPanelProfil.setVisible(true);break;
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		 if (e.getSource() == this.btQuitter) {
			 int retour = JOptionPane.showConfirmDialog(this, 
					 "Voulez-vous quitter l'application ?", 
					 "Quitter l'application", JOptionPane.YES_NO_OPTION);
			 if (retour ==0) {
				 	Filelec.creerVueGenerale(false);
				 	Filelec.rendreVisibleVueConnexion(true);
			 }
		 }
		 else if (e.getSource() == this.btProfil) {
			 this.afficherPanel(0);
		 }
		 else if (e.getSource() == this.btClients) {
			 this.afficherPanel(1);
		 }
		 else if (e.getSource() == this.btTechniciens) {
			 this.afficherPanel(2);
		 }
		 else if (e.getSource() == this.btCommandes) {
			 this.afficherPanel(3);
		 }
		 else if (e.getSource() == this.btInterventions) {
			 this.afficherPanel(4);
		 }
		 else if (e.getSource() == this.btArticles) {
			 this.afficherPanel(5);
		 }
	}

}
