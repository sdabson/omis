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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.datatype.DateRange;
import omis.testng.AbstractNonTransactionalTestNGSpringContextTests;
import omis.util.DateUtility;
import omis.util.PropertyValueAsserter;

/**
 * Tests date range instantiation.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"dateRange", "util"})
public class DateRangeTests
		extends AbstractNonTransactionalTestNGSpringContextTests {
	
	/* Helper classes. */
	
	@Autowired
	@Qualifier("dateUtility")
	private DateUtility dateUtility;
	
	/* Unit tests. */

	/** Tests instantiation with dates. */
	public void testWithDates() {
		
		// Arranges dates
		Date startDate = this.dateUtility.parseDateText("12/12/2012");
		Date endDate = this.dateUtility.parseDateText("12/12/2017");
		
		// Action - creates date range
		DateRange dateRange = new DateRange(startDate, endDate);
		
		// Asserts expected date values
		PropertyValueAsserter.create()
			.addExpectedValue("startDate", startDate)
			.addExpectedValue("endDate", endDate)
			.performAssertions(dateRange);
	}
	
	/** Tests instantiation with milliseconds. */
	public void testWithMilliseconds() {
		
		// Arranges dates
		long startDateMillis = this.dateUtility.parseDateText("12/12/2012")
				.getTime();
		long endDateMillis = this.dateUtility.parseDateText("12/12/2017")
				.getTime();
		
		// Action - creates date range
		DateRange dateRange = new DateRange(startDateMillis, endDateMillis);
		
		// Asserts expected millisecond values
		PropertyValueAsserter.create()
			.addExpectedValue("startDate.time", startDateMillis)
			.addExpectedValue("endDate.time", endDateMillis)
			.performAssertions(dateRange);
	}
}