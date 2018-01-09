package omis.criminalassociation.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.criminalassociation.domain.CriminalAssociation;
import omis.criminalassociation.domain.CriminalAssociationCategory;
import omis.criminalassociation.domain.component.CriminalAssociationFlags;
import omis.datatype.DateRange;
import omis.relationship.domain.Relationship;

/**
 * Criminal association implementation.
 * 
 * @author Joel Norris 
 * @author Yidong Li
 * @version 0.1.1 (Apr, 14 2015)
 * @since OMIS 3.0
 */
public class CriminalAssociationImpl implements CriminalAssociation {

	private static final long serialVersionUID = 1L;
	
	private Long id;

	private Relationship relationship;
	
	private DateRange dateRange;
	
	private CriminalAssociationCategory criminalAssociationCategory;
	
	private CriminalAssociationFlags criminalAssociationFlags;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;

	/** Instantiates a default instance of 
	 * criminal association implementation.*/
	public CriminalAssociationImpl() {
		//empty constructor
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
	public Relationship getRelationship() {
		return this.relationship;
	}

	/**{@inheritDoc}*/
	@Override
	public void setRelationship(final Relationship relationship) {
		this.relationship = relationship;
	}

	/**{@inheritDoc}*/
	@Override
	public DateRange getDateRange() {
		return this.dateRange;
	}

	/**{@inheritDoc}*/
	@Override
	public void setDateRange(final DateRange dateRange) {
		this.dateRange = dateRange;
	}

	/**{@inheritDoc}*/
	@Override
	public CriminalAssociationCategory getCriminalAssociationCategory() {
		return this.criminalAssociationCategory;
	}

	/**{@inheritDoc}*/
	@Override
	public void setCriminalAssociationCategory(
			final CriminalAssociationCategory criminalAssociationCategory) {
		this.criminalAssociationCategory = criminalAssociationCategory;
	}
	
	/**{@inheritDoc}*/
	@Override
	public CriminalAssociationFlags getCriminalAssociationFlags() {
		return this.criminalAssociationFlags;
	}

	/**{@inheritDoc}*/
	@Override
	public void setCriminalAssociationFlags(
			final CriminalAssociationFlags criminalAssociationFlags) {
		this.criminalAssociationFlags = criminalAssociationFlags;
	}

	/**{@inheritDoc}*/
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}

	/**{@inheritDoc}*/
	@Override
	public void setCreationSignature(
			final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/**{@inheritDoc}*/
	@Override
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature;
	}

	/**{@inheritDoc}*/
	@Override
	public void setUpdateSignature(final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}
	
	/**{@inheritDoc}*/
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CriminalAssociation)) {
			return false;
		}
		
		CriminalAssociation that = (CriminalAssociation) obj;
		
		if (this.getCriminalAssociationCategory() == null) {
			throw new IllegalStateException(
					"Criminal association category required");
		}
		if (this.getRelationship() == null) {
			throw new IllegalStateException("Relationship required");
		}
		if (!this.getRelationship().equals(that.getRelationship())) {
			return false;
		}
		if (this.getDateRange() != null) {
			if (that.getDateRange() != null) {
				if (this.getDateRange().getStartDate() != null) {
					if (!this.getDateRange().getStartDate()
							.equals(that.getDateRange().getStartDate())) {
						return false;
					}
				} else if (that.getDateRange().getStartDate() != null) {
					return false;
				}
				if (this.getDateRange().getEndDate() != null) {
					if (!this.getDateRange().getEndDate()
							.equals(that.getDateRange().getEndDate())) {
						return false;
					}
				} else if (that.getDateRange().getEndDate() != null) {
					return false;
				}
			} else {
				return false;
			}
		} else if (that.getDateRange() != null) {
			return false;
		}
		return true;
	}

	/**{@inheritDoc}*/
	@Override
	public int hashCode() {
		if (this.getRelationship() == null) {
			throw new IllegalStateException("Relationship required");
		}
		if (this.getCriminalAssociationCategory() == null) {
			throw new IllegalStateException(
					"Criminal association category required");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getRelationship().hashCode();
		if (this.getDateRange().getStartDate() != null) {
			hashCode = 29 * hashCode + this.getDateRange().getStartDate()
				.hashCode();
		}
		if (this.getDateRange().getEndDate() != null) {
			hashCode = 31 * hashCode + this.getDateRange().getEndDate()
				.hashCode();
		}
		return hashCode;
	}
}