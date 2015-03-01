package org.sobngwi.dao;



import org.sobngwi.entity.Contact;
import org.sobngwi.entity.Customer;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;

import java.util.List;

@Transactional
@Named("contactDao")
public class ContactDaoImpl implements IContactDao {
    private static final Log LOG = LogFactory.getLog(ContactDaoImpl.class);

    @Inject
    private SessionFactory sessionFactory;

    @Transactional(readOnly=true)
    public List<Contact> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from Contact c").list();
    }

    @Transactional(readOnly=true)
    public List<Contact> findAllWithDetail() {
        return sessionFactory.getCurrentSession().
            getNamedQuery("Contact.findAllWithDetail").list();
    }

    @Transactional(readOnly=true)
    public Contact findById(Long id) {
        return (Contact) sessionFactory.getCurrentSession().
            createQuery("SELECT c FROM  Contact c  WHERE c.id = :id").
            setParameter("id", id).uniqueResult();
    }

    public Contact save(Contact contact) {
        sessionFactory.getCurrentSession().saveOrUpdate(contact);
        LOG.info("Contact saved or Update with id: " + contact.getId());
        return contact;
    }

    public void delete(Contact contact) {
        sessionFactory.getCurrentSession().delete(contact);
        LOG.info("Contact deleted with id: " + contact.getId());   
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Resource(name="sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	@Override
	public List<Customer> findCustomers() {
        return sessionFactory.getCurrentSession().createQuery("from Customer c").list();
		
	}
}
