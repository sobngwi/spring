/**
 * 
 */
package org.sobngwi.mockito.verification;

/**
 * @author ALain Narcisse SOBNGWI
 *
 */
public interface IPrinter {
	
	void printTestPage();
	
	void turnOff();
	
	void print(String text, Integer copies, Boolean collate);

}
