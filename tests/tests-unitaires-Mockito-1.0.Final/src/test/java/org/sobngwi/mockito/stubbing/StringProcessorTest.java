/**
 * 
 */
package org.sobngwi.mockito.stubbing;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

/**
 * @author Alain Narcisse SOBNGWi
 *
 */




import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@RunWith(MockitoJUnitRunner.class)
public class StringProcessorTest {
	final Logger logger = LoggerFactory.getLogger(StringProcessorTest.class);
	
	@Mock
	private IPrinter printer;
	@Spy
	private SysoutPrinter sysoutPrinter;
	
	/**
	 * We want to write a test method which will test that the current buffer
	 * ie variable  String currentBuffer of StringProcessor
	 * is absent after construction and handle the printing of the test page.
	 * @throws Exception
	 */
	@Test
	public void internal_buffer_should_be_absent_after_construction() throws Exception {
		// Given
		StringProcessor processor = new StringProcessor(printer);
		
		// When
		Optional<String> actualBuffer = processor.statusAndTest();
		logger.info ( "") ;
		// Then
		assertFalse(actualBuffer.isPresent());
	}
	
	@Test
	public void internal_buffer_should_be_absent_after_construction_sysout() throws Exception {
		// Given
		StringProcessor processor = new StringProcessor(sysoutPrinter);
		
		// When
		Optional<String> actualBuffer = processor.statusAndTest();
		
		// Then
		assertFalse(actualBuffer.isPresent());
	}
	
	/**
	 * When we are doing a partial mock or Spy we can stub the method that is called to 
	 * ensure that nothing happens in it using org.mockito.Mockito.doNothing().
	 * @Result 
	 * Note the chaining of the methods doNothing.when(sysoutPrinter).printTestPage(): 
	 * this tells Mockito that when the void method printTestPage of the @Spy sysoutPrinter is called that the real method should not be executed and nothing should be done instead. 
	 * Now when we execute this test we see no output to the screen.
	 * @throws Exception
	 */
	@Test
	public void internal_buffer_should_be_absent_after_construction_sysout_with_donothing() throws Exception {
		// Given
		StringProcessor processor = new StringProcessor(sysoutPrinter);
		doNothing().when(sysoutPrinter).printTestPage();
		
		// When
		Optional<String> actualBuffer = processor.statusAndTest();
		
		// Then
		assertFalse(actualBuffer.isPresent());
	}
	/**
	 * we expand our Printer interface to 
	 * throw a new PrinterNotConnectedException exception 
	 * if the physical printer is not connected.
	 * @throws Exception
	 */
	@Test(expected = PrinterNotConnectedException.class)
	public void printer_not_connected_exception_should_be_thrown_up_the_stack() throws Exception {
		// Given
		StringProcessor processor = new StringProcessor(printer);
		doThrow(new PrinterNotConnectedException()).when(printer).printTestPage();
		
		// When
		Optional<String> actualBuffer = processor.statusAndTest();
		// Then
		fail("Exception have not been thrown ????"); // fail if exception have not been thrown.
		
	}
}
