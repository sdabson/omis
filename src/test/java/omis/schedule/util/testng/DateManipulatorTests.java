package omis.schedule.util.testng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import omis.datatype.DayOfWeek;
import omis.datatype.WeekOfMonth;
import omis.util.DateManipulator;

import org.testng.annotations.Test;


/**
 * Tests for date manipulator.
 * @author Stephen Abson
 * @version 0.1.0 (Feb 13, 2012)
 * @since OMIS 3.0
 */
@Test
public class DateManipulatorTests {

	private final SimpleDateFormat sdf = new SimpleDateFormat(
			"MM/dd/yyyy HH:mm:ss");
	
	/** Tests last week of month. */
	public void testLastWeekOfMonth() throws ParseException {
		Date date = parseDate("02/03/2012 09:30:00");
		DateManipulator manipulator = new DateManipulator(date);
		final int initialMonth = manipulator.getMonth(); 
		manipulator.setToWeekOfMonth(WeekOfMonth.LAST);
		manipulator.setToDayOfWeek(DayOfWeek.SUNDAY);
		assert manipulator.getMonth() == initialMonth
				: "Month changed - initial month: " + initialMonth
				+ "; new month: " + manipulator.getMonth() + "; new date is "
				+ manipulator.getDate();
	}
	
	// Parses a date
	private Date parseDate(final String value) throws ParseException {
		return sdf.parse(value);
	}
}