package omis.financial.domain.impl;

import omis.financial.domain.RecurringDeductionCategory;

/**
 * Recurring deduction category implementation.
 *
 * @author Yidong Li
 * @version 0.0.1 (May 9, 2017)
 * @since OMIS 3.0
 */
public class RecurringDeductionCategoryImpl implements RecurringDeductionCategory{
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private Boolean valid;
	private Short sortOrder;
	
	public RecurringDeductionCategoryImpl(){
		
	};
	
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public Boolean getValid() {
		return valid;
	}

	@Override
	public void setValid(Boolean valid) {
		this.valid = valid;
	}
	
	@Override
	public Short getSortOrder() {
		return sortOrder;
	}

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

		if (!(obj instanceof RecurringDeductionCategory)) {
			return false;
		}
		
		RecurringDeductionCategory that = (RecurringDeductionCategory) obj;
		
		if (this.getValid() == null) {
			throw new IllegalStateException("Valid required");
		}
		if (this.getSortOrder() == null) {
			throw new IllegalStateException("SortOrder required");
		}
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
		if (this.getValid() == null) {
			throw new IllegalStateException("Valid required");
		}
		if (this.getSortOrder() == null) {
			throw new IllegalStateException("Sort order required");
		}

		int hashCode = 14;
		hashCode = 29 * hashCode + this.getName().hashCode();
		return hashCode;
	}
}