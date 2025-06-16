package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controleur.Controleur;
import controleur.Filelec;
import controleur.Technicien; 

public class VueConnexion extends JFrame implements ActionListener
{
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btConnexion = new JButton("Connexion");
	private JTextField txtEmail = new JTextField("a@gmail.com"); 
	private JPasswordField txtMdp = new JPasswordField("123"); 
	private JComboBox<String> txtRole = new JComboBox<String>();
	
	private JPanel panelForm = new JPanel (); 
	
	public VueConnexion() {
		
		//Changer le titre de la fenetre 
		this.setTitle("Application Filelec 2025");
		//dimmensionner la fenetre 
		this.setBounds(100, 100, 600, 300);
		//Désactiver le redimensionnement de la fenetre 
		this.setResizable(false);
		//Tuer l'application avec la croix 
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//supprimer le layout (style) par defaut 
		this.setLayout(null);
		//choisir une couleur de fond 
		this.getContentPane().setBackground(new Color(0, 123, 255));
		
		//construction du panel Formulaire 
		this.panelForm.setBackground(new Color(0, 123, 255));
		this.panelForm.setBounds(300, 60, 260, 180);
		this.panelForm.setLayout(new GridLayout(4,2));
		
		this.panelForm.add(new JLabel("Role: ")); 
		this.panelForm.add(this.txtRole);
		this.txtRole.addItem("Admin");
		this.txtRole.addItem("User");
		
		this.panelForm.add(new JLabel("Email : ")); 
		this.panelForm.add(this.txtEmail); 
		
		this.panelForm.add(new JLabel("MDP : ")); 
		this.panelForm.add(this.txtMdp);
		
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btConnexion); 
		
		
		this.add(this.panelForm); 
		
		//ajout du logo 
		ImageIcon uneImage = new ImageIcon("src/images/logo.png"); 
		JLabel unLogo = new JLabel(uneImage); 
		unLogo.setBounds(20, 20, 250, 250);
		this.add(unLogo);
		
		//rendre les boutons ecoutables //cliquables 
		this.btAnnuler.addActionListener(this);
		this.btConnexion.addActionListener(this);
		
		//rendre visible la fenetre 
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		 if(e.getSource() == this.btAnnuler) {
			 this.txtEmail.setText("");
			 this.txtMdp.setText("");
			 this.txtRole.setSelectedIndex(0);
		 }else if (e.getSource() == this.btConnexion) {
			 String email = this.txtEmail.getText(); 
			 String mdp = new String (this.txtMdp.getPassword());
			 String role = this.txtRole.getSelectedItem().toString();
			 
			 
			Technicien unTechnicien = Controleur.selectWhereTechnicien(email, mdp, role); 
			if (unTechnicien == null) {
				JOptionPane.showMessageDialog(this, 
						"Veuillez vérifier vos idenfiants.", "Erreur Connexion",
						JOptionPane.ERROR_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(this, 
						"Bienvenue à Gestion Filelec 2025\n"
						+ " Nom : "+unTechnicien.getNom_technicien()+"\n"
						+ " Prénom : "+unTechnicien.getPrenom_technicien()+"\n"
						, "Connexion Filelec",
						JOptionPane.INFORMATION_MESSAGE);
				
				//save technicien connecte 
				Filelec.setTechConnecte(unTechnicien);
				
				//instancier la classe VueGenerale 
				Filelec.creerVueGenerale(true);
				Filelec.rendreVisibleVueConnexion(false);
			}
		 }
	}
}





















