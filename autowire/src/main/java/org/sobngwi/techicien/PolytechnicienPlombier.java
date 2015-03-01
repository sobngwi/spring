/**
 * 
 */
package org.sobngwi.techicien;

import org.sobngwi.interfaces.IElectricien;
import org.sobngwi.interfaces.IInformaticien;
import org.sobngwi.interfaces.IMecanicien;
import org.sobngwi.interfaces.IPlombier;
import org.sobngwi.modele.Moteur;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Alain Narcisse SOBNGWI.
 *
 */
public class PolytechnicienPlombier implements IElectricien, IInformaticien,
		IMecanicien, IPlombier {

	@Autowired
	private Plombier plombier ;
	
	
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
		plombier.changerUnTuyau();
	}

	@Override
	public Moteur changerMoteur(Moteur moteur) {
		System.out.println(this.nom.toUpperCase() + " le polytechnicien sait changer le moteur [" + moteur + "]");
		return moteur;
	}

	
	@Override
	public void develloper() {
		System.out.println(this.nom.toUpperCase() + " le polytechnicien sait develloper");

	}


	@Override
	public void changerUnePrise() {
		System.out.println(this.nom.toUpperCase() + " le polytechnicien sait changerUnePrise");
	}

}
