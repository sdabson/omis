package omis.caseload.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.caseload.domain.Caseload;
import omis.caseload.domain.OffenderCaseAssignment;
import omis.datatype.DateRange;
import omis.offender.domain.Offender;

/**
 * Offender case assignment implementation.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jul 26, 2016)
 * @since OMIS 3.0
 */
public class OffenderCaseAssignmentImpl implements OffenderCaseAssignment {
 
	private static final long serialVersionUID = 1L;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	private Long id;
	
	private Caseload caseload;
	
	private Offender offender;
	
	private DateRange dateRange;

	/** Instantiates an implementation of offender case assignment. */
	public OffenderCaseAssignmentImpl() {
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
		return this.creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setUpdateSignature(final UpdateSignature updateSignature) {
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
	public void setCaseload(final Caseload caseload) {
		this.caseload = caseload;
	}

	/** {@inheritDoc} */
	@Override
	public Caseload getCaseload() {
		return this.caseload;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setOffender(final Offender offender) {
		this.offender = offender;
	}

	/** {@inheritDoc} */
	@Override
	public Offender getOffender() {
		return this.offender;
	}

	/** {@inheritDoc} */
	@Override
	public void setDateRange(final DateRange dateRange) {
		this.dateRange = dateRange;
	}

	/** {@inheritDoc} */
	@Override
	public DateRange getDateRange() {
		return this.dateRange;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof OffenderCaseAssignment)) {
			return false;
		}
		
		OffenderCaseAssignment that = (OffenderCaseAssignment) obj;
		if (this.getCaseload() == null) {
			throw new IllegalStateException("Caseload required.");
		}
		if (!this.getCaseload().equals(that.getCaseload())) {
			return false;
		}
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required.");
		}
		if (!this.getOffender().equals(that.getOffender())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getCaseload() == null) {
			throw new IllegalStateException("Caseload required.");
		}
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required.");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getCaseload().hashCode();
		hashCode = 29 * hashCode + this.getOffender().hashCode();
		
		return hashCode;
	}	
}