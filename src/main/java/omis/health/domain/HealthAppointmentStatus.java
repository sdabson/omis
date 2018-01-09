package omis.health.domain;

/**
 * Status of health appointment.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (May 8, 2014)
 * @since OMIS 3.0
 */
public enum HealthAppointmentStatus {

	/** Appointment is kept. */
	KEPT,
	
	/** Appointment is rescheduled. */
	RESCHEDULED,
	
	/** Appointment is cancelled. */
	CANCELLED;
	
	/**
	 * Returns the instance name.
	 * 
	 * @return name
	 */
	public String getName() {
		return this.name();
	}
}