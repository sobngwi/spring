package com.sobngwi.hibernate.nsy135.modele.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sobngwi.hibernate.nsy135.modele.persistence.Pays;


@RunWith(MockitoJUnitRunner.class)
public class ServiceHibernateTest {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ServiceHibernateTest.class);

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
			default   : return null;
			}
		}
	};

	@Mock
	private ServiceHibernateDAO dao;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		setupCountries();
	}

	
	@Test
	public void findKwonCountry() throws Exception {
		
		
		    ClassLoader loader = ServiceHibernateTest.class.getClassLoader();
	        System.out.println(loader.getResource("com/sobngwi/hibernate/nsy135/modele/dao/ServiceHibernateTest.class"));
				// Given
		
		        LOGGER.info(" The DAO Mock ! " + dao) ;
				when(dao.findPaysByCode(france.getCode())).thenReturn(france) ;
				LOGGER.info(" finding france.getCode() ===" + france.getCode());
				Pays actualCountry = dao.findPaysByCode(france.getCode()) ;
				LOGGER.info(" actualCountry getCode() ===" + actualCountry.getCode() );
				// Then
				assertTrue(actualCountry != null );
				assertEquals(france.getCode(), actualCountry.getCode());
				assertEquals(france.getNom(), actualCountry.getNom());
				assertEquals(france.getLangue(), actualCountry.getLangue());
				verify(dao, times(0)).findPaysById(anyString()) ;
				verify(dao, times(1)).findPaysByCode(anyString()) ;
		
	}
	
	@Test
	public void findUnkwonCountry() throws Exception {
	
				// Given
		        String unknown="unknown" ;
      	      
				when(dao.findPaysByCode(france.getCode())).thenReturn(france) ;
				Pays actualCountry = dao.findPaysByCode(france.getCode()) ;
				Pays unkwonCountry = dao.findPaysByCode(unknown) ;
				
				// Then
				assertTrue(actualCountry != null );
				assertEquals(france.getCode(), actualCountry.getCode());
				assertEquals(france.getNom(), actualCountry.getNom());
				assertEquals(france.getLangue(), actualCountry.getLangue());
				assertFalse( unkwonCountry != null ) ;
				
				verify(dao, times(1)).findPaysByCode(france.getCode()) ;
				verify(dao, times(1)).findPaysByCode(unknown) ;
		
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

}
