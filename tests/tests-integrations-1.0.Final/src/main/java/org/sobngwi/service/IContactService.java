package org.sobngwi.service;

import java.util.List;

import org.sobngwi.jpa.entity.Contact;

public interface IContactService {
    List<Contact> findAll();
    Contact findById(Long id);
    Contact findByFirstNameAndLastName(String firstName, String lastName);
    Contact save(Contact contact);
    void delete(Contact contact);
}
