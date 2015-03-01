
package com.sobngwi.hibernate.nsy135.validation;

//import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class CreationForm {

   // @NotEmpty
    private String code;
    @NotEmpty
    //@Pattern(regexp="\\d*")
    private String langue;
    @NotEmpty
    private String nom;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLangue() {
		return langue;
	}
	public void setLangue(String langue) {
		this.langue = langue;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

   
}
