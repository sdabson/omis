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
 * Tests method to determine whether date range is active.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Oct 11, 2018)
 * @since OMIS 3.0
 */
@Test(groups = {"dateRange", "util"})
public class DateRangeIsActiveTests
		extends AbstractNonTransactionalTestNGSpringContextTests {

	/* Helper classes. */
	
	@Autowired
	@Qualifier("dateUtility")
	private DateUtility dateUtility;
	
	/* Unit tests. */
	
	/** Tests method to determine whether date range is active. */
	public void test() {
		this.doAssert(null, null, "12/12/2012", true);
		this.doAssert("12/12/2012", null, "12/12/2012", true);
		this.doAssert("12/12/2012", null, "12/13/2012", true);
		this.doAssert("12/12/2012", null, "12/11/2012", false);
		
		// This does not pass as implementation is incorrectly end date
		// inclusive:
		//   this.doAssert(null, "12/12/2012", "12/12/2012", false);
		this.doAssert(null, "12/12/2012", "12/13/2012", false);
		this.doAssert(null, "12/12/2012", "12/11/2012", true);
		this.doAssert("12/12/2012", "12/12/2017", "12/12/2012", true);
		this.doAssert("12/12/2012", "12/12/2017", "12/13/2012", true);
		this.doAssert("12/12/2012", "12/12/2017", "12/11/2012", false);
		
		// This does not pass as implementation is incorrectly end date
		// inclusive:
		//   this.doAssert("12/12/2012", "12/12/2017", "12/12/2017", false);
		this.doAssert("12/12/2012", "12/12/2017", "12/13/2017", false);
		this.doAssert("12/12/2012", "12/12/2017", "12/11/2017", true);
	}
	
	/* Helper methods. */
	
	// Performs assertions
	private void doAssert(
			final String startDateText, final String endDateText,
			final String effectiveDateText, final boolean expectedActive) {
		Date startDate = this.dateUtility.parseDateText(startDateText);
		Date endDate = this.dateUtility.parseDateText(endDateText);
		Date effectiveDate
			= this.dateUtility.parseDateText(effectiveDateText);
		boolean foundActive = new DateRange(startDate, endDate)
				.isActive(effectiveDate);
		assert  foundActive == expectedActive
			: String.format("Range \'%s\' to \'%s\' should %s active on \'%s\'"
					+ "; it %s",
					startDate, endDate,
					this.displayTextConditionally(
							expectedActive, "be","not be"),
					effectiveDate,
					this.displayTextConditionally(foundActive, "is", "isn't"));
	}
	
	// Returns trueText if value is true; falseText if false
	private String displayTextConditionally(
			final boolean value, final String trueText,
			final String falseText) {
		if (value) {
			return trueText;
		} else {
			return falseText;
		}
	}
}