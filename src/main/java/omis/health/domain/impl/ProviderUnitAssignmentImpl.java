package omis.health.domain.impl;

import omis.facility.domain.Unit;
import omis.health.domain.ProviderAssignment;
import omis.health.domain.ProviderUnitAssignment;

/**
 * Provider unit assignment implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Mar 31, 2014)
 * @since OMIS 3.0
 */
public class ProviderUnitAssignmentImpl implements ProviderUnitAssignment {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Unit unit;
	
	private ProviderAssignment providerAssignment;
	
	/**
	 * Instantiates a default instance of provider unit assignment 
	 * implementation.
	 */
	public ProviderUnitAssignmentImpl() {
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
	public Unit getUnit() {
		return this.unit;
	}

	/** {@inheritDoc} */
	@Override
	public void setUnit(final Unit unit) {
		this.unit = unit;
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
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}	
		if (!(o instanceof ProviderUnitAssignment)) {
			return false;
		}
		
		ProviderUnitAssignment that = (ProviderUnitAssignment) o;
		
		if (this.getUnit() == null) {
			throw new IllegalStateException("Unit required.");
		}
		if (!this.getUnit().equals(that.getUnit())) {
			return false;
		}
		if (this.getProviderAssignment() == null) {
			throw new IllegalStateException("Provider assignment required.");
		}
		if (!this.getProviderAssignment().equals(
				that.getProviderAssignment())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getUnit() == null) {
			throw new IllegalStateException("Unit required.");
		}
		if (this.getProviderAssignment() == null) {
			throw new IllegalStateException("Provider assignment required.");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getUnit().hashCode();
		hashCode = 29 * hashCode + this.getProviderAssignment().hashCode();
		
		return hashCode;
	}
}