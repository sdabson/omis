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

import org.testng.annotations.Test;

import omis.sentence.calculator.SentenceCalculator;
import omis.term.domain.component.Term;

/**
 * Tests sentence calculator with standard term calculator delegate.
 * 
 * <p>The test cases in this test class should be placed in a class for each
 * target operation. 
 * 
 * @author Stephen Abson
 * @version 0.1.0 (March 1, 2019)
 * @since OMIS 3.0
 */
@Test(groups = {"sentence", "calculator"})
public class SentenceCalculatorWithStandardTermCalculatorDelegateTests
		extends AbstractSentenceCalculatorWithStandardTermCalculatorDelegateTests {
	
	/** Tests prison days calculation. */
	public void testPrisonDaysCalculation() {
		SentenceCalculator calculator
			= this.getBuilderFactory().createBuilder()
				.withPrisonTerm(new Term(3, 0, 0))
				.build();
		int actual = calculator.calculatePrisonDays();
		int expected = 1095;
		assert actual == expected : String.format(
				"Prison days not calculated correctly - %d expected; %d found",
				expected, actual);
	}
	
	/** Tests probation days calculation. */
	public void testProbationDaysCalculation() {
		SentenceCalculator calculator
			= this.getBuilderFactory().createBuilder()
				.withProbationTerm(new Term(2, 0, 0))
				.build();
		int actual = calculator.calculateProbationDays();
		int expected = 730;
		assert actual == expected : String.format(
			"Probation days not calculated correctly - %d expected; %d found",
			expected, actual);
	}
}