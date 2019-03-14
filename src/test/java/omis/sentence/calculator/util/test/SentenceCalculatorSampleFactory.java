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
package omis.sentence.calculator.util.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import omis.sentence.calculator.SentenceCalculator;
import omis.sentence.calculator.SentenceCalculatorBuilderFactory;
import omis.term.domain.component.Term;

/**
 * Factory for sample sentence calculators.
 * 
 * <p>Provides samples for testing.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (March 14, 2019)
 * @since OMIS 3.0
 */
public class SentenceCalculatorSampleFactory {
	
	private final SentenceCalculatorBuilderFactory builderFactory;
	
	/**
	 * Instantiates sentence calculator sample factory.
	 * 
	 * @param builderFactory sentence calculator sample factory
	 */
	public SentenceCalculatorSampleFactory(
			final SentenceCalculatorBuilderFactory builderFactory) {
		this.builderFactory = builderFactory;
	}
	
	/**
	 * Returns sample of six sentences in a calculation.
	 * 
	 * @param returnAt calculator to return at - between 0 and 5
	 * @return sample of six sentences at index {@returnAt}
	 */
	public SentenceCalculator buildSampleOfSix(final int returnAt) {
		
		// Keep creation of builder for each calculator broken down so at to
		// allow commenting and step through - SA
		SentenceCalculator.Builder firstBuilder
			= this.builderFactory.createBuilder()
					.withOffenseDate(this.parseDate("08/04/1984"))
					.withSentenceDate(this.parseDate("12/18/1986"))
					.withJailTime(51)
					.withPrisonTerm(new Term(10, 0, 0))
					.withSentenceCommencementDate(this.parseDate("10/28/1986"));
		SentenceCalculator.Builder secondBuilder
			= firstBuilder.nextConcurrent()
					.withOffenseDate(this.parseDate("01/22/1985"))
					.withSentenceDate(this.parseDate("12/15/1986"))
					.withJailTime(3)
					.withPrisonTerm(new Term(9, 0, 0))
					.withProbationTerm(new Term(21, 0, 0));
		
		// A new way of adding TSID calculators might be needed - its unclear
		// whether calculations with TSIDs are consecutive or concurrent. If
		// they are needed, a nextWithTurnSelfInDate(Date) method might be
		SentenceCalculator.Builder thirdBuilder
			= secondBuilder.nextConsecutive()
					.withOffenseDate(this.parseDate("05/26/1987"))
					.withSentenceDate(this.parseDate("02/19/1988"))
					.withTurnSelfInDate(this.parseDate("07/27/1993"))
					.withPrisonTerm(new Term(10, 0, 0));
		SentenceCalculator.Builder fourthBuilder
			= thirdBuilder.nextConsecutive()
					.withOffenseDate(this.parseDate("06/05/1990"))
					.withSentenceDate(this.parseDate("03/18/1991"))
					.withJailTime(331)
					.withPrisonTerm(new Term(10, 0, 0))
					.withProbationTerm(new Term(5, 0, 0));
		SentenceCalculator.Builder fifthBuilder
			= fourthBuilder.nextConcurrent()
					.withOffenseDate(this.parseDate("05/11/1995"))
					.withSentenceDate(this.parseDate("03/07/1996"))
					.withPrisonTerm(new Term(40, 0, 0))
					.withProbationTerm(new Term(5, 0, 0));
		SentenceCalculator.Builder sixthBuilder
			= fifthBuilder.nextConsecutive()
					.withOffenseDate(this.parseDate("05/08/1995"))
					.withSentenceDate(this.parseDate("03/07/1996"))
					.withJailTime(13)
					.withPrisonTerm(new Term(3, 0, 0));
		SentenceCalculator calculator = sixthBuilder.build();
		for (int i = 0; i < returnAt; i++) {
			calculator = calculator.next();
		}
		return calculator;
	}
	
	// Parses date text
	private Date parseDate(final String text) {
		try {
			return new SimpleDateFormat("MM/dd/yyyy").parse(text);
		} catch (ParseException e) {
			throw new IllegalArgumentException(text, e);
		}
	}
}