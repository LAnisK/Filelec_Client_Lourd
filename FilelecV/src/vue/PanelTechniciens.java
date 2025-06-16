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
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.Controleur;
import controleur.Filelec;
import controleur.Tableau;
import controleur.Technicien;

public class PanelTechniciens extends PanelPrincipal implements ActionListener
{
	private JPanel panelForm = new  JPanel (); 
	private JButton btAnnuler = new JButton("Annuler"); 
	private JButton btValider= new JButton("Valider");
	
	private JButton btSupprimer= new JButton("Supprimer");
	
	private JTextField txtNom = new JTextField(); 
	private JTextField txtPrenom = new JTextField(); 
	private JTextField txtEmail = new JTextField(); 
	private JTextField txtTel = new JTextField(); 
	private JPasswordField txtMdp = new JPasswordField();
	private JComboBox<String> txtRole = new JComboBox<String>();
	
	private JTable tableTechniciens;
	private Tableau tableauTechniciens ;
	
	private JLabel lbNBTechniciens = new JLabel();
	
	private JTextField txtFiltre = new JTextField(); 
	private JButton btFiltrer = new JButton("Filtrer"); 
	
	public PanelTechniciens () {
		super ("Gestion des techniciens."); 
		
		//Placement du formulaire 
		this.panelForm.setBackground(new Color(104, 76, 68));
		this.panelForm.setLayout(new GridLayout(7, 2));
		this.panelForm.setBounds(20, 80, 300, 300);
		this.panelForm.add(new JLabel("Nom du Technicien : ")); 
		this.panelForm.add(this.txtNom); 
		
		this.panelForm.add(new JLabel("Prénom du Technicien : ")); 
		this.panelForm.add(this.txtPrenom); 
		
		this.panelForm.add(new JLabel("Role Technicien : ")); 
		this.panelForm.add(this.txtRole);
		this.txtRole.addItem("Technicien");
		this.txtRole.addItem("admin");
		this.txtRole.addItem("User");
		
		this.panelForm.add(new JLabel("Email du Technicien : ")); 
		this.panelForm.add(this.txtEmail);
		
		this.panelForm.add(new JLabel("Téléphone du Technicien : ")); 
		this.panelForm.add(this.txtTel);
		
		this.panelForm.add(new JLabel("Mot de passe : ")); 
		this.panelForm.add(this.txtMdp);
		
		this.panelForm.add(this.btAnnuler); 
		this.panelForm.add(this.btValider);
		
		this.add(this.panelForm); 
		
		//rendre les boutons ecoutables 
		this.btAnnuler.addActionListener(this);
		this.btValider.addActionListener(this);
		
		if (Filelec.getTechConnecte().getRole_technicien().equalsIgnoreCase("user")) {
            
            this.panelForm.setVisible(false);
        } 
		
		//installation de la JTable Technicien 
		String entetes [] = {"Id Technicien","Nom", "Prénom", "Role", "Email", "Téléphone"};
		
		this.tableauTechniciens = new Tableau (this.obtenirDonnees("") , entetes);
		
		this.tableTechniciens = new JTable(this.tableauTechniciens);
		JScrollPane uneScroll = new JScrollPane(this.tableTechniciens); 
		uneScroll.setBounds(360, 80, 580, 340);
		this.add(uneScroll); 
		
		this.btSupprimer.setBounds(80, 400, 200, 40);
		this.add(this.btSupprimer);
		this.btSupprimer.addActionListener(this);
		this.btSupprimer.setVisible(false);
		
		//rendre les lignes de la table ecoutables sur click de la souris 
		this.tableTechniciens.addMouseListener(new MouseListener() {
			
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
					numLigne = tableTechniciens.getSelectedRow(); 
					btSupprimer.setVisible(true);
					txtNom.setText(tableauTechniciens.getValueAt(numLigne, 1).toString());
					txtPrenom.setText(tableauTechniciens.getValueAt(numLigne, 2).toString());

					txtEmail.setText(tableauTechniciens.getValueAt(numLigne, 4).toString());
					txtTel.setText(tableauTechniciens.getValueAt(numLigne, 5).toString());
					//on change le texte du bouton modifier 
					btValider.setText("Modifier");
				}
			}
		});
		
		//installation du nb de Technicien 
		this.lbNBTechniciens.setText("Nombre de Technicien présents dans la BDD : " + tableauTechniciens.getRowCount());
		Font unePolice = new Font ("Arial",Font.BOLD, 15); 
		this.lbNBTechniciens.setFont(unePolice);

		this.lbNBTechniciens.setBounds(450, 420, 400, 40);
		this.add(this.lbNBTechniciens); 
		
		//installation du filtre 
		this.txtFiltre.setBounds(650, 50, 150, 20);
		this.add(this.txtFiltre); 
		this.btFiltrer.setBounds(810, 50, 100, 20);
		this.add(this.btFiltrer);
		this.btFiltrer.addActionListener(this);
	}
	
	public Object [][] obtenirDonnees (String filtre){
		ArrayList<Technicien> lesTechniciens ;
		if (filtre.equals("")) {
			lesTechniciens = Controleur.selectAllTechniciens();
		}else {
			lesTechniciens = Controleur.selectLikeTechniciens(filtre); 
		}
		
		Object [][] matrice = new  Object [lesTechniciens.size()][6]; 
		 int i = 0; 
		 for (Technicien unTechnicien : lesTechniciens) {
			 matrice[i][0] = unTechnicien.getId_technicien(); 
			 matrice[i][1] = unTechnicien.getNom_technicien();
			 matrice[i][2] = unTechnicien.getPrenom_technicien(); 
			 matrice[i][3] = unTechnicien.getRole_technicien();
			 matrice[i][4] = unTechnicien.getEmail_technicien();
			 matrice[i][5] = unTechnicien.getTelephone_technicien();
			 i++;
		 }
		 return matrice ;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		 
		if (e.getSource() == this.btAnnuler) {
			this.txtNom.setText("");
			this.txtPrenom.setText("");
			this.txtEmail.setText("");
			this.txtTel.setText("");
			this.btValider.setText("Valider");
			this.btSupprimer.setVisible(false);
		}
		else if (e.getSource() == this.btValider && this.btValider.getText().equals("Valider")) {
			//on récupere les données 
			String nom = this.txtNom.getText(); 
			String prenom = this.txtPrenom.getText(); 
			String role = this.txtRole.getSelectedItem().toString(); 
			String email = this.txtEmail.getText(); 
			String tel = this.txtTel.getText(); 
			String mdp = new String (this.txtMdp.getPassword());
			
			if (nom.equals("") || prenom.equals("") || role.equals("") || mdp.equals("") || email.equals("")|| tel.equals(""))
			{
				JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs");
			}else {
					//on instancie la classe Technicien 
				Technicien unTechnicien = new Technicien(nom, prenom, email, mdp,  tel, role); 
					
					//on insere dans la BDD 
					Controleur.insertTechnicien(unTechnicien);
					
					//On affiche message de confirmation 
					JOptionPane.showMessageDialog(this, "Insertion réussie du Technicien");
					
					//actualisation de l'affichage 
					this.tableauTechniciens.setDonnees(this.obtenirDonnees(""));
					this.lbNBTechniciens.setText("Nombre de Techniciens présents dans la BDD : " + tableauTechniciens.getRowCount());
					
					
					
					//on vide les champs 
					this.txtNom.setText("");
					this.txtPrenom.setText("");
					this.txtEmail.setText("");
					this.txtTel.setText("");
					this.btValider.setText("Valider");
					this.btSupprimer.setVisible(false);
				}
			
		}
		else if (e.getSource() == this.btSupprimer) {
			
			//on récupere l'id Technicien 
			int numLigne , idtechnicien ; 
			numLigne = tableTechniciens.getSelectedRow(); 
			idtechnicien = Integer.parseInt(this.tableauTechniciens.getValueAt(numLigne, 0).toString()); 
			
			//on supprime le Technicien 
			Controleur.deleteTechnicien(idtechnicien);
			
			//On affiche message de confirmation 
			JOptionPane.showMessageDialog(this, "Suppression réussie du Technicien");
			
			//actualisation de l'affichage 
			this.tableauTechniciens.setDonnees(this.obtenirDonnees(""));
			this.lbNBTechniciens.setText("Nombre de technicien présents dans la BDD : " + tableauTechniciens.getRowCount());
			
		
			
			//on vide les champs 
			this.txtNom.setText("");
			this.txtPrenom.setText("");
			this.txtEmail.setText("");
			this.txtTel.setText("");
			this.btValider.setText("Valider");
			this.btSupprimer.setVisible(false);
		}
		else if (e.getSource() == this.btValider && this.btValider.getText().equals("Modifier")) {
			//on recupere l'id du Technicien 
			int numLigne , idtechnicien ; 
			numLigne = tableTechniciens.getSelectedRow(); 
			idtechnicien= Integer.parseInt(this.tableauTechniciens.getValueAt(numLigne, 0).toString());
			
			//on recupere les donnees nom, prenom, adresse, email et tel 
			String nom = this.txtNom.getText(); 
			String prenom = this.txtPrenom.getText(); 
			String role = this.txtRole.getSelectedItem().toString(); 
			String email = this.txtEmail.getText(); 
			String tel = this.txtTel.getText();
			String mdp = new String (this.txtMdp.getPassword());
			
			//on instancie la classe Technicien 
			Technicien unTechnicien = new Technicien(idtechnicien, nom, prenom, email, mdp, tel, role);
			
			// on modifie dans la base 
			Controleur.updateTechnicien(unTechnicien);
			
			// on actualise l'affichage 
			this.tableauTechniciens.setDonnees(this.obtenirDonnees(""));
			
			// on vide les champs 
			this.txtNom.setText("");
			this.txtPrenom.setText("");
			this.txtEmail.setText("");
			this.txtTel.setText("");
			this.btValider.setText("Valider");
			this.btSupprimer.setVisible(false);
		}
		else if (e.getSource() == this.btFiltrer) {
			//récupérer le filtre 
			String filtre = this.txtFiltre.getText(); 
			
			//filtrer les Technicien avec un LIKE.
			this.tableauTechniciens.setDonnees(this.obtenirDonnees(filtre));
		}
	}
}









