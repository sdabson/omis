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
 * Tests for calculation of probation dates using standard term calculator
 * delegate.
 *
 * @author Stephen Abson
 * @version 0.1.0 (March 14, 2019)
 * @since OMIS 3.0 
 */
@Test(groups = {"sentence", "calculator"})
public class SentenceCalculatorWithStandardTermCalculatorDelegateCalculateProbationDateTests
		extends AbstractSentenceCalculatorWithStandardTermCalculatorDelegateTests {

	/**
	 * Tests probation discharge date calculation for a single sentence.
	 */
	public void testSingleCalculation() {
		SentenceCalculator calculator = this.getBuilderFactory()
				.createBuilder()
					.withOffenseDate(this.parseDate("04/15/1997"))
					.withSentenceDate(this.parseDate("02/04/1998"))
					.withJailTime(286)
					.withPrisonTerm(new Term(20, 0, 0))
					.withProbationTerm(new Term(20, 0, 0))
					.withSentenceCommencementDate(this.parseDate("04/24/1997"))
					.build();
		Date expectedDate = this.parseDate("04/14/2037");
		Date foundDate = calculator.calculateProbationDischargeDate();
		assert foundDate.equals(expectedDate)
			: String.format(
				"Wrong probation discharge date - %s expected; %s found",
				expectedDate, foundDate);
	}
	
	/**
	 * Tests that the probation discharge date of an initial sentence is
	 * adjusted to account for the prison term of a consecutive sentence.
	 */
	public void testCalculationAdjustedForConsecutive() {
		SentenceCalculator calculator = this.getBuilderFactory()
				.createBuilder()
					.withOffenseDate(this.parseDate("03/22/2004"))
					.withSentenceDate(this.parseDate("05/05/2005"))
					.withJailTime(55)
					.withPrisonTerm(new Term(3, 0, 0))
					.withProbationTerm(new Term(2, 0, 0))
					.withSentenceCommencementDate(this.parseDate("03/11/2005"))
				.nextConsecutive()
					.withOffenseDate(this.parseDate("03/15/2006"))
					.withSentenceDate(this.parseDate("05/17/2006"))
					.withJailTime(64)
					.withPrisonTerm(new Term(5, 0, 0))
					.withSentenceCommencementDate(this.parseDate("01/06/2008"))
				.build();
		Date expectedDate = this.parseDate("01/04/2015");
		Date foundDate = calculator.calculateProbationDischargeDate();
		assert foundDate.equals(expectedDate)
			: String.format(
					"Wrong probation discharge date - %s expected; %s found",
					expectedDate, foundDate);
	}
	
	/**
	 * Tests that the probation discharge date of an initial sentence is
	 * adjusted to account for the prison term of a consecutive sentence. The
	 * commencement date of the consecutive sentence is calculated from the
	 * term if the initial sentence.
	 */
	public void
	testCalculationAdjustedForConsecutiveWithCalculatedCommencementDate() {
		SentenceCalculator calculator = this.getBuilderFactory()
				.createBuilder()
					.withOffenseDate(this.parseDate("03/22/2004"))
					.withSentenceDate(this.parseDate("05/05/2005"))
					.withJailTime(55)
					.withPrisonTerm(new Term(3, 0, 0))
					.withProbationTerm(new Term(2, 0, 0))
					.withSentenceCommencementDate(this.parseDate("03/11/2005"))
				.nextConsecutive()
					.withOffenseDate(this.parseDate("03/15/2006"))
					.withSentenceDate(this.parseDate("05/17/2006"))
					.withJailTime(64)
					.withPrisonTerm(new Term(5, 0, 0))
				.build();
		Date expectedDate = this.parseDate("01/04/2015");
		Date foundDate = calculator.calculateProbationDischargeDate();
		assert foundDate.equals(expectedDate)
			: String.format(
					"Wrong probation discharge date - %s expected; %s found",
					expectedDate, foundDate);
	}
	
	/**
	 * Test calculation of probation discharge date on second in set of six
	 * calculations.
	 */
	// Requires implementation of concurrent calculation - SA
	@Test(enabled = false)
	public void testCalclatorOnSecondOfSix() {
		SentenceCalculator calculator
			= this.getSampleFactory().buildSampleOfSix(0);
		Date expectedDate = this.parseDate("12/16/2013");
		Date foundDate = calculator.calculateProbationDischargeDate();
		assert expectedDate.equals(foundDate) : String.format(
				"Wrong probation discharge date - %s expected; %s found",
				this.formatDate(expectedDate), this.formatDate(foundDate));
	}
}