package omis.immigration.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.datatype.DateRange;
import omis.immigration.domain.AlienResidence;
import omis.immigration.domain.ImmigrationStatus;
import omis.offender.domain.Offender;

/**
 * Implementation of alien residence.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 28, 2014)
 * @since OMIS 3.0
 */
public class AlienResidenceImpl
		implements AlienResidence {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;

	private CreationSignature creationSignature;

	private UpdateSignature updateSignature;

	private Offender offender;

	private DateRange dateRange;

	private ImmigrationStatus immigrationStatus;

	private Boolean legal;

	private String insNumber;
	
	/** Implementation of alien residence. */
	public AlienResidenceImpl() {
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
	public void setOffender(
			final Offender offender) {
		this.offender = offender;
	}
	
	/** {@inheritDoc} */
	@Override
	public Offender getOffender() {
		return this.offender;
	}

	/** {@inheritDoc} */
	@Override
	public void setDateRange(
			final DateRange dateRange) {
		this.dateRange = dateRange;
	}

	/** {@inheritDoc} */
	@Override
	public DateRange getDateRange() {
		return this.dateRange;
	}

	/** {@inheritDoc} */
	@Override
	public void setImmigrationStatus(
			final ImmigrationStatus immigrationStatus) {
		this.immigrationStatus = immigrationStatus;
	}

	/** {@inheritDoc} */
	@Override
	public ImmigrationStatus getImmigrationStatus() {
		return this.immigrationStatus;
	}

	/** {@inheritDoc} */
	@Override
	public void setLegal(final Boolean legal) {
		this.legal = legal;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getLegal() {
		return this.legal;
	}

	/** {@inheritDoc} */
	@Override
	public void setInsNumber(final String insNumber) {
		this.insNumber = insNumber;
	}

	/** {@inheritDoc} */
	@Override
	public String getInsNumber() {
		return this.insNumber;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof AlienResidence)) {
			return false;
		}
		AlienResidence that = (AlienResidence) obj;
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required");
		}
		if (!this.getOffender().equals(that.getOffender())) {
			return false;
		}
		if (this.getDateRange() != null) {
			if (that.getDateRange() != null) {
				if (this.getDateRange().getStartDate() != null) {
					if (!this.getDateRange().getStartDate()
							.equals(that.getDateRange().getStartDate())) {
						return false;
					}
				} else if (that.getDateRange().getStartDate() != null) {
					return false;
				}
				if (this.getDateRange().getEndDate() != null) {
					if (!this.getDateRange().getEndDate().equals(
							that.getDateRange().getEndDate())) {
						return false;
					}
				} else if (that.getDateRange().getEndDate() != null) {
					return false;
				}
			} else {
				return false;
			}
		} else if (that.getDateRange() != null) {
			return false;
		}
		if (this.getImmigrationStatus() != null) {
			if (!this.getImmigrationStatus()
					.equals(that.getImmigrationStatus())) {
				return false;
			}
		} else if (that.getImmigrationStatus() != null) {
			return false;
		}
		if (this.getLegal() != null) {
			if (!this.getLegal().equals(that.getLegal())) {
				return false;
			}
		} else if (that.getLegal() != null) {
			return false;
		}
		if (this.getInsNumber() != null) {
			if (!this.getInsNumber().equals(that.getInsNumber())) {
				return false;
			}
		} else if (that.getInsNumber() != null) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		int hashCode = 14;
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required");
		}
		hashCode = 29 * hashCode + this.getOffender().hashCode();
		if (this.getDateRange() != null) {
			if (this.getDateRange().getStartDate() != null) {
				hashCode = 31 * hashCode
						+ this.getDateRange().getStartDate().hashCode();
			}
			if (this.getDateRange().getEndDate() != null) {
				hashCode = 33 * hashCode
						+ this.getDateRange().getEndDate().hashCode();
			}
		}
		if (this.getImmigrationStatus() != null) {
			hashCode = 29 * hashCode + this.getImmigrationStatus().hashCode();
		}
		if (this.getLegal() != null) {
			hashCode = 29 * hashCode + this.getLegal().hashCode();
		}
		if (this.getInsNumber() != null) {
			hashCode = 29 * hashCode + this.getInsNumber().hashCode();
		}
		return hashCode;
	}
}