package omis.web.function;

/**
 * Function determines whether a variable is an instance of a class.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Nov 16, 2012)
 * @since OMIS 3.0
 */
public final class InstanceofFunction {

	// Hide constructor for utility class
	private InstanceofFunction() {
		throw new AssertionError("Instances not allowed");
	}
	
	/**
	 * Returns whether the specified variable is an instance of the
	 * specified class name.
	 * 
	 * @param var variable the instance of which to test
	 * @param className class name
	 * @return whether {@code var} is an instance of {@code className}
	 */
	public static boolean isInstanceof(
			final Object var, final String className) {
		Class<?> clazz;
		try {
			clazz = Class.forName(className);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Class not found: " + className, e);
		}
		return clazz.isAssignableFrom(var.getClass());
	}
}