package omis.health.domain;

import java.util.Date;

import omis.facility.domain.Facility;
import omis.offender.domain.OffenderAssociable;

/**
 * Request for authorization of external referral.
 * 
 * <p>Before each external referral is authorized, a request for authorization
 * is required.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (May 7, 2014)
 * @since OMIS 3.0
 */
public interface ExternalReferralAuthorizationRequest
		extends OffenderAssociable {
	
	/**
	 * Sets the ID.
	 * 
	 * @param id ID
	 */
	void setId(Long id);
	
	/**
	 * Returns the ID.
	 * 
	 * @return ID
	 */
	Long getId();
	
	/**
	 * Sets the facility.
	 * 
	 * @param facility facility
	 */
	void setFacility(Facility facility);

	/**
	 * Returns the facility.
	 * 
	 * @return facility
	 */
	Facility getFacility();
	
	/**
	 * Sets the date during which the referral is to be scheduled.
	 * 
	 * @param date date
	 */
	void setDate(Date date);
	
	/**
	 * Returns the date during which the referral is to be scheduled.
	 * 
	 * @return date
	 */
	Date getDate();
	
	/**
	 * Sets the provider assignment.
	 * 
	 * @param providerAssignment provider assignment
	 */
	void setProviderAssignment(ProviderAssignment providerAssignment);
	
	/**
	 * Returns the provider assignment.
	 * 
	 * @return provider assignment
	 */
	ProviderAssignment getProviderAssignment();
	
	/**
	 * Sets the medical facility at which the referral is to take place.
	 * 
	 * @param medicalFacility medical facility
	 */
	void setMedicalFacility(MedicalFacility medicalFacility);
	
	/**
	 * Returns the medical facility at which the referral is to take place.
	 * 
	 * @return medical facility
	 */
	MedicalFacility getMedicalFacility();
	
	/**
	 * Sets the reason.
	 * 
	 * @param reason reason
	 */
	void setReason(ExternalReferralReason reason);
	
	/**
	 * Returns the reason.
	 * 
	 * @return reason
	 */
	ExternalReferralReason getReason();
	
	/**
	 * Sets reason notes.
	 * 
	 * @param reasonNotes reason notes
	 */
	void setReasonNotes(String reasonNotes);
	
	/**
	 * Returns reason notes.
	 * 
	 * @return
	 */
	String getReasonNotes();
	
	/**
	 * Sets assignment of provider that referred referral.
	 * 
	 * @param referredByProviderAssignment assignment of provider that
	 * referred referral
	 */
	void setReferredByProviderAssignment(
			ProviderAssignment referredByProviderAssignment);
	
	/**
	 * Returns assignment of provider that referred referral.
	 * 
	 * @return assignment of provider that referred referral
	 */
	ProviderAssignment getReferredByProviderAssignment();
	
	/**
	 * Sets referred date.
	 * 
	 * @param referredDate referred date
	 */
	void setReferredDate(Date referredDate);
	
	/**
	 * Returns referred date.
	 * 
	 * @return referred date
	 */
	Date getReferredDate();
	
	/**
	 * Compares {@code this} and {@code obj} for equality.
	 * 
	 * <p>Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * 
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