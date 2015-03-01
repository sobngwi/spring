package org.sobngwi.jackson;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sobngwi.entity.Customer;
import org.sobngwi.entity.Employee;
import org.sobngwi.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BaseTest.class})
@ActiveProfiles("test")
public class HibernateTest extends BaseTest
{
    //protected EntityManagerFactory emf;
    
    final Logger logger = LoggerFactory.getLogger(HibernateTest.class);
    @Autowired
    ContactService contactService;

    @PersistenceContext
    private EntityManager ems;
    
    @Override
    public void setUp() {
     //   emf = Persistence.createEntityManagerFactory("persistenceUnit");
    }
    
    @Override
    public void tearDown() {
     }

    /*
    /**********************************************************************
    /* Test methods
    /**********************************************************************
     */
   
    @Test
    public void testGetEntityManager() {
       
        Assert.assertNotNull(ems);
    }

    @Test
    public void testGetCustomerJson() throws Exception {
        
        ObjectMapper mapper = mapperWithModule(false);
        String json = mapper.writeValueAsString(ems.find(Customer.class, 103));
        System.err.println("[SOB]" + json);
        assertNotNull(json);
    
    }
   
    @Test
    public void testAllCustomersJson() throws Exception {
       
        Assert.assertNotNull(ems);
        
        Query query = ems.createQuery("select c from Customer c");
        // false -> no forcing of lazy loading
        ObjectMapper mapper = mapperWithModule(false);
        String json = mapper.writeValueAsString(query.getResultList());
        System.err.println("[SOB" + json );
        assertNotNull(json);
        /*
        System.out.println("--- JSON ---");
        System.out.println(json);
        System.out.println("--- /JSON ---");
        */
    }
    
    /**
     * JPA objects relationships are bidirectional by default.
     * This test try to load an Employee who has assigned many
     * customers who, at the same time, have a link to the original
     * employee.
     */
   
    @Test
    public void testCyclesJson() throws Exception {
        
        Employee salesEmployee = ems.find(Employee.class, 1370);
        Assert.assertNotNull(salesEmployee);
       // Assert.assertTrue(salesEmployee.getCustomers().size()>0);
        
        // false -> no forcing of lazy loading
        ObjectMapper mapper = mapperWithModule(false);
        String json = mapper.writeValueAsString(salesEmployee);

        // Ok; let's try reading back
        Employee result = mapper.readValue(json, Employee.class);
        assertNotNull(result);
        //assertNotNull(result.getCustomers());
        //assertEquals(salesEmployee.getCustomers().size(), result.getCustomers().size());
    }
}
