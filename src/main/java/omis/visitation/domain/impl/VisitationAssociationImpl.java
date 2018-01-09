package omis.visitation.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.datatype.DateRange;
import omis.relationship.domain.Relationship;
import omis.visitation.domain.VisitationApproval;
import omis.visitation.domain.VisitationAssociation;
import omis.visitation.domain.VisitationAssociationCategory;
import omis.visitation.domain.VisitationAssociationFlags;

/**
 * Visitation Association Impl.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jul 17, 2013)
 * @since OMIS 3.0
 */
public class VisitationAssociationImpl implements VisitationAssociation {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Relationship relationship;
	
	private DateRange dateRange;
	
	private VisitationAssociationFlags flags;
	
	private VisitationApproval approval;
	
	private VisitationAssociationCategory category;
	
	private String notes;
	
	private String guardianship;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	/**Instantiates a default instance of visitor implementation.*/
	public VisitationAssociationImpl() {
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
	public VisitationAssociationFlags getFlags() {
		return this.flags;
	}

	/** {@inheritDoc} */
	@Override
	public void setFlags(final VisitationAssociationFlags flags) {
		this.flags = flags;
	}

	/** {@inheritDoc} */
	@Override
	public VisitationApproval getApproval() {
		return this.approval;
	}

	/** {@inheritDoc} */
	@Override
	public void setApproval(final VisitationApproval approval) {
		this.approval = approval;
	}

	/** {@inheritDoc} */
	@Override
	public VisitationAssociationCategory getCategory() {
		return this.category;
	}

	/** {@inheritDoc} */
	@Override
	public void setCategory(final VisitationAssociationCategory category) {
		this.category = category;
	}

	/** {@inheritDoc} */
	@Override
	public String getNotes() {
		return notes;
	}

	/** {@inheritDoc} */
	@Override
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	/** {@inheritDoc} */
	@Override
	public String getGuardianship() {
		return this.guardianship;
	}

	/** {@inheritDoc} */
	@Override
	public void setGuardianship(final String guardianship) {
		this.guardianship = guardianship;
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
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof VisitationAssociation)) {
			return false;
		}
		
		VisitationAssociation that = (VisitationAssociation) obj;
		
		if (this.getRelationship() == null) {
			throw new IllegalStateException("Relationship required");
		}
		if (!this.getRelationship().equals(that.getRelationship())) {
			return false;
		}
		if (this.getDateRange() == null) {
			throw new IllegalStateException("Date Range required");
		}
		if (!this.getDateRange().equals(that.getDateRange())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		int hashCode = 14;

		if (this.getRelationship() == null) {
			throw new IllegalStateException("Relationship required");
		}
		if (this.getDateRange() == null) {
			throw new IllegalStateException("Date Range required");
		}
		
		hashCode = 29 * hashCode + this.getRelationship().hashCode();
		hashCode = 29 * hashCode + this.getDateRange().hashCode();
	
		return hashCode;
	}
}