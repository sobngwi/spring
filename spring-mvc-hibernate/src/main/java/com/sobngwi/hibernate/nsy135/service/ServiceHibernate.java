/**
 * 
 */
package com.sobngwi.hibernate.nsy135.service;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sobngwi.hibernate.nsy135.modele.dao.IServiceHibernateDAO;
import com.sobngwi.hibernate.nsy135.modele.persistence.Film;
import com.sobngwi.hibernate.nsy135.modele.persistence.Internaute;
import com.sobngwi.hibernate.nsy135.modele.persistence.Pays;

/**
 * @author Alain Narcisse SOBNGWI.
 * Classe implementant les methode de tests via Hibernate.
 */
@Service("serviceHibernate")
public class ServiceHibernate implements  IServiceHibernate {


	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceHibernate.class);
	
	@Inject
	private IServiceHibernateDAO dao;

	@Override
	@Transactional(readOnly=true)
	public List<Pays> listeDesPaysViaHBNCRI() {
				
		return dao.listeDesPaysViaHBNCRI() ;
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Pays> listeDesPaysViaHBNHQL() {
		
		return dao.listeDesPaysViaHBNHQL();
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Film> listeDesFilmsViaHBNCRI() {
		
		return dao.listeDesFilmsViaHBNCRI() ;
		
	}
	@Transactional(readOnly=true)
	public List<Film> listeDesFilmsViaHBNHQL() {
				
		return  dao.listeDesFilmsViaHBNHQL() ;
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Internaute> listeDesInternautesViaCRI() {
		return dao.listeDesInternautesViaCRI() ;
	}

	@Override
	@Transactional
	public void creertPays(final String code , final String nom , final String langue ) {

		dao.creertPays(code ,nom , langue );
		
	}
	
	@Override
	@Transactional
	public Film persistFilmFromScratch() {
		
		return	dao.persistFilmFromScratch() ;
		
	}
	
	@Override
	@Transactional(readOnly=true)
	public Film lireFilmParCle(Serializable o) {
		return null ;
		
	}

	@Override
	@Transactional(readOnly=true)
	public List<Film> lireFilmParTitre(String titre) {
		return dao.LireFilmParTitre(titre) ;
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Film> lireFilmParTitreFetch(String titre) {
		return dao.lireFilmParTitreFetch(titre) ;
	}
	
	@Override
	@Transactional(readOnly=true)
	public Set<Film> listeDesFilmsRealisesParActeurDuFilmId(Serializable o) {
		return dao.listeDesFilmsRealisesParActeurDuFilmId(o);
	}
	
	@Override  
	@Transactional
	    public void supprimerPays(final String pcodePays) throws Exception {
	        final Pays lPays = new Pays();
	        lPays.setCode(pcodePays);

	        dao.supprimerPays(lPays);
	    }
	@Override   
	@Transactional
	    public void modifierPays(final List<Pays> pListePays) {
	        for (final Pays lPays : pListePays) {
	            dao.modifierPays(lPays);
	        }
	    }
	 
	
	
}
