package omis.health.domain;

import java.io.Serializable;

/**
 * Offender appointment action.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Mar 31, 2014)
 * @since OMIS 3.0
 */
public enum OffenderAppointmentAction implements Serializable {
	
	/** Kept. */
	KEPT,
	
	/** Cancelled. */
	CANCELLED,
	
	/** Refused. */
	REFUSED;
	
	/**
	 * Returns the instance name.
	 * 
	 * @return instance name
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