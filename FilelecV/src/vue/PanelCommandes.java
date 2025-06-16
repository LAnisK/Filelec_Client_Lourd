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
	
	private JComboBox<String> txtIDClient = new JComboBox<String>();
	private JTextField txtDate = new JTextField();
	private JComboBox<String> txtStatut = new JComboBox<String>();
	private JTextField txtMontant = new JTextField();
	private JTextField txtAdresse = new JTextField();

	
	private JTable tableCommandes ;
	private Tableau tableauCommandes ;
	
	private JLabel lbNBCommandes = new JLabel();
	
	private JTextField txtFiltre = new JTextField(); 
	private JButton btFiltrer = new JButton("Filtrer"); 
	
	public PanelCommandes () {
		super ("Gestion des Commandes."); 
		this.txtStatut.addItem("en preparation");
		this.txtStatut.addItem("en chemin");
		this.txtStatut.addItem("livre");
		
		//Placement du formulaire 
		this.panelForm.setBackground(new Color(104, 76, 68));
		this.panelForm.setLayout(new GridLayout(6, 2));
		this.panelForm.setBounds(20, 80, 300, 300);
		this.panelForm.add(new JLabel("Le client : ")); 
		this.panelForm.add(this.txtIDClient); 
		
		this.panelForm.add(new JLabel("Date de la commande : ")); 
		this.panelForm.add(this.txtDate);
		
		this.panelForm.add(new JLabel("Statut de la commande : ")); 
		this.panelForm.add(this.txtStatut);
		
		this.panelForm.add(new JLabel("Montant de la commande : ")); 
		this.panelForm.add(this.txtMontant);
		
		this.panelForm.add(new JLabel("Adresse de livraison : ")); 
		this.panelForm.add(this.txtAdresse);
		
		this.panelForm.add(this.btAnnuler); 
		this.panelForm.add(this.btValider);
		
		this.add(this.panelForm); 
		
		//rendre les boutons ecoutables 
		this.btAnnuler.addActionListener(this);
		this.btValider.addActionListener(this);
		
		//installation de la JTable Clients 
		String entetes [] = {"Id Commande", "Client", "Date", "Statut", "Montant", "Adresse"};
		
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
					txtAdresse.setText(tableauCommandes.getValueAt(numLigne, 5).toString());
				 
					txtMontant.setText(tableauCommandes.getValueAt(numLigne, 4).toString());
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
	
	public void remplirClients() {
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
		 for (Commande unCommande : lesCommandes) {
			 matrice[i][0] = unCommande.getId_commande(); 
			 matrice[i][1] = unCommande.getId_client();
			 matrice[i][2] = unCommande.getDate_commande(); 
			 matrice[i][3] = unCommande.getStatut(); 
			 matrice[i][4] = unCommande.getMontant_total();
			 matrice[i][5] = unCommande.getAdresse_livraison();
			 i++;
		 }
		 return matrice ;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		 
		if (e.getSource() == this.btAnnuler) {
			this.txtDate.setText("");
			this.txtAdresse.setText("");
			this.txtMontant.setText("");
			this.btValider.setText("Valider");
			this.btSupprimer.setVisible(false);
		}
		else if (e.getSource() == this.btValider && this.btValider.getText().equals("Valider")) {
			//on récupere les données 
			String date = this.txtDate.getText(); 
			String adresse = this.txtAdresse.getText(); 
			float montant = Float.parseFloat(this.txtMontant.getText()); 
			String statut = this.txtStatut.getSelectedItem().toString(); 
			String tab[] = this.txtIDClient.getSelectedItem().toString().split("-");
			int idclient = Integer.parseInt(tab[0]);
			
			if (date.equals("") || adresse.equals("") || montant == 0 || statut.equals(""))
			{
				JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs");
			}else {
					//on instancie la classe Client 
					Commande unCommande = new Commande(idclient, date, statut, montant, adresse); 
					
					//on insere dans la BDD 
					Controleur.insertCommande(unCommande);
					
					//On affiche message de confirmation 
					JOptionPane.showMessageDialog(this, "Insertion réussie de la Commande");
					
					//actualisation de l'affichage 
					this.tableauCommandes.setDonnees(this.obtenirDonnees(""));
					this.lbNBCommandes.setText("Nombre de Commandes présents dans la BDD : " + tableauCommandes.getRowCount());
					
					//actualiser le comboBox de telephone 
					//PanelTelephones.remplirIdClient();
					
					//on vide les champs 
					this.txtDate.setText("");
					this.txtMontant.setText("");
					this.txtAdresse.setText("");
					this.btValider.setText("Valider");
					this.btSupprimer.setVisible(false);
				}
			
		}
		else if (e.getSource() == this.btSupprimer) {
			
			//on récupere l'id client 
			int numLigne , idcommande ; 
			numLigne = tableCommandes.getSelectedRow(); 
			idcommande = Integer.parseInt(this.tableauCommandes.getValueAt(numLigne, 0).toString()); 
			
			//on supprime le client 
			Controleur.deleteClient(idcommande);
			
			//On affiche message de confirmation 
			JOptionPane.showMessageDialog(this, "Suppression réussie du client");
			
			//actualisation de l'affichage 
			this.tableauCommandes.setDonnees(this.obtenirDonnees(""));
			this.lbNBCommandes.setText("Nombre de Commandes présents dans la BDD : " + tableauCommandes.getRowCount());
			
			//actualiser le comboBox de telephone 
			//PanelTelephones.remplirIdClient();
			
			//on vide les champs 
			this.txtDate.setText("");
			this.txtMontant.setText("");
			this.txtAdresse.setText("");
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
			Float montant = Float.parseFloat(this.txtMontant.getText()); 
			String statut = this.txtStatut.getSelectedItem().toString(); 
			String tab[] = this.txtIDClient.getSelectedItem().toString().split("-");
			int idclient = Integer.parseInt(tab[0]);
			
			//on instancie la classe client 
			Commande unCommande = new Commande(idcommande, idclient, date, statut, montant, adresse); 
			
			// on modifie dans la base 
			Controleur.updateCommande(unCommande);
			
			// on actualise l'affichage 
			this.tableauCommandes.setDonnees(this.obtenirDonnees(""));
			
			// on vide les champs 
			this.txtDate.setText("");
			this.txtMontant.setText("");
			this.txtAdresse.setText("");
			
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










