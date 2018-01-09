package omis.supervisionfee.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.supervisionfee.domain.SupervisionFeeRequirementReason;

/**
 * Supervision fee requirement reason implementation.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Aug 27, 2014)
 * @since OMIS 3.0
 */
public class SupervisionFeeRequirementReasonImpl 
	implements 	SupervisionFeeRequirementReason {

	private static final long serialVersionUID = 1L;

	private Boolean valid;

	private String name;

	private Long id;
	
	private CreationSignature creationSignature;
	
	/**
	 * Instantiates a default instance of supervision fee requirement reason.
	 */
	public SupervisionFeeRequirementReasonImpl() {
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
	public String getName() {
		return this.name;
	}

	/**{@inheritDoc} */
	@Override
	public void setName(final String name) {
		this.name = name;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getValid() {
		return this.valid;
	}

	/**{@inheritDoc} */
	@Override
	public void setValid(final Boolean valid) {
		this.valid = valid;
	}
	
	/**{@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return creationSignature;
	}

	/**{@inheritDoc} */
	@Override
	public void setCreationSignature(final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof SupervisionFeeRequirementReason)) {
			return false;
		}
		SupervisionFeeRequirementReason that = (
				SupervisionFeeRequirementReason) obj;
		if (this.getName() == null) {
			throw new IllegalStateException("Name required.");
		}
		if (!this.getName().equals(that.getName())) {
			return false;
		}
		return true;
	}

	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getName() == null) {
			throw new IllegalStateException("Name Required.");
		}
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getName().hashCode();
		
		return hashCode;	
	}
}