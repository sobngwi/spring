/**
 * 
 */
package org.sobngwi.mockito.verification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Alain Narcisse SOBNGWI
 *Verification is the process of confirming the behaviour of a Mock.
 * It is useful in determining that the class we are testing has interacted in an expected way with any of its dependencies. 
 * Sometimes we aren’t interested in the values which are returned from a Mock,
 *  but are instead interested in how the class under test interacted with it, 
 *  what values were sent in or how often it was called. 
 *  The process of confirming this behaviour is <b> verification </b>
 *  and there are a number of tools which Mockito provides to allow us to do it.
 *  The verify method takes the Mock object as a parameter and returns an instance of the same Class as the Mock
 */
public class PrinterDiagnostics {
	
	final Logger logger = LoggerFactory.getLogger(PrinterDiagnostics.class);
	private IPrinter printer;
	
	public PrinterDiagnostics(IPrinter printer) {
		this.printer = printer;
	}
	
	public void diagnosticPrint(String text, Integer copies, Boolean collate) {
		logger.info( "Call of diagnosticPrint method "); 
		StringBuilder diagnostic = new StringBuilder();
		diagnostic.append("** Diagnostic Print **\n");
		diagnostic.append("*** Copies: ").append(copies).append(" ***\n");
		diagnostic.append("*** Collate: ").append(collate).append(" ***\n");
		diagnostic.append("********************\n\n");
		
		printer.print(new StringBuilder().append(diagnostic).append(text).toString(), copies, collate);
	}
	
	public void diagnosticAndOriginalPrint(String text, Integer copies, Boolean collate) {
		logger.info( "Call of diagnosticAndOriginalPrint method "); 
		diagnosticPrint(text, copies, collate);
		printer.print(text, copies, collate);
	}
}