/**
 * 
 */
package com.sobngwi.hibernate.nsy135.modele.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Alain Narcisse SOBNGWI
 * Entite Genre persistant.
 */
@Entity
public class Genre {

	
	@Id
	@Column(name="CODE")
	private String code ;

	public String getCode() {
		return code;}
	public void setCode(String code) {
		this.code = code;}
}
