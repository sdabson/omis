package omis.health.domain.impl;

import omis.health.domain.InternalReferral;
import omis.health.domain.ProviderAssignment;
import omis.health.domain.ProviderInternalReferralAssociation;

/**
 * Provider Internal Referral Association Implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Mar 31, 2014)
 * @since OMIS 3.0
 */
public class ProviderInternalReferralAssociationImpl 
	implements ProviderInternalReferralAssociation {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private InternalReferral internalReferral;
	
	private ProviderAssignment providerAssignment;
	
	private Boolean cancelled;
	
	private Boolean primary;
	
	/**
	 * Instantiates a default instance of provider internal referral association
	 * implementation.
	 */
	public ProviderInternalReferralAssociationImpl() {
		//Default constructor.
	}

	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public InternalReferral getInternalReferral() {
		return this.internalReferral;
	}

	/** {@inheritDoc} */
	@Override
	public void setInternalReferral(final InternalReferral internalReferral) {
		this.internalReferral = internalReferral;
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
	public void setCancelled(final Boolean cancelled) {
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
		if (!(o instanceof ProviderInternalReferralAssociation)) {
			return false;
		}
		
		ProviderInternalReferralAssociation that = 
				(ProviderInternalReferralAssociation) o;
		
		if (this.getProviderAssignment() == null) {
			throw new IllegalStateException("Provider Assignment required.");
		}
		if (!this.getProviderAssignment().equals(
				that.getProviderAssignment())) {
			return false;
		}
		if (this.getInternalReferral() == null) {
			throw new IllegalStateException("Internal Referral required.");
		}
		if (!this.getInternalReferral().equals(
				that.getInternalReferral())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getProviderAssignment() == null) {
			throw new IllegalStateException("Provider Assignment required.");
		}
		if (this.getInternalReferral() == null) {
			throw new IllegalStateException("Internal Referral required.");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getProviderAssignment().hashCode();
		hashCode = 29 * hashCode + this.getInternalReferral().hashCode();
		
		return hashCode;
	}
}