package de.sixhours.knimecc.values;

import de.sixhours.knimecc.operations.Negatable;
import de.sixhours.knimecc.operations.Reversible;

/**
 * Wrapper class that holds an integer
 * 
 * @author jasper
 */
public class IntegerValue extends Value implements Negatable, Reversible {
	
	/**
	 * The primitive value
	 */
	private int value;
	
	/**
	 * Construct by parsing a string
	 * @param s  the numerical string
	 */
	public IntegerValue(String s) {
		super(s);
		this.value = Integer.parseInt(s);
	}
	
	/**
	 * Negate the integer by taking its inverse
	 */
	@Override
	public void negate() {
		this.value *= -1;
	}

	/**
	 * Reverse the integer by reversing its decimal representation
	 * The sign of the integer will not change.
	 */
	@Override
	public void reverse() {
		int result = 0;
		while (this.value != 0) {
			int remainder = this.value % 10;
			result = result * 10 + remainder;
			this.value /= 10;
		}
		this.value = result;
	}

	/**
	 * Print the integer in its decimal representation
	 */
	@Override
	public String toString() {
		return Integer.toString(this.value);
	}

}
