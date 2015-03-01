package com.sobngwi.hibernate.nsy135.modele.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.sobngwi.hibernate.nsy135.modele.persistence.composant.RoleId;


@Entity
public class Role {
	@Id
	private RoleId pk;
	public RoleId getPk() {
		return pk;
	}
	public void setPk(RoleId pk) {
		this.pk = pk;
	}
	@Column(name="nom_role")
	private String nom;
	public void setNom(String n) {nom= n;}
	public String getNom() {return nom;}
	
	public com.sobngwi.hibernate.nsy135.modele.persistence.Film getFilm() {
		return getPk().getFilm();
		}
		public void setFilm(Film film) {
		getPk().setFilm(film);
		}
		public Artiste getActeur() {
		return getPk().getActeur();
		}
		public void setActeur(Artiste acteur) {
		getPk().setActeur(acteur);
		}

		/*@OneToMany(mappedBy = "pk.film")
		private Set<Role> roles = new HashSet<Role>();
		public Set<Role> getRoles() {
		return this.roles;
		}
		public void setRoles(Set<Role> r) {
		this.roles = r;
		}*/

	
}
