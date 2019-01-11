/*
 * OMIS - Offender Management Information System
 * Copyright (C) 2011 - 2017 State of Montana
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package omis.util.testng;

import java.util.Date;
import java.util.function.BiFunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.datatype.DateRange;
import omis.testng.AbstractNonTransactionalTestNGSpringContextTests;
import omis.util.DateUtility;

/**
 * Tests for counting full days.
 * 
 * <p>Tests both instance and static method. Results should be identical.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Aug 20, 2018)
 * @since OMIS 3.0
 */
@Test(groups = {"dateRange", "util"})
public class DateRangeCountDaysExactlyTests
		extends AbstractNonTransactionalTestNGSpringContextTests {
	
	@Autowired
	@Qualifier("dateUtility")
	private DateUtility dateUtility;
	
	/** Tests instance method. */
	public void test() {
		this.doTests(
				(startDate, endDate)
					-> new DateRange(startDate, endDate).countDaysExactly());
	}
	
	/** Tests static method. */
	public void testStatic() {
		this.doTests(DateRange::countDaysExactly);
	}

	// Performs tests.
	private void doTests(final BiFunction<Date, Date, Long> countDaysExactly) {
		
		// Fields:    start date/time        end date/time          expected
		//                                                          day count
		this.doAssert(countDaysExactly,
					  "12/12/2012 01:00 PM", "12/13/2012 12:30 PM", 1);
		this.doAssert(countDaysExactly,
					  "12/12/2012 01:00 PM", "12/14/2012 12:30 PM", 2);
		this.doAssert(countDaysExactly,
					  "12/12/2012 11:59 PM", "12/13/2012 12:00 AM", 1);
		this.doAssert(countDaysExactly,
					  "12/12/2012 11:59 PM", "12/12/2012 11:59 PM", 0);
		this.doAssert(countDaysExactly,
					  "12/12/2012 12:00 AM", "12/12/2012 12:00 AM", 0);
		this.doAssert(countDaysExactly,
					  "12/12/2012 12:00 AM", "12/12/2012 12:01 AM", 0);
		this.doAssert(countDaysExactly,
					  "12/12/2012 01:00 AM", "12/12/2012 01:00 AM", 0);
		this.doAssert(countDaysExactly,
					  "12/12/2012 12:01 AM", "12/12/2012 12:01 AM", 0);
		this.doAssert(countDaysExactly,
					  "12/12/2012 11:59 PM", "12/13/2012 12:01 AM", 1);
		this.doAssert(countDaysExactly,
					  "12/12/2012 12:30 AM", "12/12/2012 12:45 AM", 0);
		this.doAssert(countDaysExactly,
					  "07/09/2009 06:00 PM", "07/31/2009 10:00 AM", 22);
		this.doAssert(countDaysExactly,
					  "07/09/2009 04:00 PM", "07/31/2009 10:00 AM", 22);
		this.doAssert(countDaysExactly,
					  "07/09/2009 04:00 PM", "07/31/2009 07:00 PM", 22);
		this.doAssert(countDaysExactly,
					  "07/09/2009 06:00 PM", "07/31/2009 07:00 PM", 22);
		this.doAssert(countDaysExactly,
					  "02/21/2011 11:00 PM", "05/19/2016 12:00 AM", 1914);
		this.doAssert(countDaysExactly,
				  	  "02/21/2011 12:00 AM", "05/19/2016 11:00 PM", 1914);
		this.doAssert(countDaysExactly,
				  	  "05/19/2011 12:00 AM", "02/21/2016 11:00 PM", 1739);
		this.doAssert(countDaysExactly,
			  	  	  "05/19/2011 11:00 PM", "02/21/2016 12:00 AM", 1739);
	}
	
	// Performs assertions
	private void doAssert(
			final BiFunction<Date, Date, Long> countDaysExactly,
			final String startDateTimeText, final String endDateTimeText,
			final long expectedDayCount) {
		long actualDayCount = countDaysExactly.apply(
					this.dateUtility.parseDateTimeText(startDateTimeText),
					this.dateUtility.parseDateTimeText(endDateTimeText));
		assert actualDayCount == expectedDayCount
			: String.format("Wrong day count for \'%s\' to \'%s\'"
					+ " - %d expected; %d found", startDateTimeText,
					endDateTimeText, expectedDayCount, actualDayCount);
	}
}