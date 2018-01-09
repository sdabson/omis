package omis.adaaccommodation.domain.impl;

import omis.adaaccommodation.domain.DisabilityClassificationCategory;
import omis.audit.domain.CreationSignature;

/**
 * Implementation of ADA disability classifictaion category.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jul 16, 2015)
 * @since OMIS 3.0
 */
public class DisabilityClassificationCategoryImpl implements
		DisabilityClassificationCategory {

	private static final long serialVersionUID = 1L;
	
	private CreationSignature creationSignature;
	
	private Long id;
	
	private String name;
	
	private Boolean valid;

	/** Instantiates an implementation of disability classification category. */
	public DisabilityClassificationCategoryImpl() {
		// Default instantiation
	}

	/* {@inheritDoc} */
	@Override
	public void setCreationSignature(CreationSignature creationSignature) {
		this.creationSignature = creationSignature;

	}

	/* {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}

	/* {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/* {@inheritDoc} */
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	/* {@inheritDoc} */
	@Override
	public String getName() {
		return this.name;
	}

	/* {@inheritDoc} */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/* {@inheritDoc} */
	@Override
	public Boolean getValid() {
		return this.valid;
	}

	/* {@inheritDoc} */
	@Override
	public void setValid(Boolean valid) {
		this.valid = valid;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}	
		if (!(obj instanceof DisabilityClassificationCategory)) {
			return false;
		}
		
		DisabilityClassificationCategory that = (
				DisabilityClassificationCategory) obj;
		
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
			throw new IllegalStateException("Name requried.");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getName().hashCode();
		
		return hashCode;
	}
}