package com.sobngwi.hibernate.nsy135.modele.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sobngwi.hibernate.nsy135.modele.persistence.Pays;


@RunWith(MockitoJUnitRunner.class)
public class ServiceHibernateDAOTest {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ServiceHibernateDAOTest.class);

	private Pays france, cameroun, senegal;
	private Answer<Pays> withCountryById = new Answer<Pays>() {
		@Override
		public Pays answer(InvocationOnMock invocation) throws Throwable {
			Object[] args = invocation.getArguments();
			String  code = ((String)args[1]).toUpperCase(); // Cast to int for switch.
			switch (code) {
			case "FR" : return france;
			case "CM" : return cameroun;
			case "SN" : return senegal;
			default : return null;
			}
		}
	};
	

	@Mock
	private ServiceHibernateDAO dao;
	
	@Mock
	@PersistenceContext(unitName="persistence")
	private EntityManager mockEntityManager;
	
	@Mock
	private TypedQuery<Pays> mockQuery;

	@Before
	public void setUp() throws Exception {
		dao = new ServiceHibernateDAO(mockEntityManager);
		//MockitoAnnotations.initMocks(this);
		setupCountries();
	}

	private void setupCountries() {
		
		france = new Pays() ; 
		france.setCode("FR"); 
		france.setNom("FRANCE");
		france.setLangue("FRANCAIS");
		
		cameroun = new Pays( ) ; 
		cameroun.setCode("CM"); 
		cameroun.setNom("CAMEROUN");
		cameroun.setLangue("FRANCAIS");
		
		senegal = new Pays( ) ;
		senegal.setCode("SN") ; 
		senegal.setNom("SENEGAL") ;
		senegal.setLangue("WOLOFF");		
	}

	
	@Test
	public void finding_existing_country_should_return_country() throws Exception {
		// Given
		LOGGER.info(" finding dao ===" + dao);
		LOGGER.info(" finding mockQuery ===" + mockQuery);
		when(mockEntityManager.find(Pays.class, france.getCode())).thenReturn(france);
		Optional<Pays> actualCountry = dao.findPaysById(france.getCode());
	
		// Then
		assertTrue(actualCountry.isPresent() );
		assertEquals(france.getCode(), actualCountry.get().getCode());
		assertEquals(france.getNom(), actualCountry.get().getNom());
		assertEquals(france.getLangue(), actualCountry.get().getLangue());
		verify(mockEntityManager, times(1)).find(Pays.class, france.getCode()) ;
		
	}
	
	@Test
	public void invoking_mock_with_unexpected_argument_returns_null() throws Exception {
		// Given
		String expectedCode = "BAN";
		String unexpectedCode = "UNX";
		

		Pays expectedCountry = new Pays( ) ;
		expectedCountry = new Pays() ; expectedCountry.setCode("FR"); 
		expectedCountry.setNom("FRANCE");
		expectedCountry.setLangue("FRANCAIS");
		
		when(mockEntityManager.find(Pays.class, expectedCode)).thenReturn(expectedCountry);
		
		// When
		Optional<Pays> actualCountry = dao.findPaysById(unexpectedCode);
		
		// Then
		assertFalse(actualCountry.isPresent());
	}
	
	@Test
	public void invoking_mock_with_different_argument_returns_different_countries() throws Exception {
		// Given
		
		String expectedFR = "RF";
		String expectedCM = "CM";
		
		when(mockEntityManager.find(Pays.class, expectedFR)).thenReturn(france);
		when(mockEntityManager.find(Pays.class, expectedCM)).thenReturn(cameroun);
		
		// When
		Optional<Pays> actualFR = dao.findPaysById(expectedFR);
		Optional<Pays> actualCM = dao.findPaysById(expectedCM);
		
		// Then
		assertEquals(france.getNom(), actualFR.get().getNom());
		assertEquals(cameroun.getNom(), actualCM.get().getNom());
	}
	
	@Test
	public void invoking_mock_with_chained_stubs_returns_different_countries() throws Exception {
		// Given
		String expectedFR = "FR";
		
		
		when(mockEntityManager.find(Pays.class, expectedFR))
			.thenReturn(france).thenReturn(cameroun);
		
		// When
		Optional<Pays> actualFR = dao.findPaysById(expectedFR);
		Optional<Pays> actualCM = dao.findPaysById(expectedFR); // The second time i need cameroun.
		
		// Then
		assertEquals(france.getNom(),     actualFR.get().getNom());
		assertEquals(cameroun.getNom(),   actualCM.get().getNom());
	}
	
	@Test
	public void finding_missing_country_should_return_null() throws Exception {
		// Given
		String expectedCode = "BB";
		when(mockEntityManager.find(Pays.class, expectedCode)).thenReturn(null);
		
		// When
		Optional<Pays> actualCustomer = dao.findPaysById(expectedCode);
		
		// Then
		assertFalse(actualCustomer.isPresent());
	}
	
	@Test
	public void finding_customer_should_respond_appropriately() throws Exception {
		// Given
		String expectedCode = "FR";
		
		Pays expectedCountry2 = null;
		
		when(mockEntityManager.find(Pays.class, expectedCode)).thenReturn(france, expectedCountry2);
		
		// When
		Optional<Pays> france = dao.findPaysById(expectedCode);
		Optional<Pays> actualCountry = dao.findPaysById(expectedCode);
		
		// Then
		assertTrue(france.isPresent());
		assertFalse(actualCountry.isPresent());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void finding_country_should_throw_exception_up_the_stack() throws Exception {
		// Given
		String expectedCode = "FR";
		
		when(mockEntityManager.find(Pays.class, expectedCode)).thenThrow(new IllegalArgumentException());
		
		// When
		dao.findPaysById(expectedCode);
		
		// Then Never reach here
		fail("Exception should be thrown.");
	}
	
	@Test
	public void finding_customer_by_id_returns_appropriate_customer() throws Exception {
		// Given
		String[] expectedCode = {"CM", "FR", "SN"};
		
		when(mockEntityManager.find(eq(Pays.class), anyLong())).thenAnswer(withCountryById);
		
		// When
		Optional<Pays> actualCameroun =  dao.findPaysById(expectedCode[0]);
		Optional<Pays> actualFrance   =  dao.findPaysById(expectedCode[1]);
		Optional<Pays> actualSenegal  =  dao.findPaysById(expectedCode[2]);
		
		// Then
		assertEquals(france.getNom(), actualFrance.get().getNom());
		assertEquals(senegal.getNom(), actualSenegal.get().getNom());
		assertEquals(cameroun.getNom(), actualCameroun.get().getNom());	
	}
	
	@Test
	public void finding_existing_pays_should_return_pays_bdd() throws Exception {
		// Given
	   // france as Contry
		
		
		given(mockEntityManager.find(Pays.class, france.getCode())).willReturn(france);
		
		// When
		Optional<Pays> actualFrance = dao.findPaysById(france.getCode());
		
		// Then
		assertTrue(actualFrance.isPresent());
		assertEquals(france.getCode(), actualFrance.get().getCode());
		assertEquals(france.getNom(), actualFrance.get().getNom());
		assertEquals(france.getLangue(), actualFrance.get().getLangue());
	}
	
	@Test
	public void finding_all_customers_should_return_all_customers() throws Exception {
		// Given
		given(mockQuery.getResultList()).willAnswer(i -> Arrays.asList(france, cameroun, senegal));
		given(mockEntityManager.createQuery(anyString(), eq(Pays.class))).willReturn(mockQuery);
		
		// When
		List<Pays> actualCountries = dao.findAllCountries();
		// Then
		assertEquals(actualCountries.size(), 3);	
		assertTrue(actualCountries.contains(cameroun));
		assertTrue(actualCountries.contains(senegal));
		assertTrue(actualCountries.contains(france));
	}
	
	@Test
	public void updating_customer_should_result_in_latest_version_from_db_being_returned() throws Exception {
		// Given cameroun , update the  language
		 Pays camerounUpdate = new Pays()  ; 
		 camerounUpdate.setCode(cameroun.getCode());
		 camerounUpdate.setNom(cameroun.getNom());
		 camerounUpdate.setLangue("CAMEROUNAIS");
		given(mockEntityManager.merge(cameroun)).willReturn(camerounUpdate);
		
		// When
		Pays actualCameroun = dao.updatePays(cameroun);
		// Then
		assertEquals(actualCameroun.getLangue(), camerounUpdate.getLangue());
	}
	
	
}
