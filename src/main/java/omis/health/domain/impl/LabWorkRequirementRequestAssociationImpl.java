package omis.health.domain.impl;

import omis.health.domain.LabWorkRequirement;
import omis.health.domain.LabWorkRequirementRequest;
import omis.health.domain.LabWorkRequirementRequestAssociation;

/**
 * Implementation of association of a lab work requirement for a lab 
 * work request.
 * 
 * @author Sheronda Vaughn
 * @version 0.1.0 (June 4, 2014)
 * @since OMIS 3.0
 */
public class LabWorkRequirementRequestAssociationImpl 
	implements LabWorkRequirementRequestAssociation{

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private LabWorkRequirementRequest request;
	
	private LabWorkRequirement requirement;
	
	/**
	 * Instantiates an implementation of association of a lab work 
	 * requirement for a lab work request.
	 */
	public LabWorkRequirementRequestAssociationImpl() {
		// Default constructor.
	}

	/** {@inheritDoc} */
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/** {@inheritDoc} */
	@Override
	public void setRequest(LabWorkRequirementRequest request) {
		this.request = request;
	}

	/** {@inheritDoc} */
	@Override
	public LabWorkRequirementRequest getRequest() {
		return this.request;
	}

	/** {@inheritDoc} */
	@Override
	public void setRequirement(LabWorkRequirement requirement) {
		this.requirement = requirement;
	}

	/** {@inheritDoc} */
	@Override
	public LabWorkRequirement getRequirement() {
		return this.requirement;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof LabWorkRequirementRequestAssociation)) {
			return false;
		}
		LabWorkRequirementRequestAssociation that = 
				(LabWorkRequirementRequestAssociation) obj;
		if (this.getRequest() == null) {
			throw new IllegalStateException("Request required");
		}
		if (!this.getRequest().equals(that.getRequest())) {
			return false;
		}
		if (this.getRequirement() == null) {
			throw new IllegalStateException("Requirment required");
		}
		if (!this.getRequirement().equals(that.getRequirement())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getRequest() == null) {
			throw new IllegalStateException("Request required");			
		}
		if (this.getRequirement() == null) {
			throw new IllegalStateException("Requirment required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getRequest().hashCode();
		hashCode = 29 * hashCode + this.getRequirement().hashCode();
		return hashCode;
	}
}
