package omis.offenderflag.domain.impl;

import omis.offenderflag.domain.FlagUsage;
import omis.offenderflag.domain.OffenderFlagCategory;

/**
 * Implementation of category for offender flag.
 * 
 * @author Stephen Abson
 * @author Annie Jacques
 * @author Ryan Johns
 * @version 0.1.2 (Aug 9, 2017)
 * @since OMIS 3.0
 */
public class OffenderFlagCategoryImpl
		implements OffenderFlagCategory {

	private static final long serialVersionUID = 1L;
	
	private Long id;

	private String name;

	private String description;

	private Boolean valid;

	private Boolean required;
	
	private Short sortOrder;
	
	private FlagUsage usage;
	
	/** Instantiates an implementation of category for offender flag. */
	public OffenderFlagCategoryImpl() {
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
	public void setRequired(final Boolean required) {
		this.required = required;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getRequired() {
		return this.required;
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
	
	/**{@inheritDoc} */
	@Override
	public void setUsage(FlagUsage usage) {
		this.usage = usage;
	}

	/**{@inheritDoc} */
	@Override
	public FlagUsage getUsage() {
		return this.usage;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof OffenderFlagCategory)) {
			return false;
		}
		OffenderFlagCategory that = (OffenderFlagCategory) obj;
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
}