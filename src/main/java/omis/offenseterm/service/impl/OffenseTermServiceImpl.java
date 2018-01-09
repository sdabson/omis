package omis.offenseterm.service.impl;

import java.util.Date;
import java.util.List;

import omis.conviction.domain.Conviction;
import omis.conviction.domain.OffenseSeverity;
import omis.conviction.domain.component.ConvictionCredit;
import omis.conviction.domain.component.ConvictionFlags;
import omis.conviction.exception.ConvictionExistsException;
import omis.conviction.service.delegate.ConvictionDelegate;
import omis.court.domain.Court;
import omis.court.service.delegate.CourtDelegate;
import omis.courtcase.domain.Charge;
import omis.courtcase.domain.CourtCase;
import omis.courtcase.domain.JurisdictionAuthority;
import omis.courtcase.domain.OffenderDangerDesignator;
import omis.courtcase.domain.component.CourtCaseFlags;
import omis.courtcase.domain.component.CourtCasePersonnel;
import omis.courtcase.exception.CourtCaseExistsException;
import omis.courtcase.service.delegate.ChargeDelegate;
import omis.courtcase.service.delegate.CourtCaseDelegate;
import omis.docket.domain.Docket;
import omis.docket.exception.DocketExistsException;
import omis.docket.service.delegate.DocketDelegate;
import omis.judge.service.delegate.JudgeTermDelegate;
import omis.offense.domain.Offense;
import omis.offense.service.delegate.OffenseDelegate;
import omis.offenseterm.service.OffenseTermService;
import omis.person.domain.Person;
import omis.probationterm.domain.ProbationTerm;
import omis.probationterm.service.delegate.ProbationTermDelegate;
import omis.region.domain.State;
import omis.region.service.delegate.StateDelegate;
import omis.sentence.domain.LegalDispositionCategory;
import omis.sentence.domain.Sentence;
import omis.sentence.domain.SentenceCategory;
import omis.sentence.domain.SentenceLengthClassification;
import omis.sentence.domain.TermRequirement;
import omis.sentence.domain.component.SentenceConnection;
import omis.sentence.exception.ConnectedSentenceExistsException;
import omis.sentence.exception.SentenceExistsException;
import omis.sentence.service.delegate.LegalDispositionCategoryDelegate;
import omis.sentence.service.delegate.SentenceCategoryDelegate;
import omis.sentence.service.delegate.SentenceDelegate;
import omis.term.domain.component.Term;
import omis.term.service.delegate.TermCalculatorDelegate;
import omis.util.DateManipulator;

/**
 * Implementation of service to manage court cases, convictions and sentences.
 *
 * @author Stephen Abson
 * @author Josh Divine
 * @version 0.0.3 (Sept 15, 2017)
 * @since OMIS 3.0
 */
public class OffenseTermServiceImpl
		implements OffenseTermService {
	
	private final CourtDelegate courtDelegate;
	
	private final StateDelegate stateDelegate;
	
	private final OffenseDelegate offenseDelegate;
	
	private final SentenceCategoryDelegate sentenceCategoryDelegate;
	
	private final CourtCaseDelegate courtCaseDelegate;
	
	private final DocketDelegate docketDelegate;
	
	private final ConvictionDelegate convictionDelegate;
	
	private final SentenceDelegate sentenceDelegate;
	
	private final JudgeTermDelegate judgeTermDelegate;
	
	private final LegalDispositionCategoryDelegate 
		legalDispositionCategoryDelegate;
	
	private final ChargeDelegate chargeDelegate;
	
	private final TermCalculatorDelegate termCalculatorDelegate;
	
	private final ProbationTermDelegate probationTermDelegate;
	
	/**
	 * Instantiates implementation of service to manage court cases,
	 * convictions and sentences.
	 * 
	 * @param courtDelegate delegate for courts
	 * @param stateDelegate delegate for States
	 * @param offenseDelegate delegate for offenses
	 * @param sentenceCategoryDelegate delegate for sentence categories
	 * @param courtCaseDelegate delegate for court cases
	 * @param docketDelegate delegate for dockets
	 * @param convictionDelegate delegate for convictions
	 * @param sentenceDelegate delegate for sentences
	 * @param judgeTermDelegate delegate for judge terms
	 * @param termCalculatorDelegate delegate to calculate terms
	 */
	public OffenseTermServiceImpl(
			final CourtDelegate courtDelegate,
			final StateDelegate stateDelegate,
			final OffenseDelegate offenseDelegate,
			final SentenceCategoryDelegate sentenceCategoryDelegate,
			final CourtCaseDelegate courtCaseDelegate,
			final DocketDelegate docketDelegate,
			final ConvictionDelegate convictionDelegate,
			final SentenceDelegate sentenceDelegate,
			final JudgeTermDelegate judgeTermDelegate,
			final LegalDispositionCategoryDelegate 
				legalDispositionCategoryDelegate,
			final ChargeDelegate chargeDelegate,
			final TermCalculatorDelegate termCalculatorDelegate,
			final ProbationTermDelegate probationTermDelegate) {
		this.courtDelegate = courtDelegate;
		this.stateDelegate = stateDelegate;
		this.offenseDelegate = offenseDelegate;
		this.sentenceCategoryDelegate = sentenceCategoryDelegate;
		this.courtCaseDelegate = courtCaseDelegate;
		this.docketDelegate = docketDelegate;
		this.convictionDelegate = convictionDelegate;
		this.sentenceDelegate = sentenceDelegate;
		this.judgeTermDelegate = judgeTermDelegate;
		this.legalDispositionCategoryDelegate 
			= legalDispositionCategoryDelegate;
		this.chargeDelegate = chargeDelegate;
		this.termCalculatorDelegate = termCalculatorDelegate;
		this.probationTermDelegate = probationTermDelegate;
	}

	/** {@inheritDoc} */
	@Override
	public CourtCase create(final Person person, final Court court,
			final String docketValue, final String interStateNumber,
			final State interState, final Date pronouncementDate,
			final Date sentenceReviewDate,
			final JurisdictionAuthority jurisdictionAuthority,
			final OffenderDangerDesignator dangerDesignator,
			final CourtCasePersonnel personnel,
			final CourtCaseFlags flags,
			final String comments)
					throws DocketExistsException {
		
		Docket docket = this.docketDelegate.create(person, court, docketValue);
		try {
			return this.courtCaseDelegate.create(docket, interStateNumber, 
					interState, pronouncementDate, jurisdictionAuthority, 
					sentenceReviewDate, dangerDesignator, personnel, flags, 
					comments);
		} catch (CourtCaseExistsException ccee) {
			throw new RuntimeException("Court case already exists");
		}
	}

	/** {@inheritDoc} */
	@Override
	public CourtCase update(final CourtCase courtCase,
			final String interStateNumber, final State interState,
			final Date pronouncementDate, final Date sentenceReviewDate,
			final JurisdictionAuthority jurisdictionAuthority,
			final OffenderDangerDesignator dangerDesignator,
			final CourtCasePersonnel personnel, final CourtCaseFlags flags, 
			final String comments) {
		return this.courtCaseDelegate.update(courtCase, interStateNumber, 
				interState, pronouncementDate, jurisdictionAuthority, 
				sentenceReviewDate, dangerDesignator, personnel, flags, 
				comments);
	}

	/** {@inheritDoc}  */
	@Override
	public Conviction createConviction(final CourtCase courtCase,
			final Offense offense, final Date date, final Integer count,
			final OffenseSeverity severity, final ConvictionFlags flags)
					throws ConvictionExistsException {
		return this.convictionDelegate.convict(courtCase, offense, severity, 
				date, count, flags);
	}

	/** {@inheritDoc} */
	@Override
	public Conviction updateConviction(
			final Conviction conviction, final Offense offense, final Date date,
			final Integer count, final OffenseSeverity severity,
			final ConvictionFlags flags)
					throws ConvictionExistsException {
		return this.convictionDelegate.update(conviction, severity, offense, 
				date, count, flags);
	}

	/** {@inheritDoc} */
	@Override
	public void removeConviction(final Conviction conviction)
			throws ConnectedSentenceExistsException {
		this.removeConvictionImpl(conviction);
	}

	/** {@inheritDoc} */
	@Override
	public Sentence sentence(final Conviction conviction, 
			final SentenceConnection connection, final SentenceCategory category, 
			final SentenceLengthClassification lengthClassification, 
			final LegalDispositionCategory legalDispositionCategory,
			final Date pronouncementDate, final ConvictionCredit credit, 
			final Date effectiveDate, final Date turnSelfInDate, 
			final Term prisonTerm, final Term probationTerm, 
			final Term deferredTerm) 
					throws SentenceExistsException {
		this.checkTermsValidForSentenceCategory(category, prisonTerm, 
				probationTerm, deferredTerm);
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
			final SentenceCategory category, 
			final SentenceLengthClassification lengthClassification, 
			final LegalDispositionCategory legalDispositionCategory,
			final Date pronouncementDate, final ConvictionCredit credit, 
			final Date effectiveDate, final Date turnSelfInDate, 
			final Term prisonTerm, final Term probationTerm, 
			final Term deferredTerm) 
					throws SentenceExistsException {
		this.checkTermsValidForSentenceCategory(category, prisonTerm, 
				probationTerm, deferredTerm);
		return this.sentenceDelegate.updateSentence(sentence, connection, 
				prisonTerm, probationTerm, deferredTerm, category, 
				lengthClassification, legalDispositionCategory, effectiveDate, 
				pronouncementDate, credit, turnSelfInDate);
	}
	
	/** {@inheritDoc} */
	@Override
	public void removeSentence(final Sentence sentence)
			throws ConnectedSentenceExistsException {
		if (this.sentenceDelegate.countConnected(sentence) > 0) {
			throw new ConnectedSentenceExistsException(
					"Connected sentences exist");
		}
		this.sentenceDelegate.remove(sentence);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<Court> findCourts() {
		return this.courtDelegate.findAllCourts();
	}

	/** {@inheritDoc} */
	@Override
	public List<State> findHomeStates() {
		return this.stateDelegate.findInHomeCountry();
	}

	/** {@inheritDoc} */
	@Override
	public List<Offense> findOffenses(final String query) {
		return this.offenseDelegate.findByQuery(query);
	}

	/** {@inheritDoc} */
	@Override
	public List<SentenceCategory> findSentenceCategories() {
		return this.sentenceCategoryDelegate.findSentenceCategories();
	}

	/** {@inheritDoc} */
	@Override
	public List<Person> findJudges(final String query, final Date date) {
		return this.judgeTermDelegate.findJudgesOnDate(query, date);
	}

	/** {@inheritDoc} */
	@Override
	public List<Conviction> findConvictions(final CourtCase courtCase) {
		return this.convictionDelegate.findByCourtCase(courtCase);
	}

	/** {@inheritDoc} */
	@Override
	public List<Sentence> findSentences(final Conviction conviction) {
		return this.sentenceDelegate.findByConviction(conviction);
	}
	
	/** {@inheritDoc} */
	@Override
	public Sentence findActiveSentence(final Conviction conviction) {
		return this.sentenceDelegate.findActiveByConviction(conviction);
	}

	/** {@inheritDoc} */
	@Override
	public Date calculateSentenceEffectiveDate(
			final Date sentencePronouncementDate, 
			final ConvictionCredit credit) {
		DateManipulator dateManipulator 
			= new DateManipulator(sentencePronouncementDate);
		if (credit.getJailTime() != null) {
			dateManipulator.changeDate(-credit.getJailTime());
		}
		if (credit.getStreetTime() != null) {
			dateManipulator.changeDate(-credit.getStreetTime());
		}
		return dateManipulator.getDate();
	}

	/** {@inheritDoc} */
	@Override
	public Sentence amendSentence(final Sentence sentence, 
			final SentenceConnection connection, 
			final SentenceCategory category,
			final SentenceLengthClassification lengthClassification, 
			final LegalDispositionCategory legalDispositionCategory,
			final Date pronouncementDate, final ConvictionCredit credit, 
			final Date effectiveDate, final Date turnSelfInDate, 
			final Term prisonTerm, final Term probationTerm, 
			final Term deferredTerm) throws SentenceExistsException {
		Integer changeOrder = this.sentenceDelegate.countSentencesByConviction(
				sentence.getConviction()) + 1;
		return this.sentenceDelegate.amendSentence(sentence, connection, 
				category, lengthClassification, legalDispositionCategory, 
				pronouncementDate, credit, effectiveDate, turnSelfInDate, 
				prisonTerm, probationTerm, deferredTerm, changeOrder);
	}

	/** {@inheritDoc} */
	@Override
	public List<Sentence> findActiveSentencesForOtherCourtCases(
			final Person person, final CourtCase courtCase) {
		return this.sentenceDelegate.findActiveSentencesForOtherCourtCases(
				person, courtCase);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<LegalDispositionCategory> findLegalDispositionCategories() {
		return this.legalDispositionCategoryDelegate
				.findLegalDispositionCategories();
	}
	
	/** {@inheritDoc} */
	@Override
	public List<Sentence> findActiveSentences(final Person person) {
		return this.sentenceDelegate.findActiveSentences(person);
	}

	/** {@inheritDoc} */
	@Override
	public void remove(final CourtCase courtCase)
			throws ConnectedSentenceExistsException {
		List<Conviction> convictions = this.findConvictions(courtCase);
		for (Conviction conviction : convictions) {
			this.removeConvictionImpl(conviction);
		}
		List<Charge> charges = this.chargeDelegate.findByCourtCase(courtCase);
		for (Charge charge : charges) {
			this.chargeDelegate.remove(charge);
		}
		List<ProbationTerm> probationTerms = this.probationTermDelegate
				.findByCourtCase(courtCase);
		for (ProbationTerm probationTerm : probationTerms) {
			this.probationTermDelegate.remove(probationTerm);
		}
		this.courtCaseDelegate.remove(courtCase);
	}

	/** {@inheritDoc} */
	@Override
	public Integer calculateTotalDays(final Term term) {
		return this.termCalculatorDelegate.calculateTotalDays(term);
	}
	
	/** {@inheritDoc} */
	@Override
	public CourtCase dismiss(final CourtCase courtCase) {
		return this.courtCaseDelegate.dismiss(courtCase);
	}
	
	/* Helper methods */

	/**
	 * Checks that a term exists for each allowed term in the category, also 
	 * ensures that at least one value in the term is a positive value.
	 * 
	 * @param category sentence category
	 * @param prisonTerm prison term
	 * @param probationTerm probation term
	 * @param deferredTerm deferred term
	 */
	private void checkTermsValidForSentenceCategory(
			final SentenceCategory category, final Term prisonTerm,
			final Term probationTerm, final Term deferredTerm) {
		// Check to ensure the category's specified terms have at least one
		// positive value
		if (category != null) {
			if (TermRequirement.REQUIRED.equals(
					category.getPrisonRequirement())) {
				if (prisonTerm == null) {
					throw new IllegalArgumentException("Prison term required");
				}
				if (!((prisonTerm.getYears() != null 
						&& prisonTerm.getYears() > 0) 
						|| (prisonTerm.getMonths() != null 
						&& prisonTerm.getMonths() > 0) 
						|| (prisonTerm.getDays() != null 
						&& prisonTerm.getDays() > 0))) {
					throw new IllegalArgumentException("Prison term must "
							+ "contain one non-null, greater than zero value"); 
				}
			}
			if (TermRequirement.REQUIRED.equals(
					category.getProbationRequirement())) {
				if (probationTerm == null) {
					throw new IllegalArgumentException("Probation term "
							+ "required");
				}
				if (!((probationTerm.getYears() != null 
						&& probationTerm.getYears() > 0) 
						|| (probationTerm.getMonths() != null 
						&& probationTerm.getMonths() > 0) 
						|| (probationTerm.getDays() != null 
						&& probationTerm.getDays() > 0))) {
					throw new IllegalArgumentException("Probation term must "
							+ "contain one non-null, greater than zero value"); 
				}
			}
			if (TermRequirement.REQUIRED.equals(
					category.getDeferredRequirement())) {
				if (deferredTerm == null) {
					throw new IllegalArgumentException("Deferred term "
							+ "required");
				}
				if (!((deferredTerm.getYears() != null 
						&& deferredTerm.getYears() > 0) 
						|| (deferredTerm.getMonths() != null 
						&& deferredTerm.getMonths() > 0) 
						|| (deferredTerm.getDays() != null 
						&& deferredTerm.getDays() > 0))) {
					throw new IllegalArgumentException("Deferred term must "
							+ "contain one non-null, greater than zero value"); 
				}
			}
		}
	}
	
	// Removes conviction and all associated sentences
	// Checks for connected sentences - throws ConnectedSentenceExistsException
	// if connected sentences exist
	private void removeConvictionImpl(final Conviction conviction)
			throws ConnectedSentenceExistsException {
		Sentence sentence = this.sentenceDelegate
				.findActiveByConviction(conviction);
		if (this.sentenceDelegate.countConnected(sentence) > 0) {
			throw new ConnectedSentenceExistsException(
					"Connected sentences exist");
		}
		this.sentenceDelegate.removeByConviction(conviction);
		this.convictionDelegate.remove(conviction);
	}
}