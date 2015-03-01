package org.sobngwi.swagger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@EnableAutoConfiguration
@ComponentScan({"org.sobngwi.swagger,"
		+ "org.sobngwi.convert,"
		+ "com.apress.prospring4.ch12,"
		+ "org.sobngwi.dao,"
		+ "org.sobngwi.service,"
		+ "org.sobngwi.controller"
		})
@ImportResource("/META-INF/spring/app-context-annotation.xml")
public class Application {

	public static void main(String[] args) {

		   SpringApplication.run(Application.class, args);
	       
	}

}

