package omis.sentence.service.impl;

import java.util.Date;
import java.util.List;

import omis.conviction.domain.Conviction;
import omis.conviction.domain.component.ConvictionCredit;
import omis.sentence.domain.LegalDispositionCategory;
import omis.sentence.domain.Sentence;
import omis.sentence.domain.SentenceCategory;
import omis.sentence.domain.SentenceLengthClassification;
import omis.sentence.domain.component.SentenceConnection;
import omis.sentence.exception.SentenceExistsException;
import omis.sentence.service.SentenceService;
import omis.sentence.service.delegate.LegalDispositionCategoryDelegate;
import omis.sentence.service.delegate.SentenceCategoryDelegate;
import omis.sentence.service.delegate.SentenceDelegate;
import omis.term.domain.component.Term;

/**
 * Implementation of service for sentences.
 * 
 * @author Joel Norris
 * @author Stephen Abson
 * @version 0.1.2 (Aug 27, 2013)
 * @since OMIS 3.0
 */
public class SentenceServiceImpl implements SentenceService {
	
	private final SentenceDelegate sentenceDelegate;
	
	private final SentenceCategoryDelegate sentenceCategoryDelegate;
	
	private final LegalDispositionCategoryDelegate 
		legalDispositionCategoryDelegate;
	
	/**
	 * Instantiates an implementation of service for sentences with the
	 * specified resources.
	 * 
	 * @param sentenceDelegate data access object for sentences
	 * @param sentenceInstanceFactory instance factory for sentences
	 */
	public SentenceServiceImpl(
			final SentenceDelegate sentenceDelegate,
			final SentenceCategoryDelegate sentenceCategoryDelegate,
			final LegalDispositionCategoryDelegate 
				legalDispositionCategoryDelegate) {
		this.sentenceDelegate = sentenceDelegate;
		this.sentenceCategoryDelegate = sentenceCategoryDelegate;
		this.legalDispositionCategoryDelegate 
			= legalDispositionCategoryDelegate;
	}

	/** {@inheritDoc} */
	@Override
	public Sentence sentence(final Conviction conviction,
			final SentenceConnection connection,
			final Term prisonTerm, final Term probationTerm, 
			final Term deferredTerm, final SentenceCategory category, 
			final SentenceLengthClassification lengthClassification, 
			final LegalDispositionCategory legalDispositionCategory,
			final Date effectiveDate, final Date pronouncementDate, 
			final ConvictionCredit credit, final Date turnSelfInDate) 
					throws SentenceExistsException {
		Integer changeOrder = this.sentenceDelegate.countSentencesByConviction(
				conviction) + 1;
		return this.sentenceDelegate.sentence(conviction, connection, 
				prisonTerm, probationTerm, deferredTerm, category, 
				lengthClassification, legalDispositionCategory, effectiveDate, 
				pronouncementDate, credit, turnSelfInDate, changeOrder);
	}

	/** {@inheritDoc} */
	@Override
	public Sentence updateSentence(final Sentence sentence,
			final SentenceConnection connection,
			final Term prisonTerm, final Term probationTerm, 
			final Term deferredTerm, final SentenceCategory category, 
			final SentenceLengthClassification lengthClassification, 
			final LegalDispositionCategory legalDispositionCategory,
			final Date effectiveDate, final Date pronouncementDate, 
			final ConvictionCredit credit, final Date turnSelfInDate) 
					throws SentenceExistsException {
		return this.sentenceDelegate.updateSentence(sentence, connection, 
				prisonTerm, probationTerm, deferredTerm, category, 
				lengthClassification, legalDispositionCategory, effectiveDate, 
				pronouncementDate, credit, turnSelfInDate);
	}

	/** {@inheritDoc} */
	@Override
	public List<Sentence> findByConviction(final Conviction conviction) {
		return this.sentenceDelegate.findByConviction(conviction);
	}

	/** {@inheritDoc} */
	@Override
	public Sentence findActiveByConviction(final Conviction conviction) {
		return this.sentenceDelegate.findActiveByConviction(conviction);
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
	

}