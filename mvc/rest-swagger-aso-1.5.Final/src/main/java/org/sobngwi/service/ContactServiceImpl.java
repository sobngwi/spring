package org.sobngwi.service;

import java.util.List;

import org.sobngwi.dao.IContactDao;
import org.sobngwi.entity.Contact;
import org.sobngwi.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;


@Service("contactService")
@Transactional
public class ContactServiceImpl implements ContactService {
   
	@Autowired
    private IContactDao contactRepository;

    @Override
    @Transactional(readOnly=true)
    public List<Contact> findAll() {
        return Lists.newArrayList(contactRepository.findAll());
    }

    @Override
    @Transactional(readOnly=true)
    public List<Contact> findAllWithDetail() {
        return Lists.newArrayList(contactRepository.findAllWithDetail());
    }
    
    @Override
    @Transactional(readOnly=true)
    public Contact findById(Long id) {
        return contactRepository.findById(id);
    }

    @Override
    public Contact save(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public void delete(Contact contact) {
        contactRepository.delete(contact);
    }

	@Override
	public List<Customer> findCustomers() {
		return contactRepository.findCustomers();
	}
}
