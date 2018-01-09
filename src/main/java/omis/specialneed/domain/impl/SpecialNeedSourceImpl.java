/**
 * 
 */
package omis.specialneed.domain.impl;

import omis.specialneed.domain.SpecialNeedSource;

/**
 * Implementation of special need source.
 * 
 * @author Joel Norris 
 * @version 0.1.0 (Feb, 14 2013)
 * @since OMIS 3.0
 */
public class SpecialNeedSourceImpl implements SpecialNeedSource {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String name;
	
	private Boolean valid;

	/** Instantiates a default instance of special need
	 * source implementation.
	 */
	public SpecialNeedSourceImpl() {
		//empty constructor
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