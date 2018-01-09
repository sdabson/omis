package omis.guardianship.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.datatype.DateRange;
import omis.guardianship.domain.Guardianship;
import omis.relationship.domain.Relationship;

/**
 * Guardianship Impl.
 * @author Joel Norris
 * @version 0.1.0 (Sep 4, 2013)
 * @since OMIS 3.0
 */
public class GuardianshipImpl implements Guardianship {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Relationship relationship;
	
	private DateRange dateRange;
	
	private UpdateSignature updateSignature;
	
	private CreationSignature creationSignature;

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
	public Relationship getRelationship() {
		return this.relationship;
	}

	/** {@inheritDoc} */
	@Override
	public void setRelationship(final Relationship relationship) {
		this.relationship = relationship;
	}

	/** {@inheritDoc} */
	@Override
	public DateRange getDateRange() {
		return this.dateRange;
	}

	/** {@inheritDoc} */
	@Override
	public void setDateRange(final DateRange dateRange) {
		this.dateRange = dateRange;
	}

	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setUpdateSignature(final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(
			final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}	
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Guardianship)) {
			return false;
		}
		
		Guardianship that = (Guardianship) obj;
		
		if (this.getRelationship() == null) {
			throw new IllegalStateException("Relationship required");
		}
		if (!this.getRelationship().equals(that.getRelationship())) {
			return false;
		}
		if (this.getDateRange() == null) {
			throw new IllegalStateException("Date range required");
		}
		if (!this.getDateRange().equals(that.getDateRange())) {
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
		if (this.getDateRange() == null) {
			throw new IllegalStateException("Date range required");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getRelationship().hashCode();
		hashCode = 29 * hashCode + this.getDateRange().hashCode();
		return hashCode;
	}
}