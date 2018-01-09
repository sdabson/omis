package omis.health.web.controller.delegate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import omis.datatype.DateRange;
import omis.health.domain.ProviderAssignment;
import omis.health.report.DailyProviderScheduleSummary;
import omis.health.report.ProviderScheduleReportService;
import omis.health.web.form.InternalProviderScheduleDayItem;

/**
 * Delegate for provider schedule web tier components.
 *
 * @author Stephen Abson
 * @version 0.1.0 (May 6, 2014)
 * @since OMIS 3.0
 */
public class ProviderScheduleDelegate {

	private static final String START_DATE_MODEL_KEY = "startDate";

	private static final String END_DATE_MODEL_KEY = "endDate";

	private static final String INTERNAL_PROVIDER_SCHEDULE_DAY_ITEMS_MODEL_KEY
		= "internalProviderScheduleDayItems";

	private final ProviderScheduleReportService providerScheduleReportService;

	/**
	 * Instantiates a delegate for provider schedule web tier components.
	 *
	 * @param providerScheduleReportService service for reporting provider
	 * schedules
	 */
	public ProviderScheduleDelegate(
			final ProviderScheduleReportService providerScheduleReportService) {
		this.providerScheduleReportService = providerScheduleReportService;
	}

	/**
	 * Returns internal provider schedule day items for the provider during
	 * the date range.
	 *
	 * @param providerAssignment assignment of provider
	 * @param dateRange date range
	 * @return internal provider schedule day items for provider during
	 * date range
	 */
	public List<InternalProviderScheduleDayItem>
	findInternalProviderScheduleDayItems(
			final ProviderAssignment providerAssignment,
			final DateRange dateRange) {
		final List<DailyProviderScheduleSummary> dailyProviderScheduleSummaries
			= this.providerScheduleReportService
				.findDuringDateRange(providerAssignment, dateRange);
		final List<InternalProviderScheduleDayItem>
		internalProviderScheduleDayItems
			= new ArrayList<InternalProviderScheduleDayItem>();
		for (final DailyProviderScheduleSummary dailyProviderScheduleSummary
				: dailyProviderScheduleSummaries) {
			final InternalProviderScheduleDayItem internalProviderScheduleDayItem
				= new InternalProviderScheduleDayItem();
			internalProviderScheduleDayItem
				.setDate(dailyProviderScheduleSummary.getDate());
			internalProviderScheduleDayItem
				.setStartTime(dailyProviderScheduleSummary.getStartTime());
			internalProviderScheduleDayItem
				.setEndTime(dailyProviderScheduleSummary.getEndTime());
			internalProviderScheduleDayItem
				.setScheduledApppointments(
					dailyProviderScheduleSummary.getAppointmentCount());
			internalProviderScheduleDayItems
				.add(internalProviderScheduleDayItem);
		}
		return internalProviderScheduleDayItems;
	}

	/**
	 * Adds internal provider schedule day items and date range for provider
	 * to model.
	 *
	 * @param model model
	 * @param providerAssignment assignment of provider
	 * @param dateRange date range
	 */
	public void addInternalProviderScheduleDayItems(
			final Map<String, Object> model,
			final ProviderAssignment providerAssignment,
			final DateRange dateRange) {
		final List<InternalProviderScheduleDayItem> internalProviderScheduleDayItems
			= this.findInternalProviderScheduleDayItems(
					providerAssignment, dateRange);
		model.put(INTERNAL_PROVIDER_SCHEDULE_DAY_ITEMS_MODEL_KEY,
				internalProviderScheduleDayItems);
		model.put(START_DATE_MODEL_KEY, dateRange.getStartDate());
		model.put(END_DATE_MODEL_KEY, dateRange.getEndDate());
	}
}