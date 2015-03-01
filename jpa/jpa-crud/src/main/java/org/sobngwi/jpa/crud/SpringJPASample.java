package org.sobngwi.jpa.crud;

import java.util.List;
import java.util.Date;
import java.util.Set;

import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringJPASample {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/spring/app-context-annotation.xml");
        ctx.refresh();
        
        ContactService contactService = ctx.getBean(
            "jpaContactService", ContactService.class);
        
          List<Contact> contacts = contactService.findAllByNativeQuery();
       // List<Contact> contacts = contactService.findAll(); 
        for(Contact contact : contacts) {
            
           System.out.println("**********************");
           System.out.println(contact);
           contact.setFirstName("Alain");;
           System.out.println("Merging"); 
           contactService.save(contact);
          // System.out.println(contactService.findById(contact.getId()));
           System.out.println("----------------------");
        }
    
    }
}
