package omis.health.domain;

import java.io.Serializable;
import java.util.Date;

import omis.facility.domain.Facility;

/**
 * Health appointment scheduled during a health event to address the
 * issues described in a health request.
 * 
 * @author Stephen Abson
 * @version 0.1.2 (Dec 6, 2012)
 * @since OMIS 3.0
 */
public interface HealthAppointment extends Serializable {

	/**
	 * Sets the ID of the health appointment.
	 * 
	 * @param id health appointment ID
	 */
	void setId(Long id);
	
	/**
	 * Returns the ID of the health appointment.
	 * 
	 * @return health appointment ID
	 */
	Long getId();
	
	/**
	 * Returns the date of the health appointment.
	 * 
	 * @return date
	 */
	Date getDate();

	/**
	 * Sets the date of the health appointment.
	 * 
	 * @param date date
	 */
	void setDate(Date date);

	/**
	 * Returns the start time of the health appointment.
	 * 
	 * @return start time
	 */
	Date getStartTime();

	/**
	 * Sets the start time of the health appointment.
	 * 
	 * @param startTime start time
	 */
	void setStartTime(Date startTime);

	/**
	 * Returns the end time of the health appointment.
	 * 
	 * @return end time
	 */
	Date getEndTime();

	/**
	 * Sets the end time of the health appointment.
	 * 
	 * @param endTime end time
	 */
	void setEndTime(Date endTime);
	
	/**
	 * Returns the status of the health appointment.
	 * 
	 * @return status
	 */
	HealthAppointmentStatus getStatus();

	/**
	 * Sets the status of the health appointment.
	 * 
	 * @param status status
	 */
	void setStatus(HealthAppointmentStatus status);

	/**
	 * Returns the time kept of the health appointment.
	 * 
	 * @return time kept
	 */
	Date getTimeKept();

	/**
	 * Sets the time kept of the health appointment.
	 * 
	 * @param timeKept time kept
	 */
	void setTimeKept(Date timeKept);
	
	/**
	 * Returns the facility.
	 * 
	 * @return facility
	 */
	Facility getFacility();

	/**
	 * Sets the facility.
	 * 
	 * @param facility facility
	 */
	void setFacility(Facility facility);
	
	/**
	 * Compares {@code this} and {@code object} for equality.
	 * 
	 * <p>Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * 
	 * @param object reference object with which to compare {@code this}
	 * @return {@code true} if {@code this} and {@code object} are equal;
	 * {@code false} otherwise
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the comparison is {@code null} 
	 */
	@Override
	boolean equals(Object object);
	
	/**
	 * Returns a hash code for {@code this}.
	 * 
	 * <p>Any mandatory property of {@code this} may be used in the hash code.
	 * If a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * 
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null}
	 */
	@Override
	int hashCode();
}