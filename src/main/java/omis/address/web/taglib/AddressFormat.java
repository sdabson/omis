package omis.address.web.taglib;

/**
 * Address format.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Apr 3, 2015)
 * @since OMIS 3.0
 */
public enum AddressFormat {
	
	/** Line 1 */
	LINE1,
	/** Line 2 */
	LINE2;

	/**
	 * Returns the name of {@code this}.
	 * 
	 * <p>This is done by returning {@code this.name()}.
	 * 
	 * @return name of {@code this}
	 */
	public String getName() {
		return this.name();
	}
}