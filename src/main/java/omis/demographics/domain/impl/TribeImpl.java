package omis.demographics.domain.impl;

import omis.demographics.domain.Tribe;

/**
 * Implementation of tribe.
 * 
 * @author Stephen Abson
 * @version 0.1.1 (Jan 10, 2013)
 * @since OMIS 3.0
 */
public class TribeImpl implements Tribe {

	private static final long serialVersionUID = 1L;
	
	private Long id;

	private String name;

	private Boolean valid;

	/** Instantiates a default tribe. */
	public TribeImpl() {
		// Do nothing
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
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof Tribe)) {
			return false;
		}
		Tribe that = (Tribe) obj;
		if (this.getName() == null) {
			throw new IllegalStateException("Name required");
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
			throw new IllegalStateException("Name required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getName().hashCode();
		return hashCode;
	}
	
	/** {@inheritDoc} */
	@Override
	public String toString() {
		return String.format("#%d: %s", this.getId(), this.getName());
	}
}