/**
 * 
 */
package org.sobngwi.techicien;

import org.sobngwi.interfaces.IElectricien;
import org.sobngwi.interfaces.IInformaticien;
import org.sobngwi.interfaces.IMecanicien;
import org.sobngwi.interfaces.IPlombier;
import org.sobngwi.modele.Moteur;

/**
 * @author Alain Narcisse SOBNGWI.
 *
 */
public class PolytechnicienElectricien implements IElectricien, IInformaticien,
		IMecanicien, IPlombier {

	private Electricien electricien ;
	public Electricien getElectricien() {
		return electricien;
	}
	public void setElectricien(Electricien electricien) {
		this.electricien = electricien;
	}

	private String nom ;
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public void changerUnTuyau() {
		System.out.println(this.nom.toUpperCase() + " + le polytechnicien sait changerUnTuyau"
				+ " , Mais il le demande au plombier");

	}

	@Override
	public Moteur changerMoteur(Moteur moteur) {
		System.out.println(this.nom.toUpperCase() + " le polytechnicien sait changer le moteur [" + moteur + "]");
		return moteur;
	}

	
	@Override
	public void develloper() {
		System.out.println(this.nom.toUpperCase() + " le polytechnicien sait develloper "
				+ " Mais je demande au develloper ");
		

	}


	@Override
	public void changerUnePrise() {
		System.out.println(this.nom.toUpperCase() + " le polytechnicien sait changerUnePrise"
				+ " Mais je demande A LELECTRICIEN  ");
		electricien.changerUnePrise();
	}

}
