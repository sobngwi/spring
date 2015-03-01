/**
 * 
 */
package org.sobngwi.interfaces;

import org.sobngwi.modele.Moteur;

/**
 * Cette interface ser de pase pour l implementation du savoir faire des Mecanicien.
 * @author Alain Narcisse SOBNGWI
 * @version 1.0
 */
public interface IMecanicien {

	/**
	 * Renvoie le moteur changé. 
	 * Attention cette methode demmare un thread pour traiter le changement du moteur.
	 *  Thread t = new Thread(Runnable r )  t.start() 
	 * @param moteur moteur a changé.
	 * @return le moteur sur lequel on a effectué les changements.
	 * @throws RuntimeException si le moteur est un fake. 
	 */
	Moteur changerMoteur( Moteur moteur);
}
