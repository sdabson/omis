package omis.condition.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.condition.domain.Agreement;
import omis.condition.domain.AgreementCategory;
import omis.datatype.DateRange;
import omis.offender.domain.Offender;

/**
 * Agreement Implementation.
 * 
 * @author Jonny Santy
 * @author Trevor Isles
 * @author Annie Wahl
 * @version 0.1.2 (Nov 27, 2017)
 * @since OMIS 3.0
 */
public class AgreementImpl implements Agreement {

	private static final long serialVersionUID = 1L;

	private static final String EMPTY_START_DATE_MSG = "Start Date Required";

	private static final String EMPTY_OFFENDER_MSG = "Offender Required";
	
	private static final String EMPTY_END_DATE_MSG = "End Date Required";
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	private Long id;
	
	private Offender offender;
	
	private DateRange dateRange;
	
	private AgreementCategory category;
	
	private String description;

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

	/**{@inheritDoc} */
	@Override
	public AgreementCategory getCategory() {
		return this.category;
	}

	/**{@inheritDoc} */
	@Override
	public void setCategory(final AgreementCategory category) {
		this.category = category;
	}

	/**{@inheritDoc} */
	@Override
	public String getDescription() {
		return this.description;
	}

	/**{@inheritDoc} */
	@Override
	public void setDescription(final String description) {
		this.description = description;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		boolean result = false;
		if (this == obj) {
			result = true;
		} else if (obj instanceof Agreement) {
			this.checkState();
			Agreement that 
				= (Agreement) obj;
			if (this.getDateRange().getStartDate().equals(that.getDateRange()
					.getStartDate())
				&& this.getDateRange().getEndDate().equals(that.getDateRange()
					.getEndDate())
				&& this.getOffender().equals(that.getOffender())
				&& this.getDescription().equals(that.getDescription())
				&& this.getCategory().equals(that.getCategory())) {
				result = true;
			}
		}
		return result;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		this.checkState();
		int hashCode = 14;

		hashCode = 29 * hashCode + this.getOffender().hashCode();
		hashCode = 29 * hashCode + this.getDateRange().getStartDate()
				.hashCode();
		hashCode = 29 * hashCode + this.getDateRange().getEndDate().hashCode();
		hashCode = 29 * hashCode + this.getDescription().hashCode();
		hashCode = 29 * hashCode + this.getCategory().hashCode();
		
		return hashCode;
	}
		
	/* Checks state. */
	private void checkState() {

		if (this.getOffender() == null) {
			throw new IllegalStateException(EMPTY_OFFENDER_MSG);
		}
		if (this.getDateRange().getStartDate() == null) {
			throw new IllegalStateException(EMPTY_START_DATE_MSG);
		}
		if (this.getDateRange().getEndDate() == null) {
			throw new IllegalStateException(EMPTY_END_DATE_MSG);
		}
	}
}
