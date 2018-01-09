package omis.jail.domain;

/**
 * Category of jail.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Apr 18, 2016)
 * @since OMIS 3.0
 */
public enum JailCategory {

	/** County. */
	COUNTY,
	
	/** Tribal. */
	TRIBAL;
	
	/**
	 * Returns {@code this.name()}.
	 * 
	 * @return {@code this.name()}
	 */
	public String getName() {
		return this.name();
	}
}