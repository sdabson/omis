package omis.health.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;

/**
 * Lab Work referral.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 30, 2014)
 * @since OMIS 3.0
 */
public interface LabWorkReferral extends Creatable, Updatable {

	/**
	 * Returns the id.
	 * 
	 * @return id
	 */
	Long getId();
	
	/**
	 * Sets the id.
	 * 
	 * @param id id
	 */
	void setId(Long id);
	
	/**
	 * Returns the offender appointment association.
	 * 
	 * @return offender appointment association
	 */
	OffenderAppointmentAssociation getOffenderAppointmentAssociation();
	
	/**
	 * Sets the offender appointment association.
	 * 
	 * @param offenderAppointmentAssociation offender appointment association
	 */
	void setOffenderAppointmentAssociation(OffenderAppointmentAssociation 
			offenderAppointmentAssociation);
	
	/**
	 * Returns the action request.
	 * 
	 * @return action request
	 */
	HealthRequest getActionRequest();
	
	/**
	 * Sets the action request.
	 * 
	 * @param actionRequest health request
	 */
	void setActionRequest(HealthRequest actionRequest);
	
	/**
	 * Returns the status reason.
	 * 
	 * @return status reason
	 */
	LabWorkReferralStatusReason getStatusReason();
	
	/**
	 * Sets the status reason.
	 * 
	 * @param statusReason lab work referral status reason
	 */
	void setStatusReason(LabWorkReferralStatusReason statusReason);
	
	/**
	 * Returns the assessment notes.
	 * 
	 * @return assessment notes
	 */
	String getAssessmentNotes();
	
	/**
	 * Sets the assessment notes.
	 * 
	 * @param assessmentNotes assessment notes
	 */
	void setAssessmentNotes(String assessmentNotes);
	
	/**
	 * Returns the scheduling notes.
	 * 
	 * @return scheduling notes
	 */
	String getSchedulingNotes();
	
	/**
	 * Sets the scheduling notes.
	 * 
	 * @param schedulingNotes scheduling notes
	 */
	void setSchedulingNotes(String schedulingNotes);
	
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