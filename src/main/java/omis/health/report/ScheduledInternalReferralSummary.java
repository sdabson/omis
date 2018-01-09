package omis.health.report;





import java.util.Date;

import omis.health.domain.ReferralLocationDesignator;

/** Scheduled referral report object.
 * @author Ryan Johns
 * @version 0.1.0 (Apr 18, 2014)
 * @since OMIS 3.0 */
public class ScheduledInternalReferralSummary {
	private final Long appointmentId;
	private final Long referralId;
	private final String offenderLastName;
	private final String offenderMiddleName;
	private final String offenderFirstName;
	private final Integer offenderNumber;
	private final Date scheduledDate;
	private final String providerLastName;
	private final String providerMiddleName;
	private final String providerFirstName;
	private final String providerTitleName;
	private final String unitName;
	private final String notes;
	private final ReferralLocationDesignator locationDesignator;
	private final String reasonName;

	/** Constructor.
	 * @param appointmentId appointment id.
	 * @param referralId referral id.
	 * @param offenderLastName offender last name.
	 * @param offenderMiddleName offender middle name.
	 * @param offenderFirstName offender first name.
	 * @param offenderNumber offender number.
	 * @param scheduledDate scheduled date.
	 * @param providerLastName provider last name.
	 * @param providerMiddleName provider middle name.
	 * @param providerFirstName provider first name.
	 * @param providerTitleName provider title name.
	 * @param unitName unit name.
	 * @param notes notes.
	 * @param locationDesignator location designator.
	 * @param reasonName reason name. */
	public ScheduledInternalReferralSummary(final Long appointmentId,
			final Long referralId, final String offenderLastName,
			final String offenderMiddleName, final String offenderFirstName,
			final Integer offenderNumber,
			final Date scheduledDate,
			final String providerLastName, final String providerMiddleName,
			final String providerFirstName, final String providerTitleName,
			final String unitName, final String notes,
		    final ReferralLocationDesignator locationDesignator,
		    final String reasonName) {
		this.appointmentId = appointmentId;
		this.referralId = referralId;
		this.offenderLastName = offenderLastName;
		this.offenderMiddleName = offenderMiddleName;
		this.offenderFirstName = offenderFirstName;
		this.offenderNumber = offenderNumber;
		this.scheduledDate = scheduledDate;
		this.providerLastName = providerLastName;
		this.providerMiddleName = providerMiddleName;
		this.providerFirstName = providerFirstName;
		this.providerTitleName = providerTitleName;
		this.unitName = unitName;
		this.notes = notes;
		this.locationDesignator = locationDesignator;
		this.reasonName = reasonName;
	}

	/** Gets appointment id.
	 * @return appointmentId appointment id. */
	public Long getAppointmentId() { return this.appointmentId; }

	/** Gets referral id.
	 * @return internalReferralId internal referral id. */
	public Long getReferralId() { return this.referralId; }

	/** Gets offender last name.
	 * @return offenderLastName offender last name. */
	public String getOffenderLastName() { return this.offenderLastName; }

	/** Gets offender middle name.
	 * @return offenderMiddleName offender middle name. */
	public String getOffenderMiddleName() { return this.offenderMiddleName; }

	/** Gets offender first name.
	 * @return offenderFirstName offender first name. */
	public String getOffenderFirstName() { return this.offenderFirstName; }

	/** Gets offender number.
	 * @return offenderNumber offender number. */
	public Integer getOffenderNumber() { return this.offenderNumber; }

	/** Gets scheduled Date.
	 * @return scheduledDate scheduled date. */
	public Date getScheduledDate() { return this.scheduledDate; }

	/** Gets provider last name.
	 * @return providerLastName provider last name. */
	public String getProviderLastName() { return this.providerLastName; }

	/** Gets provider middle name.
	 * @return providerMiddleName provider middle name. */
	public String getProviderMiddleName() { return this.providerMiddleName; }

	/** Gets provider first name.
	 * @return providerFirstName provider first name. */
	public String getProviderFirstName() { return this.providerFirstName; }

	/** Gets provider title name.
	 * @return providerTitleName provider title name. */
	public String getProviderTitleName() { return this.providerTitleName; }

	/** Gets unit name.
	 * @return unitName unit name. */
	public String getUnitName() { return this.unitName; }

	/** Gets notes.
	 * @return notes = notes. */
	public String getNotes() { return this.notes; }

	/** Gets location name.
	 * @return location name. */
	public ReferralLocationDesignator getLocationDesignator() {
		return this.locationDesignator;
	}

	/** Gets reason name.
	 * @return reason name. */
	public String getReasonName() {
		return this.reasonName;
	}
}
