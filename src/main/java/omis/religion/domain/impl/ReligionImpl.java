package omis.religion.domain.impl;

import omis.religion.domain.Religion;
import omis.religion.domain.ReligionGroup;

/**
 * Implementation of religion.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 22, 2014)
 * @since OMIS 3.0
 */
public class ReligionImpl
		implements Religion {

	private static final long serialVersionUID = 1L;
	
	private Long id;

	private String name;
	
	private ReligionGroup group;

	private Boolean valid;

	private Short sortOrder;
	
	/** Instantiates an implementation of religion. */
	public ReligionImpl() {
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
	public void setGroup(final ReligionGroup group) {
		this.group = group;
	}

	/** {@inheritDoc} */
	@Override
	public ReligionGroup getGroup() {
		return this.group;
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
	public void setSortOrder(final Short sortOrder) {
		this.sortOrder = sortOrder;
	}

	/** {@inheritDoc} */
	@Override
	public Short getSortOrder() {
		return this.sortOrder;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof Religion)) {
			return false;
		}
		Religion that = (Religion) obj;
		if (this.getName() == null) {
			throw new IllegalStateException("Name required");
		}
		if (!(this.getName().equals(that.getName()))) {
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
}