package omis.paroleboarditinerary.domain;

/**
 * Attendee role category.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Nov 16, 2017)
 * @since OMIS 3.0
 */
public enum AttendeeRoleCategory {

	/* Attendee role category is primary */
	PRIMARY,
	/* Attendee role category is alternate */
	ALTERNATE;
	
	/**
	 * Returns <code>this.name()</code>.
	 * 
	 * @return <code>this.name()</code>
	 */
	public String getName() {
		return this.name();
	}
}
