package org.sobngwi.service;

import java.util.List;

import org.sobngwi.entity.Contact;

public interface ContactService {
    List<Contact> findAll();
    Contact findById(Long id);
    Contact save(Contact contact);
    long countAll();
}
