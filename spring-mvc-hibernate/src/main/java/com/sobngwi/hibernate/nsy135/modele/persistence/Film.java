/**
 * 
 */
package com.sobngwi.hibernate.nsy135.modele.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * Cette classe est le moule dont on va se servir 
 * pour persister/rechercher  nos objets Fim.
 * @author Alain Narcisse SOBNGWI
 */
@Entity
public class Film {

        @Id
        @Column ( name="ID")
    	private long id ;
		public long getId() {
			return id;}
		private void setId(long id) {
			this.id = id;}  
		@Column ( name="TITRE")
		private String titre ;
		public String getTitre() {
			return titre;}
		public void setTitre(String titre) {
			this.titre = titre;}
		
		@Column ( name="ANNEE")
		private int annee ;
		public int getAnnee() {
			return annee;}
		public void setAnnee(int annee) {
			this.annee = annee;}
		
		@ManyToOne
		@JoinColumn(name="genre")
		private Genre genre ;
		public Genre getGenre() {
			return genre;}
		public void setGenre(Genre g) {
			this.genre = g;}
		
		@Column ( name="RESUME")
		private String resume ;
		public String getResume() {
			return resume;}
		public void setResume(String resume) {
			this.resume = resume;}
		
		@ManyToOne
		@JoinColumn(name="code_pays") // code_pays est FK dans la table Film
		private Pays pays ;
		public Pays getPays() {return pays;}
		public void setPays(Pays p) {this.pays = p;}
		
		
		@ManyToOne
		@JoinColumn (name="id_realisateur")
		@JsonManagedReference
		private Artiste realisateur;
		public void setRealisateur(Artiste a) {realisateur = a;}
		public Artiste getRealisateur() {return realisateur;}
		
		/*@ManyToMany()
		@JsonBackReference /// recursive  mapping
		@JoinTable(name = "Role", joinColumns = @JoinColumn(name = "id_film"), // Role est la table de jointure.
		inverseJoinColumns = @JoinColumn(name = "id_acteur"))
		Set<Artiste> acteurs = new HashSet<Artiste>();
		public Set<Artiste> getActeurs() {
		return acteurs;
		}
*/
			
}
