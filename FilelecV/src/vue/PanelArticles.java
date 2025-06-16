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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.Article;

import controleur.Controleur;
import controleur.Tableau;

public class PanelArticles extends PanelPrincipal implements ActionListener
{
	private JPanel panelForm = new  JPanel (); 
	private JButton btAnnuler = new JButton("Annuler"); 
	private JButton btValider= new JButton("Valider");
	
	private JButton btSupprimer= new JButton("Supprimer");
	
	private JTextField txtNomA = new JTextField(); 
	private JTextField txtDescriptionA = new JTextField(); 
	private JTextField txtPrixA = new JTextField(); 
	private JTextField txtStockA = new JTextField(); 
	private JTextField txtCategorieA = new JTextField(); 
	
	private JTable tableArticles ;
	private Tableau tableauArticles ;
	
	private JLabel lbNBArticles = new JLabel();
	
	private JTextField txtFiltre = new JTextField(); 
	private JButton btFiltrer = new JButton("Filtrer");
	
	public PanelArticles () {
		super ("Gestion des Articles.");
		
		this.panelForm.setBackground(new Color(104, 76, 68));
		this.panelForm.setLayout(new GridLayout(6, 2));
		this.panelForm.setBounds(20, 80, 300, 300);
		this.panelForm.add(new JLabel("Nom Article : ")); 
		this.panelForm.add(this.txtNomA); 
		
		this.panelForm.add(new JLabel("Description : ")); 
		this.panelForm.add(this.txtDescriptionA); 
		
		this.panelForm.add(new JLabel("Prix : ")); 
		this.panelForm.add(this.txtPrixA);
		
		this.panelForm.add(new JLabel("Stock : ")); 
		this.panelForm.add(this.txtStockA);
		
		this.panelForm.add(new JLabel("Categorie : ")); 
		this.panelForm.add(this.txtCategorieA);
		
		this.panelForm.add(this.btAnnuler); 
		this.panelForm.add(this.btValider);
		
		this.add(this.panelForm);
		
		this.btAnnuler.addActionListener(this);
		this.btValider.addActionListener(this);
		
		String entetes [] = {"Id Article","Nom Article", "Description", "Prix", "Stock", "Categorie"};
		this.tableauArticles = new Tableau (this.obtenirDonnees("") , entetes);
		
		this.tableArticles = new JTable(this.tableauArticles);
		JScrollPane uneScroll = new JScrollPane(this.tableArticles); 
		uneScroll.setBounds(360, 80, 580, 340);
		this.add(uneScroll); 
		
		this.btSupprimer.setBounds(80, 400, 200, 40);
		this.add(this.btSupprimer);
		this.btSupprimer.addActionListener(this);
		this.btSupprimer.setVisible(false);
		
		this.tableArticles.addMouseListener(new MouseListener() {
			
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
					numLigne = tableArticles.getSelectedRow(); 
					btSupprimer.setVisible(true);
					txtNomA.setText(tableauArticles.getValueAt(numLigne, 1).toString());
					txtDescriptionA.setText(tableauArticles.getValueAt(numLigne, 2).toString());
					txtPrixA.setText(tableauArticles.getValueAt(numLigne, 3).toString());
					txtStockA.setText(tableauArticles.getValueAt(numLigne, 4).toString());
					txtCategorieA.setText(tableauArticles.getValueAt(numLigne, 5).toString());
					//on change le texte du bouton modifier 
					btValider.setText("Modifier");
				}
			}
		});
		
		this.lbNBArticles.setText("Nombre de clients présents dans la BDD : " + tableauArticles.getRowCount());
		Font unePolice = new Font ("Arial",Font.BOLD, 15); 
		this.lbNBArticles.setFont(unePolice);

		this.lbNBArticles.setBounds(450, 420, 400, 40);
		this.add(this.lbNBArticles); 
		
		//installation du filtre 
		this.txtFiltre.setBounds(650, 50, 150, 20);
		this.add(this.txtFiltre); 
		this.btFiltrer.setBounds(810, 50, 100, 20);
		this.add(this.btFiltrer);
		this.btFiltrer.addActionListener(this);
	}
	
	public Object [][] obtenirDonnees (String filtre){
		ArrayList<Article> lesArticles ;
		if (filtre.equals("")) {
			lesArticles = Controleur.selectAllArticles();
		}else {
			lesArticles = Controleur.selectLikeArticles(filtre); 
		}
		
		Object [][] matrice = new  Object [lesArticles.size()][6]; 
		 int i = 0; 
		 for (Article unArticle : lesArticles) {
			 matrice[i][0] = unArticle.getId_article(); 
			 matrice[i][1] = unArticle.getNom_article();
			 matrice[i][2] = unArticle.getDescription_article(); 
			 matrice[i][3] = unArticle.getPrix_article(); 
			 matrice[i][4] = unArticle.getStock_article();
			 matrice[i][5] = unArticle.getId_cat();
			 i++;
		 }
		 return matrice ;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		 
		if (e.getSource() == this.btAnnuler) {
			this.txtNomA.setText("");
			this.txtDescriptionA.setText("");
			this.txtPrixA.setText("");
			this.txtStockA.setText("");
			this.txtCategorieA.setText("");
			this.btValider.setText("Valider");
			this.btSupprimer.setVisible(false);
		}
		else if (e.getSource() == this.btValider && this.btValider.getText().equals("Valider")) {
			//on récupere les données 
			String nomA = this.txtNomA.getText(); 
			String descriptionA = this.txtDescriptionA.getText(); 
			float prixA = Float.parseFloat(this.txtPrixA.getText()); 
			int stockA = Integer.parseInt(this.txtStockA.getText()); 
			int categorieA =  Integer.parseInt(this.txtCategorieA.getText()); 
			
			if (nomA.equals("") || descriptionA.equals("") || prixA == 0 || stockA ==0|| categorieA==0)
			{
				JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs");
			}else {
					//on instancie la classe Article 
					Article unArticle = new Article(nomA, descriptionA, prixA, stockA, categorieA); 
					
					//on insere dans la BDD 
					Controleur.insertArticle(unArticle);
					
					//On affiche message de confirmation 
					JOptionPane.showMessageDialog(this, "Insertion réussie de l'article");
					
					//actualisation de l'affichage 
					this.tableauArticles.setDonnees(this.obtenirDonnees(""));
					this.lbNBArticles.setText("Nombre d'article présents dans la BDD : " + tableauArticles.getRowCount());
					
					//actualiser le comboBox de telephone 
					//PanelTelephones.remplirIdClient();
					
					//on vide les champs 
					this.txtNomA.setText("");
					this.txtDescriptionA.setText("");
					this.txtPrixA.setText("");
					this.txtStockA.setText("");
					this.txtCategorieA.setText("");
					this.btValider.setText("Valider");
					this.btSupprimer.setVisible(false);
				}
			
		}
		else if (e.getSource() == this.btSupprimer) {
			
			//on récupere l'id client 
			int numLigne , id_article ; 
			numLigne = tableArticles.getSelectedRow(); 
			id_article = Integer.parseInt(this.tableauArticles.getValueAt(numLigne, 0).toString()); 
			
			//on supprime le client 
			Controleur.deleteArticle(id_article);
			
			//On affiche message de confirmation 
			JOptionPane.showMessageDialog(this, "Suppression réussie de l'article");
			
			//actualisation de l'affichage 
			this.tableauArticles.setDonnees(this.obtenirDonnees(""));
			this.lbNBArticles.setText("Nombre de articles présents dans la BDD : " + tableauArticles.getRowCount());
			
			//actualiser le comboBox de telephone 
			//PanelTelephones.remplirIdClient();
			
			//on vide les champs 
			this.txtNomA.setText("");
			this.txtDescriptionA.setText("");
			this.txtPrixA.setText("");
			this.txtStockA.setText("");
			this.txtCategorieA.setText("");
			this.btValider.setText("Valider");
			this.btSupprimer.setVisible(false);
		}
		else if (e.getSource() == this.btValider && this.btValider.getText().equals("Modifier")) {
			//on recupere l'id du client 
			int numLigne , id_article ; 
			numLigne = tableArticles.getSelectedRow(); 
			id_article = Integer.parseInt(this.tableauArticles.getValueAt(numLigne, 0).toString());
			
			//on recupere les donnees nom, prenom, adresse, email et tel 
			String nomA = this.txtNomA.getText(); 
			String descriptionA = this.txtDescriptionA.getText(); 
			float prixA = Float.parseFloat(this.txtPrixA.getText()); 
			int stockA = Integer.parseInt(this.txtStockA.getText()); 
			int categorieA =  Integer.parseInt(this.txtCategorieA.getText()); 
			
			if (nomA.equals("") || descriptionA.equals("") || prixA == 0 || stockA ==0|| categorieA==0)
			{
				JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs");
			}else {
			
			//on instancie la classe client 
			Article unArticle = new Article(id_article, nomA, descriptionA, prixA, stockA, categorieA);
			
			// on modifie dans la base 
			Controleur.updateArticle(unArticle);
			
			// on actualise l'affichage 
			this.tableauArticles.setDonnees(this.obtenirDonnees(""));
			
			// on vide les champs 
			this.txtNomA.setText("");
			this.txtDescriptionA.setText("");
			this.txtPrixA.setText("");
			this.txtStockA.setText("");
			this.txtCategorieA.setText("");
			this.btValider.setText("Valider");
			this.btSupprimer.setVisible(false);
			}
		}
		else if (e.getSource() == this.btFiltrer) {
			//récupérer le filtre 
			String filtre = this.txtFiltre.getText(); 
			
			//filtrer les clients avec un LIKE.
			this.tableauArticles.setDonnees(this.obtenirDonnees(filtre));
		}
	}
}
