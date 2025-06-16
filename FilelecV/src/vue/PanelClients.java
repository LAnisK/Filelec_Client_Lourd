package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
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
import controleur.Controleur;
import controleur.Filelec;
import controleur.Tableau;

public class PanelClients extends PanelPrincipal implements ActionListener
{
	private JPanel panelForm = new  JPanel (); 
	private JButton btAnnuler = new JButton("Annuler"); 
	private JButton btValider= new JButton("Valider");
	
	private JButton btSupprimer= new JButton("Supprimer");
	
	private JTextField txtNom = new JTextField(); 
	private JTextField txtPrenom = new JTextField(); 
	private JTextField txtAdresse = new JTextField(); 
	private JTextField txtEmail = new JTextField(); 
	private JTextField txtTel = new JTextField(); 
	private JComboBox<String> type_client = new JComboBox<String>(new String[] {"Particulier", "Professionnel"}); 
	private JTable tableClients ;
	private Tableau tableauClients ;
	
	private JLabel lbNBClients = new JLabel();
	
	private JTextField txtFiltre = new JTextField(); 
	private JButton btFiltrer = new JButton("Filtrer"); 
	
	public PanelClients () {
		super ("Gestion des clients."); 
		
		//Placement du formulaire 
		this.panelForm.setBackground(new Color(104, 76, 68));
		this.panelForm.setLayout(new GridLayout(7, 2));
		this.panelForm.setBounds(20, 80, 300, 300);
		this.panelForm.add(new JLabel("Nom du client : ")); 
		this.panelForm.add(this.txtNom); 
		
		this.panelForm.add(new JLabel("Prénom du client : ")); 
		this.panelForm.add(this.txtPrenom); 
		
		this.panelForm.add(new JLabel("Adresse Postale : ")); 
		this.panelForm.add(this.txtAdresse);
		
		this.panelForm.add(new JLabel("Email du client : ")); 
		this.panelForm.add(this.txtEmail);
		
		this.panelForm.add(new JLabel("Téléphone du client : ")); 
		this.panelForm.add(this.txtTel);
		
		this.panelForm.add(new JLabel("Type du client : ")); 
		this.panelForm.add(this.type_client);
		
		this.panelForm.add(this.btAnnuler); 
		this.panelForm.add(this.btValider);
		
		this.add(this.panelForm); 
		
		//rendre les boutons ecoutables 
		this.btAnnuler.addActionListener(this);
		this.btValider.addActionListener(this);
		
		
		if (Filelec.getTechConnecte().getRole_technicien().equalsIgnoreCase("user")) {
            
            this.panelForm.setVisible(false);
        } 
		
		
		 
		//installation de la JTable Clients 
		String entetes [] = {"Id Client","Nom", "Prénom", "Adresse", "Email", "Téléphone", "Type"};
		
		this.tableauClients = new Tableau (this.obtenirDonnees("") , entetes);
		
		this.tableClients = new JTable(this.tableauClients);
		JScrollPane uneScroll = new JScrollPane(this.tableClients); 
		uneScroll.setBounds(360, 80, 580, 340);
		this.add(uneScroll); 
		
		this.btSupprimer.setBounds(80, 400, 200, 40);
		this.add(this.btSupprimer);
		this.btSupprimer.addActionListener(this);
		this.btSupprimer.setVisible(false);
		
		//rendre les lignes de la table ecoutables sur click de la souris 
		this.tableClients.addMouseListener(new MouseListener() {
			
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
					numLigne = tableClients.getSelectedRow(); 
					btSupprimer.setVisible(true);
					txtNom.setText(tableauClients.getValueAt(numLigne, 1).toString());
					txtPrenom.setText(tableauClients.getValueAt(numLigne, 2).toString());
					txtAdresse.setText(tableauClients.getValueAt(numLigne, 3).toString());
					txtEmail.setText(tableauClients.getValueAt(numLigne, 4).toString());
					txtTel.setText(tableauClients.getValueAt(numLigne, 5).toString());
					//on change le texte du bouton modifier 
					btValider.setText("Modifier");
				}
			}
		});
		
		//installation du nb de clients 
		this.lbNBClients.setText("Nombre de clients présents dans la BDD : " + tableauClients.getRowCount());
		Font unePolice = new Font ("Arial",Font.BOLD, 15); 
		this.lbNBClients.setFont(unePolice);

		this.lbNBClients.setBounds(450, 420, 400, 40);
		this.add(this.lbNBClients); 
		
		//installation du filtre 
		this.txtFiltre.setBounds(650, 50, 150, 20);
		this.add(this.txtFiltre); 
		this.btFiltrer.setBounds(810, 50, 100, 20);
		this.add(this.btFiltrer);
		this.btFiltrer.addActionListener(this);
	}
	
	public Object [][] obtenirDonnees (String filtre){
		ArrayList<Client> lesClients ;
		if (filtre.equals("")) {
			lesClients = Controleur.selectAllClients();
		}else {
			lesClients = Controleur.selectLikeClients(filtre); 
		}
		
		Object [][] matrice = new  Object [lesClients.size()][7]; 
		 int i = 0; 
		 for (Client unClient : lesClients) {
			 matrice[i][0] = unClient.getId_client(); 
			 matrice[i][1] = unClient.getNom_client();
			 matrice[i][2] = unClient.getPrenom_client(); 
			 matrice[i][3] = unClient.getAdresse_client(); 
			 matrice[i][4] = unClient.getEmail_client();
			 matrice[i][5] = unClient.getTel_client();
			 matrice[i][6] = unClient.getType_client();
			 i++;
		 }
		 return matrice ;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		 
		if (e.getSource() == this.btAnnuler) {
			this.txtNom.setText("");
			this.txtPrenom.setText("");
			this.txtAdresse.setText("");
			this.txtEmail.setText("");
			this.txtTel.setText("");
			this.btValider.setText("Valider");
			this.btSupprimer.setVisible(false);
		}
		else if (e.getSource() == this.btValider && this.btValider.getText().equals("Valider")) {
			//on récupere les données 
			String nom = this.txtNom.getText(); 
			String prenom = this.txtPrenom.getText(); 
			String adresse = this.txtAdresse.getText(); 
			String email = this.txtEmail.getText(); 
			String tel = this.txtTel.getText(); 
			String typeC = this.type_client.getSelectedItem().toString();
			
			if (nom.equals("") || prenom.equals("") || adresse.equals("") || email.equals("")|| tel.equals(""))
			{
				JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs");
			}else {
					//on instancie la classe Client 
					Client unClient = new Client(nom, prenom, adresse, email, tel, "", "","",typeC,""); 
					
					//on insere dans la BDD 
					Controleur.insertClient(unClient);
					
					//On affiche message de confirmation 
					JOptionPane.showMessageDialog(this, "Insertion réussie du client");
					
					//actualisation de l'affichage 
					this.tableauClients.setDonnees(this.obtenirDonnees(""));
					this.lbNBClients.setText("Nombre de clients présents dans la BDD : " + tableauClients.getRowCount());
					
					//actualiser le comboBox de telephone 
					//PanelTelephones.remplirIdClient();
					
					//on vide les champs 
					this.txtNom.setText("");
					this.txtPrenom.setText("");
					this.txtAdresse.setText("");
					this.txtEmail.setText("");
					this.txtTel.setText("");
					this.btValider.setText("Valider");
					this.btSupprimer.setVisible(false);
				}
			
		}
		else if (e.getSource() == this.btSupprimer) {
			
			//on récupere l'id client 
			int numLigne , idclient ; 
			numLigne = tableClients.getSelectedRow(); 
			idclient = Integer.parseInt(this.tableauClients.getValueAt(numLigne, 0).toString()); 
			
			//on supprime le client 
			Controleur.deleteClient(idclient);
			
			//On affiche message de confirmation 
			JOptionPane.showMessageDialog(this, "Suppression réussie du client");
			
			//actualisation de l'affichage 
			this.tableauClients.setDonnees(this.obtenirDonnees(""));
			this.lbNBClients.setText("Nombre de clients présents dans la BDD : " + tableauClients.getRowCount());
			
			//actualiser le comboBox de telephone 
			//PanelTelephones.remplirIdClient();
			
			//on vide les champs 
			this.txtNom.setText("");
			this.txtPrenom.setText("");
			this.txtAdresse.setText("");
			this.txtEmail.setText("");
			this.txtTel.setText("");
			this.btValider.setText("Valider");
			this.btSupprimer.setVisible(false);
		}
		else if (e.getSource() == this.btValider && this.btValider.getText().equals("Modifier")) {
			//on recupere l'id du client 
			int numLigne , idclient ; 
			numLigne = tableClients.getSelectedRow(); 
			idclient = Integer.parseInt(this.tableauClients.getValueAt(numLigne, 0).toString());
			
			//on recupere les donnees nom, prenom, adresse, email et tel 
			String nom = this.txtNom.getText(); 
			String prenom = this.txtPrenom.getText(); 
			String adresse = this.txtAdresse.getText(); 
			String email = this.txtEmail.getText(); 
			String tel = this.txtTel.getText();
			String typeC = this.type_client.getSelectedItem().toString();
			
			//on instancie la classe client 
			Client unClient = new Client(idclient, nom, prenom, adresse, email, tel,"", "", "", typeC, "");
			
			// on modifie dans la base 
			Controleur.updateClient(unClient);
			
			// on actualise l'affichage 
			this.tableauClients.setDonnees(this.obtenirDonnees(""));
			
			// on vide les champs 
			this.txtNom.setText("");
			this.txtPrenom.setText("");
			this.txtAdresse.setText("");
			this.txtEmail.setText("");
			this.txtTel.setText("");
			this.btValider.setText("Valider");
			this.btSupprimer.setVisible(false);
		}
		else if (e.getSource() == this.btFiltrer) {
			//récupérer le filtre 
			String filtre = this.txtFiltre.getText(); 
			
			//filtrer les clients avec un LIKE.
			this.tableauClients.setDonnees(this.obtenirDonnees(filtre));
		}
	}
}









