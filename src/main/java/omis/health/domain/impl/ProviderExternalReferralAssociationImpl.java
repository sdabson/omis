package omis.health.domain.impl;

import omis.health.domain.ExternalReferral;
import omis.health.domain.ProviderAssignment;
import omis.health.domain.ProviderExternalReferralAssociation;

/**
 * Provider External Referral Association Implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Mar 31, 2014)
 * @since OMIS 3.0
 */
public class ProviderExternalReferralAssociationImpl 
	implements ProviderExternalReferralAssociation {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private ExternalReferral externalReferral;
	
	private ProviderAssignment providerAssignment;
	
	private Boolean cancelled;
	
	private Boolean primary;
	
	/**
	 * Instantiates a default instance of provider external referral
	 * association.
	 */
	public ProviderExternalReferralAssociationImpl() {
		//Default constructor.
	}

	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/** {@inheritDoc} */
	@Override
	public void setId(
			final Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public ExternalReferral getExternalReferral() {
		return this.externalReferral;
	}

	/** {@inheritDoc} */
	@Override
	public void setExternalReferral(
			final ExternalReferral externalReferral) {
		this.externalReferral = externalReferral;
	}
	
	/** {@inheritDoc} */
	@Override
	public ProviderAssignment getProviderAssignment() {
		return this.providerAssignment;
	}

	/** {@inheritDoc} */
	@Override
	public void setProviderAssignment(
			final ProviderAssignment providerAssignment) {
		this.providerAssignment = providerAssignment;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getCancelled() {
		return this.cancelled;
	}

	/** {@inheritDoc} */
	@Override
	public void setCancelled(
			final Boolean cancelled) {
		this.cancelled = cancelled;
	}
	
	/** {@inheritDoc} */
	@Override
	public Boolean getPrimary() {
		return this.primary;
	}

	/** {@inheritDoc} */
	@Override
	public void setPrimary(final Boolean primary) {
		this.primary = primary;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}	
		if (!(o instanceof ProviderExternalReferralAssociation)) {
			return false;
		}
		
		ProviderExternalReferralAssociation that = 
				(ProviderExternalReferralAssociation) o;
		
		if (this.getProviderAssignment() == null) {
			throw new IllegalStateException("Provider Assignment required.");
		}
		if (!this.getProviderAssignment().equals(
				that.getProviderAssignment())) {
			return false;
		}
		if (this.getExternalReferral() == null) {
			throw new IllegalStateException("External Referral required.");
		}
		if (!this.getExternalReferral().equals(that.getExternalReferral())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getProviderAssignment() == null) {
			throw new IllegalStateException("ProviderAssignment required.");
		}
		if (this.getExternalReferral() == null) {
			throw new IllegalStateException("External Referral required.");
		}
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getProviderAssignment().hashCode();
		hashCode = 29 * hashCode + this.getExternalReferral().hashCode();
		
		return hashCode;
	}
}