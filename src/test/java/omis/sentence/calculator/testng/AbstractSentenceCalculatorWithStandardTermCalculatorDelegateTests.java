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

import omis.sentence.calculator.SentenceCalculatorBuilderFactory;
import omis.sentence.calculator.impl.SentenceCalculatorBuilderFactoryImpl;
import omis.sentence.calculator.util.test.SentenceCalculatorSampleFactory;
import omis.term.service.delegate.TermCalculatorDelegateStandard;

/**
 * Abstract class for tests for sentence calculators with standard term
 * calculator delegate.
 * 
 * <p>This class is package private to prevent use outside of this testing
 * module.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (March 14, 2019)
 * @since OMIS 3.0 
 */
abstract class AbstractSentenceCalculatorWithStandardTermCalculatorDelegateTests
		extends AbstractSentenceCalculatorTests {
	
	/**
	 * Returns builder factory for standard calculations.
	 * 
	 * @return builder factory for standard calculations
	 */
	protected SentenceCalculatorBuilderFactory getBuilderFactory() {
		return new SentenceCalculatorBuilderFactoryImpl(
				new TermCalculatorDelegateStandard());
	}
	
	/**
	 * Returns sample factory for standard calculations.
	 * 
	 * @return sample factory for standard calculations
	 */
	protected SentenceCalculatorSampleFactory getSampleFactory() {
		return new SentenceCalculatorSampleFactory(this.getBuilderFactory());
	}
}