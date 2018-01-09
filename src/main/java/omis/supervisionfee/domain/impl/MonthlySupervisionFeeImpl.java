package omis.supervisionfee.domain.impl;

import java.math.BigDecimal;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.datatype.DateRange;
import omis.offender.domain.Offender;
import omis.supervisionfee.domain.MonthlySupervisionFee;
import omis.supervisionfee.domain.MonthlySupervisionFeeAuthorityCategory;

/**
 * Monthly supervision fee implementation.
 * 
 * @author Sheronda Vaughn
 * @version 0.1.0 (Aug 15, 2014)
 * @since  OMIS 3.0
 */
public class MonthlySupervisionFeeImpl implements MonthlySupervisionFee {

	private static final long serialVersionUID = 1L;

	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	private Long id;
	
	private BigDecimal fee;
	
	private Offender offender;
	
	private DateRange dateRange;

	private MonthlySupervisionFeeAuthorityCategory authorityCategory;

	private String comment;
	
	/**
	 * Instantiates a default instance of monthly supervision fee.
	 */
	public MonthlySupervisionFeeImpl() {
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
	public void setUpdateSignature(
					final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
		
	}
	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature;
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
	public MonthlySupervisionFeeAuthorityCategory getAuthorityCategory() {
		return this.authorityCategory;
	}
	/** {@inheritDoc} */
	@Override
	public void setAuthorityCategory(
					final MonthlySupervisionFeeAuthorityCategory 
					authorityCategory) {
		this.authorityCategory = authorityCategory;
	}	
	
	/** {@inheritDoc} */
	@Override
	public String getComment() {
		return this.comment;
	}
	/** {@inheritDoc} */
	@Override
	public void setComment(final String comment) {
		this.comment = comment;
		
	}	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof MonthlySupervisionFee)) {
			return false;
		}
		
		MonthlySupervisionFee that = (MonthlySupervisionFee) obj;
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required.");
		}
		if (!this.getOffender().equals(that.getOffender())) {
			return false;
		}
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
		return true;
	}
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender requried.");
		}
		if (this.getDateRange() == null) {
			throw new IllegalStateException("Date range required.");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getOffender().hashCode();
		hashCode = 29 * hashCode + this.getDateRange().hashCode();
				
		return hashCode;
	}	
}