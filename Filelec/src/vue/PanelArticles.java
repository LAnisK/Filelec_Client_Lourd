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

import controleur.Article;
import controleur.Controleur;
import controleur.Tableau;

public class PanelArticles extends PanelPrincipal implements ActionListener {

    private JPanel panelForm = new JPanel();
    private JButton btAnnuler = new JButton("Annuler");
    private JButton btValider = new JButton("Valider");
    private JButton btSupprimer = new JButton("Supprimer");

    private JTextField txtNom = new JTextField();
    private JTextField txtDescription = new JTextField();
    private JTextField txtPrix = new JTextField();
    private JTextField txtStock = new JTextField();
    private JComboBox<String> cbCategorie;
	private JComboBox<String> txtIdCat = new JComboBox<String>();
	private JComboBox<String> txtEtat = new JComboBox<String>();


    private JTable tableArticles;
    private Tableau tableauArticles;

    private JLabel lbNBArticles = new JLabel();

    private JTextField txtFiltre = new JTextField();
    private JButton btFiltrer = new JButton("Filtrer");

    public PanelArticles() {
        super("Gestion des articles.");

        // Placement du formulaire
        this.panelForm.setBackground(new Color(0, 123, 255));
        this.panelForm.setLayout(new GridLayout(7, 2));
        this.panelForm.setBounds(20, 80, 300, 300);
        this.panelForm.add(new JLabel("Nom de l'article : "));
        this.panelForm.add(this.txtNom);

        this.panelForm.add(new JLabel("Description : "));
        this.panelForm.add(this.txtDescription);

        this.panelForm.add(new JLabel("Prix : "));
        this.panelForm.add(this.txtPrix);

        this.panelForm.add(new JLabel("Stock : "));
        this.panelForm.add(this.txtStock);

        
        
        this.panelForm.add(new JLabel("Catégorie : ")); 
		this.panelForm.add(this.txtIdCat);
		this.txtIdCat.addItem("1");
		this.txtIdCat.addItem("2");
		
		this.panelForm.add(new JLabel("Etat : ")); 
		this.panelForm.add(this.txtEtat);
		this.txtEtat.addItem("satisfaisant");
		this.txtEtat.addItem("peu satisfaisant");
		this.txtEtat.addItem("inconnu");

        this.panelForm.add(this.btAnnuler);
        this.panelForm.add(this.btValider);

        this.add(this.panelForm);

        // Rendre les boutons écoutables
        this.btAnnuler.addActionListener(this);
        this.btValider.addActionListener(this);

        // Installation de la JTable Articles
        String entetes[] = {"Id Article", "Nom", "Description", "Prix", "Stock","Etat", "ID Catégorie"};

        this.tableauArticles = new Tableau(this.obtenirDonnees(""), entetes);

        this.tableArticles = new JTable(this.tableauArticles);
        JScrollPane uneScroll = new JScrollPane(this.tableArticles);
        uneScroll.setBounds(360, 80, 580, 340);
        this.add(uneScroll);

        this.btSupprimer.setBounds(80, 400, 200, 40);
        this.add(this.btSupprimer);
        this.btSupprimer.addActionListener(this);
        this.btSupprimer.setVisible(false);

        // Rendre les lignes de la table écoutables sur clic de la souris
        this.tableArticles.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                int numLigne = tableArticles.getSelectedRow();
                if (numLigne >= 0) {
                    btSupprimer.setVisible(true);
                    txtNom.setText(tableauArticles.getValueAt(numLigne, 1).toString());
                    txtDescription.setText(tableauArticles.getValueAt(numLigne, 2).toString());
                    txtPrix.setText(tableauArticles.getValueAt(numLigne, 3).toString());
                    txtStock.setText(tableauArticles.getValueAt(numLigne, 4).toString());
                    txtIdCat.setToolTipText(tableauArticles.getValueAt(numLigne, 5).toString());
                    txtEtat.setToolTipText(tableauArticles.getValueAt(numLigne, 6).toString());
                    btValider.setText("Modifier");
                }
            }
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
        });

        // Installation du compteur d'articles
        this.lbNBArticles.setText("Nombre d'articles : " + tableauArticles.getRowCount());
        this.lbNBArticles.setFont(new Font("Arial", Font.BOLD, 15));
        this.lbNBArticles.setBounds(450, 420, 400, 40);
        this.add(this.lbNBArticles);

        // Installation du filtre
        this.txtFiltre.setBounds(650, 50, 150, 20);
        this.add(this.txtFiltre);
        this.btFiltrer.setBounds(810, 50, 100, 20);
        this.add(this.btFiltrer);
        this.btFiltrer.addActionListener(this);
    }

    public Object[][] obtenirDonnees(String filtre) {
        ArrayList<Article> lesArticles = (filtre.equals("")) ? Controleur.selectAllArticles() : Controleur.selectLikeArticles(filtre);

        Object[][] matrice = new Object[lesArticles.size()][7];
        for (int i = 0; i < lesArticles.size(); i++) {
            Article unArticle = lesArticles.get(i);
            matrice[i][0] = unArticle.getId_article();
            matrice[i][1] = unArticle.getNom_article();
            matrice[i][2] = unArticle.getDescription_article();
            matrice[i][3] = unArticle.getPrix_article();
            matrice[i][4] = unArticle.getStock_article();
            matrice[i][5] = unArticle.getEtat();
            matrice[i][6] = unArticle.getId_cat();
        }
        return matrice;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btAnnuler) {
            this.txtNom.setText("");
            this.txtDescription.setText("");
            this.txtPrix.setText("");
            this.txtStock.setText("");
            this.txtIdCat.setSelectedIndex(0);
            this.txtEtat.setSelectedIndex(0);
            this.btValider.setText("Valider");
            this.btSupprimer.setVisible(false);
        } 
        else if (e.getSource() == this.btValider && this.btValider.getText().equals("Valider")) {
            // Récupération des données
            String nom = this.txtNom.getText();
            String description = this.txtDescription.getText();
            float prix = Float.parseFloat(this.txtPrix.getText());
            int stock = Integer.parseInt(this.txtStock.getText());
            int idCategorie = Integer.parseInt(this.txtIdCat.getSelectedItem().toString());
            String etat = this.txtEtat.getSelectedItem().toString();


            // Validation des champs
            if (nom.equals("") || description.equals("") || prix <= 0 || stock < 0) {
                JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs correctement.");
            } else {
                // Création de l'objet Article
                Article unArticle = new Article(nom, description, prix, stock, idCategorie, etat);

                // Insertion dans la BDD
                Controleur.insertArticle(unArticle);

                // Confirmation
                JOptionPane.showMessageDialog(this, "Insertion réussie de l'article.");

                // Actualisation de l'affichage
                this.tableauArticles.setDonnees(this.obtenirDonnees(""));

                // Réinitialisation des champs
                this.txtNom.setText("");
                this.txtDescription.setText("");
                this.txtPrix.setText("");
                this.txtStock.setText("");
                this.txtIdCat.setSelectedIndex(0);
                this.txtEtat.setSelectedIndex(0);
                this.btValider.setText("Valider");
                this.btSupprimer.setVisible(false);
            }
        } 
        else if (e.getSource() == this.btSupprimer) {
            // Suppression d'un article
            int numLigne = tableArticles.getSelectedRow();
            int idArticle = Integer.parseInt(this.tableauArticles.getValueAt(numLigne, 0).toString());

            Controleur.deleteArticle(idArticle);

            JOptionPane.showMessageDialog(this, "Suppression réussie de l'article.");

            // Actualisation de l'affichage
            this.tableauArticles.setDonnees(this.obtenirDonnees(""));

            // Réinitialisation des champs
            this.txtNom.setText("");
            this.txtDescription.setText("");
            this.txtPrix.setText("");
            this.txtStock.setText("");
            this.txtIdCat.setSelectedIndex(0);
            this.txtEtat.setSelectedIndex(0);
            this.btValider.setText("Valider");
            this.btSupprimer.setVisible(false);
        } 
        else if (e.getSource() == this.btValider && this.btValider.getText().equals("Modifier")) {
            // Modification d'un article
            int numLigne = tableArticles.getSelectedRow();
            int idArticle = Integer.parseInt(this.tableauArticles.getValueAt(numLigne, 0).toString());

            String nom = this.txtNom.getText();
            String description = this.txtDescription.getText();
            float prix = Float.parseFloat(this.txtPrix.getText());
            int stock = Integer.parseInt(this.txtStock.getText());
            int idCategorie = Integer.parseInt(this.txtIdCat.getSelectedItem().toString());
            String etat = this.txtEtat.getSelectedItem().toString();


            Article unArticle = new Article(idArticle, nom, description, prix, stock,idCategorie, etat);

            Controleur.updateArticle(unArticle);

            JOptionPane.showMessageDialog(this, "Modification réussie de l'article.");

            // Actualisation de l'affichage
            this.tableauArticles.setDonnees(this.obtenirDonnees(""));

            // Réinitialisation des champs
            this.txtNom.setText("");
            this.txtDescription.setText("");
            this.txtPrix.setText("");
            this.txtStock.setText("");
            this.txtIdCat.setSelectedIndex(0);
            this.btValider.setText("Valider");
            this.btSupprimer.setVisible(false);
        } 
        else if (e.getSource() == this.btFiltrer) {
            // Filtrage des articles
            String filtre = this.txtFiltre.getText();
            this.tableauArticles.setDonnees(this.obtenirDonnees(filtre));
        }
    }
    
}

