package omis.health.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;

/**
 * Internal referral.
 * 
 * @author Joel Norris
 * @author Stephen Abson
 * @version 0.1.0 (Mar 31, 2014)
 * @since OMIS 3.0
 */
public interface InternalReferral extends Creatable, Updatable {

	/**
	 * Returns the ID of the inside referral.
	 * 
	 * @return ID
	 */
	Long getId();
	
	/**
	 * Sets the Id of the inside referral.
	 * 
	 * @param id ID
	 */
	void setId(Long id);
	
	/**
	 * Return the offender appointment association of the inside referral.
	 * 
	 * @return offender appointment association
	 */
	OffenderAppointmentAssociation getOffenderAppointmentAssociation();
	
	/**
	 * Set the offender appointment association of the inside referral.
	 * 
	 * @param offenderAppointmentAssociation offender appointment association
	 */
	void setOffenderAppointmentAssociation(
			OffenderAppointmentAssociation offenderAppointmentAssociation);
	
	/**
	 * Sets the provider level.
	 * 
	 * @param providerLevel provider level
	 */
	void setProviderLevel(ProviderLevel providerLevel);
	
	/**
	 * Returns the provider level.
	 * 
	 * @return provider level
	 */
	ProviderLevel getProviderLevel();
	
	/**
	 * Sets the reason.
	 * 
	 * @param reason reason
	 */
	void setReason(InternalReferralReason reason);
	
	/**
	 * Returns the reason.
	 * 
	 * @return reason
	 */
	InternalReferralReason getReason();
	
	/**
	 * Sets the reason for the status of the appointment.
	 * 
	 * @param statusReason reason for status of appointment
	 */
	void setStatusReason(InternalReferralStatusReason statusReason);
	
	/**
	 * Returns the reason for the status of the appointment.
	 * 
	 * @return reason for status of appointment
	 */
	InternalReferralStatusReason getStatusReason();
	
	/**
	 * Return the reschedule required status of the inside referral.
	 * 
	 * @return reschedule required status
	 */
	Boolean getRescheduleRequired();
	
	/**
	 * Set the reschedule required status of the inside referral.
	 * 
	 * @param recheduleRequired reschedule required
	 */
	void setRescheduleRequired(Boolean recheduleRequired);
	
	/**
	 * Returns the scheduling notes of the inside referral.
	 * 
	 * @return scheduling notes
	 */
	String getSchedulingNotes();
	
	/**
	 * Sets scheduling notes of the inside referral.
	 * 
	 * @param schedulingNotes scheduling notes
	 */
	void setSchedulingNotes(String schedulingNotes);
	
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
	 * Return the action request of the inside referral.
	 * 
	 * @return health request
	 */
	HealthRequest getActionRequest();
	
	/**
	 * Set the action request of the inside referral.
	 * 
	 * @param actionRequest health request
	 */
	void setActionRequest(HealthRequest actionRequest);	
	
	/**
	 * Returns the location designator.
	 * 
	 * @return referral location designator
	 */
	ReferralLocationDesignator getLocationDesignator();

	/**
	 * Sets the location designator.
	 * 
	 * @param locationDesignator
	 */
	void setLocationDesignator(ReferralLocationDesignator locationDesignator);
	
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