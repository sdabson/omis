package omis.incident.web.form;

/**
 * Involved party option.
 * 
 * @author Joel Norris
 * @version 0.1.0 (February 13, 2019)
 * @since OMIS 3.0
 */
public enum InvolvedPartyOption {
	
	/** Staff */
	STAFF,
	/** Offender */
	OFFENDER,
	/** Other */
	OTHER,
	/** None */
	NONE;
	
	/**
	 * 	Returns the name.
	 * 
	 * @return name
	 */
	public String getName() {
		return this.name();
	}
	
	/**
	 * Returns the name.
	 * 
	 * @return name
	 */
	@Override
	public String toString() {
		return this.name();
	}
}
