package omis.relationship.domain.impl;

import omis.relationship.domain.RelationshipNoteCategory;

/**
 * Implementation of relationship note category.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Nov 13, 2017)
 * @since OMIS 3.0
 */
public class RelationshipNoteCategoryImpl
		implements RelationshipNoteCategory {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String name;
	
	private Short sortOrder;
	
	/** Instantiates implementation of relationship note category. */
	public RelationshipNoteCategoryImpl() {
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
		if (!(obj instanceof RelationshipNoteCategory)) {
			return false;
		}
		RelationshipNoteCategory that = (RelationshipNoteCategory) obj;
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
	
	/** {@inheritDoc} */
	@Override
	public String toString() {
		return String.format("#%d, %s", this.getId(), this.getName());
	}
}