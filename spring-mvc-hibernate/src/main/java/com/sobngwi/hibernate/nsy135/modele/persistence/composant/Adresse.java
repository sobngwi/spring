package com.sobngwi.hibernate.nsy135.modele.persistence.composant;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Entité composant Embeddable. ELle va etre introduite dans l entité Internaute.
 * Cette entité n a pas d existence propre. 
 * Elle peut être reutlisé dans tout autre entité que Internaute.
 * @author Alain Narcisse SOBNGWI
 *
 */
@Embeddable
public class Adresse {
	
	@Column(name="ADRESSE", insertable=false, updatable=false)
	private String adresse ;
	public String getAdresse() {
		return adresse;}
    public void setAdresse(String adresse) {
		this.adresse = adresse;}

	@Column(name="VILLE",insertable=false, updatable=false)
	private String ville ;
	public String getVille() {
		return ville;}
	public void setVille(String ville) {
		this.ville = ville;}
	
	@Column(name="CODE_POSTALE", insertable=false, updatable=false)
	private int codePostale ;
	public int getCodePostale() {
		return codePostale;}
	public void setCodePostale(int codePostale) {
		this.codePostale = codePostale;}

}
