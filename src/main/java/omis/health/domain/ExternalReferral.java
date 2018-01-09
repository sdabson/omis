package omis.health.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;

/**
 * External Referral.
 * 
 * @author Joel Norris
 * @author Stephen Abson
 * @version 0.1.0 (Mar 31, 2014)
 * @since OMIS 3.0
 */
public interface ExternalReferral extends Creatable, Updatable {

	/**
	 * Returns the ID.
	 * @return id
	 */
	Long getId();
	
	/**
	 * Sets the ID.
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
	void setOffenderAppointmentAssociation(
			OffenderAppointmentAssociation offenderAppointmentAssociation);
	
	/**
	 * Sets the status reason.
	 * 
	 * @param statusReason status reason
	 */
	void setStatusReason(ExternalReferralStatusReason statusReason);
	
	/**
	 * Returns the status reason.
	 * 
	 * @return status reason
	 */
	ExternalReferralStatusReason getStatusReason();
	
	/**
	 * Returns the reschedule required status.
	 * 
	 * @return reschedule required status
	 */
	Boolean getRescheduleRequired();
	
	/**
	 * Sets the reschedule required status.
	 * 
	 * @param rescheduleRequired reschedule required
	 */
	void setRescheduleRequired(Boolean rescheduleRequired);
	
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
	 * Returns date assessment was reported.
	 * 
	 * @return date assessment was reported
	 */
	Date getReportedDate();
	
	/**
	 * Sets date assessment was reported.
	 * 
	 * @param reportedDate date assessment was reported
	 */
	void setReportedDate(Date reportedDate);
	
	/**
	 * Returns the action request.
	 * 
	 * @return action request
	 */
	HealthRequest getActionRequest();
	
	/**
	 * Sets the action request.
	 * 
	 * @param actionRequest action request
	 */
	void setActionRequest(HealthRequest actionRequest);
	
	/**
	 * Returns the external referral authorization.
	 * 
	 * @return external referral authorization
	 */
	ExternalReferralAuthorization getAuthorization();

	/**
	 * Sets the external referral authorization.
	 * 
	 * @param authorization external referral authorization
	 */
	void setAuthorization(
			ExternalReferralAuthorization authorization);
	
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
}