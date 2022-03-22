package de.sixhours.knimecc.operations;

/**
 * When implementing this interface, a data type should have a meaningful way of being capitalized
 * 
 * @author jasper
 */
public interface Capitalizable {
	
	/**
	 * Apply capitalization to the data
	 */
	public void capitalize();
	
}
