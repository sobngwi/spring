package org.sobngwi.test.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sobngwi.jpa.entity.Contact;
import org.sobngwi.service.IContactService;
import org.sobngwi.test.configuration.ServiceTestConfig;
import org.sobngwi.test.listener.ServiceTestExecutionListener;
import org.sobngwi.utilities.DataSets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ServiceTestConfig.class})
@TestExecutionListeners({ServiceTestExecutionListener.class})
@ActiveProfiles("test")
public class ContactServiceImplTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	final Logger logger = LoggerFactory.getLogger(ContactServiceImplTest.class);
    @Autowired
    IContactService contactService;

    @PersistenceContext
    private EntityManager em;

    @DataSets(setUpDataSet= "/org/sobngwi/files/ContactServiceImplTest.xls")
    @Test
    public void findAll() throws Exception {
        List<Contact> result = contactService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @DataSets(setUpDataSet="/org/sobngwi/files/ContactServiceImplTest.xls")
    @Test
    public void findByFirstNameAndLastName_1() throws Exception {
        Contact result = contactService.findByFirstNameAndLastName("Chris", "Schaefer");
        assertNotNull(result);
    }

    @DataSets(setUpDataSet="/org/sobngwi/files/ContactServiceImplTest.xls")
    @Test
    public void firstNameAndLastName_2() throws Exception {
        Contact result = contactService.findByFirstNameAndLastName("Peter", "Chan");
        assertNull(result);
    }

    @Test
    public void  addContact() throws Exception {
        deleteFromTables("CONTACT");

        Contact contact = new Contact();
        contact.setFirstName("Rod");
        contact.setLastName("Johnson");
        int rowCountBefore =
        countRowsInTable("CONTACT") ;
        contactService.save(contact);
        em.flush();
        int rowCountAfter= countRowsInTable("CONTACT") ;
        List<Contact> contacts = contactService.findAll();
        assertEquals(1, contacts.size());
        assertTrue(rowCountAfter > rowCountBefore );
        assertTrue(rowCountBefore == 0 );
        assertTrue(rowCountAfter == 1 );
    }

    @Test(expected=ConstraintViolationException.class)
    public void addContactWithJSR349Error() throws Exception {
        deleteFromTables("CONTACT");
        
        Contact contact = new Contact();

        contactService.save(contact);
        em.flush();

        List<Contact> contacts = contactService.findAll();
        assertEquals(0, contacts.size());
    }
    @Test
    public void findByFirstNameAndLastName_3() throws Exception {
    	deleteFromTables("CONTACT");
    	executeSqlScript("classpath:integration-data.sql", false);
    	int rowCountBefore =
    	        countRowsInTable("CONTACT") ;
    	logger.info("rowCountBefore ==" + rowCountBefore);
    	Contact contact = contactService.findByFirstNameAndLastName("Alain", "SOBNGWI");
    	int rowCountAfter= countRowsInTable("CONTACT") ;
        assertNotNull(contact);
        logger.info(contact.toString());
        assertTrue(contact.getFirstName().equals("Alain"));
        assertTrue(contact.getLastName().equals("SOBNGWI"));
        assertTrue( rowCountBefore == rowCountAfter ) ;
    }
    
    @Test
    public void findAllFromSQLInsertion() throws Exception {
    	int nbDeletedRows = deleteFromTables("CONTACT");
    	logger.info(" nbDeletedRows =[" + nbDeletedRows + "]" );
    	executeSqlScript("classpath:integration-data.sql", false); // stop on Insertion Error.
    	int rowCountBefore =
    	        countRowsInTable("CONTACT") ;
    	List<Contact> contacts = contactService.findAll();
    	Contact alain = contactService.findByFirstNameAndLastName("Alain", "SOBNGWI");
        assertNotNull(contacts);
        assertEquals(rowCountBefore, contacts.size());
        assertTrue(contacts.contains(alain));
    }
    
        @Test(expected=PersistenceException.class)
        public void changeTheContacIDOfAnExistantContact() throws Exception {
        deleteFromTables("CONTACT");
        executeSqlScript("classpath:integration-data.sql", false); // stop on Insertion Error.
        int rowCountBefore =
    	        countRowsInTable("CONTACT") ;
        Contact alain = contactService.findByFirstNameAndLastName("Alain", "SOBNGWI");
        logger.info(" Contact alain =[" + alain + "]" );
        alain.setId(alain.getId() + 1L);
        contactService.save(alain);
        em.flush();
        int rowCountAfter= countRowsInTable("CONTACT") ;
        logger.info(" After Save and flush alain countRowsInTable =[" + rowCountAfter + "]" );
        List<Contact> contacts = contactService.findAll();
        assertEquals(rowCountBefore, contacts.size());
        assertTrue( rowCountBefore == rowCountAfter ) ;
    }
        
        @Test(expected=ConstraintViolationException.class) // FirsName can not be null 
        public void addContactWithoutFirstName() throws Exception {
        deleteFromTables("CONTACT");
        executeSqlScript("classpath:integration-data.sql", false); // stop on Insertion Error.
        int rowCountBefore =
    	        countRowsInTable("CONTACT") ;
        Contact alain = contactService.findByFirstNameAndLastName("Alain", "SOBNGWI");
        logger.info(" Contact alain =[" + alain + "]" );
        Contact alainBis = new Contact() ;
        alainBis.setId(alain.getId());
        contactService.save(alainBis);
        em.flush();
        int rowCountAfter= countRowsInTable("CONTACT") ;
        logger.info(" After Save and flush alain countRowsInTable =[" + rowCountAfter + "]" );
        List<Contact> contacts = contactService.findAll();
        assertEquals(rowCountBefore, contacts.size());
        assertTrue( rowCountBefore == rowCountAfter ) ;
    }
        /**
         * 23:17:35,910  WARN org.hibernate.engine.jdbc.spi.SqlExceptionHelper: 
         * 143 - SQL Error: 23505, SQLState: 23505
         * ERROR org.hibernate.engine.jdbc.spi.SqlExceptionHelper: 
         * 144 - Unique index or primary key violation: "UQ_CONTACT_1_INDEX_6 
         * ON PUBLIC.CONTACT(FIRST_NAME, LAST_NAME)"; SQL statement:
         * @throws Exception
         */
        
        @Test(expected=DataIntegrityViolationException.class) // FirsName can not be null 
        public void addExistingContactWithoutFirstNameAndLastName() throws Exception {
        deleteFromTables("CONTACT");
        executeSqlScript("classpath:integration-data.sql", false); // stop on Insertion Error.
        int rowCountBefore =
    	        countRowsInTable("CONTACT") ;
        Contact alain = contactService.findByFirstNameAndLastName("Alain", "SOBNGWI");
        logger.info(" Contact alain =[" + alain + "]" );
        Contact alainBis = new Contact() ;
       // alainBis.setId(alain.getId());
        alainBis.setFirstName(alain.getFirstName());
        alainBis.setLastName(alain.getLastName());
        contactService.save(alainBis);
        em.flush();
        int rowCountAfter= countRowsInTable("CONTACT") ;
        logger.info(" After Save and flush alain countRowsInTable =[" + rowCountAfter + "]" );
        List<Contact> contacts = contactService.findAll();
        assertEquals(rowCountBefore, contacts.size());
        assertTrue( rowCountBefore == rowCountAfter ) ;
    }  
        /**
         * In fact the save for JPA is save or update
         * You can see an update i have never ask for : 
         * Hibernate: update contact set BIRTH_DATE=?, FIRST_NAME=?, LAST_NAME=? where ID=?.
         * Rq : the Birthday is now nul as i have not set IT in the new reference created.
         * @throws Exception
         */
        @Test 
        public void addExistingContactWithoutFirstNameAndLastName1() throws Exception {
        deleteFromTables("CONTACT");
        executeSqlScript("classpath:integration-data.sql", false); // stop on Insertion Error.
        int rowCountBefore =
    	        countRowsInTable("CONTACT") ;
        Contact alain = contactService.findByFirstNameAndLastName("Alain", "SOBNGWI");
        logger.info(" Contact alain =[" + alain + "]" );
        Contact alainBis = new Contact() ;
        alainBis.setId(alain.getId());
        alainBis.setFirstName("SAGUEU");
        alainBis.setLastName(alain.getLastName());
        contactService.save(alainBis);
        em.flush();
        alain = contactService.findByFirstNameAndLastName("Alain", "SOBNGWI");
        logger.info(" Contact alain null  =[" + alain + "]" );
        alain = contactService.findByFirstNameAndLastName("SAGUEU", "SOBNGWI");
        logger.info(" Contact alain   =[" + alain + "]" );
        int rowCountAfter= countRowsInTable("CONTACT") ;
        logger.info(" After Save and flush alain countRowsInTable =[" + rowCountAfter + "]" );
        List<Contact> contacts = contactService.findAll();
        assertEquals(rowCountBefore, contacts.size());
        assertTrue( rowCountBefore == rowCountAfter ) ;
   
        }         

}
