package omis.health.domain.impl;

import omis.health.domain.HealthAppointmentStatus;
import omis.health.domain.LabWorkReferralStatusReason;

/**
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jun 30, 2014)
 * @since OMIS 3.0
 */
public class LabWorkReferralStatusReasonImpl implements LabWorkReferralStatusReason {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String name;
	
	private String description;
	
	private Boolean valid;
	
	private Short sortOrder;

	private HealthAppointmentStatus appointmentStatus;
	
	public LabWorkReferralStatusReasonImpl() {
		// Default constructor.
	}

	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/** {@inheritDoc} */
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public String getName() {
		return this.name;
	}

	/** {@inheritDoc} */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/** {@inheritDoc} */
	@Override
	public String getDescription() {
		return this.description;
	}

	/** {@inheritDoc} */
	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getValid() {
		return this.valid;
	}

	/** {@inheritDoc} */
	@Override
	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	/** {@inheritDoc} */
	@Override
	public Short getSortOrder() {
		return this.sortOrder;
	}

	/** {@inheritDoc} */
	@Override
	public void setSortOrder(Short sortOrder) {
		this.sortOrder = sortOrder;
	}

	/** {@inheritDoc} */
	@Override
	public HealthAppointmentStatus getAppointmentStatus() {
		return this.appointmentStatus;
	}

	/** {@inheritDoc} */
	@Override
	public void setAppointmentStatus(HealthAppointmentStatus appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof LabWorkReferralStatusReason)) {
			return false;
		}
		LabWorkReferralStatusReason that = (LabWorkReferralStatusReason) obj;
		
		if (this.getName() == null) {
			throw new IllegalStateException("Name required.");
		}
		if (this.getName().equals(that.getName())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getName() == null) {
			throw new IllegalStateException("Name Required.");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getName().hashCode();
		return hashCode;
	}
}
