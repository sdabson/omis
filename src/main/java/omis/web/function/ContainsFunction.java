package omis.web.function;

import java.util.Collection;

/**
 * Function determines whether an object is contained in a collection.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jul 24, 2013)
 * @since OMIS 3.0
 */
public final class ContainsFunction {

	// Hide constructor and prevent instantiation
	private ContainsFunction() {
		throw new AssertionError("Accidental instantiation");
	}
	
	/**
	 * Returns whether {@code collection} contains {@code object}.
	 * 
	 * @param collection collection
	 * @param object object
	 * @return whether {@code collection} contains {@code object}
	 */
	public static boolean contains(
			final Collection<?> collection, final Object object) {
		return collection.contains(object);
	}
}