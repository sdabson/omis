package omis.health.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;

/**
 * Intake Physical.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Apr 1, 2014)
 * @since OMIS 3.0
 */
public interface IntakePhysical extends Creatable, Updatable {

	/**
	 * Returns the ID of the intake physical.
	 * 
	 * @return id
	 */
	Long getId();
	
	/**
	 * Sets the ID of the intake physical.
	 * 
	 * @param id id
	 */
	void setId(Long id);
	
	/**
	 * Returns the offender appointment association of the intake physical.
	 * 
	 * @return offender appointment association
	 */
	OffenderAppointmentAssociation getOffenderAppointmentAssociation();
	
	/**
	 * Sets the offender appointment association of the intake physical.
	 * 
	 * @param offenderAppointmentAssociation offender appointment association
	 */
	void setOffenderAppointmentAssociation(
			OffenderAppointmentAssociation offenderAppointmentAssociation);
	
	/**
	 * Returns the kept status of the intake physical.
	 * 
	 * @return kept status
	 */
	Boolean getKept();
	
	/**
	 * Sets the kept status of the intake physical.
	 * 
	 * @param kept ketp status
	 */
	void setKept(Boolean kept);
	
	/**
	 * Returns the reschedule required status of the intake physical.
	 * 
	 * @return reschedule required
	 */
	Boolean getRescheduleRequired();
	
	/**
	 * Sets the reschedule required of the intake physical.
	 * 
	 * @param rescheduleRequired reschedule required
	 */
	void setRescheduleRequired(Boolean rescheduleRequired);
	
	/**
	 * Returns the time kept of the intake physical.
	 * 
	 * @return time kept
	 */
	Date getTimeKept();
	
	/**
	 * Sets the time kept of the intake physical.
	 * 
	 * @param timeKept time kept
	 */
	void setTimeKept(Date timeKept);
	
	/**
	 * Returns the notes of the intake physical.
	 * 
	 * @return notes
	 */
	String getNotes();
	
	/**
	 * Sets the notes of the intake physical.
	 * 
	 * @param notes notes
	 */
	void setNotes(String notes);
	
	/**
	 * Returns the action request of the intake physical.
	 * 
	 * @return action request
	 */
	HealthRequest getActionRequest();
	
	/**
	 * Sets the action request of the intake physical.
	 * 
	 * @param actionRequest action request
	 */
	void setActionRequest(HealthRequest actionRequest);
	
	/**
	 * Compares {@code this} and {@code obj} for equality.
	 * <p>
	 * Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * @param obj reference object with which to compare {@code this}
	 * @return {@code true} if {@code this} and {@code obj} are equal;
	 * {@code false} otherwise
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the comparison is {@code null} 
	 */
	@Override
	boolean equals(Object obj);
	
	/**
	 * Returns a hash code for {@code this}.
	 * <p>
	 * Any mandatory property of {@code this} may be used in the hash code. If
	 * a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null}
	 */
	@Override
	int hashCode();
}
