package omis.caseload.domain.impl;

import java.io.Serializable;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.caseload.domain.CaseWorkerAssignment;
import omis.caseload.domain.Caseload;
import omis.datatype.DateRange;
import omis.person.domain.Person;

/**
 * Case worker assignment implementation.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jul 26, 2016)
 * @since OMIS 3.0
 */
public class CaseWorkerAssignmentImpl 
	implements CaseWorkerAssignment, Serializable {

	private static final long serialVersionUID = 1L;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	private Long id;
	
	private Person worker;
	
	private DateRange dateRange;
	
	private Caseload caseload;

	/** Instantiates an implementation of CaseWorkerAssignmentImpl */
	public CaseWorkerAssignmentImpl() {
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
	public void setWorker(final Person worker) {
		this.worker = worker;
	}

	/** {@inheritDoc} */
	@Override
	public Person getWorker() {
		return this.worker;
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
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof CaseWorkerAssignment)) {
			return false;
		}
		
		CaseWorkerAssignment that = (CaseWorkerAssignment) obj;
		if (this.getWorker() == null) {
			throw new IllegalStateException("Staff member required.");
		}
		if (!this.getWorker().equals(that.getWorker())) {
			return false;
		}
		if (this.getCaseload() == null) {
			throw new IllegalStateException("Caseload required.");
		}
		if (!this.getCaseload().equals(that.getCaseload())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getWorker() == null) {
			throw new IllegalStateException("Staff member required.");
		}
		if (this.getCaseload() == null) {
			throw new IllegalStateException("Caseload required.");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getWorker().hashCode();
		hashCode = 29 * hashCode + this.getCaseload().hashCode();
		
		return hashCode;
	}
}