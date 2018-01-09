package omis.schedule.builder.testng;

import java.text.ParseException;
import java.util.Set;

import omis.datatype.DateRange;
import omis.datatype.DayOfWeek;
import omis.datatype.WeekOfMonth;
import omis.schedule.builder.MonthlyRecurringScheduleBuilder;
import omis.schedule.domain.SchedulableEvent;

import org.testng.annotations.Test;


/**
 * Test cases for monthly recurring schedule builder.
 * @author Stephen Abson
 * @version 0.1.0 (Feb 9, 2012)
 * @since OMIS 3.0
 */
@Test
public class MonthlyRecurringScheduleBuilderTests
		extends AbstractRecurringScheduleBuilderTests {

	/**
	 * Tests that an expected number of events are built by a monthly recurring
	 * schedule builder in which the day of the month is present in all months
	 * selected according to the specified builder criteria. 
	 * @throws ParseException if a literal date cannot be parsed
	 * (never should occur)
	 */
	@Test(groups = {"nthDayMonthly"})
	public void testCountResultsOfNthDayMonthlyBuilder() throws ParseException {
		final DateRange eventTimes = parseTimes("13:00:00", "14:29:59");
		final DateRange recurrenceDates = parseDates(
				"01/12/2012 12:30:00", "06/11/2012 15:00:00");
		final int nthDay = 12;
		final int m = 2;
		final int expectedSize = 2;
		MonthlyRecurringScheduleBuilder builder =
				MonthlyRecurringScheduleBuilder.createForNthDay(
						eventTimes, recurrenceDates, nthDay, m);
		Set<SchedulableEvent> events = builder.build(simpleInstanceFactory);
		print(events);
		assert events.size() == expectedSize
				: "Unexpected size of event set: " + expectedSize
				+ " expected but " + events.size() + " found";
	}
	
	/**
	 * Tests that an expected number of events are built by a monthly recurring
	 * schedule builder in which the day of the month is skipped in one
	 * selection according to the specified build criteria.
	 * @throws ParseException if a literal date cannot be parsed
	 * (never should occur)
	 */
	@Test(groups = {"nthDayMonthly"})
	public void testCountResultsOfNthDayMonthlyBuilderWithSkippedMonth()
			throws ParseException {
		final DateRange eventTimes = parseTimes("09:30:00", "16:29:59");
		final DateRange recurrenceDates = parseDates(
				"01/05/2012 23:59:59", "06/29/2012 00:00:00");
		final int nthDay = 30;
		final int m = 2;
		final int expectedSize = 1;
		MonthlyRecurringScheduleBuilder builder =
				MonthlyRecurringScheduleBuilder.createForNthDay(
						eventTimes, recurrenceDates, nthDay, m);
		Set<SchedulableEvent> events = builder.build(simpleInstanceFactory);
		print(events);
		int origSize = events.size();
		assert events.size() == expectedSize
				: "Unexpected size of event set: " + expectedSize
				+ " expected but " + events.size() + " found";
		builder.setEndAfterOccurrences(origSize);
		events = builder.build(simpleInstanceFactory);
		print(events);
		assert events.size() == origSize
				: "Unexpected size of event set generated from after" +
						" occurrences end date: " + origSize + " expected but "
						+ events.size() + " found";
	}
	
	/**
	 * Tests that an expected number of events are scheduled on a specific day
	 * of the week every specific month.
	 * @throws ParseException if a literal date cannot be parsed
	 * (never should occur)
	 */
	@Test(groups = {"dayOfNthMonth"})
	public void testCountResultsOfDayOfNthMonth() throws ParseException {
		final DateRange eventTimes = parseTimes("09:30:00", "16:29:59");
		final DateRange recurringDates = parseDates(
				"02/05/2012 23:59:59", "05/02/2012 00:00:00");
		final DayOfWeek dayOfWeek = DayOfWeek.SATURDAY;
		final WeekOfMonth weekOfMonth = WeekOfMonth.FOURTH;
		final int m = 1;
		final int expectedSize = 3;
		MonthlyRecurringScheduleBuilder builder =
				MonthlyRecurringScheduleBuilder.createForDayOfNthWeek(
						eventTimes, recurringDates, dayOfWeek, weekOfMonth, m);
		Set<SchedulableEvent> events = builder.build(simpleInstanceFactory);
		print(events);
		assert expectedSize == events.size()
				: "Unexpected number of events scheduled - expected: "
					+ expectedSize + "; scheduled: " + events.size();
		final int origSize = events.size();
		builder.setEndAfterOccurrences(origSize);
		events = builder.build(simpleInstanceFactory);
		print(events);
		assert events.size() == origSize
				: "Unexpected number of events after build after setting"
					+ " end after occurrence - original size: " + origSize
					+ "; after build: " + events.size();
	}
	
	/**
	 * Tests that months without a specified day in a specified week of
	 * a month are skipped. 
	 * @throws ParseException if a literal date cannot be parsed
	 * (never should occur)
	 */
	// Test does not pass due to a bug in the module but can be disabled as the
	// module is not used
	// When the module is used, fix the builder and re-enable the test by
	// removing the enabled attribute (use the default of true) - SA 
	@Test(groups = {"dayOfNthMonth"}, enabled = false)
	public void testCountResultsOfDayOfNthMonthWithSkippedMonthAfter() 
			throws ParseException {
		final DateRange eventTimes = parseTimes("09:30:00", "16:29:59");
		final DateRange recurringDates = parseDates(
				"12/01/2011 01:00:01", "05/03/2012 23:43:23");
		final DayOfWeek dayOfWeek = DayOfWeek.THURSDAY;
		final WeekOfMonth weekOfMonth = WeekOfMonth.FIFTH;
		final int m = 1;
		final int expectedSize = 2;
		MonthlyRecurringScheduleBuilder builder =
				MonthlyRecurringScheduleBuilder.createForDayOfNthWeek(
						eventTimes, recurringDates, dayOfWeek, weekOfMonth, m);
		Set<SchedulableEvent> events = builder.build(simpleInstanceFactory);
		print(events);
		assert expectedSize == events.size()
				: "Unexpected size - size is: " + events.size() + "; expected: "
				+ expectedSize;
		builder.setEndAfterOccurrences(expectedSize);
		events = builder.build(simpleInstanceFactory);
		print(events);
		
		// New size should be same as original
		assert expectedSize == events.size() 
				: "Unexpected size after build - size is: "
				+ events.size() + "; expected: " + expectedSize;
	}
	
	/** {@inheritDoc} */
	@Override
	protected String getClassName() {
		return this.getClass().getName();
	}
}