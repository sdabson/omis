package omis.substanceuse.domain.impl;

import omis.substanceuse.domain.UseFrequency;

/**
 * Use frequency implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Nov 17, 2016)
 * @since OMIS 3.0
 */
public class UseFrequencyImpl implements UseFrequency {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private Boolean valid;
	private Short sortOrder;
	
	/**
	 * Instantiates a default instance of use frequency.
	 */
	public UseFrequencyImpl() {
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
	public Boolean getValid() {
		return this.valid;
	}

	/** {@inheritDoc} */
	@Override
	public void setValid(final Boolean valid) {
		this.valid = valid;
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
}