package de.sixhours.knimecc.operations;

/**
 * The different operations that are supported in this application
 * 
 * @author jasper
 */
public enum Operations {
	CAPITALIZE,
	NEGATE,
	REVERSE;
	
	/**
	 * Convert a shorthand string to the corresponding {@link Operation} entry
	 * @param op 						 the shorthand string
	 * @return  						 {@link Operations}-instance
	 * @throws IllegalArgumentException  if the shorthand is not recognized
	 */
	public static Operations fromString(String op) throws IllegalArgumentException {
		if (op.equals("cap")) return Operations.CAPITALIZE;
		else if (op.equals("neg")) return Operations.NEGATE;
		else if (op.equals("rev")) return Operations.REVERSE;
		else throw new IllegalArgumentException("Unknown operation: " + op);
	}
}
