/**
 * 
 */
package org.sobngwi.main;

import org.sobngwi.techicien.Plombier;
import org.sobngwi.techicien.Polytechnicien;
import org.sobngwi.techicien.PolytechnicienElectricien;
import org.sobngwi.techicien.PolytechnicienPlombier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * @author Alain Narcisse SOBNGWi.
 *
 */
//@ComponentScan(basePackages={"org.sobngwi.service"})
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

			ApplicationContext appCtx = new ClassPathXmlApplicationContext("autowire.xml") ;
			
			Polytechnicien alain = appCtx.getBean("unPolytecnicien", Polytechnicien.class);
			System.out.println("Le nom du polytechnicien est donné en attribut lors de la contruction du bean");
			alain.develloper();
			
			PolytechnicienPlombier polyPlombier = appCtx.getBean("unPolytecnicienPlombier", PolytechnicienPlombier.class);	
			
			System.out.println( "Autowire par type du plombier dans polytechnicien ...");
			polyPlombier.changerUnTuyau();
			System.out.println( "Autowire par nom de l electricien  dans polytechnicien ...");
			PolytechnicienElectricien polyElectricien = appCtx.getBean("unPolytecnicienElectricien", PolytechnicienElectricien.class);	
			polyElectricien.changerUnePrise();
	}

}
