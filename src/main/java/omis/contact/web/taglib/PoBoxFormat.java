package omis.contact.web.taglib;

/**
 * Post Office Box Format.
 * 
 * @author Joel Norris
 * @version 0.1.0 (January 17, 2017)
 * @since OMIS 3.0
 */
public enum PoBoxFormat {
	
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