/**
 * 
 */
package org.sobngwi.techicien;

import org.sobngwi.interfaces.IElectricien;

/**
 * @author Alain Narcisse SOBNGWI.
 *
 */
public class Electricien implements IElectricien {

	private String nom ;
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	@Override
	public void changerUnePrise() {
		System.out.println(this.nom.toLowerCase() + ", Je sais changerUnePrise");
	}

}
