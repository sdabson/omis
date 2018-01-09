package omis.sentence.report;

import java.util.List;

import omis.conviction.domain.Conviction;
import omis.person.domain.Person;

/**
 * Sentence report service.
 * @author Josh Divine
 * @version 0.1.0 (May 1, 2017)
 * @since OMIS 3.0
 */
public interface SentenceReportService {

	/**
	 * Returns all sentences for the specified person.
	 * 
	 * @param person person
	 * @return sentence summaries
	 */
	List<SentenceSummary> summarizeSentences(Person person);
	
	/**
	 * Returns inactive sentences for the specified conviction.
	 * 
	 * @param conviction conviction
	 * @return inactive sentence summaries
	 */
	List<SentenceSummary> summarizeInactiveSentencesByConviction(
			Conviction conviction);
	
	/**
	 * Returns active sentences for the specified person.
	 * 
	 * @param person person
	 * @return active sentences
	 */
	List<SentenceSummary> summarizeActiveSentences(Person person);
}
