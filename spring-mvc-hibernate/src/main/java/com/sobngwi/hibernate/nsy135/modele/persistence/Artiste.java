/**
 * 
 */
package com.sobngwi.hibernate.nsy135.modele.persistence;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;



/**
 * @author Alain Narcisse SOBNGWI
 *
 */
@Entity
public class Artiste {
	
	@Id
	@Column ( name="ID")
	private long Id ;
	
	@Column ( name="NOM")
	private String nom ;
	public String getNom() {
		return nom;}
	public void setNom(String n) {
		this.nom = n;}
	
	@Column ( name="PRENOM")
	private String prenom ;
	public String getPrenom() {
		return prenom;}
	public void setPrenom(String p) {
		this.prenom = p;}
	
	@Column ( name="ANNEE_NAISSANCE")
	private int anneNaissance ;
	public int getAnneNaissance() {
		return anneNaissance;
	}
	public void setAnneNaissance(int n) {
		this.anneNaissance = n;}
	
	
	@OneToMany(mappedBy="realisateur")
	@JsonBackReference
	private Set<Film> filmsRealises = new HashSet<Film>();
	public void addFilmsRealises(Film f) {
	f.setRealisateur(this); // Faire Apple au responsable de la mise a  jour.
	filmsRealises.add(f) ;}
	public Set<Film> getFilmsRealises() {return filmsRealises;}

	/*@ManyToMany(mappedBy = "acteurs")
	@JsonBackReference // recursive 
	Set<Film> filmo;
	public Set<Film> getFilmo() {
	return filmo;
	}*/
	/*
	@OneToMany(mappedBy = "pk.acteur")
	private Set<Role> roles = new HashSet<Role>();
	public Set<Role> getRoles() {
	return this.roles;
	}
	public void setRoles(Set<Role> r) {
	this.roles = r;
	}
*/
	
}
