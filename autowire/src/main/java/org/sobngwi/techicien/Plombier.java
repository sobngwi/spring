/**
 * 
 */
package org.sobngwi.techicien;

import org.sobngwi.interfaces.IPlombier;
import org.sobngwi.modele.Moteur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author suse
 *
 */
@Component
public class Plombier implements IPlombier {

	
	private String nom ;
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
	/* (non-Javadoc)
	 * @see org.sobngwi.interfaces.IPlombier#changerUnTuyau()
	 */
	@Override
	public void changerUnTuyau() {
		if (this.nom == null) this.nom ="defaultPlombier" ;
		System.out.println(this.nom.toUpperCase()  +" , Je sais changerUnTuyau");

	}

}
