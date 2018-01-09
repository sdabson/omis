package omis.util.testng;

import java.util.Date;

import org.testng.annotations.Test;

import omis.datatype.DateRange;

/**
 * Tests date range utility.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"dateRange", "util"})
public class DateRangeTests {

	/** Tests inclusive count of days in date range. */
	public void testInclusiveDayCount() {
		Date effectiveDate = new Date();
		long dayCount = DateRange
				.countDaysInclusively(effectiveDate, effectiveDate);
		assert dayCount == 1 : String.format(
						"Inclusive day count of date range counts wrongly"
						+ " - %d expected; %d counted", 1, dayCount);
	}
}