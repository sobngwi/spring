package org.sobngwi.mockito.dao;

/**
 * @author Alain Narcisse SOBNGWI
 *
 */

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//Here we use Java Optional to avoid having to do null checks on the results.
public class CustomerDAO {
	
	final Logger logger = LoggerFactory.getLogger(CustomerDAOTest.class);
	public CustomerDAO () {
	}
	
	public CustomerDAO(EntityManager em) {
		this.em = em;
	}
	@PersistenceContext // configure and inject the datasource in the appl.
	EntityManager em;
	TypedQuery<Customer> query ;

		
	public Optional<Customer> findById(long id) throws Exception {
		logger.info( "Looking for customer ID [" + id + "]");
		Customer customer = em.find(Customer.class, id);
		return Optional.ofNullable(customer);
	}
	
	public List<Customer> findAll() throws Exception {
		logger.info( "Looking for  All Customers " ) ;
		query = em.createQuery("select * from CUSTOMER", Customer.class);
		return query.getResultList();	
	}

	public Customer update(Customer customer) throws Exception {
		logger.info( "Update customer [" + customer + "]" ) ;
		return em.merge(customer);
	}
	
}

