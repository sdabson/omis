package omis.offenseterm.service;

import java.util.Date;
import java.util.List;

import omis.conviction.domain.Conviction;
import omis.conviction.domain.component.ConvictionCredit;
import omis.exception.DuplicateEntityFoundException;
import omis.sentence.domain.LegalDispositionCategory;
import omis.sentence.domain.Sentence;
import omis.sentence.domain.SentenceCategory;
import omis.sentence.domain.SentenceLengthClassification;
import omis.sentence.exception.SentenceExistsException;
import omis.term.domain.component.Term;

/**
 * Service for historical offense terms.
 *
 * <p>Historical offense terms are represented by inactive sentences.
 * 
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public interface HistoricalOffenseTermService {
	
	/**
	 * Creates historical offense term.
	 * 
	 * @param conviction conviction
	 * @param category category
	 * @param lengthClassification length classification
	 * @param legalDispositionCategory legal disposition category
	 * @param pronouncementDate pronouncement date
	 * @param credit credit
	 * @param effectiveDate effective date
	 * @param turnSelfInDate turn self in date
	 * @param prisonTerm prison term
	 * @param probationTerm probation term
	 * @param deferredTerm deferred term
	 * @return created inactive sentence representing historical offense term
	 * @throws DuplicateEntityFoundException if sentence exists
	 */
	Sentence create(Conviction conviction, SentenceCategory category,
			SentenceLengthClassification lengthClassification,
			LegalDispositionCategory legalDispositionCategory,
			Date pronouncementDate, ConvictionCredit credit,
			Date effectiveDate, Date turnSelfInDate, Term prisonTerm,
			Term probationTerm, Term deferredTerm)
				throws SentenceExistsException;
	
	/**
	 * Updates historical offense term.
	 * 
	 * @param sentence sentence
	 * @param category category
	 * @param lengthClassification length classification
	 * @param legalDispositionCategory legal disposition category
	 * @param pronouncementDate pronouncement date
	 * @param credit credit
	 * @param effectiveDate effective date
	 * @param turnSelfInDate turn self in date
	 * @param prisonTerm prison term
	 * @param probationTerm probation term
	 * @param deferredTerm deferred term
	 * @return updated inactive sentence representing historical offense term
	 * @throws DuplicateEntityFoundException if sentence exists
	 */
	Sentence update(Sentence sentence, SentenceCategory category,
			SentenceLengthClassification lengthClassification,
			LegalDispositionCategory legalDispositionCategory,
			Date pronouncementDate, ConvictionCredit credit,
			Date effectiveDate, Date turnSelfInDate, Term prisonTerm,
			Term probationTerm, Term deferredTerm)
				throws SentenceExistsException;
	
	/**
	 * Returns sentence categories.
	 * 
	 * @return sentence categories
	 */
	List<SentenceCategory> findSentenceCategories();
	
	/**
	 * Returns legal disposition categories.
	 * 
	 * @return legal disposition categories
	 */
	List<LegalDispositionCategory> findLegalDispositionCategories();

	/**
	 * Removes historical offense term.
	 * 
	 * <p>Historical offense terms are represented by inactive sentences.
	 * 
	 * <p>Operation is prevented with an {@code IllegalArgumentException} if
	 * sentence is active.
	 * 
	 * @param sentence sentence to remove - must be inactive
	 */
	void remove(Sentence sentence);

	/**
	 * Calculates total days of term.
	 * 
	 * @param term term
	 * @return total days of term
	 */
	Integer calculateTotalDays(Term term);

	/**
	 * Calculates sentence effective date.
	 * 
	 * @param pronouncementDate pronouncement date
	 * @param convictionCredit conviction credit
	 * @return sentence effective date
	 */
	Date calculateSentenceEffectiveDate(
			final Date pronouncementDate, 
			final ConvictionCredit convictionCredit);
}