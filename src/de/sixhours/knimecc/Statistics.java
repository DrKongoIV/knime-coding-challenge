package de.sixhours.knimecc;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Captures statistics about the lines being read from the input file.
 * 
 * @author KNIME GmbH
 */
public class Statistics {
	
	/**
	 * The singleton instance that is in use
	 */
	private static Statistics instance;

	private final Set<String> linesRead = new HashSet<>();

	private int lineCounter;
	
	/**
	 * Do not use the constructor, use getInstance() instead
	 */
	private Statistics() {}

	/**
	 * Updates statistics with respect to the given line. This method is supposed to
	 * be called when a new line has been read from the input file.
	 * 
	 * @param line
	 *            A new line that has been read from the input file.
	 */
	public void updateStatisticsWithLine(final String line) {
		lineCounter++;
		linesRead.add(line);
	}

	/**
	 * 
	 * @return the total number of lines read.
	 */
	public int getNoOfLinesRead() {
		return lineCounter;
	}

	/**
	 * 
	 * @return the number of unique lines read.
	 */
	public int getNoOfUniqueLines() {
		return linesRead.size();
	}

	/**
	 * 
	 * @return the shared {@link Statistics} instance to use.
	 */
	public static Statistics getInstance() {
		if (instance == null) instance = new Statistics();
		return instance;
	}
}
