package omis.adaaccommodation.domain.impl;

import omis.adaaccommodation.domain.AuthorizationSourceCategory;
import omis.audit.domain.CreationSignature;

/**
 * Implementation of ADA authorization source category.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jul 17, 2015)
 * @since OMIS 3.0
 */
public class AuthorizationSourceCategoryImpl implements
		AuthorizationSourceCategory {

	private static final long serialVersionUID = 1L;
	
	private CreationSignature creationSignature;
	
	private Long id;
	
	private String name;
	
	private Boolean valid;

	/** Instantiates an implementation of ADA authorization source category. */
	public AuthorizationSourceCategoryImpl() {
		// Default instantiation
	}

	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
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
	public void setName(final String name) {
		this.name = name;
	}

	/** {@inheritDoc} */
	@Override
	public String getName() {
		return this.name;
	}

	/** {@inheritDoc} */
	@Override
	public void setValid(final Boolean valid) {
		this.valid = valid;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getValid() {
		return this.valid;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}	
		if (!(obj instanceof AuthorizationSourceCategory)) {
			return false;
		}
		
		AuthorizationSourceCategory that = (AuthorizationSourceCategory) obj;
		
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