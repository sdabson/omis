package omis.health.domain.impl;

import omis.health.domain.HealthAppointmentStatus;
import omis.health.domain.InternalReferralStatusReason;

/**
 * Implementation of reason for status of appointment of internal referral.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (May 20, 2014)
 * @since OMIS 3.0
 */
public class InternalReferralStatusReasonImpl
		implements InternalReferralStatusReason {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String name;
	
	private String description;
	
	private Short sortOrder;
	
	private Boolean valid ;
	
	private HealthAppointmentStatus appointmentStatus;
	
	/**
	 * Instantiates an implementation of reason for status of appointment of
	 * internal referral.
	 */
	public InternalReferralStatusReasonImpl() {
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
	public void setName(final String name) {
		this.name = name;
	}

	/** {@inheritDoc} */
	@Override
	public String getName() {
		return this.name;
	}

	/** {@inheritDoc} */
	@Override
	public void setDescription(final String description) {
		this.description = description;
	}

	/** {@inheritDoc} */
	@Override
	public String getDescription() {
		return this.description;
	}

	/** {@inheritDoc} */
	@Override
	public void setSortOrder(final Short sortOrder) {
		this.sortOrder = sortOrder;
	}

	/** {@inheritDoc} */
	@Override
	public Short getSortOrder() {
		return this.sortOrder;
	}

	/** {@inheritDoc} */
	@Override
	public void setValid(final Boolean valid) {
		this.valid = valid;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getValid() {
		return this.valid;
	}

	/** {@inheritDoc} */
	@Override
	public void setAppointmentStatus(
			final HealthAppointmentStatus appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}

	/** {@inheritDoc} */
	@Override
	public HealthAppointmentStatus getAppointmentStatus() {
		return this.appointmentStatus;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof InternalReferralStatusReason)) {
			return false;
		}
		InternalReferralStatusReason that = (InternalReferralStatusReason) obj;
		if (this.getName() == null) {
			throw new IllegalStateException("Name required");
		}
		if (!this.getName().equals(that.getName())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getName() == null) {
			throw new IllegalStateException("Name required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getName().hashCode();
		return hashCode;
	}
}