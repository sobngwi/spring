package org.sobngwi.techicien;
import org.sobngwi.interfaces.IMecanicien;
import org.sobngwi.modele.Moteur;

/**
 * 
 */

/**
 * @author suse
 *
 */
public class Mecanicien implements IMecanicien {

	private Moteur moteur;
	private String nom ;
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public Moteur getMoteur() {
		return moteur;
	}

	public void setMoteur(Moteur moteur) {
		this.moteur = moteur;
	}
	
	@Override
	public Moteur changerMoteur(Moteur moteur) {
		System.out.println(" Je sais changé le moteur [" + moteur + "]");
		return moteur;
	}
	
	
	

}
