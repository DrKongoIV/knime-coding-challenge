package de.sixhours.knimecc.values;

import de.sixhours.knimecc.operations.Capitalizable;
import de.sixhours.knimecc.operations.Reversible;

/**
 * Wrapper class that holds a double
 * 
 * @author jasper
 */
public class StringValue extends Value implements Capitalizable, Reversible {
	
	/**
	 * The actual string
	 */
	private String value;
	
	/**
	 * Construct the string
	 * @param s  the string
	 */
	public StringValue(String s) {
		super(s);
		this.value = s;
	}
	
	/**
	 * Reverse the string by reversing its characters
	 * Relying on {@link StringBuilder} to handle Unicode edge cases in a meaningful way
	 */
	@Override
	public void reverse() {
		this.value = new StringBuilder(this.value).reverse().toString();
	}

	/**
	 * Capitalize a string by capitalizing its lowercase letters
	 */
	@Override
	public void capitalize() {
		this.value = this.value.toUpperCase();
	}

	/**
	 * Literally just the string itself
	 */
	@Override
	public String toString() {
		return this.value;
	}

}
