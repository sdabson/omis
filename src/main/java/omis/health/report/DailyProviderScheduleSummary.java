package omis.health.report;

import java.util.Date;

import omis.health.domain.ProviderAssignmentCategory;

/** Daily provider schedule summary report.
 * @author Ryan Johns
 * @version 0.1.0 (Apr 10, 2014)
 * @since OMIS 3.0 */
public class DailyProviderScheduleSummary {
	private final Date date;
	private final Date startTime;
	private final Date endTime;
	private final String providerFirstName;
	private final String providerLastName;
	private final ProviderAssignmentCategory providerAssignmentCategory;
	private final String facilityName;
	private final int appointmentCount;

	/** Constructor.
	 * @param date date.
	 * @param startTime start time.
	 * @param endTime end time.
	 * @param providerFirstName provider first name.
	 * @param providerLastName provider last name.
	 * @param providerAssignmentCategory provider assignment category.
	 * @param facilityName facilityName facility name.
	 * @param appointmentCount appointment count. */
	public DailyProviderScheduleSummary(final Date date, final Date startTime,
			final Date endTime, final String providerFirstName,
			final String providerLastName,
			final ProviderAssignmentCategory providerAssignmentCategory,
			final String facilityName, final int appointmentCount) {
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.providerFirstName = providerFirstName;
		this.providerLastName = providerLastName;
		this.providerAssignmentCategory = providerAssignmentCategory;
		this.facilityName = facilityName;
		this.appointmentCount = appointmentCount;
	}

	/** Gets date.
	 * @return date. */
	public Date getDate() {
		return this.date;
	}

	/** Gets start time.
	 * @return startTime. */
	public Date getStartTime() {
		return this.startTime;
	}

	/** Gets end time.
	 * @return endTime. */
	public Date getEndTime() {
		return this.endTime;
	}

	/** Gets providers first name.
	 * @return first name. */
	public String getProviderFirstName() {
		return this.providerFirstName;
	}

	/** Gets providers last name.
	 * @return last name. */
	public String getProviderLastName() {
		return this.providerLastName;
	}

	/** Gets provider assignment category.
	 * @return providerAssignmentCategory. */
	public ProviderAssignmentCategory getProviderAssignmentCategory() {
		return this.providerAssignmentCategory;
	}

	/** Gets facility name.
	 * @return facilityName. */
	public String getFacilityName() {
		return this.facilityName;
	}

	/** Gets appointment count.
	 * @return appointmentCount appointment count. */
	public int getAppointmentCount() {
		return this.appointmentCount;
	}
}
