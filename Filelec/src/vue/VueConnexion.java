package vue;
 
import java.awt.Color;
import java.awt.Font;
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
		this.setBounds(100, 50, 900, 600);
		//Désactiver le redimensionnement de la fenetre 
		this.setResizable(false);
		//Tuer l'application avec la croix 
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//supprimer le layout (style) par defaut 
		this.setLayout(null);
		//choisir une couleur de fond 
		this.getContentPane().setBackground(new Color(0, 123, 255));
		
		Font unePolice = new Font("Arial", Font.ITALIC, 26);
		
		//construction du panel Formulaire 
		this.panelForm.setBackground(new Color(0, 123, 255));
		this.panelForm.setBounds(540, 200, 350, 200);
		this.panelForm.setLayout(new GridLayout(3,2));
		
		JLabel lbEmail = new JLabel("EMAIL : "); 
		this.panelForm.add(lbEmail); 
		this.panelForm.add(this.txtEmail);
		lbEmail.setFont(unePolice);
		
		JLabel lbMdp = new JLabel("MDP : "); 
		this.panelForm.add(lbMdp); 
		this.panelForm.add(this.txtMdp);
		lbMdp.setFont(unePolice);
		
		this.panelForm.add(new JLabel("Role Technicien : ")); 
		this.panelForm.add(this.txtRole);
		this.txtRole.addItem("Admin");
		this.txtRole.addItem("Technicien");
		this.txtRole.addItem("User");
		
		this.panelForm.add(this.btAnnuler); 
		this.panelForm.add(this.btConnexion);
		
		this.add(this.panelForm); 
		
		//ajout du logo 
		ImageIcon uneImage = new ImageIcon("src/images/logo.png"); 
		JLabel unLogo = new JLabel(uneImage); 
		unLogo.setBounds(30, 30, 500, 500);
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
	    } else if (e.getSource() == this.btConnexion) {
	        String email = this.txtEmail.getText(); 
	        String mdp = new String(this.txtMdp.getPassword()); 
	        String roleSelectionne = (String) this.txtRole.getSelectedItem();
	        
	        Technicien unTechnicien = Controleur.selectWhereTechnicien(email, mdp); 
	        if (unTechnicien == null) {
	            JOptionPane.showMessageDialog(this, 
	                    "Veuillez vérifier vos identifiants.", "Erreur Connexion",
	                    JOptionPane.ERROR_MESSAGE);
	        } else {
	            // Vérification du rôle
	            String roleTechnicien = unTechnicien.getRole_technicien();
	            if (!roleSelectionne.equalsIgnoreCase(roleTechnicien)) {
	                JOptionPane.showMessageDialog(this, 
	                        "Le rôle sélectionné ne correspond pas à votre compte.", 
	                        "Erreur de rôle",
	                        JOptionPane.ERROR_MESSAGE);
	                return;
	            }
	            
	            JOptionPane.showMessageDialog(this, 
	                    "Bienvenue à Gestion Orange 2025\n"
	                    + " Nom : "+unTechnicien.getNom_technicien()+"\n"
	                    + " Prénom : "+unTechnicien.getPrenom_technicien()+"\n"
	                    + " Rôle : "+roleTechnicien,
	                    "Connexion Orange",
	                    JOptionPane.INFORMATION_MESSAGE);
	            
	            // save technicien connecté avec son rôle
	            Filelec.setTechConnecte(unTechnicien);
	            
	            // instancier la classe VueGenerale 
	            Filelec.creerVueGenerale(true, roleTechnicien); // Passer le rôle à la vue générale
	            Filelec.rendreVisibleVueConnexion(false);
	        }
	    }
	}
}