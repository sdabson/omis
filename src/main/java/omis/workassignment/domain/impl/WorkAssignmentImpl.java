package omis.workassignment.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.offender.domain.Offender;
import omis.workassignment.domain.FenceRestriction;
import omis.workassignment.domain.WorkAssignment;
import omis.workassignment.domain.WorkAssignmentCategory;
import omis.workassignment.domain.WorkAssignmentChangeReason;

/**
 * Implementation of work assignment.
 * @author Yidong Li
 * @version 0.1.0 (Aug 17, 2016)
 * @since OMIS 3.0 
 */
public class WorkAssignmentImpl implements WorkAssignment {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Offender offender;
	private FenceRestriction fenceRestriction;
	private WorkAssignmentCategory category;
	private WorkAssignmentChangeReason changeReason;
	private Date assignedDate;
	private Date terminationDate;
	private String comments;
	private UpdateSignature updateSignature;
	private CreationSignature creationSignature;
	
	/** Constructor. */
	public WorkAssignmentImpl() {
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
	public Offender getOffender() {
		return this.offender;
	}

	/** {@inheritDoc} */
	@Override
	public void setOffender(final Offender offender) {
		this.offender = offender;
	}

	/** {@inheritDoc} */
	@Override
	public FenceRestriction getFenceRestriction() {
		return this.fenceRestriction;
	}

	/** {@inheritDoc} */
	@Override
	public void setFenceRestriction(final FenceRestriction fenceRestriction) {
		this.fenceRestriction = fenceRestriction;
	}
	
	/** {@inheritDoc} */
	@Override
	public WorkAssignmentCategory getCategory() {
		return this.category;
	}

	/** {@inheritDoc} */
	@Override
	public void setCategory(final WorkAssignmentCategory category) {
		this.category = category;
	}
	
	/** {@inheritDoc} */
	@Override
	public WorkAssignmentChangeReason getChangeReason() {
		return this.changeReason;
	}

	/** {@inheritDoc} */
	@Override
	public void setChangeReason(final WorkAssignmentChangeReason 
		changeReason) {
		this.changeReason = changeReason;
	}
	
	/** {@inheritDoc} */
	@Override
	public Date getAssignedDate() {
		return this.assignedDate;
	}

	/** {@inheritDoc} */
	@Override
	public void setAssignedDate(final Date assignedDate) {
		this.assignedDate = assignedDate;
	}
	
	/** {@inheritDoc} */
	@Override
	public Date getTerminationDate() {
		return this.terminationDate;
	}

	/** {@inheritDoc} */
	@Override
	public void setTerminationDate(final Date terminationDate) {
		this.terminationDate = terminationDate;
	}
	
	/** {@inheritDoc} */
	@Override
	public String getComments() {
		return this.comments;
	}

	/** {@inheritDoc} */
	@Override
	public void setComments(final String comments) {
		this.comments = comments;
	}
	
	@Override
	public void setCreationSignature(final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
		
	}

	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}

	@Override
	public void setUpdateSignature(final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
		
	}

	@Override
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WorkAssignment)) {
			return false;
		}
		
		WorkAssignment that = (WorkAssignment) obj;
		
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required.");
		}
		if (this.getFenceRestriction() == null) {
			throw new IllegalStateException("Fence restriction required.");
		}
		if (this.getCategory() == null) {
			throw new IllegalStateException("Work assignment category required.");
		}
		if (this.getChangeReason() == null) {
			throw new IllegalStateException("Work assignment change reason required.");
		}
		if (this.getAssignedDate() == null) {
			throw new IllegalStateException("Assigned date required.");
		}
		
		if (this.getOffender().equals(that.getOffender())
			&&this.getCategory().equals(that.getCategory())
			&&this.getAssignedDate().equals(that.getAssignedDate())) {
			return true;
		}
		return false;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required.");
		}
		if (this.getFenceRestriction() == null) {
			throw new IllegalStateException("Fence restriction required.");
		}
		if (this.getCategory() == null) {
			throw new IllegalStateException("Work assignment category required.");
		}
		if (this.getChangeReason() == null) {
			throw new IllegalStateException("Work assignment change reason required.");
		}
		if (this.getAssignedDate() == null) {
			throw new IllegalStateException("Assigned date required.");
		}
		
		int hashCode = 14;
		
		hashCode += 29 * this.getOffender().hashCode();
		hashCode += 29 * this.getFenceRestriction().hashCode();
		hashCode += 29 * this.getCategory().hashCode();
		hashCode += 29 * this.getChangeReason().hashCode();
		hashCode += 29 * this.getAssignedDate().hashCode();
		
		return hashCode;
	}
}