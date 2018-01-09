package omis.financial.domain.impl;

import java.math.BigDecimal;
import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.financial.domain.FinancialAsset;
import omis.financial.domain.FinancialAssetCategory;
import omis.offender.domain.Offender;

/**
 * Financial Asset Implementation.
 *
 * @author Josh Divine
 * @version 0.0.1 (November 21, 2016)
 * @since OMIS 3.0
 */
public class FinancialAssetImpl implements FinancialAsset {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Offender offender;
	private FinancialAssetCategory category;
	private String description;
	private BigDecimal amount;
	private Date reportedDate;
	private CreationSignature creationSignature;
	private UpdateSignature updateSignature;
	
	/**
	 * Constructor 
	 */
	public FinancialAssetImpl() {
		
	}
	
	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return id;
	}

	/** {@inheritDoc} */
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public Offender getOffender() {
		return offender;
	}

	/** {@inheritDoc} */
	@Override
	public void setOffender(Offender offender) {
		this.offender = offender;
	}

	/** {@inheritDoc} */
	@Override
	public FinancialAssetCategory getCategory() {
		return category;
	}

	/** {@inheritDoc} */
	@Override
	public void setCategory(FinancialAssetCategory category) {
		this.category = category;
	}

	/** {@inheritDoc} */
	@Override
	public String getDescription() {
		return description;
	}

	/** {@inheritDoc} */
	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	/** {@inheritDoc} */
	@Override
	public BigDecimal getAmount() {
		return amount;
	}

	/** {@inheritDoc} */
	@Override
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/** {@inheritDoc} */
	@Override
	public Date getReportedDate() {
		return reportedDate;
	}

	/** {@inheritDoc} */
	@Override
	public void setReportedDate(Date reportedDate) {
		this.reportedDate = reportedDate;
	}
	/** {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return updateSignature;
	}

	/** {@inheritDoc} */
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

		if (!(obj instanceof FinancialAsset)) {
			return false;
		}
		
		FinancialAsset that = (FinancialAsset) obj;
		
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
