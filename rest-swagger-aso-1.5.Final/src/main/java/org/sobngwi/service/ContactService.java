package org.sobngwi.service;

import java.util.List;

import org.sobngwi.entity.Contact;
import org.sobngwi.entity.Customer;

public interface ContactService {
    List<Contact> findAll();
    List<Contact> findAllWithDetail() ;
    Contact findById(Long id);
    Contact save(Contact contact);
    void delete(Contact contact);
    List<Customer> findCustomers();
}
