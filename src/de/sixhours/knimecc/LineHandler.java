package de.sixhours.knimecc;

import java.util.Arrays;

import de.sixhours.knimecc.operations.Capitalizable;
import de.sixhours.knimecc.operations.Negatable;
import de.sixhours.knimecc.operations.Operations;
import de.sixhours.knimecc.operations.Reversible;
import de.sixhours.knimecc.values.Value;

/**
 * This class is designed to execute a series of operations on the provided data
 * Once configured, multiple data points can be processed with the same instance
 * 
 * @author jasper
 */
public class LineHandler {
	
	private boolean countLines = true;
	private String inputType;
	private Operations[] operations;
	
	/**
	 * Creates a LineHandler instance, provided the data type of the data points and the operations
	 * @param inputType  either "string", "int", or "double" -- as of right now.
	 * @param stringOps  array of either "cap", "neg", or "rev" -- more to come (maybe)
	 */
	public LineHandler(String inputType, String[] stringOps) {
		this.inputType = inputType;
		// convert the operations from a short string to actual operations
		this.operations = Arrays.stream(stringOps).map(Operations::fromString).toArray(Operations[]::new);
	}
	
	/**
	 * Apply the specified operations to a string and return the result
	 * @param string  the data point that will be processed
	 * @return        the resulting string representation of the data point
	 */
	public String handle(String string) {
		if (this.countLines) Statistics.getInstance().updateStatisticsWithLine(string);
		
		Value v = Value.fromString(this.inputType, string);
		
		// Apply the operations
		for (Operations op : this.operations) {
			switch (op) {
			case CAPITALIZE:
				((Capitalizable) v).capitalize();
				break;
			case NEGATE:
				((Negatable) v).negate();
				break;
			case REVERSE:
				((Reversible) v).reverse();
				break;
			}
		}
		// every Value can be transformed into a string
		return v.toString();
	}
	
	/**
	 * Disables logging to the {@link Statistics}-instance
	 */
	public void disableCounting() {
		countLines = false;
	}
}
