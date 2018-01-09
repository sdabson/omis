package omis.region.domain.impl;

import omis.region.domain.County;
import omis.region.domain.State;

/**
 * Implementation of county.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Sep 3, 2013)
 * @since OMIS 3.0
 */
public class CountyImpl
		implements County {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String name;
	
	private Boolean valid;
	
	private State state;

	/** Instantiates a default implementation of county. */
	public CountyImpl() {
		// Default instantiation
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
	public void setState(final State state) {
		this.state = state;
	}

	/** {@inheritDoc} */
	@Override
	public State getState() {
		return this.state;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof County)) {
			return false;
		}
		County that = (County) obj;
		if (this.getName() == null) {
			throw new IllegalStateException("Name required");
		}
		if (!this.getName().equals(that.getName())) {
			return false;
		}
		if (this.getState() == null) {
			throw new IllegalStateException("State required");
		}
		if (!this.getState().equals(that.getState())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getName() == null) {
			throw new IllegalStateException("Name required");
		}
		if (this.getState() == null) {
			throw new IllegalStateException("State required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getName().hashCode();
		hashCode = 29 * hashCode + this.getState().hashCode();
		return hashCode;
	}
}