package omis.schedule.builder.testng;

import java.text.ParseException;
import java.util.Set;

import omis.datatype.DateRange;
import omis.schedule.builder.DailyRecurringScheduleBuilder;
import omis.schedule.domain.SchedulableEvent;

import org.testng.annotations.Test;


/**
 * Test cases for daily recurring schedule builder.
 * @author Stephen Abson
 * @version 0.1.7 (Jan 24, 2012)
 * @since OMIS 3.0
 */
@Test
public class DailyRecurringScheduleBuilderTests
		extends AbstractRecurringScheduleBuilderTests {
	
	/**
	 * Tests nth day recurring schedule builder.
	 * @throws ParseException should not occur - ever!
	 */
	public void testNthDayBuilder() throws ParseException {
		for (int i = 1; i <= 2; i ++) {
			nthDayBuilderTest(i);
		}
	}
	
	// Perform test
	private void nthDayBuilderTest(int nthDay) throws ParseException {
		String startTime = "11:00:00";
		String endTime = "11:29:59";
		DateRange eventTimeRange = parseTimes(startTime, endTime);
		logger.info("Using time range: " + eventTimeRange
				+ " (originally " + startTime + " to " + endTime + ")");
		String startDate = "01/16/2012 12:30:00";
		String endDate = "03/16/2012 12:30:00";
		DateRange recurrenceDateRange = parseDates(startDate, endDate);
		logger.info("Using recurrence date range: "
				+ recurrenceDateRange + " (originally " + startDate + " to "
				+ endDate + ")");
		logger.info("nth day is (alternating day on which the event is" +
				" to recur): " + nthDay);
		DailyRecurringScheduleBuilder builder = DailyRecurringScheduleBuilder
			.createForNthDay(eventTimeRange, recurrenceDateRange, nthDay);
		Set<SchedulableEvent> events = builder.build(simpleInstanceFactory);
		print(events);
		int origSize = events.size();
		builder.setEndAfterOccurrences(origSize);
		events = builder.build(simpleInstanceFactory);
		print(events);
		int newSize = events.size();
		logger.info("Original size: " + origSize + "; new size: " + newSize);
		assert origSize == newSize
				: "Unexpected size when performing specified and nth day" +
					" builder; expected: " + origSize + "; found: " + newSize;
	}
	
	/**
	 * Tests weekday recurring schedule builder.
	 * @throws ParseException  should not occur - ever!
	 */
	public void weekdayBuilderTest() throws ParseException {
		// TODO: Test - it is broken
		String startTime = "11:00:00";
		String endTime = "11:29:59";
		DateRange eventTimeRange = parseTimes(startTime, endTime);
		logger.info("Using time range: " + eventTimeRange
				+ " (originally " + startTime + " to " + endTime + ")");
		String startDate = "01/16/2012 12:30:00";
		String endDate = "03/16/2012 12:30:00";
		DateRange recurrenceDateRange = parseDates(startDate, endDate);
		logger.info("Using recurrence date range: "
				+ recurrenceDateRange + " (originally " + startDate + " to "
				+ endDate + ")");
		DailyRecurringScheduleBuilder builder = DailyRecurringScheduleBuilder
			.createEveryWeekday(eventTimeRange, recurrenceDateRange);
		Set<SchedulableEvent> events = builder.build(simpleInstanceFactory);
		print(events);
		int origSize = events.size();
		builder.setEndAfterOccurrences(origSize);
		events = builder.build(simpleInstanceFactory);
		print(events);
		int newSize = events.size();
		logger.info("Original size: " + origSize + "; new size: " + newSize);
		assert origSize == newSize
				: "Unexpected size when performing specified and nth day" +
					" builder; expected: " + origSize + "; found: " + newSize;
	}
	
	/** {@inheritDoc} */
	@Override
	protected String getClassName() {
		return this.getClass().getName();
	}
}