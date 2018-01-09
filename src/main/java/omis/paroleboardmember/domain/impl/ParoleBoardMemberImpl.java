package omis.paroleboardmember.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.datatype.DateRange;
import omis.paroleboardmember.domain.ParoleBoardMember;
import omis.staff.domain.StaffAssignment;

/**
 * Implementation of parole board member.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Nov 8, 2017)
 * @since OMIS 3.0
 */
public class ParoleBoardMemberImpl implements ParoleBoardMember {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	private StaffAssignment staffAssignment;
	
	private DateRange dateRange;
	
	/** 
	 * Instantiates an implementation of parole board member. 
	 */
	public ParoleBoardMemberImpl() {
		// Default constructor.
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
		return creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setUpdateSignature(UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return id;
	}

	/** {@inheritDoc} */
	@Override
	public void setStaffAssignment(StaffAssignment staffAssignment) {
		this.staffAssignment = staffAssignment;
	}

	/** {@inheritDoc} */
	@Override
	public StaffAssignment getStaffAssignment() {
		return staffAssignment;
	}

	/** {@inheritDoc} */
	@Override
	public void setDateRange(DateRange dateRange) {
		this.dateRange = dateRange;
	}

	/** {@inheritDoc} */
	@Override
	public DateRange getDateRange() {
		return dateRange;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ParoleBoardMember)) {
			return false;
		}
		ParoleBoardMember that = (ParoleBoardMember) obj;
		if (this.getStaffAssignment() == null) {
			throw new IllegalStateException("Staff assignment required");
		}
		if (!this.getStaffAssignment().equals(that.getStaffAssignment())) {
			return false;
		}
		if (DateRange.getStartDate(this.dateRange) == null) {
			throw new IllegalStateException("Start date required");
		}
		if (!DateRange.getStartDate(this.dateRange).equals(
				DateRange.getStartDate(that.getDateRange()))) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getStaffAssignment() == null) {
			throw new IllegalStateException("Staff assignment required");
		}
		if (DateRange.getStartDate(this.dateRange) == null) {
			throw new IllegalStateException("Start date required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getStaffAssignment().hashCode();
		hashCode = 29 * hashCode + DateRange.getStartDate(this.dateRange)
				.hashCode();
		
		return hashCode;
	}
}
