package de.sixhours.knimecc.values;

import de.sixhours.knimecc.operations.Negatable;

/**
 * Wrapper class that holds a double
 * 
 * @author jasper
 */
public class DoubleValue extends Value implements Negatable {
	
	/**
	 * The primitive value
	 */
	private double value;
	
	/**
	 * Construct by parsing a string
	 * @param s  the numerical string
	 */
	public DoubleValue(String s) {
		super(s);
		this.value = Double.parseDouble(s);
	}

	/**
	 * Negate the double by taking its inverse
	 */
	@Override
	public void negate() {
		this.value *= -1;
	}

	/**
	 * Print the double in its decimal representation
	 */
	@Override
	public String toString() {
		return Double.toString(this.value);
	}

}
