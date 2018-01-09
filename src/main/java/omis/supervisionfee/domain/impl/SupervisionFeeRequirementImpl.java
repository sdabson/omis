package omis.supervisionfee.domain.impl;

import java.math.BigDecimal;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.court.domain.Court;
import omis.datatype.DateRange;
import omis.person.domain.Person;
import omis.supervisionfee.domain.MonthlySupervisionFee;
import omis.supervisionfee.domain.SupervisionFeeRequirement;
import omis.supervisionfee.domain.SupervisionFeeRequirementReason;

/**
 * Supervision fee requirement implementation.
 * 
 * @author Sheronda Vaughn
 * @version 0.1.0 (Aug 15, 2014)
 * @since  OMIS 3.0
 */
public class SupervisionFeeRequirementImpl 
			implements SupervisionFeeRequirement {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private BigDecimal fee;
	
	private MonthlySupervisionFee monthlySupervisionFee;
	
	private DateRange dateRange;
	
	private String comment;
	
	private SupervisionFeeRequirementReason reason;	
	
	private CreationSignature creationSignature;

	private UpdateSignature updateSignature;
	
	private Person officer;
	
	private Court court;

	/**
	 * Instantiates a default instance of supervision fee requirement.
	 */
	public SupervisionFeeRequirementImpl() {
	}
	
	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}	
	/** {@inheritDoc} */
	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
	/** {@inheritDoc} */
	@Override
	public BigDecimal getFee() {
		return this.fee;
	}
	/** {@inheritDoc} */
	@Override
	public void setFee(final BigDecimal fee) {
		this.fee = fee;
	}
	
	/** {@inheritDoc} */
	@Override
	public MonthlySupervisionFee getMonthlySupervisionFee() {
		return this.monthlySupervisionFee;
	}	
	/** {@inheritDoc} */
	@Override
	public void setMonthlySupervisionFee(
			MonthlySupervisionFee monthlySupervisionFee) {
		this.monthlySupervisionFee = monthlySupervisionFee;
	}
	
	/** {@inheritDoc} */
	@Override
	public DateRange getDateRange() {
		return this.dateRange;
	}	
	/** {@inheritDoc} */
	@Override
	public void setDateRange(DateRange dateRange) {
		this.dateRange = dateRange;
	}
	
	/** {@inheritDoc} */
	@Override
	public String getComment() {
		return this.comment;
	}	
	/** {@inheritDoc} */
	@Override
	public void setComment(String comment) {
		this.comment = comment;
	}	
	
	/** {@inheritDoc} */
	@Override
	public SupervisionFeeRequirementReason getReason() {
		return this.reason;
	}	
	/**{@inheritDoc} */
	@Override
	public void setReason(SupervisionFeeRequirementReason reason) {
		this.reason = reason;
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
	public Person getOfficer() {
		return this.officer;
	}
	/** {@inheritDoc} */
	@Override
	public void setOfficer(final Person officer) {
		this.officer = officer;
	}
	
	/** {@inheritDoc} */
	@Override
	public Court getCourt() {
		return this.court;
	}
	/** {@inheritDoc} */
	@Override
	public void setCourt(final Court court) {
		this.court =  court;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof SupervisionFeeRequirement)) {
			return false;
		}
		
		SupervisionFeeRequirement that = (SupervisionFeeRequirement) obj;
		if (this.getDateRange() == null) {
			throw new IllegalStateException("Date range required.");
		}
		if (this.getDateRange().getStartDate() == null) {
				throw new IllegalStateException("Start date required.");
		}		
		if (this.getDateRange().getEndDate() == null) {
			throw new IllegalStateException("End date requried.");
		}
		if (!this.getDateRange().equals(that.getDateRange())) {
			return false;
		}
		if (this.getMonthlySupervisionFee() == null) {
			throw new IllegalStateException(
					"Monthly supervision fee required.");
		}
		if (!this.getMonthlySupervisionFee().equals(
					that.getMonthlySupervisionFee())) {
			return false;
		}
		if (this.getReason() == null) {
			throw new IllegalStateException(
					"Reason required.");
		}
		if (!this.getReason().equals(
					that.getReason())) {
			return false;
		}
		if (this.getOfficer() == null) {
			throw new IllegalStateException("Officer required.");
		}	
		if (!this.getOfficer().equals(that.getOfficer())) {
			return false;
		}
		if (this.getCourt() == null) {
			throw new IllegalStateException("Court required.");
		}
		if (!this.getCourt().equals(that.getCourt())) {
			return false;
		}
		return true;
		}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getDateRange() == null) {
			throw new IllegalStateException("Date range required.");
		}
		if (this.getMonthlySupervisionFee() == null) {
			throw new IllegalStateException(
					"Monthly supervision fee requried.");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getDateRange().hashCode();
		hashCode = 29 * hashCode + this.getMonthlySupervisionFee().hashCode();
		
		return hashCode;
	}
}