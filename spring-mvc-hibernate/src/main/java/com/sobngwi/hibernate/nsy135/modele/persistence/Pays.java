/**
 * 
 */
package com.sobngwi.hibernate.nsy135.modele.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Cette classe est le moule dont on va se servir 
 * pour persister/rechercher  nos objets Pays.
 * @author Alain Narcisse SOBNGWI
 *
 */
@Entity
public class Pays {

	 @Id
	 @Column(name="CODE")
     private String code;
     public void setCode(String c) {code = c;}
     public String getCode() {return code ;}

     @Column(name="NOM")
     private String nom;
     public void setNom(String n) {nom = n;}
     public String getNom() {return nom;}

     @Column(name="LANGUE")
     String langue;
     public void setLangue(String l) {langue = l;}
     public String getLangue() {return langue;} 
	
}
