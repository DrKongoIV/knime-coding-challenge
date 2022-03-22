package de.sixhours.knimecc.operations;

/**
 * When implementing this interface, a data type should have a meaningful way of being negated
 * 
 * @author jasper
 */
public interface Negatable {
	
	/**
	 * Negate the data
	 */
	public void negate();
	
}
