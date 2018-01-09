package omis.health.domain.impl;

import omis.health.domain.IntakePhysical;
import omis.health.domain.ProviderAssignment;
import omis.health.domain.ProviderIntakePhysicalAssociation;

/**
 * Intake Physical Referral Association Implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Apr 1, 2014)
 * @since OMIS 3.0
 */
public class ProviderIntakePhysicalAssociationImpl 
	implements ProviderIntakePhysicalAssociation {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private ProviderAssignment providerAssignment;
	
	private Boolean cancelled;
	
	private Boolean primary;
	
	private IntakePhysical intakePhysical;
	
	
	
	/**
	 * Instantiates a default instance of intake physical referral
	 * association.
	 */
	public ProviderIntakePhysicalAssociationImpl() {
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
	public IntakePhysical getIntakePhysical() {
		return this.intakePhysical;
	}

	/** {@inheritDoc} */
	@Override
	public void setIntakePhysical(final IntakePhysical intakePhysical) {
		this.intakePhysical = intakePhysical;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}	
		if (!(o instanceof ProviderIntakePhysicalAssociation)) {
			return false;
		}
		
		ProviderIntakePhysicalAssociation that = 
				(ProviderIntakePhysicalAssociation) o;
		
		if (this.getProviderAssignment() == null) {
			throw new IllegalStateException("Provider Assignment required.");
		}
		if (!this.getProviderAssignment().equals(
				that.getProviderAssignment())) {
			return false;
		}
		if (this.getIntakePhysical() == null) {
			throw new IllegalStateException("Intake Physical required.");
		}
		if (!this.getIntakePhysical().equals(that.getIntakePhysical())) {
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
		if (this.getIntakePhysical() == null) {
			throw new IllegalStateException("Intake Physical required.");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getProviderAssignment().hashCode();
		hashCode = 29 * hashCode + this.getIntakePhysical().hashCode();
		
		return hashCode;
	}

}