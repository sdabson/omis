package omis.victim.web.controller;

/**
 * Target of a victim screen redirect.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Nov 17, 2015)
 * @since OMIS 3.0
 */
public enum VictimRedirectTarget {

	/** Offender. */
	OFFENDER,
	
	/** Victim. */
	VICTIM;
	
	/**
	 * Returns {@code this.name()}.
	 * 
	 * <p>See {@link Enum#name()}.
	 * 
	 * @return {@code this.name()}
	 */
	public String getName() {
		return this.name();
	}
}