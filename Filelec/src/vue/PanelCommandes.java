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
import controleur.Tableau;

public class PanelCommandes extends PanelPrincipal implements ActionListener
{
	private JPanel panelForm = new  JPanel (); 
	private JButton btAnnuler = new JButton("Annuler"); 
	private JButton btValider= new JButton("Valider");
	
	private JButton btSupprimer= new JButton("Supprimer");
	
	private JComboBox <String> txtIDClient = new JComboBox<String>(); 
	private JTextField txtDate = new JTextField(); 
	private JComboBox <String> txtStatut = new JComboBox<String>(); 
	private JTextField txtMontant = new JTextField(); 
	private JTextField txtAdresse = new JTextField(); 
	
	private JTable tableCommandes ;
	private Tableau tableauCommandes ;
	
	private JLabel lbNBCommandes = new JLabel();
	
	private JTextField txtFiltre = new JTextField(); 
	private JButton btFiltrer = new JButton("Filtrer"); 
	
	public PanelCommandes () {
		super ("Gestion des commandes."); 
		
		this.txtStatut.addItem("En preparation");
		this.txtStatut.addItem("En chemin");
		this.txtStatut.addItem("Livré");
		//Placement du formulaire 
		this.panelForm.setBackground(new Color(0, 123, 255));
		this.panelForm.setLayout(new GridLayout(6, 2));
		this.panelForm.setBounds(20, 80, 300, 300);
		this.panelForm.add(new JLabel("le client : ")); 
		this.panelForm.add(this.txtIDClient); 
		
		this.panelForm.add(new JLabel("date commande : ")); 
		this.panelForm.add(this.txtDate); 
		
		this.panelForm.add(new JLabel("Statut : ")); 
		this.panelForm.add(this.txtStatut);
		
		this.panelForm.add(new JLabel("montant de la commande : ")); 
		this.panelForm.add(this.txtMontant);
		
		this.panelForm.add(new JLabel("adresse du livraison : ")); 
		this.panelForm.add(this.txtAdresse);
		
		this.panelForm.add(this.btAnnuler); 
		this.panelForm.add(this.btValider);
		
		this.add(this.panelForm); 
		
		//rendre les boutons ecoutables 
		this.btAnnuler.addActionListener(this);
		this.btValider.addActionListener(this);
		
		//installation de la JTable Clients 
		String entetes [] = {"Id Commande","Client", "Date", "statut", "montant", "adresse"};
		
		this.tableauCommandes = new Tableau (this.obtenirDonnees("") , entetes);
		
		this.tableCommandes = new JTable(this.tableauCommandes);
		JScrollPane uneScroll = new JScrollPane(this.tableCommandes); 
		uneScroll.setBounds(360, 80, 580, 340);
		this.add(uneScroll); 
		
		this.btSupprimer.setBounds(80, 400, 200, 40);
		this.add(this.btSupprimer);
		this.btSupprimer.addActionListener(this);
		this.btSupprimer.setVisible(false);
		
		//rendre les lignes de la table ecoutables sur click de la souris 
		this.tableCommandes.addMouseListener(new MouseListener() {
			
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
					numLigne = tableCommandes.getSelectedRow(); 
					btSupprimer.setVisible(true);
					txtDate.setText(tableauCommandes.getValueAt(numLigne, 2).toString());
					txtAdresse.setText(tableauCommandes.getValueAt(numLigne, 3).toString());
					txtMontant.setText(tableauCommandes.getValueAt(numLigne, 5).toString());
					//on change le texte du bouton modifier 
					btValider.setText("Modifier");
				}
			}
		});
		
		//installation du nb de clients 
		this.lbNBCommandes.setText("Nombre de clients présents dans la BDD : " + tableauCommandes.getRowCount());
		Font unePolice = new Font ("Arial",Font.BOLD, 15); 
		this.lbNBCommandes.setFont(unePolice);

		this.lbNBCommandes.setBounds(450, 420, 400, 40);
		this.add(this.lbNBCommandes); 
		
		//installation du filtre 
		this.txtFiltre.setBounds(650, 50, 150, 20);
		this.add(this.txtFiltre); 
		this.btFiltrer.setBounds(810, 50, 100, 20);
		this.add(this.btFiltrer);
		this.btFiltrer.addActionListener(this);
		remplirClients();
	}
	
	public void remplirClients () {
		txtIDClient.removeAllItems();
		ArrayList<Client> lesClients = Controleur.selectAllClients();
		for (Client unClient : lesClients) {
			txtIDClient.addItem(unClient.getId_client()+"-"+unClient.getNom_client());
		}
	}
	
	public Object [][] obtenirDonnees (String filtre){
		ArrayList<Commande> lesCommandes ;
		if (filtre.equals("")) {
			lesCommandes = Controleur.selectAllCommandes();
		}else {
			lesCommandes = Controleur.selectLikeCommandes(filtre); 
		}
		
		Object [][] matrice = new  Object [lesCommandes.size()][6]; 
		 int i = 0; 
		 for (Commande uneCommande : lesCommandes) {
			 matrice[i][0] = uneCommande.getId_commande(); 
			 matrice[i][1] = uneCommande.getId_client();
			 matrice[i][2] = uneCommande.getId_commande(); 
			 matrice[i][3] = uneCommande.getStatut(); 
			 matrice[i][4] = uneCommande.getMontant_total();
			 matrice[i][5] = uneCommande.getAdresse_livraison();
			 i++;
		 }
		 return matrice ;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		 
		if (e.getSource() == this.btAnnuler) {
		
			this.txtAdresse.setText("");
			this.txtDate.setText("");
			this.txtMontant.setText("");
			this.btValider.setText("Valider");
			this.btSupprimer.setVisible(false);
		}
		else if (e.getSource() == this.btValider && this.btValider.getText().equals("Valider")) {
			//on récupere les données 
			String date = this.txtDate.getText(); 
			String adresse = this.txtAdresse.getText(); 
			String statut = this.txtStatut.getSelectedItem().toString(); 
			float montant = Float.parseFloat(this.txtMontant.getText()); 
			String tab[] = this.txtIDClient.getSelectedItem().toString().split("-");
			int idclient = Integer.parseInt(tab[0]);
			
			
			if (date.equals("") || statut.equals("") || montant == 0 || adresse.equals("")  )
			{
				JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs");
			}else {
					//on instancie la classe Client 
					Commande uneCommande = new Commande(idclient, date, statut, montant, adresse);
					
					//on insere dans la BDD 
					Controleur.insertCommande(uneCommande);
					
					//On affiche message de confirmation 
					JOptionPane.showMessageDialog(this, "Insertion réussie d'une Commande");
					
					//actualisation de l'affichage 
					this.tableauCommandes.setDonnees(this.obtenirDonnees(""));
					this.lbNBCommandes.setText("Nombre de clients présents dans la BDD : " + tableauCommandes.getRowCount());
					
					//actualiser le comboBox de telephone 
					//PanelTelephones.remplirIdClient();
					
					//on vide les champs 
					this.txtAdresse.setText("");
					this.txtDate.setText("");
					this.txtMontant.setText("");
					this.btValider.setText("Valider");
					this.btSupprimer.setVisible(false);
				}
			
		}
		else if (e.getSource() == this.btSupprimer) {
			
			//on récupere l'id client 
			int numLigne , idclient ; 
			numLigne = tableCommandes.getSelectedRow(); 
			idclient = Integer.parseInt(this.tableauCommandes.getValueAt(numLigne, 0).toString()); 
			
			//on supprime le client 
			Controleur.deleteClient(idclient);
			
			//On affiche message de confirmation 
			JOptionPane.showMessageDialog(this, "Suppression réussie du client");
			
			//actualisation de l'affichage 
			this.tableauCommandes.setDonnees(this.obtenirDonnees(""));
			this.lbNBCommandes.setText("Nombre de clients présents dans la BDD : " + tableauCommandes.getRowCount());
			
			//actualiser le comboBox de telephone 
			//PanelTelephones.remplirIdClient();
			
			//on vide les champs 
			this.txtAdresse.setText("");
			this.txtDate.setText("");
			this.txtMontant.setText("");
			this.btValider.setText("Valider");
			this.btSupprimer.setVisible(false);
		}
		else if (e.getSource() == this.btValider && this.btValider.getText().equals("Modifier")) {
			//on recupere l'id du client 
			int numLigne , idcommande ; 
			numLigne = tableCommandes.getSelectedRow(); 
			idcommande = Integer.parseInt(this.tableauCommandes.getValueAt(numLigne, 0).toString());
			
			//on recupere les donnees nom, prenom, adresse, email et tel 
			String date = this.txtDate.getText(); 
			String adresse = this.txtAdresse.getText(); 
			String statut = this.txtStatut.getSelectedItem().toString(); 
			float montant = Float.parseFloat(this.txtMontant.getText()); 
			String tab[] = this.txtIDClient.getSelectedItem().toString().split("-");
			int idclient = Integer.parseInt(tab[0]);
			
			//on instancie la classe client 
			Commande uneCommande = new Commande(idclient, date, statut, montant, adresse);
			
			// on modifie dans la base 
			Controleur.updateCommande(uneCommande);
			
			// on actualise l'affichage 
			this.tableauCommandes.setDonnees(this.obtenirDonnees(""));
			
			// on vide les champs 
			this.txtAdresse.setText("");
			this.txtDate.setText("");
			this.txtMontant.setText("");
			this.btValider.setText("Valider");
			this.btSupprimer.setVisible(false);
		}
		else if (e.getSource() == this.btFiltrer) {
			//récupérer le filtre 
			String filtre = this.txtFiltre.getText(); 
			
			//filtrer les clients avec un LIKE.
			this.tableauCommandes.setDonnees(this.obtenirDonnees(filtre));
		}
	}
}









