package omis.schedule.builder.testng;

import java.text.ParseException;
import java.util.EnumSet;
import java.util.Set;

import omis.datatype.DateRange;
import omis.datatype.DayOfWeek;
import omis.schedule.builder.WeeklyRecurringScheduleBuilder;
import omis.schedule.domain.SchedulableEvent;

import org.testng.annotations.Test;


/**
 * Test cases for weekly recurring schedule builder.
 * @author Stephen Abson
 * @version 0.1.5 (Feb 8, 2012)
 * @since OMIS 3.0
 */
@Test
public class WeeklyRecurringScheduleBuilderTests extends
		AbstractRecurringScheduleBuilderTests {
	
	/**
	 * Tests the building of a schedule every nth week.
	 * <p>
	 * Tests for multiple nth week values are performed. 
	 * @throws ParseException;
	 */
	public void testNthWeekScheduleBuilder() throws ParseException {
		for (int nthWeek : new int[] { 1, 2, 3, 4 }) {
			String startTime = "11:00:00";
			String endTime = "11:29:59";
			DateRange eventTimeRange = parseTimes(startTime, endTime);
			logger.info("Using time range: " + eventTimeRange
					+ " (originally " + startTime + " to " + endTime + ")");
			String startDate = "12/16/2011 12:30:00";
			String endDate = "02/16/2012 12:30:00";
			DateRange recurrenceDateRange = parseDates(startDate, endDate);
			logger.info("Using recurrence date range: "
					+ recurrenceDateRange + " (originally " + startDate + " to "
					+ endDate + ")");
			logger.info("nth week is (alternating week on which the event is" +
					" to recur): " + nthWeek);
			EnumSet<DayOfWeek> daysOfWeek = EnumSet.noneOf(DayOfWeek.class);
			for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
				if (Math.random() > 0.66667) {
					daysOfWeek.add(dayOfWeek);
				}
			}
			if (daysOfWeek.isEmpty()) {
				logger.severe(
					"Aborting attempt to build schedule for no days of week");
				continue;
			}
			logger.info("Testing days of week: " + daysOfWeek);
			WeeklyRecurringScheduleBuilder builder =
					WeeklyRecurringScheduleBuilder.create(eventTimeRange,
							recurrenceDateRange, nthWeek, daysOfWeek);
			Set<SchedulableEvent> events = builder.build(simpleInstanceFactory);
			print(events);
			int origSize = events.size();
			builder.setEndAfterOccurrences(origSize);
			events = builder.build(simpleInstanceFactory);
			print(events);
			assert events.size() == origSize
					: "Size mismatch  - original: " + origSize + "; new: "
					+ events.size();
		}
	}
	
	/** {@inheritDoc} */
	@Override
	protected String getClassName() {
		return this.getClass().getName();
	}
}