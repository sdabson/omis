package omis.religion.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.audit.domain.VerificationSignature;
import omis.datatype.DateRange;
import omis.offender.domain.Offender;
import omis.religion.domain.Religion;
import omis.religion.domain.ReligiousPreference;

/**
 * Implementation of religious preference.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 22, 2014)
 * @since OMIS 3.0
 */
public class ReligiousPreferenceImpl
		implements ReligiousPreference {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Offender offender;
	
	private UpdateSignature updateSignature;
	
	private CreationSignature creationSignature;
	
	private Religion religion;
	
	private DateRange dateRange;
	
	private String comment;
	
	private VerificationSignature verificationSignature;
	
	/** Instantiates an implementation of religious preference. */
	public ReligiousPreferenceImpl() {
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
	public void setReligion(final Religion religion) {
		this.religion = religion;
	}

	/** {@inheritDoc} */
	@Override
	public Religion getReligion() {
		return this.religion;
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
	public void setVerificationSignature(
			final VerificationSignature verificationSignature) {
		this.verificationSignature = verificationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public VerificationSignature getVerificationSignature() {
		return this.verificationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof ReligiousPreference)) {
			return false;
		}
		ReligiousPreference that = (ReligiousPreference) obj;
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
					if (!(this.getDateRange().getEndDate().equals(
							that.getDateRange().getEndDate()))) {
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
		if (this.getReligion() == null) {
			throw new IllegalStateException("Religion required");
		}
		if (!this.getReligion().equals(that.getReligion())) {
			return false;
		}
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required");
		}
		if (!this.getOffender().equals(that.getOffender())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getReligion() == null) {
			throw new IllegalStateException("Religion required");
		}
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getReligion().hashCode();
		hashCode = 29 * hashCode + this.getOffender().hashCode();
		if (this.getDateRange() != null) {
			if (this.getDateRange().getStartDate() != null) {
				hashCode = 31 * hashCode + this.getDateRange()
						.getStartDate().hashCode();
			}
			if (this.getDateRange().getEndDate() != null) {
				hashCode = 33 * hashCode + this.getDateRange()
						.getEndDate().hashCode();
			}
		}
		return hashCode;
	}
}