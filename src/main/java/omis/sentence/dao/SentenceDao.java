package omis.sentence.dao;

import java.util.Date;
import java.util.List;

import omis.conviction.domain.Conviction;
import omis.courtcase.domain.CourtCase;
import omis.dao.GenericDao;
import omis.offender.domain.Offender;
import omis.person.domain.Person;
import omis.sentence.domain.Sentence;

/**
 * Sentence DAO.
 * 
 * @author Joel Norris
 * @Author Stephen Abson
 * @version 0.1.1 (Aug 12, 2013)
 * @since OMIS 3.0
 */
public interface SentenceDao extends GenericDao<Sentence> {

	/**
	 * Returns the sentences for the conviction.
	 * 
	 * @param conviction conviction the sentence of which to return
	 * @return sentences for conviction; {@code null} if no such sentences
	 * exists
	 */
	List<Sentence> findByConviction(Conviction conviction);
	
	/**
	 * Returns the active sentence for the conviction.
	 * 
	 * @param conviction conviction
	 * @return active sentence
	 */
	Sentence findActiveByConviction(Conviction conviction);
	
	/**
	 * Returns sentences by offender.
	 * 
	 * @param offender offender the sentences of which to return
	 * @return sentences by offender
	 */
	List<Sentence> findByOffender(Offender offender);
	
	/**
	 * Returns all active sentences except for the specified court case.
	 * 
	 * @param person person
	 * @param courtCase excluded court case
	 * @return active sentences
	 */
	List<Sentence> findActiveSentencesForOtherCourtCases(Person person, 
			CourtCase courtCase);
	
	/**
	 * Returns the sentence matching the specified parameters.
	 * 
	 * @param conviction conviction
	 * @param effectiveDate effective date
	 * @return sentence
	 */
	Sentence find(Conviction conviction, Date effectiveDate);
	
	/**
	 * Returns the sentence matching the specified parameters excluding the 
	 * specified sentence.
	 * 
	 * @param sentence excluded sentence
	 * @param conviction conviction
	 * @param effectiveDate effective date
	 * @return sentence
	 */
	Sentence findExcluding(Sentence sentence, Conviction conviction, 
			Date effectiveDate);
	
	/**
	 * Returns all active sentences except for the specified person.
	 * 
	 * @param person person
	 * @return active sentences
	 */
	List<Sentence> findActiveSentences(Person person);

	/**
	 * Removes all sentences associated with the specified conviction.
	 * 
	 * @param conviction conviction
	 */
	void removeByConviction(Conviction conviction);
	
	/**
	 * Returns the number of existing sentences for the conviction.
	 * 
	 * @param conviction conviction
	 * @return sentence count
	 */
	Integer countSentencesByConviction(Conviction conviction);
	
	/**
	 * Updates sentences that reference {@code oldSentence} to
	 * {@code newSentence} as consecutive sentence.
	 * 
	 * @param oldSentence old sentence
	 * @param newSentence new sentence
	 * @return records updated
	 */
	int updateWithConsecutiveSentence(
			Sentence oldSentence, Sentence newSentence);
	
	/**
	 * Counts connected sentences.
	 * 
	 * @param sentence sentence to which connections are to be counted
	 * @return count of connected sentences
	 */
	long countConnected(Sentence sentence);
}