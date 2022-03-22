import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.sixhours.knimecc.Statistics;

class StatisticsTest {

	@Test
	void test() {
		Statistics ins = Statistics.getInstance();
		assertNotNull(ins);
		
		// initially, no lines have been read
		assertEquals(0, ins.getNoOfLinesRead());
		assertEquals(0, ins.getNoOfUniqueLines());
		
		// the instance should still be the same as prior
		assertEquals(ins, Statistics.getInstance());
		
		// add some lines
		ins.updateStatisticsWithLine("foo");
		ins.updateStatisticsWithLine("fooo");
		ins.updateStatisticsWithLine("bar");
		ins.updateStatisticsWithLine("BaR");
		ins.updateStatisticsWithLine("bar");

		assertEquals(5, ins.getNoOfLinesRead());
		assertEquals(4, ins.getNoOfUniqueLines());
		
		// empty strings do count
		ins.updateStatisticsWithLine("");
		assertEquals(6, ins.getNoOfLinesRead());
		assertEquals(5, ins.getNoOfUniqueLines());
		
		// null values do also count -- should they? :D
		ins.updateStatisticsWithLine(null);
		assertEquals(6, ins.getNoOfLinesRead());
		assertEquals(5, ins.getNoOfUniqueLines());
		

		
	}

}
