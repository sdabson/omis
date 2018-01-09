package omis.employment.domain.impl;

import omis.datatype.DateRange;
import omis.offender.domain.Offender;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.audit.domain.VerificationSignature;
import omis.employment.domain.EmploymentChangeReason;
import omis.employment.domain.EmploymentTerm;
import omis.employment.domain.component.Job;

/** 
 * Implementation of employment term.
 * 
 * @author Yidong Li
 * @author Josh Divine
 * @version 0.1.1 (Dec 13, 2017)
 * @since OMIS 3.0 
 */
public class EmploymentTermImpl implements EmploymentTerm {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Offender offender;
	private DateRange dateRange;
	private VerificationSignature verificationSignature;
	private Boolean convictedOfEmployerTheft;
	private EmploymentChangeReason endReason;
	private Job job;
	private CreationSignature creationSignature;
	private UpdateSignature updateSignature;

	/** Constructor. */
	public EmploymentTermImpl() {
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
	public VerificationSignature getVerificationSignature() {
		return this.verificationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setVerificationSignature(final VerificationSignature 
		verificationSignature) {
		this.verificationSignature = verificationSignature;
	}
	
	/** {@inheritDoc} */
	@Override
	public Boolean getConvictedOfEmployerTheft() {
		return this.convictedOfEmployerTheft;
	}

	/** {@inheritDoc} */
	@Override
	public void setConvictedOfEmployerTheft(
		final Boolean convictedOfEmployerTheft){
			this.convictedOfEmployerTheft=convictedOfEmployerTheft;
	}	
	
	/** {@inheritDoc} */
	@Override
	public Job getJob() {
		return this.job;
	}

	/** {@inheritDoc} */
	@Override
	public void setJob(final Job job) {
		this.job = job;
	}

	/** {@inheritDoc} */
	@Override
	public EmploymentChangeReason getEndReason() {
		return this.endReason;
	}

	/** {@inheritDoc} */
	@Override
	public void setEndReason(final EmploymentChangeReason 
		endReason) {
		this.endReason = endReason;
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
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof EmploymentTerm)) {
			return false;
		}
		EmploymentTerm that = (EmploymentTerm) obj;
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required");
		}
		if(!this.getOffender().equals(that.getOffender())){
			return false;
		}
		if (this.getJob().getJobTitle()==null) {
			throw new IllegalStateException("Job title required");
		}
		if(!this.getJob().equals(that.getJob())){
			return false;
		}
		if (this.getJob().getEmployer().getLocation() == null) {
			throw new IllegalStateException("Employer location required");
		}	
		if(!this.getJob().getEmployer().getLocation().equals(that.getJob()
			.getEmployer().getLocation())){
			return false;
		}
		if (this.getDateRange() != null) {
			if (!this.getDateRange().equals(that.getDateRange())) {
				return false;
			}
		} else if (that.getDateRange() != null) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		int hashCode = 7;
		
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required");
		}
		if (this.getJob().getJobTitle()==null) {
			throw new IllegalStateException("Job title required");
		}		
		if (this.getJob().getEmployer().getLocation() == null) {
			throw new IllegalStateException("Employer location required");
		}	
		
		hashCode += 29 * this.getOffender().hashCode();
		hashCode += 31 * this.getJob().getJobTitle().hashCode();
		hashCode += 33 * this.getJob().getEmployer().getLocation().hashCode();
		
		return hashCode;
	}
}
