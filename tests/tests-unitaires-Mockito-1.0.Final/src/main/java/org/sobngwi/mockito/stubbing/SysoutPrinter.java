package org.sobngwi.mockito.stubbing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.apress.prospring4.ch13.ContactController;


/**
 * @author Alain Narcisse SOBNGWi
 *
 */

public class SysoutPrinter implements IPrinter {

	final Logger logger = LoggerFactory.getLogger(SysoutPrinter.class);
	
	@Override
	public void printTestPage() {
		logger.info("This is a test page");
	}

}