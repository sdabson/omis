package omis.relationship.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.relationship.domain.Relationship;
import omis.relationship.domain.RelationshipNote;
import omis.relationship.domain.RelationshipNoteCategory;

/**
 * Implementation of relationship note.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Nov 13, 2017)
 * @since OMIS 3.0
 */
public class RelationshipNoteImpl
		implements RelationshipNote {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Relationship relationship;
	
	private RelationshipNoteCategory category;
	
	private String value;
	
	private Date date;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	/** Implementation of relationship note. */
	public RelationshipNoteImpl() {
		// Default instantiation
	}

	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(
			final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setUpdateSignature(
			final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature;
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
	public void setRelationship(final Relationship relationship) {
		this.relationship = relationship;
	}

	/** {@inheritDoc} */
	@Override
	public Relationship getRelationship() {
		return this.relationship;
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
	public void setDate(final Date date) {
		this.date = date;
	}

	/** {@inheritDoc} */
	@Override
	public Date getDate() {
		return this.date;
	}

	/** {@inheritDoc} */
	@Override
	public void setValue(final String value) {
		this.value = value;
	}

	/** {@inheritDoc} */
	@Override
	public String getValue() {
		return this.value;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (!(obj instanceof RelationshipNote)) {
			return false;
		}
		RelationshipNote that = (RelationshipNote) obj;
		if (this.getRelationship() == null) {
			throw new IllegalStateException("Relationship required");
		}
		if (!this.getRelationship().equals(that.getRelationship())) {
			return false;
		}
		if (this.getCategory() == null) {
			throw new IllegalStateException("Category required");
		}
		if (!this.getCategory().equals(that.getCategory())) {
			return false;
		}
		if (this.getDate() == null) {
			throw new IllegalStateException("Date required");
		}
		if (!this.getDate().equals(that.getDate())) {
			return false;
		}
		if (this.getValue() == null) {
			throw new IllegalStateException("Value required");
		}
		if (!this.getValue().equals(that.getValue())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getRelationship() == null) {
			throw new IllegalStateException("Relationship required");
		}
		if (this.getCategory() == null) {
			throw new IllegalStateException("Category required");
		}
		if (this.getDate() == null) {
			throw new IllegalStateException("Date required");
		}
		if (this.getValue() == null) {
			throw new IllegalStateException("Value required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getRelationship().hashCode();
		hashCode = 29 * hashCode + this.getCategory().hashCode();
		hashCode = 29 * hashCode + this.getDate().hashCode();
		hashCode = 29 * hashCode + this.getValue().hashCode();
		return hashCode;
	}
	
	/** {@inheritDoc} */
	@Override
	public String toString() {
		return String.format("#%d, [%s] [%s] %s %s", 
				this.getId(), this.getRelationship(), this.getCategory(),
				this.getDate(), this.getValue());
	}
}