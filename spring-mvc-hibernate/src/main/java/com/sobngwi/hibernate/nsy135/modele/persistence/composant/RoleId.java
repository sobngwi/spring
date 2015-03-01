package com.sobngwi.hibernate.nsy135.modele.persistence.composant;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.sobngwi.hibernate.nsy135.modele.persistence.Film;
import com.sobngwi.hibernate.nsy135.modele.persistence.Artiste;

@Embeddable
public class RoleId implements java.io.Serializable { // Set de cle pour le Role 
	private static final long serialVersionUID = 1L; // Cache Hibernate
	@ManyToOne
	@JoinColumn(name = "id_acteur")
	private Artiste acteur;
	public Artiste getActeur() {
		return acteur;
	}
	public void setActeur(Artiste a) {
		this.acteur = a;
	}
	@ManyToOne
	@JoinColumn(name = "id_film")
	private Film film;
	public Film getFilm() {
		return film;
	}
	public void setFilm(Film f) {
		this.film = f;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((acteur == null) ? 0 : acteur.hashCode());
		result = prime * result + ((film == null) ? 0 : film.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoleId other = (RoleId) obj;
		if (acteur == null) {
			if (other.acteur != null)
				return false;
		} else if (!acteur.equals(other.acteur))
			return false;
		if (film == null) {
			if (other.film != null)
				return false;
		} else if (!film.equals(other.film))
			return false;
		return true;
	}
	
	
}
