package omis.financial.domain.impl;

import omis.financial.domain.FinancialLiabilityCategory;

/**
 * Financial Liability Category Implementation.
 *
 * @author Josh Divine
 * @version 0.0.1 (November 21, 2016)
 * @since OMIS 3.0
 */
public class FinancialLiabilityCategoryImpl implements 
	FinancialLiabilityCategory {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private Boolean valid;
	private Short sortOrder;
	
	/**
	 * Constructor
	 */
	public FinancialLiabilityCategoryImpl() {
	}
	
	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return id;
	}

	/** {@inheritDoc} */
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public String getName() {
		return name;
	}

	/** {@inheritDoc} */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getValid() {
		return valid;
	}

	/** {@inheritDoc} */
	@Override
	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	/** {@inheritDoc} */
	@Override
	public Short getSortOrder() {
		return sortOrder;
	}

	/** {@inheritDoc} */
	@Override
	public void setSortOrder(Short sortOrder) {
		this.sortOrder = sortOrder;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof FinancialLiabilityCategory)) {
			return false;
		}
		
		FinancialLiabilityCategory that = (FinancialLiabilityCategory) obj;
		
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
