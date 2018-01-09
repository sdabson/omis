package omis.substance.domain.impl;

import omis.substance.domain.Substance;

/**
 * Substance Impl.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 3, 2013)
 * @since OMIS 3.0
 */
public class SubstanceImpl implements Substance {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String name;
	
	private Boolean valid;
	
	private Boolean testable;

	/**
	 * Instantiates a default instance of substance implementation.
	 */
	public SubstanceImpl() {
		//Default constructor
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
	public Boolean getTestable() {
		return this.testable;
	}

	/** {@inheritDoc} */
	@Override
	public void setTestable(final Boolean testable) {
		this.testable = testable;
	}
	
}