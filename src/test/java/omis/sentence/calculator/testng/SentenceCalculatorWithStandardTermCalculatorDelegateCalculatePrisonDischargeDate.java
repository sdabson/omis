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
package omis.sentence.calculator.testng;

import java.util.Date;

import org.testng.annotations.Test;

import omis.sentence.calculator.SentenceCalculator;
import omis.term.domain.component.Term;

/**
 * Tests for calculation of prison discharge date using standard term calculator
 * delegate. 
 *
 * @author Stephen Abson
 * @version 0.1.0 (March 14, 2019)
 * @since OMIS 3.0
 */
@Test(groups = {"sentence", "calculator"})
public class SentenceCalculatorWithStandardTermCalculatorDelegateCalculatePrisonDischargeDate
		extends AbstractSentenceCalculatorWithStandardTermCalculatorDelegateTests {

	/** Tests prison term calculation with leap year. */
	public void testWithLeapYear() {
		SentenceCalculator calculator
			= this.getBuilderFactory().createBuilder()
				.withPrisonTerm(new Term(3, 0, 0))
				.withSentenceCommencementDate(this.parseDate("03/11/2005"))
				.build();
		Date actual = calculator.calculatePrisonDischargeDate();
		Date expected = this.parseDate("03/10/2008");
		assert actual.equals(expected) : String.format(
				"Prison discharge date not calculated correctly - %s expected;"
						+ " %s actual",
				this.formatDate(expected),
				this.formatDate(actual));
	}
	
	/** Tests prison term calculation with leap year. */
	public void testWithTwoLeapYears() {
		SentenceCalculator calculator
			= this.getBuilderFactory().createBuilder()
				.withPrisonTerm(new Term(5, 0, 0))
				.withSentenceCommencementDate(this.parseDate("01/06/2008"))
				.build();
		Date actual = calculator.calculatePrisonDischargeDate();
		Date expected = this.parseDate("01/04/2013");
		assert actual.equals(expected) : String.format(
				"Prison discharge date not calculated correctly - %s expected;"
						+ " %s actual",
				this.formatDate(expected),
				this.formatDate(actual));
	}
}