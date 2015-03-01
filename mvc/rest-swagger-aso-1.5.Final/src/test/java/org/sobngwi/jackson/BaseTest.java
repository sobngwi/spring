package org.sobngwi.jackson;

import java.util.Arrays;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;


@Configuration
@ComponentScan({
		  "org.sobngwi.convert,"
		+ "com.apress.prospring4.ch12,"
		+ "org.sobngwi.dao,"
		+ "org.sobngwi.service,"
		+ "org.sobngwi.controller"
		})
@ImportResource("/META-INF/spring/app-context-annotation.xml")
@Profile("test")
public abstract class BaseTest extends junit.framework.TestCase
{
	final Logger logger = LoggerFactory.getLogger(BaseTest.class);

	   @Bean
	    public DataSource dataSource() {
	    	logger.info("setting the datasource !!!");
	        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
	        		.setName("integrationDB")
	        		.addScript("classpath:META-INF/jackson/classicmodels.sql").build();
	    }
    protected BaseTest() { }

    protected ObjectMapper mapperWithModule(boolean forceLazyLoading)
    {
        return new ObjectMapper().registerModule(hibernateModule(forceLazyLoading));
    }

    protected Hibernate4Module hibernateModule(boolean forceLazyLoading)
    {
        Hibernate4Module mod = new Hibernate4Module();
        mod.configure(Hibernate4Module.Feature.FORCE_LAZY_LOADING, forceLazyLoading);
        return mod;
    }
    
    protected void verifyException(Throwable e, String... matches)
    {
        String msg = e.getMessage();
        String lmsg = (msg == null) ? "" : msg.toLowerCase();
        for (String match : matches) {
            String lmatch = match.toLowerCase();
            if (lmsg.indexOf(lmatch) >= 0) {
                return;
            }
        }
        fail("Expected an exception with one of substrings ("+Arrays.asList(matches)+"): got one with message \""+msg+"\"");
    }

    protected String aposToQuotes(String json) {
        return json.replace("'", "\"");
    }
}
