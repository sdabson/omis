package omis.financial.domain.impl;

import java.math.BigDecimal;
import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.financial.domain.FinancialLiability;
import omis.financial.domain.FinancialLiabilityCategory;
import omis.offender.domain.Offender;

/**
 * Financial Liability implementation.
 *
 * @author Josh Divine
 * @version 0.0.1 (November 21, 2016)
 * @since OMIS 3.0
 */
public class FinancialLiabilityImpl implements FinancialLiability {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Offender offender;
	private FinancialLiabilityCategory category;
	private String description;
	private BigDecimal amount;
	private Date reportedDate;
	private CreationSignature creationSignature;
	private UpdateSignature updateSignature;
	
	/**
	 * Constructor
	 */
	public FinancialLiabilityImpl() {
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
	public FinancialLiabilityCategory getCategory() {
		return category;
	}

	@Override
	public void setCategory(FinancialLiabilityCategory category) {
		this.category = category;
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

		if (!(obj instanceof FinancialLiability)) {
			return false;
		}
		
		FinancialLiability that = (FinancialLiability) obj;
		
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required");
		}
		if (!this.getOffender().equals(that.getOffender())) {
			return false;
		}
		if (!this.getCategory().equals(that.getCategory())) {
			return false;
		}
		if (!this.getDescription().equals(that.getDescription())) {
			return false;
		}
		if (!this.getAmount().equals(that.getAmount())) {
			return false;
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
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getOffender().hashCode();
		hashCode = 29 * hashCode + this.getCategory().hashCode();
		hashCode = 29 * hashCode + this.getDescription().hashCode();
		return hashCode;
	}

}
