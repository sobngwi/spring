package com.sobngwi.hibernate.nsy135.controleur;

import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotEmpty;

public class ModificationPays {

    private String code;
    private String nom;
    private String langue;
    @NotEmpty(message="{modification.course.quantite.notempty}")
    @Pattern(regexp="\\d*", message="{modification.course.quantite.numerique}")
    private String quantite;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getLangue() {
		return langue;
	}
	public void setLangue(String langue) {
		this.langue = langue;
	}
	public String getQuantite() {
		return quantite;
	}
	public void setQuantite(String quantite) {
		this.quantite = quantite;
	}

   
}