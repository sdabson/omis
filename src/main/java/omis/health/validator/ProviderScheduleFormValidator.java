package omis.health.validator;

import java.util.Date;
import java.util.Iterator;

import omis.datatype.EntityPersistenceState;
import omis.health.web.form.IrregularScheduleDayItemForm;
import omis.health.web.form.ProviderScheduleForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for provider schedule form.
 *
 * @author Sheronda Vaughn
 * @author Ryan Johns
 * @version 0.1.0 (Jun 10, 2014)
 * @since OMIS 3.0
 */
public class ProviderScheduleFormValidator implements Validator {

	/* Path variables. */
	private static final String SUNDAY_START_TIME_PATH =
			"sundayStartTime";
	private static final String SUNDAY_END_TIME_PATH =
			"sundayEndTime";
	private static final String MONDAY_START_TIME_PATH =
			"mondayStartTime";
	private static final String MONDAY_END_TIME_PATH =
			"mondayEndTime";
	private static final String TUESDAY_START_TIME_PATH =
			"tuesdayStartTime";
	private static final String TUESDAY_END_TIME_PATH =
			"tuesdayEndTime";
	private static final String WEDNESDAY_START_TIME_PATH =
			"wednesdayStartTime";
	private static final String WEDNESDAY_END_TIME_PATH =
			"wednesdayEndTime";
	private static final String THURSDAY_START_TIME_PATH =
			"thursdayStartTime";
	private static final String THURSDAY_END_TIME_PATH =
			"thursdayEndTime";
	private static final String FRIDAY_START_TIME_PATH =
			"fridayStartTime";
	private static final String FRIDAY_END_TIME_PATH =
			"fridayEndTime";
	private static final String SATURDAY_START_TIME_PATH =
			"saturdayStartTime";
	private static final String SATURDAY_END_TIME_PATH =
			"saturdayEndTime";
	private static final String IRREGULAR_SCHEDULE_PATH =
			"irregularScheduleDays[%1$d]";
	private static final String IRREGULAR_SCHEDULE_DAY_PATH =
			IRREGULAR_SCHEDULE_PATH + ".day";
	private static final String IRREGULAR_SCHEDULE_START_TIME_PATH =
			IRREGULAR_SCHEDULE_PATH + ".startTime";
	private static final String IRREGULAR_SCHEDULE_END_TIME_PATH =
			IRREGULAR_SCHEDULE_PATH + ".endTime";


	/* Error codes. */
	private static final String PROVIDER_SCHEDULE_DAY = "providerScheduleDay";
	private static final String PROVIDER_IRREGULAR_SCHEDULE_DAY =
			"providerIrregularSchedule";
	private static final String DAY_CODE_KEY = "day";
	private static final String START_TIME_CODE_KEY = "startTime";
	private static final String REG_SCHED_START_TIME_CODE_KEY =
			PROVIDER_SCHEDULE_DAY + "." + START_TIME_CODE_KEY;
	private static final String END_TIME_CODE_KEY = "endTime";
	private static final String REG_SCHED_END_TIME_CODE_KEY =
			PROVIDER_SCHEDULE_DAY + "." + END_TIME_CODE_KEY;
	private static final String EMPTY_ERROR = "empty";
	private static final String START_AFTER_END_ERROR = "afterEndTime";

	// TODO need to be tested
	/** Instantiates a default provider schedule form validator. */
	public ProviderScheduleFormValidator() {
		// Default instantiation
	}

	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return ProviderScheduleForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		final ProviderScheduleForm providerScheduleForm
			= (ProviderScheduleForm) target;

		this.checkAndRejectDateRange(SUNDAY_START_TIME_PATH,
				SUNDAY_END_TIME_PATH, providerScheduleForm.getSundayStartTime(),
				providerScheduleForm.getSundayEndTime(), errors);

		this.checkAndRejectDateRange(MONDAY_START_TIME_PATH,
				MONDAY_END_TIME_PATH, providerScheduleForm.getMondayStartTime(),
				providerScheduleForm.getMondayEndTime(), errors);

		this.checkAndRejectDateRange(TUESDAY_START_TIME_PATH,
				TUESDAY_END_TIME_PATH,
				providerScheduleForm.getTuesdayStartTime(),
				providerScheduleForm.getTuesdayEndTime(), errors);

		this.checkAndRejectDateRange(WEDNESDAY_START_TIME_PATH,
				WEDNESDAY_END_TIME_PATH,
				providerScheduleForm.getWednesdayStartTime(),
				providerScheduleForm.getWednesdayEndTime(), errors);

		this.checkAndRejectDateRange(THURSDAY_START_TIME_PATH,
				THURSDAY_END_TIME_PATH,
				providerScheduleForm.getThursdayStartTime(),
				providerScheduleForm.getThursdayEndTime(), errors);

		this.checkAndRejectDateRange(FRIDAY_START_TIME_PATH,
				FRIDAY_END_TIME_PATH, providerScheduleForm.getFridayStartTime(),
				providerScheduleForm.getFridayEndTime(), errors);

		this.checkAndRejectDateRange(SATURDAY_START_TIME_PATH,
				SATURDAY_END_TIME_PATH,
				providerScheduleForm.getSaturdayStartTime(),
				providerScheduleForm.getSaturdayEndTime(), errors);
		if (providerScheduleForm.getIrregularScheduleDays() != null) {
		final Iterator<IrregularScheduleDayItemForm> iterator =
				providerScheduleForm.getIrregularScheduleDays().iterator();
		while(iterator.hasNext()) {
			final IrregularScheduleDayItemForm irregularScheduleDayItem =
					iterator.next();

			final EntityPersistenceState state = irregularScheduleDayItem
					.checkPersistenceState();
			if (state == null) {
				iterator.remove();
			}
		}

		for (int x = 0;
				x < providerScheduleForm.getIrregularScheduleDays().size();
				x++) {

			final IrregularScheduleDayItemForm irregularScheduleDayItem =
				providerScheduleForm.getIrregularScheduleDays().get(x);

			if (irregularScheduleDayItem.checkPersistenceState() != EntityPersistenceState.REMOVED) {
			if (irregularScheduleDayItem.getStartTime() != null
					&& irregularScheduleDayItem.getEndTime() != null) {
				if (irregularScheduleDayItem.getStartTime().getTime()
					> irregularScheduleDayItem.getEndTime().getTime()) {
						errors.rejectValue(String.format(
							IRREGULAR_SCHEDULE_START_TIME_PATH,	x),
							PROVIDER_IRREGULAR_SCHEDULE_DAY + "."
							+ START_TIME_CODE_KEY + "."
									+ START_AFTER_END_ERROR);
				}
			} else {
				if (irregularScheduleDayItem.getStartTime() != null) {
					errors.rejectValue(String.format(
							IRREGULAR_SCHEDULE_END_TIME_PATH, x),
							PROVIDER_IRREGULAR_SCHEDULE_DAY + "."
							+ END_TIME_CODE_KEY + "." + EMPTY_ERROR);
				}
				if (irregularScheduleDayItem.getEndTime() != null) {
					errors.rejectValue(String.format(
							IRREGULAR_SCHEDULE_START_TIME_PATH, x),
							PROVIDER_IRREGULAR_SCHEDULE_DAY + "."
							+ START_TIME_CODE_KEY + "." + EMPTY_ERROR);
				}
			}
			if (irregularScheduleDayItem.getDay() == null) {
				errors.rejectValue(String.format(IRREGULAR_SCHEDULE_DAY_PATH,
						x),
						PROVIDER_IRREGULAR_SCHEDULE_DAY + "." + DAY_CODE_KEY
						+ "." + EMPTY_ERROR);
			}
			}
		}}
	}

	/* Checks validity of provider schedule time ranges. */
	private void checkAndRejectDateRange(final String startTimePath,
			final String endTimePath, final Date startTime,
			final Date endTime, final Errors errors) {
		final boolean isNullStartTime = (startTime == null);
		final boolean isNullEndTime = (endTime == null);

		if (!isNullStartTime || !isNullEndTime) {
			if (isNullStartTime) {
				errors.rejectValue(startTimePath,
						REG_SCHED_START_TIME_CODE_KEY + "." + EMPTY_ERROR);
			}
			if (isNullEndTime) {
				errors.rejectValue(endTimePath,
						REG_SCHED_END_TIME_CODE_KEY + "." + EMPTY_ERROR);
			}
			if (!isNullStartTime && !isNullEndTime) {
				if (startTime.getTime() > endTime.getTime()) {
					errors.rejectValue(startTimePath,
							REG_SCHED_START_TIME_CODE_KEY + "."
									+ START_AFTER_END_ERROR);
				}
			}
		}
	}

}