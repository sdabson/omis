package omis.sentence.service;

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
import omis.term.domain.component.Term;

/**
 * Service for sentences.
 * 
 * @author Joel Norris
 * @author Stephen Abson
 * @version 0.1.2 (Aug 27, 2013)
 * @since OMIS 3.0
 */
public interface SentenceService {
	
	/**
	 * Sentences conviction concurrently.
	 * 
	 * @param conviction conviction
	 * @param connection sentence connection
	 * @param prisonTerm prison term
	 * @param probationTerm probation term
	 * @param deferredTerm deferred term
	 * @param category sentence category
	 * @param lengthClassification sentence length classification
	 * @param legalDispositionCategory legal disposition category
	 * @param effectiveDate effective date
	 * @param pronouncementDate pronouncement date
	 * @param credit conviction credit
	 * @param turnSelfInDate turn self in date
	 * @return sentence
	 * @exception SentenceExistsException thrown when entity exists
	 */
	Sentence sentence(Conviction conviction, SentenceConnection connection, 
			Term prisonTerm, Term probationTerm, Term deferredTerm,
			SentenceCategory category, 
			SentenceLengthClassification lengthClassification,
			LegalDispositionCategory legalDispositionCategory,
			Date effectiveDate, Date pronouncementDate, ConvictionCredit credit, 
			Date turnSelfInDate) throws SentenceExistsException;
	
	/**
	 * Updates the specified sentence.  Circular sentence connections are not 
	 * allowed and will cause an {@code IllegalArgumentException} to be thrown.
	 * 
	 * @param sentence sentence
	 * @param connection sentence connection
	 * @param prisonTerm prison term
	 * @param probationTerm probation term
	 * @param deferredTerm deferred term
	 * @param category sentence category
	 * @param lengthClassification sentence length classification
	 * @param legalDispositionCategory legal disposition category
	 * @param effectiveDate effective date
	 * @param pronouncementDate pronouncement date
	 * @param credit conviction credit
	 * @param turnSelfInDate turn self in date
	 * @return sentence
	 * @exception SentenceExistsException thrown when entity exists
	 */
	Sentence updateSentence(Sentence sentence, 
			SentenceConnection connection, Term prisonTerm, Term probationTerm, 
			Term deferredTerm, SentenceCategory category, 
			SentenceLengthClassification lengthClassification, 
			LegalDispositionCategory legalDispositionCategory,
			Date effectiveDate, Date pronouncementDate, ConvictionCredit credit, 
			Date turnSelfInDate) throws SentenceExistsException;
	
	/**
	 * Returns sentences for conviction.
	 * 
	 * @param conviction conviction
	 * @return sentences
	 */
	List<Sentence> findByConviction(Conviction conviction);
	
	/**
	 * Returns the active sentence for conviction
	 * @param conviction conviction
	 * @return sentence
	 */
	Sentence findActiveByConviction(Conviction conviction);

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
}