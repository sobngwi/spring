/**
 * 
 */
package com.sobngwi.hibernate.nsy135.modele.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.sobngwi.hibernate.nsy135.modele.persistence.Artiste;
import com.sobngwi.hibernate.nsy135.modele.persistence.Film;
import com.sobngwi.hibernate.nsy135.modele.persistence.Genre;
import com.sobngwi.hibernate.nsy135.modele.persistence.Internaute;
import com.sobngwi.hibernate.nsy135.modele.persistence.Pays;

/**
 * @author Alain Narcisse SOBNGWI
 *
 */
@Repository
public class ServiceHibernateDAO implements IServiceHibernateDAO {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ServiceHibernateDAO.class);

	@PersistenceContext(unitName="persistence")
	private EntityManager entityManager;

	
	
	public ServiceHibernateDAO() {
		
	}
	public ServiceHibernateDAO(EntityManager entityManager) {
			this.entityManager = entityManager;
	}

	@Override
	public List<Pays> listeDesPaysViaHBNCRI() {
		final CriteriaBuilder lCriteriaBuilder = entityManager
				.getCriteriaBuilder();

		final CriteriaQuery<Pays> lCriteriaQuery = lCriteriaBuilder
				.createQuery(Pays.class);
		final Root<Pays> lRoot = lCriteriaQuery.from(Pays.class);
		lCriteriaQuery.select(lRoot);
		final TypedQuery<Pays> lTypedQuery = entityManager
				.createQuery(lCriteriaQuery);

		return lTypedQuery.getResultList();
	}

	@Override
	public List<Pays> listeDesPaysViaHBNHQL() {
		StringBuilder sql = new StringBuilder();
		sql.append("from Pays");
		Query query = entityManager.createQuery(sql.toString());
		return query.getResultList();

	}
	
	@Override
	public List<Pays> findAllCountries() throws Exception {
		TypedQuery<Pays> query = entityManager.createQuery("select * from Pays", Pays.class);
		return query.getResultList();	
	}
	
	@Override
	public List<Film> listeDesFilmsViaHBNCRI() {

		final CriteriaBuilder lCriteriaBuilder = entityManager
				.getCriteriaBuilder();

		final CriteriaQuery<Film> lCriteriaQuery = lCriteriaBuilder
				.createQuery(Film.class);
		final Root<Film> lRoot = lCriteriaQuery.from(Film.class);
		lCriteriaQuery.select(lRoot);
		final TypedQuery<Film> lTypedQuery = entityManager
				.createQuery(lCriteriaQuery);
		return lTypedQuery.getResultList();
	}

	@Override
	public List<Film> listeDesFilmsViaHBNHQL() {

		StringBuilder sql = new StringBuilder();
		sql.append("from Film f LEFT JOIN fetch f.genre "
				+ "left join fetch f.pays " + "left join fetch f.realisateur ");
		Query query = entityManager.createQuery(sql.toString());

		return query.getResultList();
		
	}

	@Override
	public List<Internaute> listeDesInternautesViaCRI() {
		final CriteriaBuilder lCriteriaBuilder = entityManager
				.getCriteriaBuilder();
		final CriteriaQuery<Internaute> lCriteriaQuery = lCriteriaBuilder
				.createQuery(Internaute.class);
		final Root<Internaute> lRoot = lCriteriaQuery.from(Internaute.class);
		lCriteriaQuery.select(lRoot);
		final TypedQuery<Internaute> lTypedQuery = entityManager
				.createQuery(lCriteriaQuery);
		return lTypedQuery.getResultList();
	}

	@Override
	public void creertPays(final String code , final String nom , final String langue ) {
		
		     final Pays pays = new Pays() ;
		     pays.setCode(code);
		     pays.setNom(nom);
		     pays.setLangue(langue);
		     Session session = entityManager.unwrap(Session.class);
		    // Persistons le pays en Serializable 
		     session.doWork(
		    		    new Work() {
		    		            @Override
		    		            public void execute(Connection connection) throws SQLException {
		    		                connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
		    		            }
		    		        });
		     entityManager.persist(pays);
		  
		   
	}

	@Override
	public Film persistFilmFromScratch() {

		Film gravity;
		Genre genre;
		Artiste cuaron;
		try {
			LOGGER.info("Begin Transaction of commit Country");

			gravity = new Film();
			gravity.setTitre("Gravity");
			gravity.setAnnee(2013);
			genre = new Genre();
			genre.setCode("Science-fiction");
			gravity.setGenre(genre);
			cuaron = new Artiste();
			cuaron.setPrenom("Alfonso");
			cuaron.setNom("Cuaron");
			// Alfonso Cuaron a réalisé Gravity
			cuaron.addFilmsRealises(gravity);
			// Sauvegardons dans la base

			entityManager.persist(gravity);
			entityManager.persist(cuaron);
		} catch (RuntimeException e) {

			LOGGER.error(" Transaction have been Roll Back !!! see error ");
			throw e;
		}

		LOGGER.info("End  Transaction of commit Country");
		return gravity;
	}

	@Override
	public Film LireFilmParCle(Serializable o) {
		return entityManager.find(Film.class, o);

	}

	@Override
	public List<Film> LireFilmParTitre(String titre) {

		Query query = entityManager
				.createQuery("from Film f where f.titre= :titre");
		query.setParameter("titre", titre);
		return query.getResultList();

	}

	@Override
	public List<Film> lireFilmParTitreFetch(String titre) {

		StringBuilder sql = new StringBuilder();
		sql.append("from Film f LEFT JOIN fetch f.genre "
				+ "left join fetch f.pays " + "left join fetch f.realisateur "
				+ " where  titre = :titre");
		Query query = entityManager.createQuery(sql.toString());
		return query.getResultList();
	}

	@Override
	public Set<Film> listeDesFilmsRealisesParActeurDuFilmId(Serializable o) {
		Film film = entityManager.find(Film.class, o);
		return film.getRealisateur().getFilmsRealises(); // Par navigation.
	}

	@Override
	public Optional<Pays> findPaysById(String code) throws Exception {
		Pays pays = entityManager.find(Pays.class, code);
		return Optional.ofNullable(pays);
	}
	@Override
	public Pays findPaysByCode(String code) throws Exception {
		return entityManager.find(Pays.class, code);
		 
	}
	
	@Override
	public Pays updatePays(Pays pays) throws Exception {
		return entityManager.merge(pays);
	}
	@Override
	 public void supprimerPays(final Pays pPays) {
	        final Pays lPays = entityManager.getReference(Pays.class, pPays.getCode());
	        entityManager.remove(lPays);
	    }
	
	@Override
    public void modifierPays(final Pays pPays) {
        final CriteriaBuilder lCriteriaBuilder = entityManager.getCriteriaBuilder();

        final CriteriaUpdate<Pays> lCriteriaUpdate = lCriteriaBuilder.createCriteriaUpdate(Pays.class);
        final Root<Pays> lRoot = lCriteriaUpdate.from(Pays.class);
        final Path<Pays> lPath = lRoot.get("code");
        final Expression<Boolean> lExpression = lCriteriaBuilder.equal(lPath, pPays.getCode());
        lCriteriaUpdate.where(lExpression);
        lCriteriaUpdate.set("nom", pPays.getNom());
        final Query lQuery = entityManager.createQuery(lCriteriaUpdate);
        final int lRowCount = lQuery.executeUpdate();

        if (lRowCount != 1) {
            final org.hibernate.Query lHQuery = lQuery.unwrap(org.hibernate.Query.class);
            final String lSql = lHQuery.getQueryString();
            throw new RuntimeException("Nombre d'occurences (" + lRowCount + 
                    ") modifiés différent de 1 pour " + lSql);
        }
    }

}
