package omis.util;

/**
 * Given ordered arguments and ordered types, checks that the types of each
 * ordered argument match the ordered types.
 * 
 * <p>If only one type is specified, all arguments are checked to be of or
 * extending this type.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Oct 31, 2012)
 * @since OMIS 3.0
 */
public final class ArgumentTypeChecker {
	
	private Class<?>[] types;
	
	private Object[] arguments; 
	
	// Hide constructor - use create instance instead
	private ArgumentTypeChecker() {
		// Default instance
	}
	
	/**
	 * Instantiates and returns an instance of an argument type checker.
	 * 
	 * @return new instance of argument type checker
	 */
	public static ArgumentTypeChecker createInstance() {
		return new ArgumentTypeChecker();
	}
	
	/**
	 * Sets the types that the types of the arguments should match.
	 * 
	 * @param types type that types of argument should match
	 * @return argument type checker
	 */
	public ArgumentTypeChecker setTypes(final Class<?>... types) {
		this.types = types;
		return this;
	}
	
	/**
	 * Sets the arguments the types of which to check.
	 * 
	 * @param arguments arguments types of which to check 
	 * @return argument type checker
	 */
	public ArgumentTypeChecker setArguments(
			final Object... arguments) {
		this.arguments = arguments;
		return this;
	}
	
	/**
	 * Performs check.
	 * 
	 * <p>This method checks whether the types of the arguments match the
	 * types specified in order. An illegal argument exception is thrown if
	 * there is more than one type and the number of types do not match
	 * the number of arguments or if one if the arguments is not of the
	 * matching or single type.
	 * 
	 * @throws IllegalStateException if no types are arguments were set
	 * @throws IllegalArgumentException if there is a mismatch between
	 * number of arguments and number of types or if the argument types do
	 * not match the expected types
	 */
	public void check() {
		if (this.types == null || this.types.length <= 0) {
			throw new IllegalStateException("Types required");
		}
		if (this.arguments == null || this.arguments.length <= 0) {
			throw new IllegalStateException("Arguments required");
		}
		if (this.types.length != this.arguments.length
				&& this.types.length != 1) {
			throw new IllegalArgumentException(
					this.types.length + " arguments or single type required");
		}
		for (int i = 0; i < this.arguments.length; i++) {
			int index;
			if (this.types.length != 1) {
				index = i;
			} else {
				index = 0;
			}
			Class<?> type = this.types[index];
			Object argument = this.arguments[i];
			if (!(type.isAssignableFrom(argument.getClass()))) {
				throw new IllegalArgumentException("Argument "
						+ i + " must be of type " + type.getName());
			}
		}
	}
}