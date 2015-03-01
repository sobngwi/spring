package org.sobngwi.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sobngwi.convert.ContactView;
import org.sobngwi.convert.ContactViewToContact;
import org.sobngwi.convert.Contacts;
import org.sobngwi.entity.Contact;
import org.sobngwi.entity.Customer;
import org.sobngwi.service.ContactService;
import org.sobngwi.service.mbean.IRegisterMbean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller("My Contact Controller")
public class ContactController {
    final Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired 
    private ContactService contactService;
    
    @Autowired 
    ConversionService conversionService ;
    @Autowired
    ContactViewToContact converterContact ;
    
    @Autowired
    IRegisterMbean registerMbeanHibernate ;

    @ApiOperation(value = "Get All The Contacts in the SYSTEM" , response =List.class)
    @RequestMapping(value = "Pilote/findAllContacts", method = RequestMethod.GET)
    @ResponseBody
    public Contacts findAllContacts() {
    	logger.info("[ASO]Going to List datas");
    	List<Contact> lc = contactService.findAll() ;
        return new Contacts(lc);
    }
    
    @ApiOperation(value = "Get All The Contacts and The detais attributes  in the SYSTEM" , response =List.class)
    @RequestMapping(value = "Pilote/findAllContactsDetails", method = RequestMethod.GET)
    @ResponseBody
    public Contacts findAllContactsDetails() {
    	logger.info("[ASO]Going to List contact dats and the details");
        return new Contacts(contactService.findAllWithDetail());
    }

    @ApiOperation(value = "Get A specific Contact in the SYSTEM" , response =Contact.class)
    @RequestMapping(value="Pilote/findContactById/{IdToFind}", method=RequestMethod.GET)
    @ResponseBody
    public Contact findContactById(
    		@ApiParam(defaultValue = "4999") // swagger
    		@PathVariable String IdToFind ) {
    	logger.info("[ASO] Find by Id " + IdToFind);
    	Long pId = conversionService.convert(IdToFind, Long.class) ; // We just test the converter
    	logger.info("[ASO] Find by Long " + pId);
    	return contactService.findById(pId);
    }

    @ApiOperation(value = "Post a creation of a new ressource Contact in the SYSTEM" , response =Contact.class)
    @RequestMapping(value="Pilote/create", method=RequestMethod.POST)
    @ResponseBody
    public Contact create(@RequestBody ContactView contactView) {
        logger.info("Creating contact View : " + contactView);
        Contact contact = converterContact.convert(contactView);
        logger.info("Contact to Create : " + contact);
        	return contactService.save(contact);
    }

    @ApiOperation(value = "Update a resource Contact in a system  the SYSTEM" , response =Contact.class)
    @RequestMapping(value="Pilote/update/", method=RequestMethod.POST)
    @ResponseBody
    public Contact update(@RequestBody ContactView contactView ) {
    	Contact contact = converterContact.convert(contactView);
        logger.info("Updating contact: " + contact);
         return contactService.save(contact);
       //logger.info("Contact updated successfully with info: " + contact);
    }

    @ApiOperation(value = "DELETE / SUBSTITUATE BY A POST  a resource contact in a in the SYSTEM" , response =Void.class)
    @RequestMapping(value="Pilote/delete/{IdtoDelete}", method=RequestMethod.POST)
    @ResponseBody
    public void delete(
    		@ApiParam(name ="IdtoDelete")
    		@PathVariable Long IdtoDelete) {
        logger.info("Deleting contact with id: " + IdtoDelete);
        Contact contact = contactService.findById(IdtoDelete);
        contactService.delete(contact);
        logger.info("Contact deleted successfully");
    }
    
    @ApiOperation(value = "Get All The Customers  and The detais attributes  in the SYSTEM" , response =List.class)
    @RequestMapping(value = "Pilote/findCustomersAndDetails", method = RequestMethod.GET)
    @ResponseBody
    public List<Customer> findCustomersAndDetails() {
    	logger.info("[ASO]Going to List  customers and their details");
        return contactService.findCustomers();
    }
    
    @ApiOperation(value = "Register Mbean Service in the JRE SYSTEM" , response =Void.class)
    @RequestMapping(value = "Pilote/registerHibernateMbeanStats", method = RequestMethod.GET)
    @ResponseBody
    public void  registerHibernateMbeanStats() {
    	logger.info("[ASO]Registration starts");
        registerMbeanHibernate.registerHibernateMBeans();
        logger.info("[ASO]Registration Finished ");
    }
    
}
