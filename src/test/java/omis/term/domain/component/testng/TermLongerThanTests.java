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
 * Tests method to determine whether one term is longer than another.
 *
 * @author Stephen Abson
 * @version 0.1.0 (18 March 2019)
 * @since OMIS 3.0
 */
@Test(groups = {"term", "component"})
public class TermLongerThanTests {

	/**
	 * Tests that term with a greater year is longer than another.
	 */
	public void testWithGreaterYear() {
		assert new Term(5, 0, 0).longerThan(new Term(3, 0, 0))
			: "Term with greater year must be longer";
	}
	
	/**
	 * Tests that term with a year is longer than one without a year.
	 */
	public void testWithGreaterYearThanNull() {
		assert new Term(5, null, null).longerThan(new Term(null, null, null))
			: "Term with greater year must be longer than term without year";
	}
	
	/**
	 * Tests that term without a year is not greater than one with one.
	 */
	public void testWithoutYearIsNotGreaterThanWithYear() {
		assert !(new Term(null, null, null).longerThan(new Term(5, null, null)))
			: "Term without year must not be longer than one with year";
	}
	
	/**
	 * Tests that term with a greater year is longer than another when other
	 * properties are {@code null}.
	 */
	public void testWithGreaterYearAndNulls() {
		assert new Term(5, null, null).longerThan(new Term(3, null, null))
			: "Term with greater year must be longer";
	}
	
	/**
	 * Tests that term with a greater month is longer than another when other
	 * properties are {@code null}.
	 */
	public void testWithGreaterMonthAndNulls() {
		assert new Term(null, 5, null).longerThan(new Term(null, 3, null))
			: "Term with greater month must be longer when other properties"
					+ " are empty";
	}
	
	/**
	 * Tests that term with a greater day is longer than another when other
	 * properties of both terms are {@code null}.
	 */
	public void testWithGreaterDayAndNulls() {
		assert new Term(null, null, 5).longerThan(new Term(null, null, 3))
			: "Term with greater day must be longer when other properties are"
					+ " empty";
	}
	
	/**
	 * Tests that term with greater month is longer than another when year
	 * are equal.
	 */
	public void testWithSameYearGreaterMonth() {
		assert new Term(5, 3, 0).longerThan(new Term(5, 1, 0))
			: "Term with greater month must be longer when years are equal";
	}
	
	/**
	 * Tests that term with a month is longer than one without a month.
	 * 
	 * <p>All other properties are null.
	 */
	public void testWithNullYearAndGreaterMonthThanNull() {
		assert new Term(null, 3, null).longerThan(new Term(null, null, null))
			: "Term with greater month must be longer than term without month";
	}
	
	/**
	 * Tests that term without a month is not greater than one with one.
	 * 
	 * <p>All other properties are {@code null}.
	 */
	public void testWithNullYearAndWithoutMonthIsNotGreaterThanWithMonth() {
		assert !(new Term(null, null, null).longerThan(new Term(null, 3, null)))
			: "Term without month must not be longer than one with month";
	}
	
	/**
	 * Tests that term with greater day is longer than another when year and
	 * month are equal.
	 */
	public void testWithSameYearAndMonthGreaterDay() {
		assert new Term(7, 5, 3).longerThan(new Term(7, 5, 1))
			: "Term with greater day must be longer when year and month"
					+ " are equal";
	}
	
	
	/**
	 * Tests that term with a day is longer than one without a day.
	 * 
	 * <p>All other properties are {@code null}.
	 */
	public void testWithNullYearAndMonthAndGreaterDayThanNull() {
		assert new Term(null, null, 3).longerThan(new Term(null, null, null))
			: "Term with greater day must be longer than term without day";
	}
	
	/**
	 * Tests that term without a day is not greater than one with one.
	 * 
	 * <p>All other properties are {@code null}.
	 */
	public void
	testWithNullYearAndMonthAndWithoutDayIsNotGreaterThanWithDay() {
		assert !(new Term(null, null, null)
				.longerThan(new Term(null, null, 3)))
			: "Term without day must not be longer than one with day";
	}
	
	
	/**
	 * Tests that equal term is not longer.
	 */
	public void testEqualIsNotLonger() {
		assert !(new Term(7, 5, 3).longerThan(new Term(7, 5, 3)))
			: "Equal term must not be longer";
	}
	
	/**
	 * Tests that equal term with {@null} properties is not longer.
	 */
	public void testEqualsIsNotLongerWithNulls() {
		assert !(new Term(null, null, null)
				.longerThan(new Term(null, null, null)))
			: "Equal term with nulls must not be longer";
	}
}