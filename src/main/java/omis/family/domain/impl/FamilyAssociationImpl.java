package omis.family.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.datatype.DateRange;
import omis.family.domain.FamilyAssociation;
import omis.family.domain.FamilyAssociationCategory;
import omis.family.domain.component.FamilyAssociationFlags;
import omis.relationship.domain.Relationship;

/**
 * Family association implementation.
 * 
 * @author Ryan Johns
 * @author Joel Norris
 * @author Yidong Li.
 * @author Sheronda Vaughn
 * @version 0.1.1 (June 1, 2015)
 * @since OMIS 3.0
 */
public class FamilyAssociationImpl implements FamilyAssociation {
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private Relationship relationship;
	private DateRange dateRange;
	private Date marriageDate;
	private Date divorceDate;
	private FamilyAssociationFlags flags;
	private FamilyAssociationCategory category;
	private CreationSignature creationSignature;
	private UpdateSignature updateSignature;
	
	/** Instantiates an implementation of family association. */
	public FamilyAssociationImpl() {
		// do nothing
	}
	
	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return id;
	}

	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public Relationship getRelationship() {
		return relationship;
	}

	/** {@inheritDoc} */
	@Override
	public void setRelationship(final Relationship relationship) {
		this.relationship = relationship;
	}
	
	/** {@inheritDoc} */
	@Override
	public DateRange getDateRange() {
		return dateRange;
	}

	/** {@inheritDoc} */
	@Override
	public void setDateRange(final DateRange dateRange) {
		this.dateRange = dateRange;
	}

	/** {@inheritDoc} */
	@Override
	public Date getMarriageDate() {
		return marriageDate;
	}

	/** {@inheritDoc} */
	@Override
	public void setMarriageDate(final Date marriageDate) {
		this.marriageDate = marriageDate;
	}
	
	/** {@inheritDoc} */
	@Override
	public Date getDivorceDate() {
		return divorceDate;
	}

	/** {@inheritDoc} */
	@Override
	public void setDivorceDate(final Date divorceDate) {
		this.divorceDate = divorceDate;
	}

	/** {@inheritDoc} */
	@Override
	public FamilyAssociationFlags getFlags() {
		return flags;
	}

	/** {@inheritDoc} */
	@Override
	public void setFlags(final FamilyAssociationFlags flags) {
		this.flags = flags;
	}

	/** {@inheritDoc} */
	@Override
	public FamilyAssociationCategory getCategory() {
		return category;
	}

	/** {@inheritDoc} */
	@Override
	public void setCategory(final FamilyAssociationCategory category) {
		this.category = category;
	}
	
	/** {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(
			final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setUpdateSignature(final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof FamilyAssociation)) {
			return false;
		}
		
		FamilyAssociation that = (FamilyAssociation) obj;
		
		if (this.dateRange.getStartDate() == null) {
			throw new IllegalStateException("Start date required.");
		}
		if (this.dateRange.getEndDate() == null) {
			throw new IllegalStateException("End date required.");
		}
		if (this.getRelationship() == null) {
			throw new IllegalStateException("Relationship required.");
		}
		if (!this.getRelationship().equals(that.getRelationship())) {
			return false;
		}
		if (!(this.getDateRange().getStartDate() != null
			&& that.getDateRange().getStartDate() == null)
			|| (this.getDateRange().getStartDate() == null
			&& that.getDateRange().getStartDate() != null)
			|| (this.getDateRange().getStartDate() != null
			&& that.getDateRange().getStartDate() == null
			&& this.getDateRange().getStartDate().equals(that.getDateRange()
			.getStartDate()))) {
			return false;
		}
		if (!this.getDateRange().getEndDate().equals(
			that.getDateRange().getEndDate())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getRelationship() == null) {
			throw new IllegalStateException("Relationship required.");
		}
		if (this.dateRange.getStartDate() == null) {
			throw new IllegalStateException("Start date required.");
		}
		if (this.dateRange.getEndDate() == null) {
			throw new IllegalStateException("End date required.");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getRelationship().hashCode();
		hashCode = 29 * hashCode + this.getDateRange().getStartDate()
				.hashCode();
		hashCode = 29 * hashCode + this.getDateRange().getEndDate().hashCode();
		return hashCode;
	}
}