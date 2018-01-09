package omis.specialneed.domain.impl;

import omis.specialneed.domain.SpecialNeedClassification;

/**
 * Implementation of special need classification.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Aug 9, 2016)
 * @since OMIS 3.0
 */
public class SpecialNeedClassificationImpl 
	implements SpecialNeedClassification {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String name;
	
	private Boolean valid;

	/** Instantiates an implementation of SpecialNeedClassificationImpl */
	public SpecialNeedClassificationImpl() {
		// Default constructor.
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

	/** {@inheritDoc} */
	@Override
	public void setName(final String name) {
		this.name = name;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getValid() {
		return this.valid;
	}

	/** {@inheritDoc} */
	@Override
	public void setValid(final Boolean valid) {
		this.valid = valid;
	}
}