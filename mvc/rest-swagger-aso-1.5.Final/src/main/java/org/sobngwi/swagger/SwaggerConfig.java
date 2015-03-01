package org.sobngwi.swagger;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import com.wordnik.swagger.model.ApiInfo;
 
@Configuration
@EnableSwagger
@ComponentScan("com.apress.prospring4.ch12")
//@EnableWebMvc
public class SwaggerConfig extends WebMvcConfigurerAdapter{
	private SpringSwaggerConfig springSwaggerConfig;

	   @Autowired
	   public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
	      this.springSwaggerConfig = springSwaggerConfig;
	      
	   }

	   @Bean //Don't forget the @Bean annotation
	   public SwaggerSpringMvcPlugin customImplementation(){
		 
	     /*  return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
	            .apiInfo(new com.mangofactory.swagger.models.dto.ApiInfo(
	            		"title", "description", "termsOfServiceUrl", "contact", "license", "licenseUrl")
	            ).includePatterns("/sobngwi/*");
	            ///.includePatterns("*.*") //.includePatterns(".*")
	       
	            */
		   return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
		   .apiInfo(apiInfo())
		   .includePatterns("/Pilote/.*");
	   }

	    private ApiInfo apiInfo() {
	      ApiInfo apiInfo = new ApiInfo(
	              "SOBNGWI SWAGGER SPRING  API",
	              "",
	              "SOBNGWI API terms of service",
	              "sobngwi@gmail.com",
	              "SOBNGWI API Licence Type",
	              ""
	        );
	      return apiInfo;
	    }
	    
	  //More configuration....

	    /* Here we register the Hibernate4Module into an ObjectMapper, then set this custom-configured ObjectMapper
	     * to the MessageConverter and return it to be added to the HttpMessageConverters of our application*/
	    public MappingJackson2HttpMessageConverter jacksonMessageConverter(){
	        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();

	        ObjectMapper mapper = new ObjectMapper();
	        //Registering Hibernate4Module to support lazy objects
	        mapper.registerModule(new Hibernate4Module());

	        messageConverter.setObjectMapper(mapper);
	        return messageConverter;

	    }

	    @Override
	    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
	        //Here we add our custom-configured HttpMessageConverter
	        converters.add(jacksonMessageConverter());
	        super.configureMessageConverters(converters);
	    }
	  
	    
}