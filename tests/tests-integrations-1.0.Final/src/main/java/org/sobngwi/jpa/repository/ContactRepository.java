package org.sobngwi.jpa.repository;

import org.sobngwi.jpa.entity.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Long> {
    Contact findByFirstNameAndLastName(String firstName, String lastName);
}
