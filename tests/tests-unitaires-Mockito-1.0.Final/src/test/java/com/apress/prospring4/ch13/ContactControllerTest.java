package com.apress.prospring4.ch13;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ExtendedModelMap;

@RunWith(MockitoJUnitRunner.class)
public class ContactControllerTest {
    private final List<Contact> contacts = new ArrayList<Contact>();
    @Mock
	private ContactService contactService ;
    @Spy
    ContactController contactController  ;
    private Contact contact ; 
   
    @Before
    public void initContacts() {

    	ReflectionTestUtils.setField(contactController, "contactService", contactService);
    	contact = new Contact();
    	contact.setId(1l);
        contact.setFirstName("Chris");
        contact.setLastName("Schaefer");
        contacts.add(contact);
    }

    @Test
    public void testList() throws Exception {

    	// Given 
    	given(contactService.findAll()).willReturn(contacts);
        ExtendedModelMap uiModel = new ExtendedModelMap();
        
        // Then 
        uiModel.addAttribute("contacts", contactController.listData());

        Contacts modelContacts = (Contacts) uiModel.get("contacts");
        
        // Assertions and verifications.
        assertTrue(contacts.size() == 1);
        assertEquals(1, modelContacts.getContacts().size());
        verify(contactService, times(1)).findAll();
        verify(contactService, times(0)).findById(contact.getId());
    }

    @Test
    public void testCreate() {

    	final Contact newContact = new Contact();
        newContact.setId(999l);
        newContact.setFirstName("Rod");
        newContact.setLastName("Johnson");

        when(contactService.save(newContact)).thenAnswer(new Answer<Contact>() {
            public Contact answer(InvocationOnMock invocation) throws Throwable {
                contacts.add(newContact);
                return newContact;
            }
        });
        	// Then 
        Contact contact = contactController.create(newContact);
        assertEquals(Long.valueOf(999l), contact.getId());
        assertEquals("Rod", contact.getFirstName());
        assertEquals("Johnson", contact.getLastName());
        assertEquals(2, contacts.size());
        verify(contactService, times(0)).findById(newContact.getId());
        }
    
    @Test
    public void testUpdate() {
    	// Given a contact define at initialisation
        final Contact newContact = new Contact() ;
        newContact.setId(1l);
        newContact.setFirstName("Alain");
        newContact.setLastName("SOBNGWI");
       
        // When 
        when(contactService.save(newContact)).thenAnswer(new Answer<Contact>() {
            public Contact answer(InvocationOnMock invocation) throws Throwable {
                contacts.add(newContact);
                contacts.remove(contact);
                return newContact;
            }
        });
        
        // Tests 
        contactController.update(newContact, contact.getId());
        Contact contact =contacts.get(0);
        
        // Assertions 
        assertEquals(Long.valueOf(1l), contact.getId());
        assertEquals("Alain", contact.getFirstName());
        assertEquals("SOBNGWI", contact.getLastName());
        assertEquals(1, contacts.size());
        verify(contactService, times(1)).save(newContact);
        verify(contactService, times(0)).findById(newContact.getId());
    }

    @Test
    public void testDelete() {
    	//given
    	given(contactService.findById(contact.getId())).willReturn(contact);
       
    	// Then 
        contactController.delete(contact.getId());
       
        verify(contactService, times(1)).findById(contact.getId());
        verify(contactService, times(1)).delete(contact);
 
    }
    
    @Test
    public void testDelete_withSpy() {
           	  
        // Given 
    	given(contactService.findById(contact.getId())).willReturn(contact);
        
    	//Then
    	contactController.delete(contact.getId());
              
        verify(contactService, times(1)).findById(contact.getId());
        verify(contactService, times(1)).delete(contact);
        
    }
    
    @Test(expected = ContactNotFoundException.class)
	public void deleteUnknownContactShouldThrowsException() throws  Exception {
		// Given
    	given(contactService.findById(contact.getId())).willReturn(contact); // Be carefull 
   		doThrow(new ContactNotFoundException()).when(contactService).deleteUnknown(contact);
		
		// When
		contactController.deleteUnknown(contact.getId());
		// Then
		fail("Exception have not been thrown ????"); // 
		
	}
 }
