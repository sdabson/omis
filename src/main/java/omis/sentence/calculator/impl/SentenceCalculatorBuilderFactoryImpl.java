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
package omis.sentence.calculator.impl;

import java.util.Objects;

import omis.sentence.calculator.SentenceCalculator;
import omis.sentence.calculator.SentenceCalculator.Builder;
import omis.sentence.calculator.SentenceCalculatorBuilderFactory;
import omis.term.service.delegate.TermCalculatorDelegate;

/**
 * Implementation of factory for builders for sentence calculations.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (March 14, 2019)
 * @since OMIS 3.0
 */
public class SentenceCalculatorBuilderFactoryImpl
		implements SentenceCalculatorBuilderFactory {

	private final TermCalculatorDelegate termCalculatorDelegate;
	
	/**
	 * Instantiates implementation of factory for builders for sentence
	 * calculations.
	 * 
	 * @param termCalculatorDelegate delegate to calculate term
	 */
	public SentenceCalculatorBuilderFactoryImpl(
			final TermCalculatorDelegate termCalculatorDelegate) {
		Objects.requireNonNull(termCalculatorDelegate,
				"Term calculator delegate required");
		this.termCalculatorDelegate = termCalculatorDelegate;
	}
	
	/** {@inheritDoc} */
	@Override
	public Builder createBuilder() {
		return SentenceCalculator.Builder.create(this.termCalculatorDelegate);
	}
}