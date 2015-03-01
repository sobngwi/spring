package org.sobngwi.test.configuration;

import javax.sql.DataSource;

import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.util.fileloader.XlsDataFileLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
@ImportResource("classpath:META-INF/spring/datasource-tx-jpa.xml")
@ComponentScan(basePackages={"org.sobngwi.service"})
@Profile("test")
public class ServiceTestConfig {
	final Logger logger = LoggerFactory.getLogger(ServiceTestConfig.class);
    @Bean
    public DataSource dataSource() {
    	logger.info("setting the datasource !!!");
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
        		.setName("integrationDB")
        		.addScript("classpath:META-INF/config/schema.sql").build();
    }

    @Bean(name="databaseTester")
    public DataSourceDatabaseTester dataSourceDatabaseTester() {
        DataSourceDatabaseTester databaseTester =
                new DataSourceDatabaseTester(dataSource());
        return databaseTester;
    }

    @Bean(name="xlsDataFileLoader")
    public XlsDataFileLoader xlsDataFileLoader() {
    	logger.info("loading the xls loader !!!");
    	return new XlsDataFileLoader();
    }
}
