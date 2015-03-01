/**
 * 
 */
package org.sobngwi.techicien;

import org.sobngwi.interfaces.IInformaticien;

/**
 * @author Alain Narcisse SOBNGWI.
 *
 */
public class Informaticien implements IInformaticien {

	private String nom ;
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/* (non-Javadoc)
	 * @see org.sobngwi.interfaces.IInformaticien#develloper()
	 */
	@Override
	public void develloper() {
		System.out.println("Je sais pisser du code ...");

	}

}
