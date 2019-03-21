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
 * Tests method to determine whether one term is shorter than another.
 *
 * @author Stephen Abson
 * @version 0.1.0 (18 March 2019)
 * @since OMIS 3.0
 */
@Test(groups = {"term", "component"})
public class TermShorterThanTests {

	/**
	 * Tests that term with a smaller year is shorter than another.
	 */
	public void testWithSmallerYear() {
		assert new Term(3, 0, 0).shorterThan(new Term(5, 0, 0))
			: "Term with smaller year must be shorter";
	}
	
	/**
	 * Tests that term without a year is shorter than one with any year.
	 */
	public void testWithoutYearIsShorterThanAny() {
		assert new Term(null, null, null).shorterThan(new Term(5, null, null))
			: "Term without year must be shorter than one with any year";
	}
	
	/**
	 * Tests that term with a year is not shorter than one without a year.
	 */
	public void testWithYearIsNotShorterThanWithoutYear() {
		assert !(new Term(5, null, null)
				.shorterThan(new Term(null, null, null)))
			: "Term with year must not be shorter than one without a year";
	}
	
	/**
	 * Tests that term with smaller year is shorter than another.
	 */
	public void testWithSmallerYearAndNulls() {
		assert new Term(3, null, null).shorterThan(new Term(5, 0, 0))
			: "Term with smaller year must be shorter";
	}
	
	/**
	 * Tests that term with smaller month is shorter when other properties
	 * of both terms are {@code null}.
	 */
	public void testWithSmallerMonthAndNulls() {
		assert new Term(null, 3, null).shorterThan(new Term(null, 5, null))
			: "Term with smaller month must be shorter when other properties"
					+ " are empty";
	}
	
	/**
	 * Tests that term with smaller day is shorter than another when other
	 * properties of both terms are {@code null}.
	 */
	public void testWithSmallerDayAndNulls() {
		assert new Term(null, null, 1).shorterThan(new Term(null, null, 3))
			: "Term with smaller day must be shorter when other properties"
					+ " are empty";
	}
	
	/**
	 * Tests that term with smaller month is shorter than another when
	 * years are equal.
	 */
	public void testWithSameYearSmallerMonth() {
		assert new Term(7, 3, 3).shorterThan(new Term(7, 5, 3))
			: "Term with smaller month must be shorter when years are equal";
	}
	
	/**
	 * Tests that term without month is shorter than another with a month
	 * when other properties are {@code null}.
	 */
	public void testWithNullYearAndNullMonthShorterThanAnyMonth() {
		assert new Term(null, null, null).shorterThan(new Term(null, 3, null))
			: "Term without month must be shorter than one with month when"
				+ " other properties are null";
	}
	
	/**
	 * Tests that term with day is not shorter than term without day when
	 * other properties are {@code null}.
	 */
	public void testWithNullYearAndMonthIsNotSmallerThanWithoutMonth() {
		assert !(new Term(null, 3, null)
				.shorterThan(new Term(null, null, null)))
			: "Term with day must not be shorter than one without when"
				+ " other properties are null";
	}
	
	/**
	 * Tests that term with smaller day is shorter than another when other
	 * properties are the same.
	 */
	public void testWithSameYearAndMonthSmallerDay() {
		assert new Term(7, 5, 1).shorterThan(new Term(7, 5, 3))
			: "Term with smaller day must be shorter when year and month"
				+ " are equal";
	}
	
	/**
	 * Tests that term without a day is shorter than than one with one.
	 * 
	 * <p>All other properties are {@code null}.
	 */
	public void testWithNullYearMonthDayShorterThanAnyDay() {
		assert new Term(null, null, null).shorterThan(new Term(null, null, 1))
			: "Term without day must be shorter than term with day";
	}
	
	/**
	 * Tests that term with a day is not shorter than one without one.
	 * 
	 * <p>All other properties are {@code null}.
	 */
	public void
	testWithNullYearAndMonthAndWithoutDayIsNotShorterThanWithDay() {
		assert !(new Term(null, null, 3)
				.shorterThan(new Term(null, null, null)))
			: "Term with day must not be shorter than one without day";
	}
	
	
	/**
	 * Tests that term is not shorter than an equal term.
	 */
	public void testEqualsIsNotShorter() {
		assert !(new Term(7, 5, 3).shorterThan(new Term(7, 5, 3)))
			: "Equal term must not be shorter";
	}
	
	/**
	 * Tests that term is not shorter than equal term with {@code null} values. 
	 */
	public void testEqualsIsNotShorterWithNulls() {
		assert !(new Term(null, null, null)
				.shorterThan(new Term(null, null, null)))
			: "Equal term with nulls must not be shorter";
	}
}