package controleur;

import vue.VueConnexion;
import vue.VueGenerale;

public class Filelec {
	private static VueConnexion uneVueConnexion; 
	private static VueGenerale uneVueGenerale; 
	
	private static Technicien techConnecte ;
	
	
	public static Technicien getTechConnecte() {
		return techConnecte;
	}

	public static void setTechConnecte(Technicien techConnecte) {
		Filelec.techConnecte = techConnecte;
	}

	public static void main (String args[]) {
		uneVueConnexion = new VueConnexion(); 
	}
	
	public static void creerVueGenerale (boolean action) {
		if (action==true) {
			uneVueGenerale = new VueGenerale(); 
			uneVueGenerale.setVisible(true);
		}else {
			uneVueGenerale.dispose(); 
		}
	}
	public static void rendreVisibleVueConnexion (boolean action) {
		uneVueConnexion.setVisible(action);
	}
}
