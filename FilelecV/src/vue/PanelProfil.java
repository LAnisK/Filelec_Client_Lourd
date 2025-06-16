package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controleur.Controleur;
import controleur.Filelec;
import controleur.Technicien;

public class PanelProfil extends PanelPrincipal implements ActionListener
{
	private JTextArea txtInfos = new JTextArea(); 
	private Technicien unTechnicien ;
	private JButton btModifier = new JButton("Modifier"); 
	
	private JPanel panelForm = new JPanel(); 
	private JTextField txtNom = new JTextField(); 
	private JTextField txtPrenom = new JTextField();
	private JTextField txtRole = new JTextField();
	private JTextField txtEmail = new JTextField();
	private JTextField txtTel = new JTextField();
	private JPasswordField txtMdp1 = new JPasswordField(); 
	private JPasswordField txtMdp2 = new JPasswordField(); 
	
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btValider = new JButton("Valider");
	
	public PanelProfil () {
		super ("Gestion du Profil Technicien.");
		
		//on récupère le technicien connecte 
		unTechnicien = Filelec.getTechConnecte();
		
		//afficher les infos dans le textArea 
		this.txtInfos.setText(""
				+ "\n______________ INFOS PROFIL _______________\n\n"
				+ " Nom technicien    : "+unTechnicien.getNom_technicien() + "\n\n"
				+ " Prénom technicien : "+unTechnicien.getPrenom_technicien() + "\n\n"
				+ " Role              : "+unTechnicien.getRole_technicien() + "\n\n"
				+ " Email contact     : "+unTechnicien.getEmail_technicien() + "\n\n"
				+ " Tel    technicien : "+unTechnicien.getTelephone_technicien()+ "\n\n"
				+ "\n___________________________________________\n\n"
				);
		this.txtInfos.setEditable(false);
		this.txtInfos.setBackground(new Color(104, 76, 68));
		this.txtInfos.setBounds(50, 80, 300, 250);
		this.add(this.txtInfos);
		
		//installation panel form 
		this.panelForm.setBackground(new Color(104, 76, 68));
		this.panelForm.setLayout(new GridLayout(8,2));
		this.panelForm.setBounds(420, 80, 400, 320);
		this.panelForm.add(new JLabel("Nom Technicien : ")); 
		this.panelForm.add(this.txtNom); 
		
		this.panelForm.add(new JLabel("Prénom Technicien : ")); 
		this.panelForm.add(this.txtPrenom);
		
		this.panelForm.add(new JLabel("Spécialité  : ")); 
		this.panelForm.add(this.txtRole);
		
		this.panelForm.add(new JLabel("Email Contact : ")); 
		this.panelForm.add(this.txtEmail);
		
		this.panelForm.add(new JLabel("Tel Contact : ")); 
		this.panelForm.add(this.txtTel);
		
		this.panelForm.add(new JLabel("Mot de passe : ")); 
		this.panelForm.add(this.txtMdp1);
		
		this.panelForm.add(new JLabel("Confirmation : ")); 
		this.panelForm.add(this.txtMdp2);
		
		this.panelForm.add(this.btAnnuler); 
		this.panelForm.add(this.btValider);
		
		this.panelForm.setVisible(false);
		this.add(this.panelForm);
		
		this.btModifier.setBounds(200, 360, 120, 40);
		this.add(this.btModifier); 
		
		//rendre les boutons ecoutables 
		this.btAnnuler.addActionListener(this);
		this.btModifier.addActionListener(this);
		this.btValider.addActionListener(this);
		
		if (unTechnicien.getRole_technicien().equalsIgnoreCase("user")) {
            this.btValider.setVisible(false);
            this.btModifier.setVisible(false);
            this.btAnnuler.setVisible(false);
        } else {
            this.btValider.setVisible(true);
            this.btModifier.setVisible(true);
            this.btAnnuler.setVisible(true);
        }
		 
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btAnnuler) {
			this.txtNom.setText("");
			this.txtPrenom.setText("");
			this.txtRole.setText("");
			this.txtEmail.setText("");
			this.txtTel.setText("");
			this.txtMdp1.setText("");
			this.txtMdp2.setText("");
			this.panelForm.setVisible(false);
		}
		
		else if (e.getSource() == this.btModifier) {
			this.txtNom.setText(unTechnicien.getNom_technicien());
			this.txtPrenom.setText(unTechnicien.getPrenom_technicien());
			this.txtRole.setText(unTechnicien.getRole_technicien());
			this.txtEmail.setText(unTechnicien.getEmail_technicien());
			this.txtTel.setText(unTechnicien.getTelephone_technicien());
			this.panelForm.setVisible(true);
	    
		}
		else if (e.getSource() == this.btValider) {
			String nom = this.txtNom.getText();  // substring(0, 50); 
			String prenom = this.txtPrenom.getText();
			String role = this.txtRole.getText();
			String email = this.txtEmail.getText();
			String tel = this.txtTel.getText(); 
			String mdp1 = new String (this.txtMdp1.getPassword()); 
			String mdp2 = new String (this.txtMdp2.getPassword());
			
			// mdp1.length()>6 au moins six caractères et sle substring pour limiter 15. 
			if (nom.equals("")||prenom.equals("")||role.equals("") 
					||email.equals("") ||tel.equals("") ||mdp1.equals("") ||mdp2.equals(""))
			{
				JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs");
			}
			else {
				if (mdp1.equals(mdp2)) {
					unTechnicien.setNom_technicien(nom);
					unTechnicien.setPrenom_technicien(prenom);
					unTechnicien.setRole_technicien(role);
					unTechnicien.setEmail_technicien(email);
					unTechnicien.setTelephone_technicien(tel);
					unTechnicien.setMdp_technicien(mdp1);
					
					Controleur.updateTechnicien(unTechnicien); 
					
					JOptionPane.showMessageDialog(this, "Modification Effectuée");
					
					//on actualise les données dans le textArea 
					this.txtInfos.setText(""
							+ "\n______________ INFOS PROFIL _______________\n\n"
							+ " Nom technicien    : "+unTechnicien.getNom_technicien() + "\n\n"
							+ " Prénom technicien : "+unTechnicien.getPrenom_technicien() + "\n\n"
							+ " Role              : "+unTechnicien.getRole_technicien() + "\n\n"
							+ " Email contact     : "+unTechnicien.getEmail_technicien() + "\n\n"
							+ " Tel    technicien : "+unTechnicien.getTelephone_technicien()+ "\n\n"
							+ "\n___________________________________________\n\n"
							);
					
					this.panelForm.setVisible(false);
					
				}else {
					JOptionPane.showMessageDialog(this, "Les deux mdp doivent être equivalents.");
				}
			}
		}
		
	}
}










