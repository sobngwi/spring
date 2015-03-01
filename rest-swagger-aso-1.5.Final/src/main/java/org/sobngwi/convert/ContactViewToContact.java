package org.sobngwi.convert;


import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.sobngwi.entity.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service("contactViewToContact")
public class ContactViewToContact
        implements Converter<ContactView, Contact> {

    @Override
    public Contact convert(ContactView contactView) {
    	Contact anotherContact = new Contact();
        anotherContact.setFirstName(contactView.getLastName());
        anotherContact.setLastName(contactView.getFirstName());
        DateTimeFormatter formatter = DateTimeFormat.forPattern(contactView.getDateFormat()); // "dd/MM/yyyy"
        anotherContact.setBirthDate(formatter.parseDateTime(contactView.getBirthDate()));
        return anotherContact;
    }
}
