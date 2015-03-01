/**
 * 
 */
package org.sobngwi.main;

import org.sobngwi.animal.Cat;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Alain Narcisse SOBNGWI
 *
 */
public class Main {

	/**
	 * 
	 */
	public Main() {

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext  apps = new ClassPathXmlApplicationContext("edit.xml");
		Cat nathalie = apps.getBean("nathalie" , Cat.class) ;
		System.out.println(nathalie.getAbilities().getValue());
	}

}
