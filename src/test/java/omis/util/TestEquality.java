package omis.util;

/** Runs unit test for properties of equality.
 * @author Ryan JOhns
 * @version 0.1.0 (Jan 2, 2014)
 * @since OMIS 3.0 */
public final class TestEquality {
	private static final String SYMMETRY_FAILED_MSG = 
			"%1$s:  failed the symmetric property of equals";
	private static final String REFLEXIVITY_FAILED_MSG = 
			"%1$s: failed the reflexive property of equals";
	private static final String TRANSIVITY_FAILED_MSG =
			"%1$s: failed transitivity property of equals";
	private static final String NULL_FAILED_MSG =
			"%1$s: failed equality of null";
	private static final String NOT_EQUAL_FAILED_MSG =
			"%1$s: failed equality of null";
	private static final String EQUALS_FAILED_MSG =
			"%1$s: failed equality";
	private static final String HASHCODE_FAILED_MSG = 
			"%1$S: failed hashCode";
	private static final Object NULL = null;
	
	/* Constructor. */
	private TestEquality() { }
	
	/** Tests symmetry of equality assertion that if x.equals(y) then 
	 * y.equals(x). 
	 * @param x - object x.
	 * @param y - object y. */
	public static void testSymmetry(final Object x, final Object y) {
		assert x.equals(y) && y.equals(x) : String.format(
				SYMMETRY_FAILED_MSG, x.getClass().getCanonicalName());
	}

	/** Test reflexivity assertion the x.equals(x).
	 * @param x - object x. */
	public static void testReflexivity(final Object x) {
		assert x.equals(x) : String.format(REFLEXIVITY_FAILED_MSG, 
				x.getClass().getClass().getCanonicalName());
	}

	/** Test transitivity of equality assertion the if x.equals(y) and 
	 * y.equals(z) then x.equals(z).
	 * @param x - object x.
	 * @param y - object y.
	 * @param z - object z. */
	public static void testTransitivity(final Object x, final Object y, 
			final Object z) {
		assert (x.equals(y) == y.equals(z)) == x.equals(z) : String.format(
				TRANSIVITY_FAILED_MSG, x.getClass().getCanonicalName());
	}

	/** Tests equality of null assertion that if x is not null then 
	 * x.equals(null) is false.
	 * @param x - object x. */
	public static void testNullTest(final Object x) {
		assert !x.equals(NULL) : String.format(NULL_FAILED_MSG, 
				x.getClass().getCanonicalName());
	}

	/** Test not equals assertion that if x != y than x.equals(y) is false. 
	 * @param x - object x.
	 * @param y - object y. */
	public static void testNotEquals(final Object x, final Object y) {
		assert !x.equals(y) : String.format(NOT_EQUAL_FAILED_MSG, 
				x.getClass().getCanonicalName());
	}

	/** Tests equals assertion that if x == y than x.equals(y) is true. 
	 * @param x - object x.
	 * @param y - object y. */
	public static void testEquals(final Object x, final Object y) {
		assert x.equals(y) : String.format(EQUALS_FAILED_MSG, 
				x.getClass().getCanonicalName());

		assert x.hashCode() == y.hashCode() : String.format(
				HASHCODE_FAILED_MSG,
				x.getClass().getCanonicalName());
	}
}
