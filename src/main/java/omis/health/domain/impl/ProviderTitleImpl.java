package omis.health.domain.impl;

import omis.health.domain.ProviderAssignmentCategory;
import omis.health.domain.ProviderTitle;

/**
 * Provider title implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Apr 11, 2014)
 * @since OMIS 3.0
 */
public class ProviderTitleImpl implements ProviderTitle {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private ProviderAssignmentCategory category;
	
	private String name;
	
	private String abbreviation;
	
	private String description;
	
	private Boolean valid;
	
	private Short sortOrder;
	
	/**
	 * Instantiates a default implementation of provider title.
	 */
	public ProviderTitleImpl() {
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
	public ProviderAssignmentCategory getCategory() {
		return this.category;
	}

	/** {@inheritDoc} */
	@Override
	public void setCategory(final ProviderAssignmentCategory category) {
		this.category = category;
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
	public String getAbbreviation() {
		return this.abbreviation;
	}

	/** {@inheritDoc} */
	@Override
	public void setAbbreviation(final String abbreviation) {
		this.abbreviation = abbreviation;
	}

	/** {@inheritDoc} */
	@Override
	public String getDescription() {
		return this.description;
	}

	/** {@inheritDoc} */
	@Override
	public void setDescription(final String description) {
		this.description = description;
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

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}	
		if (!(o instanceof ProviderTitle)) {
			return false;
		}
		
		ProviderTitle that = (ProviderTitle) o;
		
		if (this.getName() == null) {
			throw new IllegalStateException("Name required.");
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
			throw new IllegalStateException("Name required.");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getName().hashCode();
		
		return hashCode;
	}
}