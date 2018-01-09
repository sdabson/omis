package omis.relationship.domain.impl;

import omis.relationship.domain.RelationshipNoteCategory;
import omis.relationship.domain.RelationshipNoteCategoryDesignator;

/**
 * Implementation of relationship note category designator.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Nov 13, 2017)
 * @since OMIS 3.0
 */
public class RelationshipNoteCategoryDesignatorImpl
		implements RelationshipNoteCategoryDesignator {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private RelationshipNoteCategory category;
	
	/** Instantiates implementation of relationship note category designator. */
	public RelationshipNoteCategoryDesignatorImpl() {
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
	public void setCategory(final RelationshipNoteCategory category) {
		this.category = category;
	}

	/** {@inheritDoc} */
	@Override
	public RelationshipNoteCategory getCategory() {
		return this.category;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (!(obj instanceof RelationshipNoteCategoryDesignator)) {
			return false;
		}
		RelationshipNoteCategoryDesignator that
			= (RelationshipNoteCategoryDesignator) obj;
		if (this.getCategory() == null) {
			throw new IllegalStateException("Category required");
		}
		if (!this.getCategory().equals(that.getCategory())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getCategory() == null) {
			throw new IllegalStateException("Category required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getCategory().hashCode();
		return hashCode;
	}
	
	/** {@inheritDoc} */
	@Override
	public String toString() {
		return String.format("#%d [%s]", this.getId(), this.getCategory());
	}
}