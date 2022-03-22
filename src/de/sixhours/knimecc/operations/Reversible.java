package de.sixhours.knimecc.operations;

/**
 * When implementing this interface, a data type should have a meaningful way of being reversed
 * 
 * @author jasper
 */
public interface Reversible {
	
	/**
	 * Reverse the data
	 */
	public void reverse();
}
