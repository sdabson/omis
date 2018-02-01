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
package omis.offenseterm.service.impl;

import java.util.Date;
import java.util.List;

import omis.conviction.domain.Conviction;
import omis.conviction.domain.component.ConvictionCredit;
import omis.offenseterm.service.HistoricalOffenseTermService;
import omis.sentence.domain.LegalDispositionCategory;
import omis.sentence.domain.Sentence;
import omis.sentence.domain.SentenceCategory;
import omis.sentence.domain.SentenceLengthClassification;
import omis.sentence.exception.SentenceExistsException;
import omis.sentence.service.delegate.LegalDispositionCategoryDelegate;
import omis.sentence.service.delegate.SentenceCategoryDelegate;
import omis.sentence.service.delegate.SentenceDelegate;
import omis.term.domain.component.Term;
import omis.term.service.delegate.TermCalculatorDelegate;
import omis.util.DateManipulator;

/**
 * Implementation of service for historical offense terms.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class HistoricalOffenseTermServiceImpl
		implements HistoricalOffenseTermService {

	private final SentenceDelegate sentenceDelegate;
	
	private final LegalDispositionCategoryDelegate
	legalDispositionCategoryDelegate;
	
	private final SentenceCategoryDelegate sentenceCategoryDelegate;
	
	private final TermCalculatorDelegate termCalculatorDelegate;
	
	/**
	 * Instantiates implementation of service for historical offense terms.
	 * 
	 * @param sentenceDelegate sentence delegate
	 * @param legalDispositionCategoryDelegate legal disposition category
	 * delegate
	 * @param sentenceCategoryDelegate sentence category delegate
	 * @param termCalculatorDelegate term calculator delegate
	 */
	public HistoricalOffenseTermServiceImpl(
			final SentenceDelegate sentenceDelegate,
			final LegalDispositionCategoryDelegate
			legalDispositionCategoryDelegate,
			final SentenceCategoryDelegate sentenceCategoryDelegate,
			final TermCalculatorDelegate termCalculatorDelegate) {
		this.sentenceDelegate = sentenceDelegate;
		this.legalDispositionCategoryDelegate
			= legalDispositionCategoryDelegate;
		this.sentenceCategoryDelegate = sentenceCategoryDelegate;
		this.termCalculatorDelegate = termCalculatorDelegate;
	}

	/** {@inheritDoc} */
	@Override
	public Sentence create(final Conviction conviction,
			final SentenceCategory category,
			final SentenceLengthClassification lengthClassification,
			final LegalDispositionCategory legalDispositionCategory,
			final Date pronouncementDate, final ConvictionCredit credit,
			final Date effectiveDate, final Date turnSelfInDate,
			final Term prisonTerm, final Term probationTerm,
			final Term deferredTerm) throws SentenceExistsException {
		int nextChangeOrder = this.sentenceDelegate
				.countSentencesByConviction(conviction) + 1;
		return this.sentenceDelegate.create(
				conviction, null, prisonTerm, probationTerm, deferredTerm,
				category, lengthClassification, legalDispositionCategory,
				effectiveDate, pronouncementDate, credit, turnSelfInDate, false,
				nextChangeOrder);
	}

	/** {@inheritDoc} */
	@Override
	public Sentence update(final Sentence sentence,
			final SentenceCategory category,
			final SentenceLengthClassification lengthClassification,
			final LegalDispositionCategory legalDispositionCategory,
			final Date pronouncementDate, final ConvictionCredit credit,
			final Date effectiveDate, final Date turnSelfInDate,
			final Term prisonTerm, final Term probationTerm,
			final Term deferredTerm) throws SentenceExistsException {
		return this.sentenceDelegate.update(sentence, sentence.getConviction(),
				null, prisonTerm, probationTerm, deferredTerm, category,
				lengthClassification, legalDispositionCategory, effectiveDate,
				pronouncementDate, credit, turnSelfInDate, false,
				sentence.getChangeOrder());
	}
	
	/** {@inheritDoc} */
	@Override
	public void remove(final Sentence sentence) {
		if (sentence.getActive()) {
			throw new IllegalArgumentException("Sentence is active");
		}
		this.sentenceDelegate.remove(sentence);
	}

	/** {@inheritDoc} */
	@Override
	public List<SentenceCategory> findSentenceCategories() {
		return this.sentenceCategoryDelegate.findSentenceCategories();
	}

	/** {@inheritDoc} */
	@Override
	public List<LegalDispositionCategory> findLegalDispositionCategories() {
		return this.legalDispositionCategoryDelegate
				.findLegalDispositionCategories();
	}

	/** {@inheritDoc} */
	@Override
	public Integer calculateTotalDays(final Term term) {
		return this.termCalculatorDelegate.calculateTotalDays(term);
	}

	/** {@inheritDoc} */
	@Override
	public Date calculateSentenceEffectiveDate(
			final Date pronouncementDate,
			final ConvictionCredit convictionCredit) {
		DateManipulator dateManipulator 
			= new DateManipulator(pronouncementDate);
		if (convictionCredit.getJailTime() != null) {
			dateManipulator.changeDate(-convictionCredit.getJailTime());
		}
		if (convictionCredit.getStreetTime() != null) {
			dateManipulator.changeDate(-convictionCredit.getStreetTime());
		}
		return dateManipulator.getDate();
	}
}