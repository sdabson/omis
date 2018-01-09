package omis.commitstatus.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.commitstatus.domain.CommitStatus;
import omis.commitstatus.domain.CommitStatusTerm;
import omis.datatype.DateRange;
import omis.offender.domain.Offender;

/** Implementation of Commit Status Term.
 * @author Yidong Li
 * @version 0.1.0 (May 31, 2017)
 * @since OMIS 3.0 */
public class CommitStatusTermImpl implements CommitStatusTerm {
	private Long id;
	private static final long serialVersionUID = 1L;
	private Offender offender;
	private CommitStatus status;
	private DateRange dateRange;
	private CreationSignature creationSignature;
	private UpdateSignature updateSignature;

	/** Constructor. */
	public CommitStatusTermImpl() {
		
	}
	
	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}
	
	
	/** {@inheritDoc} */
	@Override
	public void setId(final Long id){
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
	public CommitStatus getStatus() {
		return this.status;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setStatus(final CommitStatus status) {
		this.status = status;
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
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof CommitStatusTerm)) {
			return false;
		}
		CommitStatusTerm that = (CommitStatusTerm) obj;
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required");
		}
		if (this.getStatus() == null) {
			throw new IllegalStateException("Status required");
		}
		if (this.dateRange.getStartDate() == null) {
			throw new IllegalStateException("Start date required");
		}
		if(this.getOffender().equals(that.getOffender())
			&&this.getStatus().equals(that.getStatus())
			&&this.dateRange.getStartDate().equals(that.getDateRange()
				.getStartDate())){
			return true;
		} else {
			return false;
		}
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		int hashCode = 7;
		if (this.offender == null) {
			throw new IllegalStateException("Offender required");
		}
		if (this.status==null) {
			throw new IllegalStateException("Status required");
		}		
		if (this.dateRange.getStartDate()==null) {
			throw new IllegalStateException("Start date required");
		}
		hashCode += 29 * this.offender.hashCode();
		hashCode += 29 * this.status.hashCode();
		hashCode += 29 * this.dateRange.getStartDate().hashCode();
		return hashCode;
	}
}
