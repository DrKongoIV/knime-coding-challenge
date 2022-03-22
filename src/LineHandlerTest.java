import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.sixhours.knimecc.LineHandler;
import de.sixhours.knimecc.Statistics;

/**
 * 
 */

/**
 * @author jasper
 *
 */
class LineHandlerTest {

	/**
	 * Test method for {@link de.sixhours.knimecc.LineHandler#handle(java.lang.String)}.
	 */
	@Test
	void test() {
		Statistics stats = Statistics.getInstance();
		
		LineHandler h1 = new LineHandler("string", new String[0]);
		
		// Initializing the handler should not count in the statistics
		assertEquals(0, stats.getNoOfLinesRead());
		
		// h1 should not change anything
		assertEquals("foo", h1.handle("foo"));
		assertEquals("", h1.handle(""));
		
		assertEquals(2, stats.getNoOfLinesRead());
		
		// same with doubles
		LineHandler h2 = new LineHandler("double", new String[0]);
		assertEquals("0.4", h2.handle("0.4"));
		assertEquals("200.0", h2.handle("200"));

		// Test from task #2
		String[] ops3 = {"neg", "rev"};
		LineHandler h3 = new LineHandler("int", ops3);
		
		assertEquals("-201", h3.handle("102"));
		assertEquals("-1", h3.handle("10"));
		assertEquals("5", h3.handle("-5"));
		
		// Just for fun
		assertEquals("-1", h3.handle("10"));
		assertEquals("-31", h3.handle("13"));
		assertEquals("-49", h3.handle("94"));
		assertEquals("-61", h3.handle("16"));
		assertEquals("-24", h3.handle("42"));
		assertEquals("-76", h3.handle("67"));
		
		// if implemented, check here for graceful failure etc...
		
	}

}
