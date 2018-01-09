package omis.health.report;

import java.util.Date;

/** Resolved referral report object.
 * @author Ryan Johns
 * @version 0.1.0 (Apr 30, 2014)
 * @since OMIS 3.0 */
public class ResolvedInternalReferralSummary {
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
	private final String notes;
	private final String internalReferralStatusReasonName;

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
	 * @param providerTitleName provider title.
	 * @param notes notes.
	 * @param internalReferrralStatusReasonName referral status reason name. */
	public ResolvedInternalReferralSummary(final Long appointmentId,
			final Long referralId, final String offenderLastName,
			final String offenderMiddleName, final String offenderFirstName,
			final Integer offenderNumber,
			final Date scheduledDate,
			final String providerLastName, final String providerMiddleName,
			final String providerFirstName, final String providerTitleName,
			final String notes,
			final String internalReferralStatusReasonName) {
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
		this.notes = notes;
		this.internalReferralStatusReasonName =
				internalReferralStatusReasonName;
	}

	/** Gets appointment id.
	 * @return appointmentId appointment id. */
	public Long getAppointmentId() { return this.appointmentId; }

	/** Gets referral id.
	 * @return referralId referral id. */
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
	 * @return offenderNUmber offender number. */
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

	/** Gets provider first n	ame.
	 * @return providerFirstName provider first name. */
	public String getProviderFirstName() { return this.providerFirstName; }

	/** Gets provider title name.
	 * @return providerTitleName provider title name. */
	public String getProviderTitleName() { return this.providerTitleName; }

	/** Gets notes.
	 * @return notes. */
	public String getNotes() { return this.notes; }

	/** Gets internal referral status reason.
	 * @return status reason. */
	public String getInternalReferralStatusReasonName() {
		return this.internalReferralStatusReasonName;
	}
}
