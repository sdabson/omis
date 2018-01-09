package omis.financial.domain.impl;

import java.math.BigDecimal;
import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.financial.domain.RecurringDeduction;
import omis.financial.domain.RecurringDeductionCategory;
import omis.financial.domain.RecurringDeductionFrequency;
import omis.offender.domain.Offender;

/**
 * Recurring deduction implementation.
 *
 * @author Yidong Li
 * @version 0.0.1 (May 9, 2017)
 * @since OMIS 3.0
 */
public class RecurringDeductionImpl implements RecurringDeduction {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Offender offender;
	private RecurringDeductionCategory category;
	private RecurringDeductionFrequency frequency;
	private String description;
	private BigDecimal amount;
	private Date reportedDate;
	private CreationSignature creationSignature;
	private UpdateSignature updateSignature;
	
	/**
	 * Constructor
	 */
	public RecurringDeductionImpl() {
	}
	
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Offender getOffender() {
		return offender;
	}

	@Override
	public void setOffender(Offender offender) {
		this.offender = offender;
	}

	@Override
	public RecurringDeductionCategory getCategory() {
		return category;
	}

	@Override
	public void setCategory(RecurringDeductionCategory category) {
		this.category = category;
	}
	
	@Override
	public RecurringDeductionFrequency getFrequency() {
		return frequency;
	}

	@Override
	public void setFrequency(RecurringDeductionFrequency frequency) {
		this.frequency = frequency;
	}
	
	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public BigDecimal getAmount() {
		return amount;
	}

	@Override
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Override
	public Date getReportedDate() {
		return reportedDate;
	}

	@Override
	public void setReportedDate(Date reportedDate) {
		this.reportedDate = reportedDate;
	}


	@Override
	public CreationSignature getCreationSignature() {
		return creationSignature;
	}

	@Override
	public void setCreationSignature(CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	@Override
	public UpdateSignature getUpdateSignature() {
		return updateSignature;
	}

	@Override
	public void setUpdateSignature(UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof RecurringDeduction)) {
			return false;
		}
		
		RecurringDeduction that = (RecurringDeduction) obj;
		
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required");
		}
		if (!this.getOffender().equals(that.getOffender())) {
			return false;
		}
		if (this.getCategory() == null) {
			throw new IllegalStateException("Recurring deduction required");
		}
		if (!this.getCategory().equals(that.getCategory())) {
			return false;
		}
		if (this.getFrequency() == null) {
			throw new IllegalStateException("Recurring deduction frequency "
					+ "required");
		}
		if (!this.getFrequency().equals(that.getFrequency())) {
			return false;
		}
		if (this.getDescription() == null) {
			throw new IllegalStateException("Description required");
		}
		if (!this.getDescription().equals(that.getDescription())) {
			return false;
		}
		if (this.getAmount() == null) {
			throw new IllegalStateException("Amount required");
		}
		if (!this.getAmount().equals(that.getAmount())) {
			return false;
		}
		if (this.getReportedDate() == null) {
			throw new IllegalStateException("Report date required");
		}
		if (!this.getReportedDate().equals(that.getReportedDate())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required");
		}
		if (this.getCategory() == null) {
			throw new IllegalStateException("Recurring deduction required");
		}
		if (this.getFrequency() == null) {
			throw new IllegalStateException("Recurring deduction frequency "
					+ "required");
		}
		if (this.getDescription() == null) {
			throw new IllegalStateException("Description required");
		}
		if (this.getAmount() == null) {
			throw new IllegalStateException("Amount required");
		}
		if (this.getReportedDate() == null) {
			throw new IllegalStateException("Report date required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getOffender().hashCode();
		hashCode = 29 * hashCode + this.getCategory().hashCode();
		hashCode = 29 * hashCode + this.getFrequency().hashCode();
		hashCode = 29 * hashCode + this.getDescription().hashCode();
		hashCode = 29 * hashCode + this.getAmount().hashCode();
		hashCode = 29 * hashCode + this.getReportedDate().hashCode();
		return hashCode;
	}
}