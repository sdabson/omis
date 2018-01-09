package omis.web.function;

/**
 * Function to access system properties.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Apr 5, 2016)
 * @since OMIS 3.0
 */
public class SystemPropertyFunction {

	// Instantiation not allowed
	private SystemPropertyFunction() {
		
		// Prevents accidental instantiation
		throw new UnsupportedOperationException("Instances not allowed");
	}
	
	/**
	 * Returns system property of {@code name}.
	 * 
	 * @param name name
	 * @return system property
	 */
	public static String getSystemProperty(final String name) {
		return System.getProperty(name);
	}
}