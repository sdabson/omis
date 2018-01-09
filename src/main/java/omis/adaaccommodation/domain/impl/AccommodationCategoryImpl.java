package omis.adaaccommodation.domain.impl;

import omis.adaaccommodation.domain.AccommodationCategory;
import omis.audit.domain.CreationSignature;

/**
 * Implementation of ADA accommodation category.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jul 17, 2015)
 * @since OMIS 3.0
 */
public class AccommodationCategoryImpl implements AccommodationCategory {

	private static final long serialVersionUID = 1L;
	
	private CreationSignature creationSignature;
	
	private Long id;
	
	private String name;
	
	private String description;
	
	private Boolean valid;

	/** Instantiates an implementation of ADA accommodation category. */
	public AccommodationCategoryImpl() {
		// Default instantiation
	}

	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(
			final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
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
	public void setDescription(final String description) {
		this.description = description;
	}
	
	/** {@inheritDoc} */
	@Override
	public String getDescription() {
		return this.description;
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
}