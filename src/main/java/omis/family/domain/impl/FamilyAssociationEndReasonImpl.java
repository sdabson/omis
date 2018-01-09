package omis.family.domain.impl;

import omis.family.domain.FamilyAssociationEndReason;

/**
 * Family association end reason implementation.
 * 
 * @author Joel Norris
 * @author Yidong Li
 * @version 0.1.1 (June 1, 2015)
 * @since OMIS 3.0
 */
public class FamilyAssociationEndReasonImpl 
	implements FamilyAssociationEndReason {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String name;
	
	private Boolean valid;
	
	private Short sortOrder;
	
	/**
	 * Instantiates a default instance of family association end reason 
	 * implementation.
	 */
	FamilyAssociationEndReasonImpl() {
		//Default constructor.
	}

	/**{@inheritDoc}*/
	@Override
	public Long getId() {
		return this.id;
	}

	/**{@inheritDoc}*/
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/**{@inheritDoc}*/
	@Override
	public String getName() {
		return this.name;
	}

	/**{@inheritDoc}*/
	@Override
	public void setName(final String name) {
		this.name = name;
	}

	/**{@inheritDoc}*/
	@Override
	public Boolean getValid() {
		return this.valid;
	}

	/**{@inheritDoc}*/
	@Override
	public void setValid(final Boolean valid) {
		this.valid = valid;
	}

	/**{@inheritDoc}*/
	@Override
	public Short getSortOrder() {
		return this.sortOrder;
	}

	/**{@inheritDoc}*/
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
		if (!(o instanceof FamilyAssociationEndReason)) {
			return false;
		}
		
		FamilyAssociationEndReason that = (FamilyAssociationEndReason) o;
		
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