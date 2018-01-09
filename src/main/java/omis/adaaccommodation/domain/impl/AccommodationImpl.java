package omis.adaaccommodation.domain.impl;

import omis.adaaccommodation.domain.Accommodation;
import omis.adaaccommodation.domain.AccommodationCategory;
import omis.adaaccommodation.domain.Disability;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;

/**
 * Implementation of ADA accommodation.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jul 17, 2015)
 * @since OMIS 3.0
 */
public class AccommodationImpl 
	implements Accommodation {
	
	private static final long serialVersionUID = 1L;
		
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	private Long id;
	
	private String description;
	
	private Disability disability;
	
	private AccommodationCategory accommodationCategory;

	/** Instantiates an implementation of ADA accommodation. */
	public AccommodationImpl() {
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
	public void setUpdateSignature(final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature;
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
	public void setDisability(final Disability disability) {
		this.disability = disability;
	}

	/** {@inheritDoc} */
	@Override
	public Disability getDisability() {
		return this.disability;
	}

	/** {@inheritDoc} */
	@Override
	public void setAccommodationCategory(
			final AccommodationCategory accommodationCategory) {
		this.accommodationCategory = accommodationCategory;
	}

	/** {@inheritDoc} */
	@Override
	public AccommodationCategory getAccommodationCategory() {
		return this.accommodationCategory;
	}
}