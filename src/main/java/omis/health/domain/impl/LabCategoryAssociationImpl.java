package omis.health.domain.impl;

import omis.health.domain.Lab;
import omis.health.domain.LabCategory;
import omis.health.domain.LabCategoryAssociation;

/**
 * Lab Category Association Implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Apr 1, 2014)
 * @since OMIS 3.0
 */
public class LabCategoryAssociationImpl implements LabCategoryAssociation {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Lab lab;
	
	private LabCategory category;
	
	/**
	 * Instantiates a default instance of lab category association 
	 * implementation.
	 */
	public LabCategoryAssociationImpl() {
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
	public Lab getLab() {
		return this.lab;
	}

	/** {@inheritDoc} */
	@Override
	public void setLab(final Lab lab) {
		this.lab = lab;
	}

	/** {@inheritDoc} */
	@Override
	public LabCategory getCategory() {
		return this.category;
	}

	/** {@inheritDoc} */
	@Override
	public void setCategory(final LabCategory category) {
		this.category = category;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}	
		if (!(o instanceof LabCategoryAssociation)) {
			return false;
		}
		
		LabCategoryAssociation that = (LabCategoryAssociation) o;
		
		if (this.getLab() == null) {
			throw new IllegalStateException("Lab required.");
		}
		if (!this.getLab().equals(that.getLab())) {
			return false;
		}
		if (this.getCategory() == null) {
			throw new IllegalStateException("Lab Category required.");
		}
		if (!this.getCategory().equals(that.getCategory())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getLab() == null) {
			throw new IllegalStateException("Lab required.");
		}
		if (this.getCategory() == null) {
			throw new IllegalStateException("Lab Category required.");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getLab().hashCode();
		hashCode = 29 * hashCode + this.getCategory().hashCode();
		
		return hashCode;
	}
}