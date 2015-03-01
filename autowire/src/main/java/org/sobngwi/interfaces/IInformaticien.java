package org.sobngwi.interfaces;

/**
 * Cette interface ser de pase pour l implementation du savoir faire des Informaticien.
 * @author Alain Narcisse SOBNGWI
 *@version 1.0
 */
public interface IInformaticien {
	/**
	 *  C est le propre de  linformaticien que de develloper 
	 *  Attention cette methode demmare un thread pour traiter le changement du moteur.
	 *  Thread t = new Thread(Runnable r )  t.start() 
	 * le moteur sur lequel on a effectué les changements.
	 * @throws RuntimeException si le moteur est un fake. 
	 *  @author Alain Narcisse SOBNGWI 
	 */
	void develloper() ;
}
