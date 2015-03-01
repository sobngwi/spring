package com.sobngwi.hibernate.nsy135.converters;

import com.sobngwi.hibernate.nsy135.modele.persistence.Pays;

public class Origine  {
	
	private final Pays pays ;



	private final String code ;
	private final String nom ;
	private final String langue ;
	
	
	
	
	public Origine(Pays pays) {
		this.pays = pays;
		this.code = pays.getCode();
		this.nom = pays.getNom();
		this.langue = pays.getLangue();
	}
	public String getCode() {
		return code;
	}
	public String getNom() {
		return nom;
	}
	public String getLangue() {
		return langue;
	} 
	
	
	
}