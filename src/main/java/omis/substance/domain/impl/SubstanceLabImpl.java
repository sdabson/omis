package omis.substance.domain.impl;

import omis.substance.domain.SubstanceLab;

/**
 * Substance lab implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Mar 7, 2016)
 * @since OMIS 3.0
 */
public class SubstanceLabImpl implements SubstanceLab {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String name;
	
	private Boolean privateLab;
	
	private Short sortOrder;
	
	private Boolean valid;
	
	/**
	 * Instantiates a default instance of substance lab.
	 */
	public SubstanceLabImpl() {
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
	public Boolean getPrivateLab() {
		return this.privateLab;
	}

	/** {@inheritDoc} */
	@Override
	public void setPrivateLab(final Boolean privateLab) {
		this.privateLab = privateLab;
	}

	/** {@inheritDoc} */
	@Override
	public Short getSortOrder() {
		return this.sortOrder;
	}

	/** {@inheritDoc} */
	@Override
	public void setSortOrder(final Short sortOrder) {
		this.sortOrder = sortOrder;
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