package org.sobngwi.service;


import com.google.common.collect.Lists;

import org.sobngwi.dao.ContactRepository;
import org.sobngwi.entity.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("contactService")
@Repository
@Transactional
public class ContactServiceImpl implements ContactService {
    
	@Autowired
	private ContactRepository contactRepository;

    @Override
    @Transactional(readOnly=true)
    public List<Contact> findAll() {
        return Lists.newArrayList(contactRepository.findAll());
    }

    @Override
    @Transactional(readOnly=true)
    public Contact findById(Long id) {
        return contactRepository.findOne(id);
    }

    @Override
    public Contact save(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    //@Transactional(propagation= Propagation.NEVER)
    @Transactional(readOnly=true)
    public long countAll() {
        return contactRepository.countAllContacts();
    }

    
   /* public void setContactRepository(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }*/
}
