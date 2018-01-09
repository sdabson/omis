package omis.health.domain.impl;

import omis.health.domain.Lab;
import omis.health.domain.LabWorkerAssignment;
import omis.health.domain.ProviderAssignment;

/**
 * Lab Worker Assignment Implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Apr 1, 2014)
 * @since OMIS 3.0
 */
public class LabWorkerAssignmentImpl 
	implements LabWorkerAssignment {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private ProviderAssignment providerAssignment;
	
	private Lab lab;
	
	/**
	 * Instantiates a default instance of lab worker assignment implementation.
	 */
	public LabWorkerAssignmentImpl() {
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
	public Lab getLab() {
		return this.lab;
	}

	/** {@inheritDoc} */
	@Override
	public void setLab(final Lab lab) {
		this.lab = lab;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}	
		if (!(o instanceof LabWorkerAssignment)) {
			return false;
		}
		
		LabWorkerAssignment that = (LabWorkerAssignment) o;
		
		if (this.getProviderAssignment() == null) {
			throw new IllegalStateException("Provider Assignment required.");
		}
		if (!this.getProviderAssignment().equals(
				that.getProviderAssignment())) {
			return false;
		}
		if (this.getLab() == null) {
			throw new IllegalStateException("Lab required.");
		}
		if (!this.getLab().equals(that.getLab())) {
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
		if (this.getLab() == null) {
			throw new IllegalStateException("Lab required.");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getProviderAssignment().hashCode();
		hashCode = 29 * hashCode + this.getLab().hashCode();
		
		return hashCode;
	}
}