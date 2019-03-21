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
package omis.term.domain.component.testng;

import org.testng.annotations.Test;

import omis.term.domain.component.Term;

/**
 * Tests methods to calculate difference between terms.
 *
 * @author Stephen Abson
 * @since OMIS 3.0
 * @version 0.1.0 (18 March, 2019) 
 */
@Test(groups = {"term", "component"})
public class TermDifferenceTests {

	/* Test cases. */
	
	/**
	 * Tests that difference between two terms - the first having all greater
	 * values - returns a difference of all positive values.
	 */
	public void testWithAllGreaterValues() {
		assert this.create(9, 7, 5).difference(this.create(8, 6, 4))
				.equals(this.create(1, 1, 1))
			: "Incorrect reduction of all values";
	}
	
	/**
	 * Tests that difference between two terms - the first having a greater
	 * year but the same month and day - results in a term with a positive
	 * year but zero month and day.
	 */
	public void testWithYearGreaterRestEqual() {
		assert this.create(9, 7, 5).difference(this.create(8, 7, 5))
				.equals(this.create(1,  0,  0))
			: "Incorrect reduction of year";
	}
	
	/**
	 * Tests that difference between two terms - this first having greater
	 * month but the same year and day - results in a term with a positive 
	 * month but zero month and day.
	 */
	public void testWithMonthGreaterRestEqual() {
		assert this.create(9, 7, 5).difference(this.create(9, 6, 5))
				.equals(this.create(0, 1, 0))
			: "Incorrect reduction of months";
	}
	
	/**
	 * Tests that difference between two term - the first having a greater
	 * day but the same year and month - results in a term with a positive
	 * day but zero year and month. 
	 */
	public void testWithDayGreaterRestEqual() {
		assert this.create(9, 7, 5).difference(this.create(9, 7, 4))
				.equals(this.create(0, 0, 1))
			: "Incorrect reduction of days";
	}
	
	/**
	 * Tests that difference with an empty term (all {@code null} values) is the
	 * same as the original (that {@code null}s are treated like zeros).
	 */
	public void testDifferenceWithEmptyTermIsSame() {
		assert this.create(9, 7, 5).difference(this.create(null, null, null))
				.equals(new Term(9, 7, 5))
			: "Difference with empty term must be same";
	}
	
	/* Helper methods. */
	
	// Helper method to create term
	private Term create(
			final Integer years, final Integer months, final Integer days) {
		return new Term(years, months, days);
	}
}