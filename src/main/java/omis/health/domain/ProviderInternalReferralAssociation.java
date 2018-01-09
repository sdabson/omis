package omis.health.domain;

import java.io.Serializable;

/**
 * Provider Internal Referral Association.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Mar 31, 2014)
 * @since OMIS 3.0
 */
public interface ProviderInternalReferralAssociation 
	extends Serializable {

	/**
	 * Returns the ID.
	 * 
	 * @return id
	 */
	Long getId();
	
	/**
	 * Sets the ID.
	 * 
	 * @param id id
	 */
	void setId(Long id);
	
	/**
	 * Returns the internal referral.
	 * 
	 * @return internal referral
	 */
	InternalReferral getInternalReferral();
	
	/**
	 * Sets the internal referral.
	 * 
	 * @param internalReferral internal referral
	 */
	void setInternalReferral(InternalReferral internalReferral);
	
	/**
	 * Returns the provider assignment.
	 * 
	 * @return provider assignment
	 */
	ProviderAssignment getProviderAssignment();
	
	/**
	 * Sets the provider assignment.
	 * 
	 * @param providerAssignment provider assignment
	 */
	void setProviderAssignment(ProviderAssignment providerAssignment);
	
	/**
	 * Returns the cancelled status.
	 * 
	 * @return cancelled status
	 */
	Boolean getCancelled();
	
	/**
	 * Sets the cancelled status.
	 * 
	 * @param cancelled cancelled
	 */
	void setCancelled(Boolean cancelled);
	
	/**
	 * Returns the primary status.
	 * 
	 * @return primary
	 */
	Boolean getPrimary();
	
	/**
	 * Sets the primary status.
	 * 
	 * @param primary primary
	 */
	void setPrimary(Boolean primary);
	
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