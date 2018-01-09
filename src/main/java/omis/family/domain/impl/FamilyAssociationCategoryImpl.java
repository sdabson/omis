package omis.family.domain.impl;

import omis.family.domain.FamilyAssociationCategory;
import omis.family.domain.FamilyAssociationCategoryClassification;

/**
 * FamilyAssociationCategory Impl.
 * @author Joel Norris
 * @author Yidong Li
 * @version 0.1.1 (June 2, 2015)
 * @since OMIS 3.0
 */
public class FamilyAssociationCategoryImpl 
	implements FamilyAssociationCategory {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String name;
	
	private FamilyAssociationCategoryClassification	classification;
	
	private Boolean valid;
	
	private Short sortOrder;
	
	/** Instantiates an implementation of family association category. */
	public FamilyAssociationCategoryImpl() {
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
	public void setName(final String name) {
		this.name = name;
	}

	/** {@inheritDoc} */
	@Override
	public FamilyAssociationCategoryClassification getClassification() {
		return this.classification;
	}

	/** {@inheritDoc} */
	@Override
	public void setClassification(final FamilyAssociationCategoryClassification 
			classification) {
		this.classification = 
				classification;
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
		if (!(o instanceof FamilyAssociationCategory)) {
			return false;
		}
		
		FamilyAssociationCategory that = (FamilyAssociationCategory) o;
		
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