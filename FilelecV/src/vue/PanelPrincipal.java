package vue;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class PanelPrincipal extends JPanel 
{
	public PanelPrincipal(String titre) {
		//les caractéristiques communes de tous les panels 
		this.setBounds(20, 80, 950, 480);
		this.setBackground(new Color(104, 76, 68));
		this.setLayout(null);
		
		Font unePolice = new Font ("Arial",Font.BOLD, 20); 
		
		//afficher le titre 
		JLabel lbTitre = new JLabel(titre); 
		
		lbTitre.setFont(unePolice);
		
		lbTitre.setBounds(350, 10, 300, 40);
		this.add(lbTitre); 
		
		this.setVisible(false);
	}
}
