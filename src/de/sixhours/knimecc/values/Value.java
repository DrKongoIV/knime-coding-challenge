package de.sixhours.knimecc.values;

/**
 * Represents a data point
 * 
 * @author jasper
 */
public abstract class Value {
	
	/**
	 * A data point needs to be constructible from a string
	 * @param s  the string which shall be converted
	 */
	public Value(String s) {}
	
	/**
	 * A data point must have a meaningful way of being printed to the output file
	 */
	@Override
	public abstract String toString();
	
	/**
	 * Construct a data point from its type shorthand and the initializing string
	 * @param type  shorthand for the data type
	 * @param init  the string which shall be converted
	 * @return  {@type Value}-instance 
	 */
	public static Value fromString(String type, String init) throws IllegalArgumentException {
		if (type.equals("string")) return new StringValue(init);
		if (type.equals("int")) return new IntegerValue(init);
		if (type.equals("double")) return new DoubleValue(init);
		throw new IllegalArgumentException("Unknown data type: " + type);
	}
}
