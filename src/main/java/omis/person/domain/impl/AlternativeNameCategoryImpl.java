package omis.person.domain.impl;

import omis.person.domain.AlternativeNameCategory;

/**
 * Implementation of category of alternative name.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Nov 21, 2013)
 * @since OMIS 3.0
 */
public class AlternativeNameCategoryImpl
		implements AlternativeNameCategory {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;

	private String name;

	private String description;

	private Boolean valid;

	private Short sortOrder;

	/** Instantiates an implementation of category for alternative name. */
	public AlternativeNameCategoryImpl() {
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
		if (!(obj instanceof AlternativeNameCategory)) {
			return false;
		}
		AlternativeNameCategory that = (AlternativeNameCategory) obj;
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