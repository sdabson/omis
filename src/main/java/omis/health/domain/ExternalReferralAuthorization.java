package omis.health.domain;

import java.io.Serializable;
import java.util.Date;

import omis.health.domain.component.ExternalReferralMedicalPanelReviewDecision;
import omis.person.domain.Person;

/**
 * External referral authorization.
 * 
 * @author Joel Norris
 * @author Stephen Abson
 * @version 0.1.0 (Mar 31, 2014)
 * @since OMIS 3.0
 */
public interface ExternalReferralAuthorization
		extends Serializable {

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
	 * Sets the request.
	 * 
	 * @param request request
	 */
	void setRequest(ExternalReferralAuthorizationRequest request);
	
	/**
	 * Returns the request.
	 * 
	 * @return request
	 */
	ExternalReferralAuthorizationRequest getRequest();
	
	/**
	 * Sets the decision date.
	 * 
	 * @param decisionDate decision date
	 */
	void setDecisionDate(final Date decisionDate);
	
	/**
	 * Returns the decision date.
	 * 
	 * @return decision date
	 */
	Date getDecisionDate();

	/**
	 * Sets the authorized by person.
	 * 
	 * @param authorizedBy authorized by
	 */
	void setAuthorizedBy(final Person authorizedBy);
	
	/**
	 * Returns the authorized by person.
	 * 
	 * @return authorized by person
	 */
	Person getAuthorizedBy();
	
	/**
	 * Sets notes.
	 * 
	 * @param notes notes
	 */
	void setNotes(String notes);
	
	/**
	 * Returns notes.
	 * 
	 * @return notes
	 */
	String getNotes();

	/**
	 * Sets the panel review decision.
	 * 
	 * @param panelReviewDecision panel review decision
	 */
	void setPanelReviewDecision(
			final ExternalReferralMedicalPanelReviewDecision
			panelReviewDecision);

	/**
	 * Returns the panel review decision.
	 * 
	 * @return panel review decision
	 */
	ExternalReferralMedicalPanelReviewDecision getPanelReviewDecision();

	/**
	 * Sets the authorization status.
	 * 
	 * @param status external referral authorization status
	 */
	void setStatus(final ExternalReferralAuthorizationStatus status);
	
	/**
	 * Returns the authorization status.
	 * 
	 * @return status
	 */
	ExternalReferralAuthorizationStatus getStatus();
	
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