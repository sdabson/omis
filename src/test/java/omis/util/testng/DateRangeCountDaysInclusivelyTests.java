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

/**
 * Tests date range utility inclusive day count.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"dateRange", "util"})
public class DateRangeCountDaysInclusivelyTests
		extends AbstractNonTransactionalTestNGSpringContextTests {
	
	/* Helper classes. */
	
	@Autowired
	@Qualifier("dateUtility")
	private DateUtility dateUtility;
	
	/* Unit tests. */

	/**
	 * Tests that date range starting and ending on same date counts as 1 day.
	 */
	public void testSameDateTime() {
		Date effectiveDate = this.dateUtility.parseDateText("12/12/2012");
		long expectedDayCount = 1;
		long dayCount = DateRange
				.countDaysInclusively(effectiveDate, effectiveDate);
		assert dayCount == expectedDayCount : String.format(
						"Inclusive day count of date range counts wrongly"
						+ " - %d expected; %d counted",
						expectedDayCount, dayCount);
	}
	
	/**
	 * Tests that date range starting and ending a day apart counts as 2 days.
	 */
	public void testOneDayApart() {
		Date startDate = this.dateUtility.parseDateText("12/12/2012");
		Date endDate = this.dateUtility.parseDateText("12/13/2012");
		long expectedDayCount = 2;
		long dayCount = DateRange
				.countDaysInclusively(startDate, endDate);
		assert dayCount == expectedDayCount : String.format(
						"Inclusive day count of date range counts wrongly"
						+ " - %d expected; %d counted",
						expectedDayCount, dayCount);
	}
}