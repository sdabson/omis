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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.BiFunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.datatype.DateRange;
import omis.testng.AbstractNonTransactionalTestNGSpringContextTests;
import omis.util.DateUtility;

/**
 * Tests for counting full years.
 * 
 * <p>Tests both static and instance methods - results must be identical.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Oct 10, 2018)
 * @since OMIS 3.0
 */
@Test(groups = {"dateRange", "util"})
public class DateRangeCountYearsExactlyTests
		extends AbstractNonTransactionalTestNGSpringContextTests {
	
	/* Helper classes. */
	
	@Autowired
	@Qualifier("dateUtility")
	private DateUtility dateUtility;
	
	/* Test methods. */
	
	/** Tests instance method. */
	public void test() {
		this.performTests((startDate, endDate) ->
			new DateRange(startDate, endDate).countYearsExactly());
	}
	
	/** Tests static method. */
	public void testStatic() {
		this.performTests(DateRange::countYearsExactly);
	}
	
	/**
	 * Tests static method to make sure that  {@code IllegalArgumentException{
	 * is thrown if start date is greated than end date.
	 * 
	 * @throws IllegalArgumentException if start date is greater than end date
	 * - asserted
	 */
	@Test(expectedExceptions = {IllegalArgumentException.class})
	public void testStaticIllegalArgumentException() {
		DateRange.countYearsExactly(
				this.dateUtility.parseDateText("12/12/2017"),
				this.dateUtility.parseDateText("12/12/2012"));
	}
	
	/* Helper methods. */
	
	// Method to perform tests
	private void performTests(
			final BiFunction<Date, Date, Long> countYearsExactly) {
		this.doAsserts(countYearsExactly,
				"12/12/2012 12:00:00 000 AM", "12/12/2017 12:00:00 000 AM", 5);
		this.doAsserts(countYearsExactly,
				"12/12/2012 12:00:00 000 AM", "12/13/2017 12:00:00 000 AM", 5);
		this.doAsserts(countYearsExactly,
				"11/12/2012 12:00:00 000 AM", "12/12/2017 12:00:00 000 AM", 5);
		this.doAsserts(countYearsExactly,
				"12/12/2012 11:00:00 000 AM", "12/12/2017 11:00:00 000 PM", 5);
		this.doAsserts(countYearsExactly,
				"12/12/2012 11:00:00 000 AM", "12/12/2017 11:01:00 000 AM", 5);
		this.doAsserts(countYearsExactly,
				"12/12/2012 11:00:00 000 AM", "12/12/2017 11:00:01 000 AM", 5);
		this.doAsserts(countYearsExactly,
				"12/12/2012 11:00:00 000 AM", "12/12/2017 11:00:00 001 AM", 5);
		this.doAsserts(countYearsExactly,
				"12/12/2012 11:01:00 000 AM", "12/12/2017 11:00:00 000 AM", 4);
		this.doAsserts(countYearsExactly,
				"12/12/2012 11:00:01 000 AM", "12/12/2017 11:00:00 000 AM", 4);
		this.doAsserts(countYearsExactly,
				"12/12/2012 11:00:00 001 AM", "12/12/2017 11:00:00 000 AM", 4);
		this.doAsserts(countYearsExactly,
				"12/12/2012 12:00:00 000 AM", "12/11/2017 11:59:59 999 AM", 4);
		this.doAsserts(countYearsExactly,
				"12/12/2012 11:00:00 000 AM", "12/12/2017 10:59:59 999 AM", 4);
		this.doAsserts(countYearsExactly,
				"12/12/2012 11:00:00 000 AM", "12/12/2017 10:00:00 999 AM", 4);
		this.doAsserts(countYearsExactly,
				"12/12/2012 11:00:00 000 AM", "12/12/2017 10:00:59 000 AM", 4);
		this.doAsserts(countYearsExactly,
				"12/12/2012 11:00:00 000 AM", "12/12/2017 10:59:00 000 AM", 4);
		this.doAsserts(countYearsExactly,
				"12/12/2012 11:00:00 000 AM", "12/12/2017 10:00:59 999 AM", 4);
		this.doAsserts(countYearsExactly,
				"12/12/2012 11:00:00 000 AM", "12/12/2017 10:59:00 999 AM", 4);
		this.doAsserts(countYearsExactly,
				"12/12/2012 11:00:00 000 AM", "12/12/2017 10:59:59 000 AM", 4);
		this.doAsserts(countYearsExactly,
				"12/12/2012 11:00:00 000 PM", "12/12/2017 11:00:00 000 AM", 4);
		this.doAsserts(countYearsExactly,
				"12/12/2012 11:00:00 000 AM", "11/12/2017 11:00:00 000 AM", 4);
	}
	
	// Performs assertions
	private void doAsserts(final BiFunction<Date, Date, Long> countYearsExactly,
			final String startDateTimeString, final String endDateTimeString,
			final long expectedCount) {
		Date startDateTime = this.parseDateTimeText(startDateTimeString);
		Date endDateTime = this.parseDateTimeText(endDateTimeString);
		long actualCount = countYearsExactly.apply(startDateTime, endDateTime);
		assert actualCount == expectedCount
				: String.format("Wrong year count for \'%s\' to \'%s\'"
						+ " - %d expected; %d found",
					startDateTime, endDateTime, expectedCount, actualCount);
	}
	
	// Parses date/time text
	private Date parseDateTimeText(final String dateTimeText) {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy h:mm:s S a");
		try {
			return dateFormat.parse(dateTimeText);
		} catch (ParseException e) {
			throw new IllegalArgumentException(
					String.format("Wrong format - \'%s\'", dateTimeText), e);
		}
	}
}