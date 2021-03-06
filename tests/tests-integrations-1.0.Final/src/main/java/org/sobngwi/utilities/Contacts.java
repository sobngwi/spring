package org.sobngwi.utilities;

import java.io.Serializable;
import java.util.List;

import org.sobngwi.jpa.entity.Contact;

public class Contacts implements Serializable {
    private List<Contact> contacts;

    public Contacts() {
    }

    public Contacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
