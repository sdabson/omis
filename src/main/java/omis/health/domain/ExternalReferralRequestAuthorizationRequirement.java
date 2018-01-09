package omis.health.domain;

import java.io.Serializable;

/**
 * Indicates that a request for an external referral is authorized.
 * 
 * Once authorized, the external referral can be scheduled.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jul 11, 2014)
 * @since OMIS 3.0
 */
public interface ExternalReferralRequestAuthorizationRequirement
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
	 * Sets the request for external referral.
	 * 
	 * @param referralRequest request for external referral
	 */
	void setReferralRequest(HealthRequest referralRequest);
	
	/**
	 * Returns the request for external referral.
	 * 
	 * @return request for external referral
	 */
	HealthRequest getReferralRequest();
	
	/**
	 * Sets the external referral authorization request.
	 * 
	 * @param authorizationRequest external referral authorization request
	 */
	void setAuthorizationRequest(
			ExternalReferralAuthorizationRequest authorizationRequest);
	
	/**
	 * Returns the external referral authorization request.
	 * 
	 * @return external referral authorization request
	 */
	ExternalReferralAuthorizationRequest getAuthorizationRequest();
	
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