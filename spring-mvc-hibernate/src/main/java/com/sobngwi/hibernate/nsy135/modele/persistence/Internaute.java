/**
 * 
 */
package com.sobngwi.hibernate.nsy135.modele.persistence;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.sobngwi.hibernate.nsy135.modele.persistence.composant.Adresse;

/**
 * Entite Persistante Internaute.
 * Elle contient une entité composant : Adresse. 
 * @author Alain Narcisse SOBNGWI.
 *
 */
@Entity
public class Internaute {
	
		@Id
		@Column (name="EMAIL")
		private String email ;
		public String getEmail() {
			return email;
		}
		public void setEmail(String e) {
			this.email = e;}
		
		@Column(name="NOM")
		private String nom ;
		public String getNom() {
			return nom;}
		public void setNom(String n) {
			this.nom = n;}
		
		@Column(name="PRENOM")
		private String prenom ;
		public String getPrenom() {
			return prenom;}
		public void setPrenom(String p) {
			this.prenom = p;}
		
		@Column(name="ANNEE_NAISSANCE")
		private int AnneeDeNaissance ;
		public int getAnneeDeNaissance() {
			return AnneeDeNaissance;}
		public void setAnneeDeNaissance(int a) {
			AnneeDeNaissance = a;}
		
		@Column(name="REGION")
		private String region ;
		public String getRegion() {
			return region;}
		public void setRegion(String r) {
			this.region = r;}
		
		@Embedded // Addresse est un composant de l Internaute.
		private Adresse adresse ;
		public Adresse getAdresse() {
			return adresse;}
		public void setAdresse(Adresse a) {
			this.adresse = a;}
		
		@Embedded // C est une adresse dont les attributs sont surchargés.
		/*@AttributeOverrides (
				@AttributeOverride(
						name="adresse",     column=@Column(name="adresse_pro")
						)
				//@AttributeOverride(name="codePostale", column=@Column(name="code_poste_pro") )
				//@AttributeOverride(name="ville",       column=@Column(name="ville_pro") )
				)*/
		private Adresse  adressePro ;
		public Adresse getAdressePro() {
			return adressePro;}
		public void setAdressePro(Adresse a) {
			this.adressePro = a;}
		
		

}
