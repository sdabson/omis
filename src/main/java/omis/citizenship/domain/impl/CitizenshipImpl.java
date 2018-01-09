package omis.citizenship.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.citizenship.domain.Citizenship;
import omis.country.domain.Country;
import omis.datatype.DateRange;
import omis.offender.domain.Offender;

/**
 * Implementation of citizenship.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 24, 2014)
 * @since OMIS 3.0
 */
public class CitizenshipImpl
		implements Citizenship {

	private static final long serialVersionUID = 1L;
	
	private Long id;

	private Offender offender;

	private CreationSignature creationSignature;

	private UpdateSignature updateSignature;

	private Country country;

	private DateRange dateRange;
	
	/** Instantiates an implementation of citizenship. */
	public CitizenshipImpl() {
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
	public void setCountry(final Country country) {
		this.country = country;
	}

	/** {@inheritDoc} */
	@Override
	public Country getCountry() {
		return this.country;
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
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof Citizenship)) {
			return false;
		}
		Citizenship that = (Citizenship) obj;
		if (this.getCountry() == null) {
			throw new IllegalStateException("Country required");
		}
		if (!this.getCountry().equals(that.getCountry())) {
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
					if (!this.getDateRange().getEndDate()
							.equals(that.getDateRange().getEndDate())) {
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
		if (this.getCountry() == null) {
			throw new IllegalStateException("Country required");
		}
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getCountry().hashCode();
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
		return hashCode;
	}
}