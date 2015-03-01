/**
 * 
 */
package org.sobngwi.mockito.stubbing;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Alain Narcisse SOBNGWI
 *
 */
public class StringProcessor {
	
	 final Logger logger = LoggerFactory.getLogger(StringProcessor.class);
	 private IPrinter printer;
     private String currentBuffer;

     public StringProcessor(IPrinter printer) {
             this.printer = printer;
     }

     public Optional<String> statusAndTest() throws PrinterNotConnectedException {
             printer.printTestPage();
             return Optional.ofNullable(currentBuffer);
     }

}
