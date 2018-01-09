package omis.health.web.form;

/**
 * Operation to be performed on lab work appointment.
 * 
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jun 4,2014)
 * @since OMIS 3.0
 */
public enum LabWorkAppointmentOperation {
	
	/** Schedule a lab work appointment. */
	SCHEDULE,
	
	/** Update a lab work appointment. */
	UPDATE,
	
	/** Remove a lab work appointment. */
	REMOVE;
	
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
