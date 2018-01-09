package omis.address.domain.impl;

import omis.address.domain.StreetSuffix;

/**
 * Implementation of street suffix..
 * 
 * @author Jason Nelson
 * @author Kelly Churchill
 * @author Joel Norris
 * @author Stephen Abson
 * @version 0.1.0
 * @since OMIS 3.0
 */
public class StreetSuffixImpl
		implements StreetSuffix {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String name;
	
	private String abbreviation;
	
	private Short sortOrder;
	
	private Boolean valid;

	/** Instantiates an implementation of street suffix. */
	public StreetSuffixImpl() {
		// do nothing
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
	public void setAbbreviation(final String abbreviation) {
		this.abbreviation = abbreviation;
	}

	/** {@inheritDoc} */
	@Override
	public String getAbbreviation() {
		return this.abbreviation;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setName(final String name) {
		this.name = name;
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

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StreetSuffix)) {
			return false;
		}
		StreetSuffix that = (StreetSuffix) obj;

		if (this.getName() == null) {
			throw new IllegalStateException("Name required");
		}

		if (!this.getName().equals(that.getName())) {
			return false;
		}

		if (this.getSortOrder() == null) {
			throw new IllegalStateException("Sort Order required");
		}

		if (!this.getSortOrder().equals(that.getSortOrder())) {
			return false;
		}

		if (this.getValid() == null) {
			throw new IllegalStateException("Valid required");
		}

		if (!this.getValid().equals(that.getValid())) {
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
		if (this.getValid() == null) {
			throw new IllegalStateException("Valid Indicator required");
		}

		if (this.getSortOrder() == null) {
			throw new IllegalStateException("SortOrder required");
		}

		int hashCode = 14;

		hashCode = 29 * hashCode + this.getName().hashCode();
		hashCode = 29 * hashCode + this.getValid().hashCode();
		hashCode = 29 * hashCode + this.getSortOrder().hashCode();

		return hashCode;
	}
}