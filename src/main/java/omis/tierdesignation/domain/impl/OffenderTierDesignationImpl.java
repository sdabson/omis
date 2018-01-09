package omis.tierdesignation.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.datatype.DateRange;
import omis.offender.domain.Offender;
import omis.tierdesignation.domain.OffenderTierDesignation;
import omis.tierdesignation.domain.TierChangeReason;
import omis.tierdesignation.domain.TierLevel;
import omis.tierdesignation.domain.TierSource;

/**
 * Implementation of offender tier designation.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Dec 12, 2013)
 * @since OMIS 3.0
 */
public class OffenderTierDesignationImpl
		implements OffenderTierDesignation {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Offender offender;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	private TierChangeReason changeReason;
	
	private TierLevel level;
	
	private TierSource source;
	
	private DateRange dateRange;
	
	private String comment;

	/** Instantiates a default implementation of offender tier designation. */
	public OffenderTierDesignationImpl() {
		// Default instantiation
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
	public void setChangeReason(final TierChangeReason changeReason) {
		this.changeReason = changeReason;
	}
	
	/** {@inheritDoc} */
	@Override
	public TierChangeReason getChangeReason() {
		return this.changeReason;
	}

	/** {@inheritDoc} */
	@Override
	public void setLevel(final TierLevel level) {
		this.level = level;
	}
	
	/** {@inheritDoc} */
	@Override
	public TierLevel getLevel() {
		return this.level;
	}

	/** {@inheritDoc} */
	@Override
	public void setSource(final TierSource source) {
		this.source = source;
	}
	
	/** {@inheritDoc} */
	@Override
	public TierSource getSource() {
		return this.source;
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
	public void setComment(final String comment) {
		this.comment = comment;
	}
	
	/** {@inheritDoc} */
	@Override
	public String getComment() {
		return this.comment;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof OffenderTierDesignation)) {
			return false;
		}
		OffenderTierDesignation that = (OffenderTierDesignation) obj;
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required");
		}
		if (!this.getOffender().equals(that.getOffender())) {
			return false;
		}
		if (this.getChangeReason() == null) {
			throw new IllegalStateException("Change reason required");
		}
		if (!this.getChangeReason().equals(that.getChangeReason())) {
			return false;
		}
		if (this.getLevel() == null) {
			throw new IllegalStateException("Level required");
		}
		if (!this.getLevel().equals(that.getLevel())) {
			return false;
		}
		if (this.getSource() == null) {
			throw new IllegalStateException("Source required");
		}
		if (!this.getSource().equals(that.getSource())) {
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
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required");
		}
		if (this.getChangeReason() == null) {
			throw new IllegalStateException("Change reason required");
		}
		if (this.getLevel() == null) {
			throw new IllegalStateException("Level required");
		}
		if (this.getSource() == null) {
			throw new IllegalStateException("Source required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getOffender().hashCode();
		hashCode = 29 * hashCode + this.getChangeReason().hashCode();
		hashCode = 29 * hashCode + this.getLevel().hashCode();
		hashCode = 29 * hashCode + this.getSource().hashCode();
		if (this.getDateRange() != null) {
			hashCode = 29 * hashCode + this.getDateRange().hashCode();
		}
		return hashCode;
	}
}