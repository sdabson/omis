package omis.sentence.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.conviction.domain.Conviction;
import omis.conviction.domain.component.ConvictionCredit;
import omis.sentence.domain.component.SentenceConnection;
import omis.term.domain.component.Term;

/**
 * Sentence.
 * 
 * <p>The prison and probation terms of a sentence are calculated from
 * the prison and probation terms the the convictions.
 * 
 * @author Stephen Abson
 * @author Josh Divine
 * @version 0.1.1 (May 9, 2017)
 * @since OMIS 3.0
 */
public interface Sentence
		extends Creatable, Updatable {
	
	/**
	 * Sets the ID.
	 *
	 * @param id ID
	 */
	void setId(Long id);
	
	/**
	 * Returns the ID.
	 *
	 * @return ID
	 */
	Long getId();
	
	/**
	 * Sets the conviction.
	 * 
	 * @param conviction conviction
	 */
	void setConviction(Conviction conviction);
	
	/**
	 * Returns the conviction.
	 * 
	 * @return conviction
	 */
	Conviction getConviction();
	
	/**
	 * Sets the prison term.
	 * 
	 * @param prisonTerm prison term
	 */
	void setPrisonTerm(Term prisonTerm);
	
	/**
	 * Returns the prison term.
	 * 
	 * @return prison term
	 */
	Term getPrisonTerm();
	
	/**
	 * Sets the probation term.
	 * 
	 * @param probationTerm probation term
	 */
	void setProbationTerm(Term probationTerm);
	
	/**
	 * Returns the probation term.
	 * 
	 * @return probation term
	 */
	Term getProbationTerm();
	
	/**
	 * Sets the sentence category.
	 * 
	 * @param sentenceCategory sentence category
	 */
	void setCategory(SentenceCategory sentenceCategory);
	
	/**
	 * Returns the sentence category.
	 * 
	 * @return sentence category
	 */
	SentenceCategory getCategory();
	
	/**
	 * Sets the deferred term.
	 * 
	 * @param deferredTerm deferred term
	 */
	void setDeferredTerm(Term deferredTerm);
	
	/**
	 * Returns the deferred term.
	 * 
	 * @return deferred term
	 */
	Term getDeferredTerm();
	
/**
	 * Sets the conviction credit.
	 * 
	 * @param convictionCredit conviction credit.
	 */
	void setCredit(ConvictionCredit convictionCredit);
	
	/**
	 * Returns the conviction credit.
	 * 
	 * @return conviction credit
	 */
	ConvictionCredit getCredit();
	
	/**
	 * Sets the turn self in date.
	 * 
	 * @param turnSelfInDate turn self in date
	 */
	void setTurnSelfInDate(Date turnSelfInDate);
	
	/**
	 * Returns the turn self in date.
	 * 
	 * @return turn self in date
	 */
	Date getTurnSelfInDate();
	
	/**
	 * Sets the sentence effective date.
	 * 
	 * @param effectiveDate sentence effective date
	 */
	void setEffectiveDate(Date effectiveDate);
	
	/**
	 * Returns the sentence effective date.
	 * 
	 * @return sentence effective date
	 */
	Date getEffectiveDate();
	
	/**
	 * Sets the sentence pronouncement date.
	 * 
	 * @param pronouncementDate sentence pronouncement date
	 */
	void setPronouncementDate(Date pronouncementDate);
	
	/**
	 * Returns the sentence pronouncement date.
	 * 
	 * @return sentence pronouncement date
	 */
	Date getPronouncementDate();
	
	/**
	 * Sets the sentence length classification.
	 * 
	 * @param lengthClassification sentence length classification
	 */
	void setLengthClassification(
			SentenceLengthClassification lengthClassification);
	
	/**
	 * Returns the sentence length classification.
	 * 
	 * @return sentence length classification
	 */
	SentenceLengthClassification getLengthClassification();
	
	/**
	 * Sets the legal disposition category.
	 * 
	 * @param legalDispositionCategory legal disposition category
	 */
	void setLegalDispositionCategory(
			LegalDispositionCategory legalDispositionCategory);
	
	/**
	 * Returns the legal disposition category.
	 * 
	 * @return legal disposition category
	 */
	LegalDispositionCategory getLegalDispositionCategory();
	
	/**
	 * Sets whether the sentence is active.
	 * 
	 * @param active active
	 */
	void setActive(Boolean active);
	
	/**
	 * Returns whether the sentence is active.
	 * 
	 * @return whether the sentence is active
	 */
	Boolean getActive();
	
	/**
	 * Sets the sentence connection.
	 * 
	 * @param connection sentence connection
	 */
	void setConnection(SentenceConnection connection);
	
	/**
	 * Returns the sentence connection.
	 * 
	 * @return sentence connections
	 */
	SentenceConnection getConnection();
	
	/**
	 * Sets the change order.
	 * 
	 * @param changeOrder change order
	 */
	void setChangeOrder(Integer changeOrder);
	
	/**
	 * Returns the change order.
	 * 
	 * @return change order
	 */
	Integer getChangeOrder();
	
	
	/**
	 * Compares {@code this} and {@code obj} for equality.
	 * 
	 * <p>Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * 
	 * @param obj reference object with which to compare {@code this}
	 * @return {@code true} if {@code this} and {@code obj} are equal;
	 * {@code false} otherwise
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the comparison is {@code null} 
	 */
	@Override
	boolean equals(Object obj);
	
	/**
	 * Returns a hash code for {@code this}.
	 * 
	 * <p>Any mandatory property of {@code this} may be used in the hash code.
	 * If a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * 
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null}
	 */
	@Override
	int hashCode();
}