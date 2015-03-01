package org.sobngwi.mockito.dao;

/**
 * 
 * @author Alain Narcisse SOBNGWI.
 * Implementation of a Behaviour in our tests TDD :
 * // Given, // When and // Then. This convention is called Behaviour Driven Development
 * Mockito supports BDD out of the box in the org.mockito.BDDMockito class. 
 * It replaces the normal stubbing methods 
 * – when(), thenReturn(), thenThrow(), thenAnswer() etc with BDD doppelgangers 
 * – given(), willReturn(), willThrow(), willAnswer(). 
 * This allows us to avoid using when() in the // Given section, as it may be confusing. 
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


//Here we use Java Optional to avoid having to do null checks on the results.
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.util.ReflectionTestUtils;

@RunWith(MockitoJUnitRunner.class)
public class CustomerDAOTest {
	
	final Logger logger = LoggerFactory.getLogger(CustomerDAOTest.class);
	@Spy
	private CustomerDAO dao;
	
	private Customer alain, rudy, anneGaelle, nullCustomer;
	private Answer<Customer> withCustomerById = new Answer<Customer>() {
		@Override
		public Customer answer(InvocationOnMock invocation) throws Throwable {
			Object[] args = invocation.getArguments();
			int id = ((Long)args[1]).intValue(); // Cast to int for switch.
			switch (id) {
			case 1 : return alain;
			case 2 : return rudy;
			case 3 : return anneGaelle;
			default : return null;
			}
		}
	};
	
	@Mock
	private EntityManager mockEntityManager;
	@Mock
	private TypedQuery<Customer> mockQuery;

	@Before
	public void setUp() throws Exception {
		/*dao = new CustomerDAO(mockEntityManager);*/
    	ReflectionTestUtils.setField(dao, "em", mockEntityManager);
    	ReflectionTestUtils.setField(dao, "query", mockQuery);
		setupCustomers();
	}

	private void setupCustomers() {
		alain = new Customer(1, "SOBNGWI Alain", "La Defense");
		rudy = new Customer(2, "SOBNGWI Rudy", "Les Rabats");
		nullCustomer = null;
		anneGaelle = new Customer(3, "SOBNGWI Anne-Gaelle", "Massy");		
	}

	/**
	 * @return  an appropriate Optional Customer for this test.
	 * will just return null if the customer is not expected.
	 * @throws Exception
	 */
	@Test  
	public void finding_existing_customer_should_return_customer() throws Exception {
		// Given   customer alain and an  unexpectedId
		given(mockEntityManager.find(Customer.class, alain.getId())).willReturn(alain);
		
		// When
		Optional<Customer> actualCustomer = dao.findById(alain.getId());
		
		// Then
		assertTrue(actualCustomer.isPresent()); // Its True
		assertEquals(alain.getId(), actualCustomer.get().getId());
		assertEquals(alain.getName(), actualCustomer.get().getName());
		assertEquals(alain.getAddress(), actualCustomer.get().getAddress());
	}
	
	@Test
	public void invoking_mock_with_unexpected_argument_returns_null() throws Exception {
		// Given customer alain and an  unexpectedId
		long unexpectedId = -10000L;

		given(mockEntityManager.find(Customer.class, alain.getId())).willReturn(alain);
		
		// When
		Optional<Customer> actualCustomer = dao.findById(unexpectedId);
		
		// Then
		assertFalse(actualCustomer.isPresent()); // customer is not persent.
	}
	
	@Test
	public void invoking_mock_with_different_argument_returns_different_customers() throws Exception {
		// Given alain and rudy and AG
		
		given(mockEntityManager.find(Customer.class, alain.getId())).willReturn(alain);
		given(mockEntityManager.find(Customer.class, rudy.getId())).willReturn(rudy);
		given(mockEntityManager.find(Customer.class, anneGaelle.getId())).willReturn(anneGaelle);
		
		
		// When
		Optional<Customer> actualCustAlain = dao.findById(alain.getId());
		Optional<Customer> actualCustRudy = dao.findById(rudy.getId());
		Optional<Customer> actualCustAG = dao.findById(anneGaelle.getId());
		
		// Then
		assertEquals(alain.getName(), actualCustAlain.get().getName());
		assertEquals(rudy.getName(), actualCustRudy.get().getName());
		assertEquals(anneGaelle.getName(), actualCustAG.get().getName());
	}
	
	@Test
	public void invoking_mock_with_chained_stubs_returns_different_customers() throws Exception {
		// Given alain and rudy and AG
		
		given(mockEntityManager.find(Customer.class, alain.getId()))
			.willReturn(alain).willReturn(rudy).willReturn(anneGaelle);
		
		// When
		Optional<Customer> actualCustAlain = dao.findById(alain.getId());
		Optional<Customer> actualCustRudy = dao.findById(alain.getId()); // rudy
		Optional<Customer> actualCustAG = dao.findById(alain.getId()); // AG
		
		// Then
		assertEquals(alain.getName(),actualCustAlain.get().getName());
		assertEquals(rudy.getName(), actualCustRudy.get().getName());
		assertEquals(anneGaelle.getName(), actualCustAG.get().getName());
	}
	
	/**
	 * Test the case where the customer is missing.
	 * @return no Customer ie null.
	 * @throws Exception
	 */
	@Test
	public void finding_missing_customer_should_return_null() throws Exception {
		// Given an unExpectedId existing in our database
		long unExpectedId = -10000L;
		given(mockEntityManager.find(Customer.class, unExpectedId)).willReturn(null); // Return no body.
		
		// When
		Optional<Customer> actualCustomer = dao.findById(unExpectedId);
		
		// Then
		assertFalse(actualCustomer.isPresent());
		assertTrue(! actualCustomer.isPresent());// (!) It is true that customer is missing.
		
	}
	
	@Test
	public void finding_customer_should_respond_appropriately() throws Exception {
		// Given alain and nullCustomer
	
		
		
		when(mockEntityManager.find(Customer.class, alain.getId())).thenReturn(alain, nullCustomer);
		
		// When
		Optional<Customer> actualCustAlain = dao.findById(alain.getId());
		Optional<Customer> actualCustNull = dao.findById(alain.getId());
		
		// Then
		assertTrue(actualCustAlain.isPresent());
		assertFalse(actualCustNull.isPresent());
	}
	/**
	 * Take care of this ,  A throwable is not an Exception.
	 * But an exception is a throwable.
	 * @throws Exception
	 */
	@Test(expected=Exception.class)
	public void finding_customer_should_throw_exception_up_the_stack() throws Exception {
		// Given
		long exceptionCustomer = -10000L;
		
		given(mockEntityManager.find(Customer.class, exceptionCustomer)).willThrow(new Throwable());
		
		// When
		dao.findById(exceptionCustomer);
		
		// Then
		fail("Exception should be thrown.");
	}
	
	@Test
	public void finding_customer_by_id_returns_appropriate_customer() throws Exception {
		// Given
		long[] expectedId = {1, 2, 3};
		
		when(mockEntityManager.find(eq(Customer.class), anyLong())).thenAnswer(withCustomerById);
		
		// When
		Optional<Customer> actualCustomerAlain = dao.findById(expectedId[0]);
		Optional<Customer> actualCustomerRudy = dao.findById(expectedId[1]);
		Optional<Customer> actualCustomerAnneGaelle = dao.findById(expectedId[2]);
		
		// Then
		assertEquals("SOBNGWI Alain", actualCustomerAlain.get().getName());
		assertEquals("SOBNGWI Rudy", actualCustomerRudy.get().getName());
		assertEquals("SOBNGWI Anne-Gaelle", actualCustomerAnneGaelle.get().getName());	
	}
	
	@Test
	public void finding_existing_customer_should_return_customer_bdd() throws Exception {
		// Given alain

		given(mockEntityManager.find(Customer.class, alain.getId())).willReturn(alain);
		
		// When
		Optional<Customer> actualCustomer = dao.findById( alain.getId());
		
		// Then
		assertTrue(actualCustomer.isPresent());
		assertEquals(alain.getId(), actualCustomer.get().getId());
		assertEquals(alain.getName(), actualCustomer.get().getName());
		assertEquals(alain.getAddress(), actualCustomer.get().getAddress());
	}
	/**
	 * Matchers used : anyLong(), anyString() and eq.
	 * We use these matchers when we don’t particularly care about the input to the Mock
	 * we are only interested in coding it’s return behaviour, 
	 * and we want it to behave the same way under all conditions
	 * As already noted, but worth paying special attention to, 
	 * is that when using argument matchers all arguments must be argument matchers, 
	 * you can not mix and match real values with argument matchers 
	 * or you will get a runtime error from Mockito.
	 * @throws Exception
	 */
	
	@Test
	public void finding_all_customers_should_return_all_customers() throws Exception {
		// Given
		given(mockQuery.getResultList()).willReturn(Arrays.asList(alain, rudy, anneGaelle));
		//given(mockQuery.getResultList()).willAnswer(i -> Arrays.asList(alain, rudy, anneGaelle));
		// Rather than bringing SQL into our test case we will just use the anyString()
		//Matcher to get the mock createQuery() to fire
		given(mockEntityManager.createQuery(anyString(), eq(Customer.class))).willReturn(mockQuery);
		
		// When
		List<Customer> actualCustomers = dao.findAll();
		
		// Then
		assertEquals(actualCustomers.size(), 3);
		assertTrue(actualCustomers.contains(alain));
		assertTrue(actualCustomers.contains(rudy));
		assertTrue(actualCustomers.contains(anneGaelle));
	}
	
	
	@Test
	public void updating_customer_should_result_in_latest_version_from_db_being_returned() throws Exception {
		// Given
		Customer inputCustomer = new Customer (10L, "Input", "My Address");
		Customer outputCustomer = new Customer (10L, "Output", "My Address");
		given(mockEntityManager.merge(inputCustomer)).willReturn(outputCustomer);
		
		// When
		Customer actualCustomer = dao.update(inputCustomer);
		
		// Then
		assertEquals(actualCustomer.getName(), outputCustomer.getName());
		assertEquals(inputCustomer.getId(), outputCustomer.getId());
		assertFalse(inputCustomer.getName() == outputCustomer.getName());
		assertTrue(inputCustomer.getAddress() == outputCustomer.getAddress());


	}
}
